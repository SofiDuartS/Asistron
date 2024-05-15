package controllers

import Horario

class RegistrarController {

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
}