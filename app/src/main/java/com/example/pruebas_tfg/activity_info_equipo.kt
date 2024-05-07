package com.example.pruebas_tfg

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.pruebas_tfg.Adapter.SpinnerEntrenadorAdapter
import com.example.pruebas_tfg.Model.Entrenador
import com.example.pruebas_tfg.Model.Equipo
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.time.LocalDate

class activity_info_equipo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_info_equipo)
        val intent = intent
        val equipoAverInfo = intent.getSerializableExtra("equipo") as Equipo
        val ciudad = findViewById<TextView>(R.id.ciudad)
        val entrenadortexto = findViewById<TextView>(R.id.entrenador)
        val presidente = findViewById<TextView>(R.id.presidente)
        val nombreEquipo = findViewById<TextView>(R.id.nombreEquipo)
        val client = OkHttpClient()
        ciudad.setText(equipoAverInfo.ciudad)
        presidente.setText(equipoAverInfo.presidente)
        nombreEquipo.setText(equipoAverInfo.nombreEquipo)

        Toast.makeText(
            baseContext,
            "Equipo creado con éxito",
            Toast.LENGTH_LONG
        ).show()
        val request = Request.Builder()
            .url("http://192.168.2.211:8080/entrenadores/entrenador?id=${equipoAverInfo.idEntrenador}").build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("No se ha podido hacer la llamada")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful && response.body != null) {
                    val responseData = response.body!!.string()
                    val entrenadores = ArrayList<Entrenador>()
                    try {
                        val entrenadorJSON = JSONObject(responseData)
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


                            runOnUiThread {
                                entrenadortexto.text = entrenadores[0].nombre+" "+entrenadores.get(0).apellido
                            }

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

        })
    }
}