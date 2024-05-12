import Utiles
import org.jose4j.json.internal.json_simple.JSONArray
import org.jose4j.json.internal.json_simple.JSONObject
import java.io.File
import com.google.gson.Gson
import kotlin.collections.ArrayList

class Horario {
    var id: Int = 0
        get() {
            return field
        }
        set(value) {
            field = value
        }
    var nombre:String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }
    var dias:String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }
    var horaI:Int = 0
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var horaF:Int = 0
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var estado:Boolean = true
        get() {
            return field
        }
        set(value) {
            field = value
        }
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
        val horario : Horario = Horario(rnds, nombre, dias, horaI, horaF)
        val gson = Gson()
        val escribir:String = gson.toJson(horario)
        File("bd.json").writeText(escribir)        
        return true
    }
    
    fun consulta(id:Int): Horario{
        var file = Utiles.leerJson(getApplicationContext(), "bd.json")
        var archivo: JSONArray = JSONArray(file)
        var h:Horario = Horario()
        for (i in archivo.length()) {
            var objeto: JSONObject = archivo.getJSONObject(i)
            var objeto_id = objeto.getInt("id")
            if (objeto_id == id) {
                h.id = objeto_id
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
        var file = Utiles.leerJson(getApplicationContext(), "bd.json")
        var archivo: JSONArray = JSONArray(file)
        var lista : ArrayList<Horario> = arrayListOf()
        for (i in archivo.length()){
            var objeto: JSONObject = archivo.getJSONObject(i)
            var h:Horario = Horario()
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
        var h: Horario = consulta(id)
        when(opcion){
            1 -> h.nombre = cambio
            2 -> h.dias = cambio
            3 -> {var change:Int = cambio.toInt()
                h.horaI = change}
            4 -> {var change:Int = cambio.toInt()
                h.horaF = change}
            5 -> {var change:Boolean = cambio.toBoolean()
                h.estado = change}
        }
        return true
    }

    fun isIdValid(id:Int): Boolean{
        var h: Horario = consulta(id)
        var respuesta:Boolean = false
        if (h.id != 0){
            respuesta = true
        }
        return respuesta
    }

    fun inactivar(id:Int):Boolean{
        var h: Horario = consulta(id)
        h.estado = false
        return true
    }

}