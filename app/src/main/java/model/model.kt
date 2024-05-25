import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.frontdiseo.R
import com.google.gson.Gson
import model.App
import java.io.File
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class Horario: ComponentActivity() {
    val db = FirebaseDatabase.getInstance() //la base de datos
    val referencia = db.reference //referencia de la base de datos
    val bd = db.getReference("Horarios") //la base de datos, nodo horarios

    fun crearHorario(nombreHorario:String, dias:String, horaI:String, horaF:String){
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
           //crearHorarioC(true)
            //el punto es que esta función sea quien haga el toast para decir que fue exitoso
        }
            .addOnFailureListener { e ->
                //crearHorario(false)
                //lo mismo, que tu función sea quien haga el toast y que devuelva al usuario
                //o lo hacemos de una vez aqui?
            }

    }

    fun visualizarHorario(buscar:String){
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
                        //verHorarioC(dias, estado, horas)
                    // aquí le quitas el comentario y el punto es que haga tu función de show Sofi :)
                    }
                } else {
                    //No existe el horario
                    //verHorarioC("","","")
                    //aquí el punto es que tu función verHorarioC (le puse así porque está en el controller)
                // si no tiene nada retorne que no encontró nada
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Error al consultar la base de datos", Toast.LENGTH_SHORT).show()
            }
        }    )
    }
    fun modificarHorario(buscando:String, nombreHorario: String, dias: String, horaI: String, horaF: String){

            val horarioRef = referencia.child("Horarios")//Se encuentra el nodo horarios
            //se crea un mapa
            val mapa = mapOf(
                "nombre" to nombreHorario,
                "dias" to dias,
                "horaI" to horaI,
                "horaF" to horaF
            )
        //busco entre los horarios
            horarioRef.orderByChild("nombre").equalTo(buscando).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (horarioSnapshot in snapshot.children) {
                        val key: String = horarioSnapshot.key.toString() //se encuentra cómo se llama el horario
                        val compare = horarioSnapshot.child("nombreGrupo").getValue(String::class.java)
                        if (compare == buscando){ //se compara si el horario buscado es el mismo que el que se tiene
                            //si sucede, entonces se actualiza
                            bd.child(key).setValue(mapa).addOnSuccessListener {
                                //modificarHorarioC(true)
                                //lo mismo que en los anteriores
                            }.addOnFailureListener{
                                //modificarHorarioC(false)
                                //lo mismo que en los anteriores
                            }
                        }
                        //si no sucede, entonces no pasa nada
                    }}

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "Error al consultar la base de datos", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
    fun inactivarHorario(buscar:String){
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
                            //inactivarHorarioC(true)
                            //lo mismo que en crear
                        }.addOnFailureListener{
                            //inactivarHorarioC(false)
                            //lo mismo que en crear
                        }
                    }
                }}

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Error al consultar la base de datos", Toast.LENGTH_SHORT).show()
            }
        })}

}



