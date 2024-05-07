package com.example.pruebas_tfg

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Model.Entrenador

class activity_modificar_equipo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_equipo)
        val nombre = findViewById<EditText>(R.id.nombreEquipo)
        val editCiudad = findViewById<EditText>(R.id.editCiudad)
        var entrenadorSeleccionado : Entrenador? = null
        val spinner = findViewById<Spinner>(R.id.eleccionEntrenador)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                    if(parent != null){
                        entrenadorSeleccionado = parent.getItemAtPosition(position) as Entrenador
                    }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        val int = intent


    }
}