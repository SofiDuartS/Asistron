package com.example.frontdiseo

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import controllers.HorarioController
import model.Horario

class Inicio_registrar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)
        val notifi_Menu = findViewById<LinearLayout>(R.id.registrar)
        notifi_Menu.setOnClickListener {
            setContentView(R.layout.registrar)
        }
    }
}

class Inicio_modificar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val controller:HorarioController = HorarioController()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)
        val notifi_Menu = findViewById<LinearLayout>(R.id.modificar)
        notifi_Menu.setOnClickListener {
            setContentView(R.layout.modificar)
            val nombrePrueba = "name" //quemado para prueba
            val horarioModificar: Horario = controller.visualizarHorario(nombrePrueba) //acá debería ir el nombre del horario



            //Accediendo a campos del formulario
            val nombreHorario = findViewById<EditText>(R.id.nombreHorario)
            val checkLunes = findViewById<CheckBox>(R.id.checkLunes)
            val checkMartes = findViewById<CheckBox>(R.id.checkMartes)
            val checkMiercoles = findViewById<CheckBox>(R.id.checkMiercoles)
            val checkJueves = findViewById<CheckBox>(R.id.checkJueves)
            val checkViernes = findViewById<CheckBox>(R.id.checkViernes)
            val checkSabado = findViewById<CheckBox>(R.id.checkSabado)
            val checkDomingo = findViewById<CheckBox>(R.id.checkDomingo)
            val horaInicio = findViewById<EditText>(R.id.horaInicio)
            val horaFin = findViewById<EditText>(R.id.horaFin)
            val estado = findViewById<EditText>(R.id.estadoHorario)

            //Modificando valores del formulario
            nombreHorario.setText(horarioModificar.getNombre()) //nombreHorario

            //dias
            val dias = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
            for (dia in dias){
                if (dia in horarioModificar.getDias()){
                    when(dia){
                        "Domingo" -> checkDomingo.isChecked = true
                        "Lunes" -> checkLunes.isChecked = true
                        "Martes" -> checkMartes.isChecked = true
                        "Miércoles" -> checkMiercoles.isChecked = true
                        "Jueves" -> checkJueves.isChecked = true
                        "Viernes" -> checkViernes.isChecked = true
                        "Sábado" -> checkSabado.isChecked = true
                    }
                } else{
                    when(dia){
                        "Domingo" -> checkDomingo.isChecked = false
                        "Lunes" -> checkLunes.isChecked = false
                        "Martes" -> checkMartes.isChecked = false
                        "Miércoles" -> checkMiercoles.isChecked = false
                        "Jueves" -> checkJueves.isChecked = false
                        "Viernes" -> checkViernes.isChecked = false
                        "Sábado" -> checkSabado.isChecked = false
                    }
                }
            }

            horaInicio.setText(horarioModificar.getHoraInicio())
            horaFin.setText(horarioModificar.getHoraFin())
            estado.setText(horarioModificar.getEstado().toString())
        }
    }
}

class Inicio_ocultar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)
        val notifi_Menu = findViewById<LinearLayout>(R.id.ocultar)
        notifi_Menu.setOnClickListener {
            setContentView(R.layout.ocultar)
        }
    }
}

class Inicio_visualizar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)
        val notifi_Menu = findViewById<LinearLayout>(R.id.visualizar)
        notifi_Menu.setOnClickListener {
            setContentView(R.layout.visualizar)
        }
    }
}

class Visualizar_aprendiendo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.visualizar)
        val notifi_Menu = findViewById<Button>(R.id.aprendiendoanadar)
        notifi_Menu.setOnClickListener {
            setContentView(R.layout.visualizar2)
        }
    }
}

class Volver_menu : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrar)
        val notifi_Menu = findViewById<Button>(R.id.volver)
        notifi_Menu.setOnClickListener {
            setContentView(R.layout.inicio)
        }
    }
}

class Volver_visualizar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.visualizar2)
        val notifi_Menu = findViewById<Button>(R.id.volver_visualizar)
        notifi_Menu.setOnClickListener {
            setContentView(R.layout.visualizar)
        }
    }
}
