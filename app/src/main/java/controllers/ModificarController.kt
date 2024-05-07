package controllers

class ModificarController {

    private var model: Modelo

    constructor(model: Modelo){
        this.model = model
    }

    fun modificarHorario(){
        /*Modificar un horario. Recibe un objeto HorarioCompleto del view y los atributos se pasan
        * al modelo para almacenarlos en base de datos*/
        println("Ingrese el id del horario a modificar")
        var id: Int = readLine()!!.toInt()
        //verificar que el id está en la base de datos
        var isAvailable: Boolean = model.isIdValid(id)

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
            modelResponse = model.modificarHorario(id, opcion, valor)

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
        var isAvailable: Boolean = model.isIdValid(id)

        if (isAvailable){
            var modelResponse: Boolean
            modelResponse = model.inactivar(id)

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