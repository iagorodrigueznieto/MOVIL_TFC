package com.example.pruebas_tfg

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
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

        buscarJugadores.addTextChangedListener(object: TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val request = Request.Builder()
                    .url("http://192.168.2.211:8080/buscar?nombre="+buscarJugadores.text)
                    .build()
                client.newCall(request).enqueue(object : Callback{
                    override fun onFailure(call: Call, e: IOException) {


                    }

                    override fun onResponse(call: Call, response: Response) {
                        if(response.isSuccessful){
                            val body=response.body
                            val jsonString = body?.string()
                            val gson=Gson()
                            val jugadores=gson.fromJson(jsonString, Array<Jugador>::class.java).toList()

                            runOnUiThread {
                                val adapter =JugadorAdaptadorLista(baseContext, jugadores)
                                lista.adapter=adapter
                            }

                        }
                    }

                })
            }

        })


    }

}