package controllers
import Model
import model.Horario

fun main(){
    val modelo = Model()
    val controller = HorarioController()

    var validInput = false
    var opcion: Int = -1
    println("Ingrese la opcion que quiere probar")
    println("1: Registrar un nuevo horario")
    println("2: Consultar todos los horarios disponibles")
    println("3: Consultar información de un horario")
    println("4: Modificar un atributo cualquiera de un horario")
    println("5: Inactivar un horario")

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
            println("Ingrese el nombre del horario")
            var nombre:String = readLine()!!
            println("Ingrese la hora de inicio del horario")
            var horaI:String = readLine()!!
            println("Ingrese la hora de fin del horario")
            var horaF:String = readLine()!!

            var diasSemana:MutableList<String> = mutableListOf()
            println("Indique en qué días de la semana estará disponible el horario")
            println("¿Incluir domingo?")
            var dia:Int = readLine()!!.toInt()
            if (dia==1){
                diasSemana.add("Domingo")
            }
            println("¿Incluir lunes?")
            dia = readLine()!!.toInt()
            if (dia==1){
                diasSemana.add("Lunes")
            }
            println("¿Incluir martes?")
            dia = readLine()!!.toInt()
            if (dia==1){
                diasSemana.add("Martes")
            }
            println("¿Incluir miércoles?")
            dia = readLine()!!.toInt()
            if (dia==1){
                diasSemana.add("Miércoles")
            }
            println("¿Incluir jueves?")
            dia = readLine()!!.toInt()
            if (dia==1){
                diasSemana.add("Jueves")
            }
            println("¿Incluir viernes?")
            dia = readLine()!!.toInt()
            if (dia==1){
                diasSemana.add("Viernes")
            }
            println("¿Incluir sábado?")
            dia = readLine()!!.toInt()
            if (dia==1){
                diasSemana.add("Sábado")
            }

            val response:String = controller.registrarHorario(nombre, diasSemana, horaI, horaF)
            println(response)
        }
        2 -> {
            val response: List<Horario> = controller.visualizarTodosHorarios()
            for (horario in response){
                println("--------------------------------------------------------------")
                println("id = ${horario.getId()}")
                println("nombre = ${horario.getNombre()}")
                println("hora inicio = ${horario.getHoraInicio()}")
                println("hora fin = ${horario.getHoraFin()}")
                println("dias = ${horario.getDias()}")
                println("estado = ${horario.getEstado()}")
            }
        }
        3 -> {
            println("Ingrese el nombre del horario que desea consultar")
            val nombre = readLine()!!
            val horario:Horario = controller.visualizarHorario(nombre)
            println("id = ${horario.getId()}")
            println("nombre = ${horario.getNombre()}")
            println("hora inicio = ${horario.getHoraInicio()}")
            println("hora fin = ${horario.getHoraFin()}")
            println("dias = ${horario.getDias()}")
            println("estado = ${horario.getEstado()}")
        }
        4 -> {
            println("Ingrese el nombre del horario que desea modificar")
            val nombreHorario = readLine()!!

            val horario:Horario = controller.visualizarHorario(nombreHorario)
            println("id = ${horario.getId()}")
            println("nombre = ${horario.getNombre()}")
            println("hora inicio = ${horario.getHoraInicio()}")
            println("hora fin = ${horario.getHoraFin()}")
            println("dias = ${horario.getDias()}")
            println("estado = ${horario.getEstado()}")

            println("Ingrese los nuevos datos (si no desea modificar un dato, escriba el mismo valor que aparece arriba)")

            println("Nuevo nombre")
            val nuevoNombre = readLine()!!
            println("Nueva hora de inicio")
            val nuevaHoraI = readLine()!!
            println("Nueva hora de fin")
            val nuevaHoraF = readLine()!!

            var diasSemana:MutableList<String> = mutableListOf()
            println("Nuevos días de la semana")
            println("¿Incluir domingo?")
            var dia:Int = readLine()!!.toInt()
            if (dia==1){
                diasSemana.add("Domingo")
            }
            println("¿Incluir lunes?")
            dia = readLine()!!.toInt()
            if (dia==1){
                diasSemana.add("Lunes")
            }
            println("¿Incluir martes?")
            dia = readLine()!!.toInt()
            if (dia==1){
                diasSemana.add("Martes")
            }
            println("¿Incluir miércoles?")
            dia = readLine()!!.toInt()
            if (dia==1){
                diasSemana.add("Miércoles")
            }
            println("¿Incluir jueves?")
            dia = readLine()!!.toInt()
            if (dia==1){
                diasSemana.add("Jueves")
            }
            println("¿Incluir viernes?")
            dia = readLine()!!.toInt()
            if (dia==1){
                diasSemana.add("Viernes")
            }
            println("¿Incluir sábado?")
            dia = readLine()!!.toInt()
            if (dia==1){
                diasSemana.add("Sábado")
            }
            val response:String = controller.modificarHorario(nombreHorario, nuevoNombre, diasSemana, nuevaHoraI, nuevaHoraF)
            println(response)
        }
        5 -> {
            println("Ingrese el nombre del horario que desea inactivar")
            val nombre = readLine()!!
            val response:String = controller.inactivarHorario(nombre)
            println(response)
        }
    }
}