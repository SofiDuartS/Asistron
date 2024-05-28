package com.example.frontdiseo

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import controllers.HorarioController
import model.Horario

class MainActivity : ComponentActivity() {
    val context:Context
        get() = this.applicationContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)
   }

    val negativeButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(applicationContext,
            android.R.string.no, Toast.LENGTH_SHORT).show()
    }

    val positiveButtonClickModificar = { dialog: DialogInterface, which: Int ->
        Toast.makeText(applicationContext,
            android.R.string.yes, Toast.LENGTH_SHORT).show()
        setContentView(R.layout.inicio)
    }

    fun Modificar(view: View) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.popup_modificar, null)
        builder.setView(dialogLayout)
        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener(function = positiveButtonClickModificar))
        builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener(function = negativeButtonClick))
        builder.show()

    }

    val positiveButtonClickOcultar = { dialog: DialogInterface, which: Int ->
        Toast.makeText(applicationContext,
            android.R.string.yes, Toast.LENGTH_SHORT).show()
        setContentView(R.layout.ocultar2)
    }

    fun Ocultar(view: View) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.popup_ocultar, null)
        builder.setView(dialogLayout)
        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener(function = positiveButtonClickOcultar))
        builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener(function = negativeButtonClick))
        builder.show()

    }

    fun registrando(view: View) {
        // Código para manejar el clic del botón aquí
        //setContentView(R.layout.registrar)
        val buttonGuardar = findViewById<Button>(R.id.registrar_horario)
        //buttonGuardar.setOnClickListener {
            Registrar_Horario(view)
        //}
    }

    fun Registrar_Horario(view: View) {
        val controller:HorarioController = HorarioController()
        //setContentView(R.layout.registrar)

        //Accediendo a campos del formulario
        val nombreHorario = findViewById<EditText>(R.id.namehorario)
        val checkLunes = findViewById<CheckBox>(R.id.checkLunes)
        val checkMartes = findViewById<CheckBox>(R.id.checkMartes)
        val checkMiercoles = findViewById<CheckBox>(R.id.checkMiercoles)
        val checkJueves = findViewById<CheckBox>(R.id.checkJueves)
        val checkViernes = findViewById<CheckBox>(R.id.checkViernes)
        val checkSabado = findViewById<CheckBox>(R.id.checkSabado)
        val checkDomingo = findViewById<CheckBox>(R.id.checkDomingo)
        val horaInicio = findViewById<EditText>(R.id.horai_horario)
        val horaFin = findViewById<EditText>(R.id.horaf_horario)
        val estado = findViewById<EditText>(R.id.estado_horario)
        val listDias: MutableList<String> = mutableListOf()

        if(checkLunes.isChecked){
            listDias.add("Lunes")
        }
        if(checkMartes.isChecked){
            listDias.add("Martes")
        }
        if(checkMiercoles.isChecked){
            listDias.add("Miércoles")
        }
        if(checkJueves.isChecked){
            listDias.add("Jueves")
        }
        if(checkViernes.isChecked){
            listDias.add("Viernes")
        }
        if(checkSabado.isChecked){
            listDias.add("Sábado")
        }
        if(checkDomingo.isChecked){
            listDias.add("Domingo")
        }
        val info = controller.registrarHorario(nombreHorario.text.toString(),listDias,horaInicio.text.toString(),horaFin.text.toString())

    }

    val positiveButtonClickRegistrar = { dialog: DialogInterface, which: Int ->
        Toast.makeText(applicationContext,
            android.R.string.yes, Toast.LENGTH_SHORT).show()
        setContentView(R.layout.inicio)
    }

    fun Registrar(view: View) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.popup_ocultar, null)
        builder.setView(dialogLayout)
        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener(function = positiveButtonClickRegistrar))
        builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener(function = negativeButtonClick))
        builder.show()

    }

    fun Inicio_registrar(view: View) {
        setContentView(R.layout.registrar)
    }
    fun Inicio_modificar(view: View) {
        //setContentView(R.layout.inicio)
        val controller:HorarioController = HorarioController()
        //val notifi_Menu = findViewById<LinearLayout>(R.id.modificar)
        //notifi_Menu.setOnClickListener {
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

            horaInicio.setText(horarioModificar.getHoraInicio().toString())
            horaFin.setText(horarioModificar.getHoraFin().toString())
            estado.setText(horarioModificar.getEstado().toString())
        //}
    }
    fun Inicio_ocultar(view: View) {
        setContentView(R.layout.ocultar)
    }
    fun Inicio_visualizar(view: View) {
        setContentView(R.layout.visualizar)
    }
    fun Visualizar_aprendiendo(view: View) {
        setContentView(R.layout.visualizar2)
    }
    fun Volver_menu(view: View) {
        setContentView(R.layout.inicio)
    }
    fun Volver_visualizar(view: View) {
        setContentView(R.layout.visualizar)
    }

}