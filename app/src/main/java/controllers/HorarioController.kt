package controllers

import Model
import model.Horario

class HorarioController{

    private var modelo: Model

    constructor(){
        this.modelo = Model()
    }

    //Registrar un nuevo horario. Los parámetros son los campos que deberían ir en la view
    fun registrarHorario(nombre:String, dias:List<String>, horaI:String, horaF:String): String {
        var message: String? = ""

        //VERIFICACIÓN DE INPUTS:
        //nombre no tiene restricciones de contenido
        //dias debe tener días de la semana
        //horaI debe estar entre 0 y 23
        //horaF debe estar entre 0 y 23. Además, tiene que ser mayor que horaI

        //VALIDACIÓN DE DIAS DE LA SEMANA
        var validInput = true
        val diasSemana =
            listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
        var diasBD = ""
        for (dia in dias) {
            if (dia !in diasSemana) {
                message = "Ups!. Verifique que los días de la semana estén bien escritos"
                validInput = false
            }else{
                diasBD += dia + " "
            }
        }

        //VALIDACIÓN DE HORA INICIO
        if (validInput) { //solo se sigue verificando si el input previo es correcto
            validInput = false
            try {
                var horaIint = horaI.toInt()
                if (horaIint < 0 || horaIint > 23) throw IllegalArgumentException("Ups! La hora de inicio debe estar entre 0 y 23. Inténtelo de nuevo")
                validInput = true
            } catch (e: NumberFormatException) { //validar que el input se pueda cambiar a entero
                message =
                    "Ups! La hora de inicio debe ser número entero entre 0 y 23. Inténtelo de nuevo"
                validInput = false
            } catch (e: NullPointerException) { //validar que la entrada no sea vacía
                message = "Ups! No puede dejar campos vacíos. Inténtelo de nuevo"
                validInput = false
            } catch (e: IllegalArgumentException) { //validar que la hora está entre 0 y 23
                message = e.message
                validInput = false
            }

            //VALIDACIÓN DE HORA FIN
            if (validInput) {//solo se sigue verificando si el input previo es correcto
                validInput = false
                try {
                    var horaFint = horaF.toInt()
                    var horaIint = horaI.toInt()
                    if (horaFint < 0 || horaFint > 23) throw IllegalArgumentException("Ups! La hora de fin debe estar entre 0 y 23. Inténtelo de nuevo")
                    if (horaFint < horaIint) throw IllegalArgumentException("Ups! La hora de fin no puede ser menor que la hora de inicio. Inténtelo de nuevo")
                    validInput = true
                } catch (e: NumberFormatException) { //validar que el input se pueda cambiar a entero
                    message =
                        "Ups! La hora de fin debe ser un número entero entre 0 y 23. Inténtelo de nuevo"
                    validInput = false
                } catch (e: NullPointerException) { //validar que la entrada no sea vacía
                    message = "Ups! No puede dejar campos vacíos. Inténtelo de nuevo"
                    validInput = false
                } catch (e: IllegalArgumentException) { //validar que la hora está entre 0 y 23, o que la hora de fin no es menor que la hora de inicio
                    message = e.message
                    validInput = false
                }
            }
        }
        if (validInput) {
            val response = this.modelo.crearHorario(nombre, diasBD, horaI, horaF)

            if (response){
                message = "El horario ha sido registrado correctamente"
            } else{
                message = "Ups! Algo salió mal. Vuelva a intentarlo"
            }
        }else{
            if (message == null){
                message = "Ups! Algo salió mal. Vuelva a intentarlo"
            }
        }
        return message

    }

    fun visualizarTodosHorarios():List<Horario>{

        /* esta función debe mostrar solo el id y el nombre del horario, para la vista en la que
        *  el usuario elige cuál de los horarios quiere modificar */

        var modelResponse: List<Horario>
        modelResponse = modelo.visualizarTodosHorarios()

        return modelResponse

    }

    fun visualizarHorario(nombreHorario: String): Horario{
        /*Obtener la información de un horario específico. Se puede utilizar para visualizar un
        * horario detalladamente, o para obtener los datos de un horario y mostrarlos en la vista
        * de modificar*/

        val modelResponse: Horario
        modelResponse = modelo.visualizarHorario(nombreHorario)

        return modelResponse
    }

    fun modificarHorario(nombreHorario:String, nuevoNombre:String, nuevosDias:List<String>, nuevaHoraI:String, nuevaHoraF:String):String{
        /*Modificar un horario*/
        var message:String? = ""
        //nombre no tiene restricciones de contenido
        //dias debe tener días de la semana, en orden
        //horaI debe estar entre 0 y 23
        //horaF debe estar entre 0 y 23. Además, tiene que ser mayor que horaI

        //VALIDACIÓN DE DIAS DE LA SEMANA
        var validInput = false
        val diasSemana =
            listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
        var diasOrden: MutableMap<Int, String> = mutableMapOf()
        var diasBD = ""

        for (dia in nuevosDias) {
            if (dia !in diasSemana) {
                message = "Ups!. Verifique que los días de la semana estén bien escritos"
                validInput = false
            } else {
                when (dia) {
                    "Domingo" -> diasOrden.put(1, "Domingo")
                    "Lunes" -> diasOrden.put(2, "Lunes")
                    "Martes" -> diasOrden.put(3, "Martes")
                    "Miércoles" -> diasOrden.put(4, "Miércoles")
                    "Jueves" -> diasOrden.put(5, "Jueves")
                    "Viernes" -> diasOrden.put(6, "Viernes")
                    "Sábado" -> diasOrden.put(7, "Sábado")
                }
            }
        }
        diasOrden = diasOrden.toSortedMap()
        for (dia in diasOrden.values) {
            diasBD += dia + ", "
        }

        //VALIDACIÓN DE HORA INICIO
        if (validInput) { //solo se sigue verificando si el input previo es correcto
            validInput = false
            try {
                var horaIint = nuevaHoraI.toInt()
                if (horaIint < 0 || horaIint > 23) throw IllegalArgumentException("Ups! La hora de inicio debe estar entre 0 y 23. Inténtelo de nuevo")
                validInput = true
            } catch (e: NumberFormatException) { //validar que el input se pueda cambiar a entero
                message =
                    "Ups! La hora de inicio debe ser número entero entre 0 y 23. Inténtelo de nuevo"
                validInput = false
            } catch (e: NullPointerException) { //validar que la entrada no sea vacía
                message = "Ups! No puede dejar campos vacíos. Inténtelo de nuevo"
                validInput = false
            } catch (e: IllegalArgumentException) { //validar que la hora está entre 0 y 23
                message = e.message
                validInput = false
            }

            //VALIDACIÓN DE HORA FIN
            if (validInput) {//solo se sigue verificando si el input previo es correcto
                validInput = false
                try {
                    var horaFint = nuevaHoraF.toInt()
                    var horaIint = nuevaHoraI.toInt()
                    if (horaFint < 0 || horaFint > 23) throw IllegalArgumentException("Ups! La hora de fin debe estar entre 0 y 23. Inténtelo de nuevo")
                    if (horaFint < horaIint) throw IllegalArgumentException("Ups! La hora de fin no puede ser menor que la hora de inicio. Inténtelo de nuevo")
                    validInput = true
                } catch (e: NumberFormatException) { //validar que el input se pueda cambiar a entero
                    message =
                        "Ups! La hora de fin debe ser un número entero entre 0 y 23. Inténtelo de nuevo"
                    validInput = false
                } catch (e: NullPointerException) { //validar que la entrada no sea vacía
                    message = "Ups! No puede dejar campos vacíos. Inténtelo de nuevo"
                    validInput = false
                } catch (e: IllegalArgumentException) { //validar que la hora está entre 0 y 23, o que la hora de fin no es menor que la hora de inicio
                    message = e.message
                    validInput = false
                }
            }
        }
        if(validInput){
            var modelResponse:Boolean
            modelResponse = this.modelo.modificarHorario(nombreHorario, nuevoNombre, diasBD, nuevaHoraI, nuevaHoraF)

            if (modelResponse){
                message = "El horario ha sido modificado correctamente"
            } else{
                message = "Ups! Algo salió mal. Vuelva a intentarlo"
            }
        }else{
            if (message == null){
                message = "Ups! Algo salió mal. Vuelva a intentarlo"
            }
        }
        return message
    }

    fun inactivarHorario(nombreHorario: String):String{
        var message = ""
        var modelResponse:Boolean = this.modelo.inactivarHorario(nombreHorario)

        if(modelResponse){
            message = "El horario ha sido inactivado correctamente"
        } else{
            message = "Ups! Algo salió mal. Vuelva a intentarlo"
        }
        return message
    }

}