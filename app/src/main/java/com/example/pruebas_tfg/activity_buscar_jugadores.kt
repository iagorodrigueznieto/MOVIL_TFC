package com.example.pruebas_tfg

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Adapter.JugadorAdaptadorLista
import com.example.pruebas_tfg.Model.Jugador
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.time.LocalDate

class activity_buscar_jugadores : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_jugadores)
        val buscarJugadores : EditText = findViewById(R.id.barraBuscarJugadores)
        val lista : ListView = findViewById(R.id.jugadores)
        var itemSeleccionado : Jugador? = null
        val client=OkHttpClient()

        buscarJugadores.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            println("as")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                println("aasd")
            }

            override fun afterTextChanged(s: Editable?) {
                val buscar = buscarJugadores.text.toString()

                val request = Request.Builder()
                    .url("https://proyecyotfc.zeabur.app/jugadores/buscar?nombre=$buscar")
                    .build()

                client.newCall(request).enqueue(object :Callback{
                    override fun onFailure(call: Call, e: IOException) {
                        Log.d("Hola mundo", e.toString())
                    }

                    override fun onResponse(call: Call, response: Response) {

                        val responseData = response.body!!.string()
                        val jugadores = ArrayList<Jugador>()
                        try {
                            val jsonArray = JSONArray(responseData)
                            for (i in 0 until jsonArray.length()){
                                val jugadorJson = jsonArray.getJSONObject(i)
                                val idJugador = jugadorJson.getInt("id_jugador")
                                val nombre = jugadorJson.getString("nombre")
                                val fechaNacimiento = jugadorJson.getString("fechaNacimiento")
                                val idEquipo : Int?= jugadorJson.getInt("id_equipo")
                                val tarjetasAmarillas = jugadorJson.getInt("tarjetasAmarillas")
                                val tarjetasRojas = jugadorJson.getInt("tarjetasRojas")
                                val partidosJugados = jugadorJson.getInt("partidosJugados")
                                val goles = jugadorJson.getInt("goles")
                                val asistencias = jugadorJson.getInt("asistencias")
                                val imagen = jugadorJson.getString("imagen")
                                val codPosicion =  jugadorJson.getInt("cod_Posicion")
                                jugadores.add(
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
                                runOnUiThread {
                                    val adapter = JugadorAdaptadorLista(baseContext, jugadores)
                                    lista.adapter = adapter
                                }
                            }
                        }catch (e : JSONException){
                            e.printStackTrace()
                        }
                    }

                })

            }

        })
        var lastClickTime = 0L
        lista.onItemClickListener= AdapterView.OnItemClickListener { parent, view, position, id ->
            val doubleClickTimeDelta=300L
            val clickTime = System.currentTimeMillis()
            if (clickTime - lastClickTime < doubleClickTimeDelta) {
                 itemSeleccionado= lista.getItemAtPosition(position) as Jugador

                val intent = Intent(this@activity_buscar_jugadores, activity_jugador_estadisticas::class.java)
                intent.putExtra("jugador",itemSeleccionado)
                startActivity(intent)
            }
            lastClickTime = clickTime
        }

       /* button.setOnClickListener {
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
        }*/






    }

}