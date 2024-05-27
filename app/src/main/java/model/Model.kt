import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import model.Horario

class Model: ComponentActivity() {
    val db = FirebaseDatabase.getInstance() //la base de datos
    val referencia = db.reference //referencia de la base de datos
    val bd = db.getReference("Horarios") //la base de datos, nodo horarios

    fun crearHorario(nombreHorario:String, dias:String, horaI:String, horaF:String):Boolean{
        var success: Boolean = false
        //Se busca el nodo horario en la base de datos, se le hace push para saber que le voy a poner un dato
        val horarioRef = referencia.child("Horarios").push()

        //Se crea un mapa con los atributos pasados, para ponerlo en la base de datos
        val mapa = mapOf(
            "nombre" to nombreHorario,
            "dias" to dias,
            "horaI" to horaI,
            "horaF" to horaF,
            "estado" to "activo"
        )

        //Se sube a la base de datos
        horarioRef.setValue(mapa).addOnSuccessListener {
           success = true
        }
            .addOnFailureListener { e ->
                success = false
            }
        return success
    }

    fun visualizarHorario(buscar:String): Horario{
        var horario = Horario()
        //ordeno por hijos
        bd.orderByChild("nombre").equalTo(buscar).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot){
                if (snapshot.exists()) {
                    //existe el horario
                    for (horarioSnapshot in snapshot.children) {
                        //Busca cada uno de los valores en la base de datos
                        val dias = horarioSnapshot.child("dias").getValue(String::class.java).toString()
                        val estado = horarioSnapshot.child("estado").getValue(String::class.java).toString()
                        val horaI = horarioSnapshot.child("horaI").getValue(String::class.java).toString()
                        val horaF = horarioSnapshot.child("horaF").getValue(String::class.java).toString()
                        val horas = "Hora: " + horaI+ " a " + horaF

                        horario.setNombre(buscar)
                        horario.setHoraInicio(horaI.toInt())
                        horario.setHoraFin(horaF.toInt())
                        horario.setEstado(estado.toBoolean())
                        horario.setDias(dias)
                    }
                } else {
                    horario.setId(-2)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Error al consultar la base de datos", Toast.LENGTH_SHORT).show()
            }
        }    )
        return horario
    }
    fun modificarHorario(buscando:String, nombreHorario: String, dias: String, horaI: String, horaF: String):Boolean{
        var success: Boolean = false
        val horarioRef = referencia.child("Horarios")//Se encuentra el nodo horarios
        val bd = db.getReference("Horarios")//donde vamos a modificar
        //busco entre los horarios
        horarioRef.orderByChild("nombre").equalTo(buscando).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (horarioSnapshot in snapshot.children) {
                    val key: String = horarioSnapshot.key.toString()
                    bd.child(key).child("nombre").setValue(nombreHorario)
                    bd.child(key).child("dias").setValue(dias)
                    bd.child(key).child("horaI").setValue(horaI)
                    bd.child(key).child("horaF").setValue(horaF).addOnSuccessListener {
                        success = true
                    }.addOnFailureListener{
                        success = false
                    }

                }}

            override fun onCancelled(error:  DatabaseError) {
                Toast.makeText(applicationContext, "Error al consultar la base de datos", Toast.LENGTH_SHORT).show()
            }
        })
        return success
    }
    fun inactivarHorario(buscar:String):Boolean{
        var success: Boolean = false
        //ordeno por hijos y luego comparo aquellos que se llamen buscar
        bd.orderByChild("nombre").equalTo(buscar).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (horarioSnapshot in snapshot.children) {
                    //se encuentra cómo se llama el horario
                    val key: String = horarioSnapshot.key.toString()
                    //se encuentra el valor del nombre para cada uno de los horarios
                    val compare = horarioSnapshot.child("nombre").getValue(String::class.java)
                    if (compare == buscar){ //se compara si el nombre que buscamos es el mismo que hallamos
                        //si sucede, actualizamos el hijo
                        bd.child(key).child("estado").setValue("inactivo").addOnSuccessListener {
                            success=true
                        }.addOnFailureListener{
                            success=false
                        }
                    }
                }}

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Error al consultar la base de datos", Toast.LENGTH_SHORT).show()
            }
        })
        return success
    }

}



