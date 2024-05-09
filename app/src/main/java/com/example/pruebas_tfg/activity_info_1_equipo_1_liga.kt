package com.example.pruebas_tfg

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

class activity_info_1_equipo_1_liga : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_1_equipo_en_1_liga)
        val intent = intent
        val equipo = intent.getSerializableExtra("equipo") as ClasificacionInputDto
        val client = OkHttpClient()
        val puntos = findViewById<TextView>(R.id.puntos)
        val partidosJugados = findViewById<TextView>(R.id.partidosJugados)
        val partidosGanados = findViewById<TextView>(R.id.partidosGanados)
        val partidosEmpatados = findViewById<TextView>(R.id.partidosEmpatados)
        val partidosPerdidos = findViewById<TextView>(R.id.partidosPerdidos)
        val golesAFavor = findViewById<TextView>(R.id.golesAFavor)
        val golesEnContra = findViewById<TextView>(R.id.GolesEnContra)
        val liga =intent.getSerializableExtra("liga") as Liga
        val nombreEquipo =findViewById<TextView>(R.id.nombreEquipo)
        nombreEquipo.text = equipo.nombreEquipo
        val nombreLiga =findViewById<TextView>(R.id.nombreLiga)
        nombreLiga.text = liga.nombre
        val url = "http://192.168.2.211:8080/equipos/info?codLiga=${liga.codLiga}&codEquipo=${equipo.idEquipo}"
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
                        puntos.text  =equipo.puntos.toString()
                        partidosJugados.text = equipo2.partidosJugados.toString()
                        partidosGanados.text = equipo2.partidosGanados.toString()
                        partidosEmpatados.text = equipo2.partidosEmpatados.toString()
                        partidosPerdidos.text = equipo2.partidosPerdidos.toString()
                        golesAFavor.text = equipo2.golesFavor.toString()
                        golesEnContra.text = equipo2.golesContra.toString()
                    }

            }
        })

    val btnEliminar = findViewById<Button>(R.id.btnEliminar)
        btnEliminar.setOnClickListener{
            val url2 = "http://192.168.2.211:8080/liga/eliminar?codEquipo=${equipo.idEquipo}&codLiga=${liga.codLiga}"
            client.newCall(request2.url(url2).delete().build()).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {

                }
                override fun onResponse(call: Call, response: Response) {
                    if(response.code==200){
                        runOnUiThread {
                            Toast.makeText(this@activity_info_1_equipo_1_liga, "Equipo eliminado en la liga{${liga.nombre}}", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                }

            })
        }

        val btnModificar = findViewById<Button>(R.id.btnModificar)
        btnModificar.setOnClickListener{
            val intent = Intent(this, activity_modificar_equipo_liga::class.java)
            intent.putExtra("equipo", equipo)
            intent.putExtra("liga", liga)
            startActivity(intent)

        }





    }
}