package controllers
import Horario

fun main(){
    val horario = Horario()
    val controller = HorarioController(horario)

    var validInput = false
    var opcion: Int = -1
    println("Ingrese la opcion que quiere probar")
    println("1: Registrar un nuevo horario")
    println("2: Consultar todos los horarios disponibles")
    println("3: Consultar información de un horario")
    println("4: Modificar un atributo cualquiera de un horario")
    println("5: Cambiar el estado de un horario (activar o inactivar)")

    while (!validInput){
        try {
            opcion= readLine()!!.toInt()
            if (opcion<1 || opcion>5) throw IllegalArgumentException("Elija una opción entre 1 y 5. Inténtelo de nuevo")
            validInput = true
        } catch (e: NumberFormatException){ //validar que el input se pueda cambiar a entero
            println("Debe ingresar un número entero entre 1 y 5. Inténtelo de nuevo")
        } catch (e: NullPointerException){ //validar que la entrada no sea vacía
            println("No puede dejar este campo vacío. Ingrese un número entero entre 1 y 5. Inténtelo de nuevo")
        } catch (e: IllegalArgumentException){ //validar que la hora está entre 0 y 23
            println(e.message)
        }
    }

    when(opcion){
        1 -> {
            println("Para probar un registro exitoso, ingrese como nombre del horario pruebaOK. De lo contrario, ingrese cualquier nombre")
            controller.registrarHorario()
        }
        2 -> controller.retrieveAllHorarios()
        3 -> {
            println("Ingrese el id del horario que desea consultar")
            val opcion = readLine()
            controller.retrieveHorario(3)//quemado para prueba
        }
        4 -> {
            println("Para probar una modificación exitosa, ingrese un id par. Si no, ingrese un id impar")
            controller.modificarHorario()
        }
        5 -> {
            println("Para probar una modificación exitosa, ingrese un id par. Si no, ingrese un id impar")
            controller.cambiarEstado()
        }
    }
}