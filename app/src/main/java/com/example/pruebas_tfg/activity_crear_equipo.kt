package com.example.pruebas_tfg

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.pruebas_tfg.Adapter.SpinnerEntrenadorAdapter
import com.example.pruebas_tfg.Model.Entrenador
import com.example.pruebas_tfg.Model.Equipo
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.net.URL
import java.time.LocalDate


class activity_crear_equipo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_equipo)
        val button: Button = findViewById(R.id.btnCrear)
        val spinner: Spinner = findViewById(R.id.eleccionEntrenador)
        val ciudad: EditText = findViewById(R.id.editCiudad)
        val nombreEquipo: EditText = findViewById(R.id.nombreEquipo)
        val client = OkHttpClient()
        var entrenadorSeleccionado: Entrenador? = null
        val btnCrearEntrenador: Button = findViewById(R.id.btnCrearEntrenador)
        btnCrearEntrenador.isVisible = false
        btnCrearEntrenador.isClickable = false
        val id = 100

        val request = Request.Builder().url("http://192.168.2.211:8080/entrenadores/libres").build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("No se ha podido hacer la llamada")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful && response.body != null) {
                    val responseData = response.body!!.string()
                    val entrenadores = ArrayList<Entrenador>()
                    try {
                        val jsonArray = JSONArray(responseData)
                        for (i in 0 until jsonArray.length()) {
                            val entrenadorJSON = jsonArray.getJSONObject(i)
                            val idEntrenador = entrenadorJSON.getInt("idEntrenador")
                            val nombre = entrenadorJSON.getString("nombre")
                            val apellido = entrenadorJSON.getString("apellido")
                            val nacionalidad = entrenadorJSON.getString("nacionalidad")
                            val fechanacimiento = entrenadorJSON.getString("fechaDeNacimiento")

                            entrenadores.add(
                                Entrenador(
                                    idEntrenador,
                                    nombre,
                                    apellido,
                                    nacionalidad,
                                    LocalDate.parse(fechanacimiento)
                                )
                            )
                            if (entrenadores.size == 0) {
                                btnCrearEntrenador.isVisible = true
                                btnCrearEntrenador.isClickable = true
                            }
                            val adapter = SpinnerEntrenadorAdapter(baseContext, entrenadores)
                            runOnUiThread {
                                spinner.adapter = adapter
                            }
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

        })

        button.setOnClickListener {
            if (nombreEquipo.text.toString().isEmpty() || ciudad.text.toString().isEmpty()) {
                Toast.makeText(baseContext, "Tiene que rellenar los campos",Toast.LENGTH_LONG).show()
            } else {
                val gson = Gson()

                val equipo = entrenadorSeleccionado?.let { it1 ->
                    Equipo(
                        nombreEquipo.text.toString(), ciudad.text.toString(),
                        it1.id, null
                    )
                }


                val json = gson.toJson(equipo)


                val url = "http://192.168.2.211:8080/equipos"

                val mediatype = "application/json; charset=utf-8".toMediaTypeOrNull()
                val body = RequestBody.create(mediatype, json)

                val request = Request.Builder().url(url).post(body).build()
                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        TODO("Not yet implemented")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        runOnUiThread {
                            if (response.code == 200) {
                                Toast.makeText(
                                    baseContext,
                                    "Equipo creado con éxito",
                                    Toast.LENGTH_LONG
                                ).show()
                                val request = Request.Builder()
                                    .url("http://192.168.2.211:8080/entrenadores/libres").build()

                                client.newCall(request).enqueue(object : Callback {
                                    override fun onFailure(call: Call, e: IOException) {
                                        println("No se ha podido hacer la llamada")
                                    }

                                    override fun onResponse(call: Call, response: Response) {
                                        if (response.isSuccessful && response.body != null) {
                                            val responseData = response.body!!.string()
                                            val entrenadores = ArrayList<Entrenador>()
                                            try {
                                                val jsonArray = JSONArray(responseData)
                                                for (i in 0 until jsonArray.length()) {
                                                    val entrenadorJSON = jsonArray.getJSONObject(i)
                                                    val idEntrenador =
                                                        entrenadorJSON.getInt("idEntrenador")
                                                    val nombre = entrenadorJSON.getString("nombre")
                                                    val apellido =
                                                        entrenadorJSON.getString("apellido")
                                                    val nacionalidad =
                                                        entrenadorJSON.getString("nacionalidad")
                                                    val fechanacimiento =
                                                        entrenadorJSON.getString("fechaDeNacimiento")

                                                    entrenadores.add(
                                                        Entrenador(
                                                            idEntrenador,
                                                            nombre,
                                                            apellido,
                                                            nacionalidad,
                                                            LocalDate.parse(fechanacimiento)
                                                        )
                                                    )
                                                    if (entrenadores.size == 0) {
                                                        btnCrearEntrenador.isVisible = true
                                                        btnCrearEntrenador.isClickable = true
                                                    }
                                                    val adapter = SpinnerEntrenadorAdapter(
                                                        baseContext,
                                                        entrenadores
                                                    )
                                                    runOnUiThread {
                                                        spinner.adapter = adapter
                                                        nombreEquipo.setText("")
                                                        ciudad.setText("")
                                                    }
                                                }
                                            } catch (e: JSONException) {
                                                e.printStackTrace()
                                            }
                                        }
                                    }

                                })
                            } else {
                                Toast.makeText(
                                    baseContext,
                                    "No se ha podido crear el equipo",
                                    Toast.LENGTH_LONG
                                ).show()

                            }
                        }

                    }
                })

            }
        }

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

    }

}