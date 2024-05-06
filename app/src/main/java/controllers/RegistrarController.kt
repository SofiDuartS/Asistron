package controllers

import com.google.gson.GsonBuilder

class RegistrarController {

    private var model: Modelo
    private var gson = GsonBuilder() //para convertir JSON en clases

    constructor(model: Modelo){
        this.model = model
    }

    //Registrar un nuevo horario. Los parámetros son los campos que deberían ir en la view
    fun registrarHorario(horario: HorarioCompleto): String{
        var horarioJson = gson.create().toJson(horario)

        var response: Boolean
        response = this.model.crearHorario(horarioJson)

        if (response){
            return "El horario ${horario.nombreHorario} ha sido registrado correctamente"
        } else{
            return "Ups! Algo salió mal. Vuelva a intentarlo"
        }

    }
}