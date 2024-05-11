package com.example.frontdiseo

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.LinearLayout
import android.widget.Button
import android.widget.TextView

class conextions {
}

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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)
        val notifi_Menu = findViewById<LinearLayout>(R.id.modificar)
        notifi_Menu.setOnClickListener {
            setContentView(R.layout.modificar)
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