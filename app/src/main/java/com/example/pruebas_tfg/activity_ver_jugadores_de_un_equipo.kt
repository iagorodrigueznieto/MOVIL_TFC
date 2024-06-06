package com.example.pruebas_tfg

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Adapter.JugadorAdaptadorLista
import com.example.pruebas_tfg.Model.Equipo
import com.example.pruebas_tfg.Model.Jugador
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class activity_ver_jugadores_de_un_equipo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_jugadores_de_un_equipo)
        val lista  = findViewById<ListView>(R.id.lista)
        val client = OkHttpClient()
        intent = getIntent()
        var seleccion : String
        val equipo = intent.getSerializableExtra("equipo") as Equipo

        val requestAdapter = Request.Builder().url("https://proyecyotfc.zeabur.app/jugadores/equipo?codEquipo=${equipo.idEquipo}").build()

        client.newCall(requestAdapter).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.code==200){
                    val json = response.body?.string()
                    val listaJugadores = Gson().fromJson(json, Array<Jugador>::class.java).toList()
                    runOnUiThread {
                        lista.adapter = JugadorAdaptadorLista(this@activity_ver_jugadores_de_un_equipo, listaJugadores)
                    }
                }

            }
        })

        val spinnerPosicion = findViewById<Spinner>(R.id.spinnerPosicion)

        val array = arrayOf("Todos", "Portero", "Defensa", "Mediocentro", "Delantero")

        spinnerPosicion.adapter= ArrayAdapter(this, android.R.layout.simple_spinner_item, array)

        val array2 = arrayOf("Goles, Asiste")

        spinnerPosicion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                seleccion = array[position]
                if(seleccion.equals("Portero")){
                    val requestPosicion = Request.Builder().url("https://proyecyotfc.zeabur.app/jugadores/posicion?codEquipo=${equipo.idEquipo}&posicion=1").build()
                    client.newCall(requestPosicion).enqueue(object : Callback{
                        override fun onFailure(call: Call, e: IOException) {

                        }

                        override fun onResponse(call: Call, response: Response) {
                            if(response.code==200){
                                val json = response.body?.string()
                                val listaJugadores = Gson().fromJson(json, Array<Jugador>::class.java).toList()
                                runOnUiThread {
                                    lista.adapter = JugadorAdaptadorLista(this@activity_ver_jugadores_de_un_equipo, listaJugadores)
                                }
                            }
                        }
                    })
                }else if(seleccion.equals("Defensa")){
                    val requestPosicion = Request.Builder().url("https://proyecyotfc.zeabur.app/jugadores/posicion?codEquipo=${equipo.idEquipo}&posicion=2").build()
                    client.newCall(requestPosicion).enqueue(object : Callback{
                        override fun onFailure(call: Call, e: IOException) {

                        }

                        override fun onResponse(call: Call, response: Response) {
                            if(response.code==200){
                                val json = response.body?.string()
                                val listaJugadores = Gson().fromJson(json, Array<Jugador>::class.java).toList()
                                runOnUiThread {
                                    lista.adapter = JugadorAdaptadorLista(this@activity_ver_jugadores_de_un_equipo, listaJugadores)
                                }
                            }
                        }
                    })

                }else if(seleccion.equals("Mediocentro")){
                    val requestPosicion = Request.Builder().url("https://proyecyotfc.zeabur.app/jugadores/posicion?codEquipo=${equipo.idEquipo}&posicion=3").build()
                    client.newCall(requestPosicion).enqueue(object : Callback{
                        override fun onFailure(call: Call, e: IOException) {

                        }

                        override fun onResponse(call: Call, response: Response) {
                            if(response.code==200){
                                val json = response.body?.string()
                                val listaJugadores = Gson().fromJson(json, Array<Jugador>::class.java).toList()
                                runOnUiThread {
                                    lista.adapter = JugadorAdaptadorLista(this@activity_ver_jugadores_de_un_equipo, listaJugadores)
                                }
                            }
                        }
                    })
                }else if (seleccion.equals("Delantero")){
                    val requestPosicion = Request.Builder().url("https://proyecyotfc.zeabur.app/jugadores/posicion?codEquipo=${equipo.idEquipo}&posicion=4").build()
                    client.newCall(requestPosicion).enqueue(object : Callback{
                        override fun onFailure(call: Call, e: IOException) {

                        }

                        override fun onResponse(call: Call, response: Response) {
                            if(response.code==200){
                                val json = response.body?.string()
                                val listaJugadores = Gson().fromJson(json, Array<Jugador>::class.java).toList()
                                runOnUiThread {
                                    lista.adapter = JugadorAdaptadorLista(this@activity_ver_jugadores_de_un_equipo, listaJugadores)
                                }
                            }
                        }
                    })
                }else{
                    val requestAdapter = Request.Builder().url("https://proyecyotfc.zeabur.app/jugadores/equipo?codEquipo=${equipo.idEquipo}").build()

                    client.newCall(requestAdapter).enqueue(object : Callback{
                        override fun onFailure(call: Call, e: IOException) {
                        }

                        override fun onResponse(call: Call, response: Response) {
                            if (response.code==200){
                                val json = response.body?.string()
                                val listaJugadores = Gson().fromJson(json, Array<Jugador>::class.java).toList()
                                runOnUiThread {
                                    lista.adapter = JugadorAdaptadorLista(this@activity_ver_jugadores_de_un_equipo, listaJugadores)
                                }
                            }

                        }
                    })
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }







    }

}