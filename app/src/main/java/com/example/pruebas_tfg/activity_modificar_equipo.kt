package com.example.pruebas_tfg

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Model.Entrenador
import com.example.pruebas_tfg.Model.Equipo
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

class activity_modificar_equipo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_equipo)
        val nombre = findViewById<EditText>(R.id.nombreEquipo)
        val editCiudad = findViewById<EditText>(R.id.editCiudad)
        var entrenadorSeleccionado: Entrenador? = null
        val client = OkHttpClient()
        val presidente = findViewById<EditText>(R.id.editPresidente)
        val spinner = findViewById<Spinner>(R.id.eleccionEntrenador)
        val int = intent
        val equipo = int.getSerializableExtra("equipo") as Equipo
        nombre.setText(equipo.nombreEquipo)
        editCiudad.setText(equipo.ciudad)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent != null) {
                    entrenadorSeleccionado = parent.getItemAtPosition(position) as Entrenador
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }


        val btnModificar = findViewById<Button>(R.id.btnModificar)

        btnModificar.setOnClickListener {
            if(nombre.text.toString().isEmpty() || editCiudad.text.toString().isEmpty() || presidente.text.toString().isEmpty()){
                Toast.makeText(this@activity_modificar_equipo, "Debe completar todos los campos", Toast.LENGTH_SHORT).show()
            }else{
                equipo.idEquipo = equipo.idEquipo
                equipo.nombreEquipo = nombre.text.toString()
                equipo.ciudad= editCiudad.text.toString()
                equipo.presidente =presidente.text.toString()
                val json = Gson().toJson(equipo)
                val requestBody =json.toRequestBody("application/json".toMediaTypeOrNull())

                val request = Request.Builder()
                    .url("http://192.168.2.211:8080/equipos")
                    .put(requestBody)
                    .build()
                client.newCall(request).enqueue(object  : Callback{
                    override fun onFailure(call: Call, e: IOException) {
                    }

                    override fun onResponse(call: Call, response: Response) {
                        if(response.code==200){
                            runOnUiThread {
                                Toast.makeText(this@activity_modificar_equipo, "Equipo Modificado con éxito", Toast.LENGTH_SHORT).show()
                            }
                            finish()
                        }else{
                            runOnUiThread {
                                Toast.makeText(this@activity_modificar_equipo, "Error al modificar el equipo", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                })

            }


        }


    }
}