package controllers

import Horario

class HorarioController {

    private var modelo: Horario

    constructor(model: Horario){
        this.modelo = model
    }

    //Registrar un nuevo horario. Los parámetros son los campos que deberían ir en la view
    fun registrarHorario(){
        println("Ingrese el nombre del horario")
        var nombre: String = readLine()!!

        println("Ingrese los días de la semana, separados por comas")
        var dias: String = readLine()!!

        var validHourInput: Boolean = false
        println("Ingrese la hora de inicio")
        var horaI:Int = 0

        while (!validHourInput){
            try {
                horaI= readLine()!!.toInt()
                if (horaI<0 || horaI>23) throw IllegalArgumentException("La hora debe estar entre 0 y 23. Inténtelo de nuevo")
                validHourInput = true
            } catch (e: NumberFormatException){ //validar que el input se pueda cambiar a entero
                println("Debe ingresar un número entero entre 0 y 23. Inténtelo de nuevo")
            } catch (e: NullPointerException){ //validar que la entrada no sea vacía
                println("No puede dejar este campo vacío. Ingrese un número entero entre 0 y 23. Inténtelo de nuevo")
            } catch (e: IllegalArgumentException){ //validar que la hora está entre 0 y 23
                println(e.message)
            }
        }

        validHourInput = false
        println("Ingrese la hora de fin")
        var horaF: Int = 0

        while (!validHourInput){
            try {
                horaF = readLine()!!.toInt()
                if (horaF<0 || horaF>23) throw IllegalArgumentException("La hora debe estar entre 0 y 23. Inténtelo de nuevo")
                if (horaF<horaI) throw IllegalArgumentException("La hora de fin no puede ser menor que la hora de inicio. Inténtelo de nuevo")
                validHourInput = true
            } catch (e: NumberFormatException){ //validar que el input se pueda cambiar a entero
                println("Debe ingresar un número entero. Inténtelo de nuevo")
                validHourInput = false
            } catch (e: NullPointerException){ //validar que la entrada no sea vacía
                println("No puede dejar este campo vacío. Ingrese un número entero entre 0 y 23. Inténtelo de nuevo")
                validHourInput = false
            } catch (e: IllegalArgumentException){ //validar que la hora está entre 0 y 23, o que la hora de fin no es menor que la hora de inicio
                println(e.message)
                validHourInput = false
            }
        }

        var response: Boolean
        //response = this.modelo.crear(nombre, dias, horaI, horaF)
        if(nombre == "pruebaOK"){
            response = true
        }else{
            response = false
        }


        if (response){
            println("El horario ${nombre} ha sido registrado correctamente")
        } else{
            println("Ups! Algo salió mal. Vuelva a intentarlo")
        }
    }

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

    fun modificarHorario(){
        /*Modificar un horario*/
        println("Ingrese el id del horario a modificar")
        var id: Int = readLine()!!.toInt()
        //verificar que el id está en la base de datos
        //var isAvailable: Boolean = modelo.isIdValid(id)
        var isAvailable = true // para prueba

        if(isAvailable){
            println("Ingrese la opcion que quiere modificar")
            println("1: Nombre del Horario")
            println("2: Dias de la semana del Horario")
            println("3: Hora de inicio del Horario")
            println("4: Hora de fin del Horario")
            println("5: Estado del Horario (Activo o Inactivo)")
            var opcion: Int = readLine()!!.toInt()

            println("Inserte el valor al que quiere cambiar")
            var valor: String = readLine()!!

            var modelResponse: Boolean
            //modelResponse = modelo.editar(id, opcion, valor)
            if(id%2 == 0){ //para prueba
                modelResponse = true
            } else{
                modelResponse = false
            }

            if(modelResponse){
                println("El horario ha sido modificado correctamente")
            } else{
                println("Ups! Algo salió mal. Vuelva a intentarlo")
            }
        } else{
            println("El id $id no es válido. Inténtelo de nuevo con un id que se encuentre en base de datos")
        }

    }

    fun cambiarEstado(){
        println("Ingrese el id del horario a modificar")
        var id: Int = readLine()!!.toInt()
        //verificar que el id está en la base de datos
        //var isAvailable: Boolean = modelo.isIdValid(id)
        var isAvailable: Boolean = true

        if (isAvailable){
            var modelResponse: Boolean
            //modelResponse = modelo.inactivar(id)
            if(id%2 == 0){ //para prueba
                modelResponse = true
            } else{
                modelResponse = false
            }

            if(modelResponse){
                println("El estado ha sido modificado correctamente")
            } else{
                println("Ups! Algo salió mal. Vuelva a intentarlo")
            }

        } else{
            println("El id $id no es válido. Inténtelo de nuevo con un id que se encuentre en base de datos")
        }
    }

}