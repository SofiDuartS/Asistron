package model

import Horario

fun main(){
    var h = Horario()
    h.crear("Antes del cambio", "lunes y martes", 2, 3)

    println(h)

val consulta = h.consulta(0)
println(consulta)
val todos = h.todosHorarios()
println(todos)

h.editar(1, 1, "Horario cambiado")
println(h)


println(h.isIdValid(1))
h.inactivar(1)
println(h.isIdValid(1))

}




