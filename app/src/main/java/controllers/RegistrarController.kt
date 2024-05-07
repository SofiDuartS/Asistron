package controllers

import com.google.gson.GsonBuilder

class RegistrarController {

    private var model: Modelo
    private var gson = GsonBuilder() //para convertir JSON en clases

    constructor(model: Modelo){
        this.model = model
    }

    //Registrar un nuevo horario. Los parámetros son los campos que deberían ir en la view
    fun registrarHorario(horario: HorarioCompleto){
        println("Ingrese el nombre del horario")
        var nombre: String = readLine()!!
        println("Ingrese los días de la semana, separados por comas")
        var dias: String = readLine()!!
        println("Ingrese la hora de inicio")

        //AGREGAR TRY CATCH
        var horaI: Int = readLine()!!.toInt()
        println("Ingrese la hora de fin")
        var horaF: Int = readLine()!!.toInt()

        var horario: HorarioNoId = HorarioNoId(nombre, dias, horaI, horaF)

        var horarioJson = gson.create().toJson(horario)

        var response: Boolean
        response = this.model.crearHorario(horarioJson)

        if (response){
            println("El horario ${horario.nombreHorario} ha sido registrado correctamente")
        } else{
            println("Ups! Algo salió mal. Vuelva a intentarlo")
        }
    }
}