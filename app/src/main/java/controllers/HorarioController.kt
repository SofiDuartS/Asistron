package controllers

import Model
import model.Horario

class HorarioController{

    private var modelo: Model

    constructor(){
        this.modelo = Model()
    }


    fun registrarHorario(nombre:String, dias:List<String>, horaI:String, horaF:String): String {
        //Registrar un nuevo horario. Los parámetros son los campos que deberían ir en la view
        var message: String? = "" //mensaje de éxito o fracaso
        var validInputs = true //true al final de la función si pasa todas las validaciones
        var diasBD = "" //para convertir la lista de días en string para la base de datos

        //VERIFICACIÓN DE INPUTS:
        //VALIDACIÓN DE HORAS
        //horas no vacías
        var horaINoVacia = validarHorasVacias(horaI)
        var horaFNoVacia = validarHorasVacias(horaF)
        if(!horaINoVacia || !horaFNoVacia) {
            message = "Ups! No puede dejar las horas vacías"
            validInputs = false
        }else{
            //horas son números enteros
            var horaIisInt = validarFormatoHoras(horaI)
            var horaFisInt = validarFormatoHoras(horaI)
            if(!horaIisInt || !horaFisInt){
                message = "Ups! Las horas deben ser números enteros"
                validInputs = false
            }else{
                //horas están entre 0 y 23
                var horaIinLimits = validarLimiteHoras(horaI.toInt())
                var horaFinLimits = validarLimiteHoras(horaF.toInt())
                if(!horaIinLimits || !horaFinLimits){
                    message = "Ups! Las horas deben estar entre 0 y 23"
                    validInputs = false
                }else{
                    //hora de inicio menor que hora de fin
                    var finMayorInicio = validarHoras(horaI.toInt(),horaF.toInt())
                    if(!finMayorInicio){
                        message = "Ups! La hora de inicio debe ser menor que la hora de fin"
                        validInputs = false
                    }
                }
            }
        }

        //VALIDACIÓN DE DIAS DE LA SEMANA
        var diasNoVacios = validarDiasVacios(dias)
        if (!diasNoVacios){
            message = "Ups!. Debe elegir al menos un día"
            validInputs = false
        }else{
            var validDias = validarDias(dias)
            if (!validDias){
                message = "Ups!. Verifique que los días de la semana estén bien escritos"
                validInputs = false
            }else{ //convertir la lista de dias a String
                diasBD = diasToString(dias)
            }
        }

        //VALIDACIÓN DE NOMBRE
        var validNombre = validarNombreHorario(nombre)
        if (!validNombre){
            message = "Ups! Debe ingresar un nombre para el horario"
            validInputs = false
        }

        //MENSAJE DE RESPUESTA
        if (validInputs) {
            //se sube a la base de datos
            val response = this.modelo.crearHorario(nombre, diasBD, horaI, horaF)

            if (response){ //el registro fue exitoso
                message = "El horario ha sido registrado correctamente"
            } else{ //hubo un error relacionado con la base de datos
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
        /*nombre no tiene restricciones de contenido
        *dias debe tener días de la semana, en orden
        *horaI debe estar entre 0 y 23
        *horaF debe estar entre 0 y 23. Además, tiene que ser mayor que horaI*/

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

    fun validarNombreHorario(nombre: String):Boolean{
        //validar que el nombre no sea un string vacío
        var validInput = true
        if (nombre == ""){
            validInput = false
        }
        return validInput
    }

    fun validarHorasVacias(hora:String):Boolean{
        //validar que la hora no es un string vacío
        var validInput = true
        if (hora.isEmpty()){
            validInput = false
        }
        return validInput
    }

    fun validarFormatoHoras(hora:String):Boolean{
        //validar que la hora se pueda convertir a entero
        var validInput = true
        try{
            var horaInt = hora.toInt()
            validInput = true
        }catch(e: NumberFormatException){
            validInput = false
        }
        return validInput
    }

    fun validarLimiteHoras(hora:Int):Boolean{
        //validar que la hora esté en formato militar, entre 0 y 23
        var validInput = false
        try {
            if (hora < 0 || hora > 23) throw IllegalArgumentException("Ups! La hora de inicio debe estar entre 0 y 23. Inténtelo de nuevo")
            validInput = true
        }catch (e: IllegalArgumentException) { //validar que la hora está entre 0 y 23
            validInput = false
        }
        return validInput
    }
    fun validarHoras(horaI:Int, horaF:Int):Boolean{
        //validar que la hora de fin sea mayor que la hora de inicio
        var validInput = true
        try {
            if (horaF < horaI) throw IllegalArgumentException("Ups! La hora de fin no puede ser menor que la hora de inicio. Inténtelo de nuevo")
            validInput = true
        } catch (e: IllegalArgumentException) {
            validInput = false
        }

        return validInput
    }

    fun validarDiasVacios(dias: List<String>):Boolean{
        //validar que haya al menos un día seleccionado
        var validInput = true
        if(dias.isEmpty()){
            validInput = false
        }
        return validInput
    }
    fun validarDias(dias:List<String>):Boolean{
        //validar que la lista de días tiene días de la semana
        var validInput = true

        val diasSemana = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
        for (dia in dias) {
            if (dia !in diasSemana) {
                validInput = false
            }
        }

        return validInput
    }

    fun diasToString(dias: List<String>):String{
        //convertir la lista de días en un string para almacenar en base de datos
        val diasSemana = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
        var diasBD = ""
        for (dia in dias) {
            if (dia in diasSemana) {
                diasBD += dia + " "
            }
        }
        return diasBD
    }
}
