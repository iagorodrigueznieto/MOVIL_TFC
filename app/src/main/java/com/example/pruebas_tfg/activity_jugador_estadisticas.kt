package com.example.pruebas_tfg

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Model.Jugador
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class activity_jugador_estadisticas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val client = OkHttpClient()
        val jugador = intent.getSerializableExtra("jugador") as Jugador
        setContentView(R.layout.activity_layout_estaditicas_jugadores)
        val nombreJugador = findViewById<TextView>(R.id.nombreJugador)
        val posicion = findViewById<TextView>(R.id.posicion)
        val goles= findViewById<TextView>(R.id.goles)
        val asistencias = findViewById<TextView>(R.id.asistencias)
        val tarjetasAmarillas = findViewById<TextView>(R.id.tarjetasAmarillas)
        val tarjetasRojas = findViewById<TextView>(R.id.tarjetasRojas)
        val fechaNacimiento = findViewById<TextView>(R.id.fechaDeNacimiento)
        val btnEliminar = findViewById<Button>(R.id.btnEliminarJugador)
        val btnModificar = findViewById<Button>(R.id.btnModificarJugador)
        if(jugador!= null){
            nombreJugador.text = jugador.nombre
            goles.text = jugador.goles.toString()
            asistencias.text = jugador.asistencias.toString()
            tarjetasAmarillas.text = jugador.tarjetasAmarillas.toString()
            tarjetasRojas.text = jugador.tarjetasRojas.toString()
            fechaNacimiento.text = jugador.fechaNacimiento.toString()
            if(jugador.codPosicion == 1){
                posicion.text =("Portero")

            }else if (jugador.codPosicion == 2){
                posicion.text = ("Defensa")

            }else if (jugador.codPosicion == 3){
                posicion.text = "MedioCentro"

            }else{
                posicion.text = ("Delantero")
            }
        }


        btnEliminar.setOnClickListener {
            finish()

            val url = "http://192.168.2.211:8080/jugadores?id=${jugador.id}"
            var mediaType  = "application/json; charset=utf-8".toMediaTypeOrNull()
            val request=Request.Builder().url(url).delete().build()
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                        Log.d("No se ha podido hacer la llamada", e.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    if(response.code ==  200){
                        runOnUiThread {
                            Toast.makeText(baseContext, "Jugador eliminado correctamente.",Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(baseContext, "No se pudo eliminar el jugador",Toast.LENGTH_LONG).show()
                    }
                }

            })
        }
        btnModificar.setOnClickListener {
            val intent = Intent(baseContext, activity_modificar_jugador::class.java)
            intent.putExtra("jugador",jugador)
            startActivity(intent)
        }

    }
}