package model

class Horario {
    private var idHorario: Int
    private var nombreHorario: String
    private var horaInicio: Int
    private var horaFin: Int
    private var estadoHorario: Boolean
    private var diaHorario: String

    constructor(){
        idHorario = -1
        nombreHorario = "Prueba"
        horaInicio = 12
        horaFin = 14
        estadoHorario = true
        diaHorario = "Lunes"
    }

    constructor(id:Int, nombre:String, horaI:Int, horaF:Int, estado:Boolean, dia:String){
        idHorario = id
        nombreHorario = nombre
        horaInicio = horaI
        horaFin = horaF
        estadoHorario = estado
        diaHorario = dia
    }

    fun getId():Int{
        return this.idHorario
    }
    fun setId(newId: Int){
        this.idHorario = newId
    }
    fun getNombre():String{
        return this.nombreHorario
    }
    fun setNombre(newNombre:String){
        this.nombreHorario = newNombre
    }
    fun getHoraInicio():Int{
        return this.horaInicio
    }
    fun setHoraInicio(newHoraI:Int){
        this.horaInicio = newHoraI
    }
    fun getHoraFin():Int{
        return this.horaFin
    }
    fun setHoraFin(newHoraF:Int){
        this.horaFin = newHoraF
    }
    fun getEstado():Boolean{
        return this.estadoHorario
    }
    fun setEstado(newEstado: Boolean){
        this.estadoHorario = newEstado
    }
    fun getDias():String{
        return this.diaHorario
    }
    fun setDias(newDias:String){
        this.diaHorario = newDias
    }
}