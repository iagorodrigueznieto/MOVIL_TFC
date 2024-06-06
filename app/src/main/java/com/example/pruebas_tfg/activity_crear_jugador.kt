package com.example.pruebas_tfg

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Adapter.BuscarEquiposAdapterLista
import com.example.pruebas_tfg.Adapter.SpinnerEntrenadorAdapter
import com.example.pruebas_tfg.Adapter.SpinnerEquiposAdapter
import com.example.pruebas_tfg.Model.Equipo
import com.example.pruebas_tfg.Model.Jugador
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class activity_crear_jugador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_jugador)
        var itemSeleccionado: Equipo? = null
        val nombreJugador: EditText = findViewById(R.id.nombreJugador)
        val tarjetasAmarillas: EditText = findViewById(R.id.tarjetas_amarillas)
        val tarjetasRojas: EditText = findViewById(R.id.Tarjetas_Rojas)
        val Goles: EditText = findViewById(R.id.Goles)
        val spinnerPosicion: Spinner = findViewById(R.id.spinnerPosicion)
        val asistencias: EditText = findViewById(R.id.asistencias)
        var posicion: Int = 1
        val btn: Button = findViewById(R.id.btnCrearJugador)
        val client = OkHttpClient()

        btn.setOnClickListener {
            if (nombreJugador.text.toString().isEmpty() || tarjetasAmarillas.text.toString()
                    .isEmpty() || tarjetasRojas.text.toString().isEmpty() || Goles.text.toString()
                    .isEmpty() || asistencias.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Por favor rellene todos los datos", Toast.LENGTH_SHORT).show()
            } else {
                val gson = Gson()
                val jugador = Jugador(
                    nombreJugador.text.toString(),
                    "",
                    3333,
                    tarjetasAmarillas.text.toString().toInt(),
                    tarjetasRojas.text.toString().toInt(),
                    0,
                    Goles.text.toString().toInt(),
                    asistencias.text.toString().toInt(),
                    null,
                    posicion
                )
                val json = gson.toJson(jugador)
                val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())
                val request = Request.Builder()
                    .url("https://proyecyotfc.zeabur.app/jugadores")
                    .post(requestBody)
                    .build()
                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {

                    }

                    override fun onResponse(call: Call, response: Response) {
                        if (response.code == 200) {
                            runOnUiThread {
                                Toast.makeText(
                                    baseContext,
                                    "Jugador insertado con éxito",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        } else {
                            runOnUiThread {
                                Toast.makeText(
                                    baseContext,
                                    "Error al insertar jugador",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }

                })
                finish()
            }


        }


        val array = arrayOf("Portero", "Defensa", "Mediocentro", "Delantero")

        spinnerPosicion.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array)

        spinnerPosicion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent != null) {
                    if (array[position] == "Portero") {
                        posicion = 1
                    } else if (array[position] == "Defensa") {
                        posicion = 2

                    } else if (array[position] == "Mediocentro") {
                        posicion = 3

                    } else if (array[position] == "Delantero") {
                        posicion = 4

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {


            }

        }
    }
}



