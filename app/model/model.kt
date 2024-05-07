import ReadJSONFromAssets
import controllers //carpeta de Sofi

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
        horario : Horario(nombre, dias, horaI, horaF)
        return true
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
