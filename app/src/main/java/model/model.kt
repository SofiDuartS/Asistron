import android.app.Application
import android.content.Context
import com.google.gson.Gson
import model.Utiles
import model.App
import org.jose4j.json.internal.json_simple.JSONArray
import org.jose4j.json.internal.json_simple.JSONObject
import java.io.File


class Horario {
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
        val context:Context = App.getContext()
        val file:String = Utiles.leerJson(context, "bd.json")
        val archivo: org.json.JSONArray = org.json.JSONArray(file)
        val h = Horario()
        for (i in 0 until archivo.length()) {
            val objeto: org.json.JSONObject = archivo.getJSONObject(i)
            val objetoId = objeto.getInt("id")
            if (objetoId == id) {
                h.id = objetoId
                h.nombre = objeto.getString("nombre")
                h.dias = objeto.getString("dias")
                h.horaI = objeto.getInt("horaI")
                h.horaF = objeto.getInt("horaF")
                h.estado = objeto.getString("estado").toBoolean()
            }
        }
        return h
    }

    fun todosHorarios(): ArrayList<Horario>{
        val context:Context = App.getContext()
        val file:String = Utiles.leerJson(context, "bd.json")
        val archivo: org.json.JSONArray = org.json.JSONArray(file)
        val lista : ArrayList<Horario> = arrayListOf()
        for (i in 0 until archivo.length()){
            val objeto: org.json.JSONObject = archivo.getJSONObject(i)
            var h = Horario()
            h.id = objeto.getInt("id")
            h.nombre = objeto.getString("nombre")
            h.dias = objeto.getString("dias")
            h.horaI = objeto.getInt("horaI")
            h.horaF = objeto.getInt("horaF")
            h.estado = objeto.getString("estado").toBoolean()
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



