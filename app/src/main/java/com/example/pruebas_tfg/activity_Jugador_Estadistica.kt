package com.example.pruebas_tfg

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Adapter.JugadorAdaptadorLista
import com.example.pruebas_tfg.Model.Jugador
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class activity_Jugador_Estadistica : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_jugadores)
        val buscarJugadores : EditText = findViewById(R.id.barraBuscarJugadores)
        val lista : ListView = findViewById(R.id.jugadores)
        val client=OkHttpClient()
        val button = findViewById<Button>(R.id.buscar)
        button.setOnClickListener {
            val request = Request.Builder()
                .url("http://localhost:8080/jugadores")
                .build()
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val body=response.body
                    val jsonString = body?.toString()
                    val gson=Gson()

                    val jugadores=gson.fromJson(jsonString,Array<Jugador>::class.java).toList()

                    runOnUiThread {

                        val adapter = JugadorAdaptadorLista(baseContext, jugadores)
                        lista.adapter = adapter

                    }
                }
            })
        }





    }

}