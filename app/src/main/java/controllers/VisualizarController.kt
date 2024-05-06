package controllers

import com.google.gson.GsonBuilder

// Clases creadas para la estructura del JSON. PASAR ESTO AL MODELO
data class IdNombreHorario(val id: Int, val nombreHorario: String)
data class HorarioCompleto(val id: Int, val nombreHorario: String, val diasSemana: String, val horaInicio: Int, val horaFin: Int)

class VisualizarController {

    private var model: Modelo
    private var gson = GsonBuilder() //para convertir JSON en clases

    constructor(model: Modelo){
        this.model = model
    }

    //Obtener todos los horarios
    fun retrieveAllHorarios(){

        //modelResponse debería tener solamente Id y NombreHorario
        var modelResponse: String
        modelResponse = model.getHorarios() //en forma de json

        //convertir en una lista de objetos IdNombreHorario

    }

    fun retrieveHorario(id: Int): HorarioCompleto {

        /*Obtener la información de un horario. Retorna un objeto de tipo HorarioCompleto, y sus
        atributos son los datos que deben ir en el formulario para poder modificarlos*/

        var modelResponse: String //en forma de JSON
        var horario: HorarioCompleto

        modelResponse = model.getHorario(id)
        horario = gson.create().fromJson(modelResponse, HorarioCompleto::class.java)

        return horario
    }

}