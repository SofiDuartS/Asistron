package controllers

import Horario

class VisualizarController {

    private var modelo: Horario
    /*el controlador debe tener una referencia del modelo para poder
    * invocar los métodos*/

    constructor(model: Horario){
        this.modelo = model
    }

    //Obtener todos los horarios
    fun retrieveAllHorarios(){

        /* esta función debe mostrar solo el id y el nombre del horario, para la vista en la que
        *  el usuario elige cuál de los horarios quiere modificar */

        var modelResponse: ArrayList<Horario>
        modelResponse = modelo.todosHorarios()

        for (horario in modelResponse){
            println("-------------------------------------------------")
            println("id: ${horario.id}")
            println("nombreHorario: ${horario.nombre}")
        }

    }

    fun retrieveHorario(id: Int){

        /*Obtener la información de un horario específico. Se puede utilizar para visualizar un
        * horario detalladamente, o para obtener los datos de un horario y mostrarlos en la vista
        * de modificar*/

        var modelResponse: Horario

        modelResponse = modelo.consulta(id)

        println("id: ${modelResponse.id}")
        println("nombreHorario: ${modelResponse.nombre}")
        println("horaInicio: ${modelResponse.horaI}")
        println("horaFin: ${modelResponse.horaF}")
        println("estado ${modelResponse.estado}")
    }

}