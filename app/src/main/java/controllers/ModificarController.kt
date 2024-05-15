package controllers
import Horario

class ModificarController {

    private var modelo: Horario

    constructor(model: Horario){
        this.modelo = model
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