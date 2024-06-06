package com.example.pruebas_tfg

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Model.Equipo
import com.example.pruebas_tfg.Model.Liga
import com.example.pruebas_tfg.dto.ClasificacionInputDto
import com.example.pruebas_tfg.dto.InfoEquipoEn1LigaOutputDto
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

class activity_modificar_equipo_liga : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_un_equipo_en_1_liga)
        val intent = intent
        val equipo = intent.getSerializableExtra("equipo") as ClasificacionInputDto
        val liga = intent.getSerializableExtra("liga") as Liga
        val nombre = findViewById<TextView>(R.id.nombreEquipo)
        nombre.setText(equipo.nombreEquipo)
        val client = okhttp3.OkHttpClient()
        val partidosGanados = findViewById<EditText>(R.id.partidosGanados)
        val partidosEmpatados = findViewById<EditText>(R.id.partidosEmpatados)
        val partidosPerdidos = findViewById<EditText>(R.id.partidosPerdidos)
        val golesAFavor = findViewById<EditText>(R.id.golesAFavor)
        val golesEnContra = findViewById<EditText>(R.id.GolesEnContra)
        val nombreLiga = findViewById<TextView>(R.id.nombreLiga)
        nombreLiga.setText(liga.nombre)
        val url =
            "https://proyecyotfc.zeabur.app/equipos/info?codLiga=${liga.codLiga}&codEquipo=${equipo.idEquipo}"
        val request = okhttp3.Request.Builder().url(url).build()
        val request2 = okhttp3.Request.Builder()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = Gson()
                val equipo2 = gson.fromJson(body, InfoEquipoEn1LigaOutputDto::class.java)
                runOnUiThread {
                    partidosGanados.setText(equipo2.partidosGanados.toString())
                    partidosEmpatados.setText(equipo2.partidosEmpatados.toString())
                    partidosPerdidos.setText(equipo2.partidosPerdidos.toString())
                    golesAFavor.setText(equipo2.golesFavor.toString())
                    golesEnContra.setText(equipo2.golesContra.toString())
                }
            }
        })

        val btnGuardar = findViewById<Button>(R.id.btnModificar)
        btnGuardar.setOnClickListener {
            if (partidosGanados.text.toString()
                    .isEmpty() || partidosEmpatados.text.toString()
                    .isEmpty() || partidosPerdidos.text.toString()
                    .isEmpty() || golesAFavor.text.toString().isEmpty()
            ) {
                Toast.makeText(
                    this@activity_modificar_equipo_liga,
                    "Rellene todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                val puntos = (partidosEmpatados.text.toString().toInt() * 1 + partidosGanados.text.toString().toInt() * 3)
                val partidosJugados = partidosPerdidos.text.toString().toInt() + partidosEmpatados.text.toString().toInt() + partidosGanados.text.toString().toInt()
                val url = "https://proyecyotfc.zeabur.app/liga/info"
                val json = Gson().toJson(
                    InfoEquipoEn1LigaOutputDto(
                        equipo.idEquipo.toInt(),
                        liga.codLiga.toInt(),
                        puntos,
                        partidosJugados,
                        partidosGanados.text.toString().toInt(),
                        partidosEmpatados.text.toString().toInt(),
                        partidosPerdidos.text.toString().toInt(),
                        golesAFavor.text.toString().toInt(),
                        golesEnContra.text.toString().toInt()
                    )
                )
                val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())
                val request = okhttp3.Request.Builder()
                    .put(requestBody)
                    .url(url)
                    .build()
                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                    }

                    override fun onResponse(call: Call, response: Response) {
                        if (response.code == 200) {
                            runOnUiThread {
                                Toast.makeText(
                                    this@activity_modificar_equipo_liga,
                                    "Equipo modificado",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            finish()
                        }
                    }
                })
            }
        }
    }


}