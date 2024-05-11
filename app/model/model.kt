import lector
import controllers //carpeta de Sofi
import org.jose4j.json.internal.json_simple.JSONArray
import org.jose4j.json.internal.json_simple.JSONObject
import java.util.ArrayList
import java.io.File
import com.google.gson.Gson

class Horario {
    val id: Int
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
    constructor(nombre:String, dias:String, horaI:Int, horaF:Int) {
        this.nombre = nombre
        this.dias = dias
        this.horaI = horaI
        this.horaF = horaF
    }

    fun crear(nombre:String, dias:String, horaI:Int, horaF:Int){
        val rnds = (0..150).random()
        val horario : Horario(rnds, nombre, dias, horaI, horaF)
        val gson = Gson()
        val escribir:String = gson.toJson(horario)
        File("bd.json").writeText(escribir)        
        return true
    }
    
    fun archivo(String filename){
        var JSONArray file = new JSONArray(jsonFileContent)
        for (i < file.length()){
            JSONObject objeto = jsonArray.getJSONObject(i)
            id:Int = objeto.getInt("id"
            String name = objeto.getString("id"))
        }
    }
    
    fun consulta(id:Int){
        var file = Utiles.leerJson(getApplicationContext(), "bd.json")
        var org.jose4j.json.internal.json_simple.JSONArray archivo = new org.jose4j.json.internal.json_simple.JSONArray(file)
        var h:Horario
        for (i in archivo.length()) {
            org.jose4j.json.internal.json_simple.JSONObject objeto = archivo.getJSONObject(i)
            objeto_id = objeto.getInt("id")
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

    fun todosHorarios(){
        var file = Utiles.leerJson(getApplicationContext(), "bd.json")
        var org.jose4j.json.internal.json_simple.JSONArray archivo = new org.jose4j.json.internal.json_simple.JSONArray(file)
        var lista = ArrayList<Horario>
        for (i in archivo.length()){
            org.jose4j.json.internal.json_simple.JSONObject objeto = archivo.getJSONObject(i)
            var h:Horario
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

    fun editar(id:Int, opcion:Int, cambio:String){
        h = consulta(id)
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

    fun isIdValid(id:Int){
        h = consulta(id)
        var respuesta:Boolean = false
        if (h != null){
            respuesta = true
        }
        return respuesta
    }

    fun inactivar(id:Int){
        h = consulta(id)
        h.estado = false
        return true
    }

}
