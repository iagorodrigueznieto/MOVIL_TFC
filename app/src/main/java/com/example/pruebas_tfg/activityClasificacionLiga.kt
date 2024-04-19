package com.example.pruebas_tfg

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Adapter.AdaptadorLista
import com.example.pruebas_tfg.Model.Jugador
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.time.LocalDate

class activityClasificacionLiga : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clasificacion_liga)

        val lista = findViewById<ListView>(R.id.listaJugadores)
        val client = OkHttpClient()
        val request= Request.Builder().url("http://192.168.2.211:8080/jugadores").build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Error al realizar la solicitud", e.message!!)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful && response.body != null) {
                    val responseData = response.body!!.string()
                    val ligas = ArrayList<Jugador>()
                    try {
                        val jsonArray = JSONArray(responseData)
                        for (i in 0 until jsonArray.length()) {
                            val jugadorJson = jsonArray.getJSONObject(i)
                            val idJugador = jugadorJson.getInt("idJugador")
                            val nombre = jugadorJson.getString("nombre")
                            val fechaNacimiento = LocalDate.parse(jugadorJson.getString("fechaNacimiento"))
                            val idEquipo = jugadorJson.optInt("idEquipo", -1) // -1 por si es nulo
                            val tarjetasAmarillas = jugadorJson.getInt("tarjetasAmarillas")
                            val tarjetasRojas = jugadorJson.getInt("tarjetasRojas")
                            val partidosJugados = jugadorJson.getInt("partidosJugados")
                            val goles = jugadorJson.getInt("goles")
                            val asistencias = jugadorJson.getInt("asistencias")
                            val imagen = jugadorJson.optString("imagen", null)
                            val codPosicion = jugadorJson.getInt("codPosicion")
                            ligas.add(
                                Jugador(
                                    idJugador,
                                    nombre,
                                    fechaNacimiento,
                                    idEquipo,
                                    tarjetasAmarillas,
                                    tarjetasRojas,
                                    partidosJugados,
                                    goles,
                                    asistencias,
                                    imagen,
                                    codPosicion
                                )
                            )
                        }
                        runOnUiThread {
                            val adapter = AdaptadorLista(baseContext, ligas)
                            lista.adapter = adapter
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                    println("La respuesta es: $responseData")
                } else {
                    println("No se pudo completar la solicitud")
                }
            }
        })
    }
}
