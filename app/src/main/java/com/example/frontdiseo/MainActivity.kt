package com.example.frontdiseo

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.frontdiseo.ui.theme.FrontDiseñoTheme

class MainActivity : ComponentActivity() {
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
        setContentView(R.layout.modificar)
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