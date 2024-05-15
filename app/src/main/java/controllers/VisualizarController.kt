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
        //modelResponse = modelo.todosHorarios()

        //para prueba
        var h1: Horario = Horario(1, "Horario1", "Lunes, Martes", 17, 20)
        var h2: Horario = Horario(2, "Horario2", "Miércoles, Jueves, Viernes", 16, 20)
        var h3: Horario = Horario(3, "Horario3", "Sábado, Domingo", 11, 14)

        modelResponse = arrayListOf(h1, h2, h3)

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

        //modelResponse = modelo.consulta(id)
        //para prueba
        modelResponse = Horario(3, "Horario3", "Sábado, Domingo", 11, 14)

        println("id: ${modelResponse.id}")
        println("nombreHorario: ${modelResponse.nombre}")
        println("horaInicio: ${modelResponse.horaI}")
        println("horaFin: ${modelResponse.horaF}")
        println("estado ${modelResponse.estado}")
    }

}