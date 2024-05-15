import android.content.Context
import com.google.gson.Gson
import model.App
import java.io.File

class GranHorario: ArrayList<Horario>()
class GH {
    val horarios: ArrayList<Horario> = arrayListOf()
}
class Horario{
    var id: Int = 0
    var nombre:String = ""
    var dias:String = ""
    var horaI:Int = 0
    var horaF:Int = 0
    var estado:Boolean = true
    constructor(id:Int, nombre:String, dias:String, horaI:Int, horaF:Int) {
        this.id = id
        this.nombre = nombre
        this.dias = dias
        this.horaI = horaI
        this.horaF = horaF
    }

    constructor(){
    }

    fun crear(nombre:String, dias:String, horaI:Int, horaF:Int): Boolean{
        val rnds = (0..150).random()
        val horario = Horario(rnds, nombre, dias, horaI, horaF)
        val gson = Gson()
        val escribir:String = gson.toJson(horario)
        File("bd.json").writeText(escribir)        
        return true
    }

    fun consulta(id:Int): Horario{

        val file = File("bd.json")
        val respuesta = file.readText()
        val horarios = Gson().fromJson(respuesta, GH::class.java)

        val h = Horario()
        for (i in 0 until horarios.horarios!!.size){
            println("sirvo")
            val objeto = horarios.horarios?.get(i)
            val objetoId = objeto!!.id
            if (objetoId == id) {
                h.id = objetoId
                h.nombre = objeto!!.nombre
                h.dias = objeto!!.dias
                h.horaI = objeto!!.horaI
                h.horaF = objeto!!.horaF
                h.estado = objeto!!.estado
            }
        }
        if (h == null){
            println("No ha sido hallado")
        }

        return h
    }

    fun todosHorarios(): ArrayList<Horario>{
        val file = File("bd.json")
        val respuesta = file.readText()
        val horarios = Gson().fromJson(respuesta, GH::class.java)
        var lista : ArrayList<Horario> = arrayListOf()
        for (i in 0 until horarios.horarios!!.size){
            val objeto: Horario? = horarios.horarios?.get(i)
            var h = Horario()
            h.id = objeto!!.id
            h.nombre = objeto!!.nombre
            h.dias = objeto!!.dias
            h.horaI = objeto!!.horaI
            h.horaF = objeto!!.horaF
            h.estado = objeto!!.estado
            lista.add(h)
        }
        return lista
    }

    fun editar(id:Int, opcion:Int, cambio:String): Boolean{
        val h: Horario = consulta(id)
        when(opcion){
            1 -> h.nombre = cambio
            2 -> h.dias = cambio
            3 -> {val change:Int = cambio.toInt()
                h.horaI = change}
            4 -> {val change:Int = cambio.toInt()
                h.horaF = change}
            5 -> {val change:Boolean = cambio.toBoolean()
                h.estado = change}
        }
        return true
    }

    fun isIdValid(id:Int): Boolean{
        val h: Horario = consulta(id)
        var respuesta = false
        if (h.id != 0){
            respuesta = true
        }
        return respuesta
    }

    fun inactivar(id:Int):Boolean{
        val h: Horario = consulta(id)
        h.estado = false
        return true
    }

}



