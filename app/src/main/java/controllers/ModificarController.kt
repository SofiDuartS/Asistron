package controllers

import com.google.gson.GsonBuilder

class ModificarController {

    private var model: Modelo
    private var gson = GsonBuilder() //para convertir JSON en clases

    constructor(model: Modelo){
        this.model = model
    }

    fun modificarHorario(horarioMod: HorarioCompleto): String{
        /*Modificar un horario. Recibe un objeto HorarioCompleto del view y los atributos se pasan
        * al modelo para almacenarlos en base de datos*/

        /*Esta función también se puede usar para ocultar o mostrar un horario, cambiando solamente
        * el atributo estado*/

        var horarioJson = gson.create().toJson(horarioMod)

        var modelResponse: Boolean
        modelResponse = model.modificarHorario(horarioJson)

        if(modelResponse){
            return "El horario ${horarioMod.nombreHorario} ha sido modificado correctamente"
        } else{
            return "Ups! Algo salió mal. Vuelva a intentarlo"
        }
    }

}