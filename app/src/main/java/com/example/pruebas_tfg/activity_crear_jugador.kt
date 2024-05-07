package com.example.pruebas_tfg

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Adapter.BuscarEquiposAdapterLista
import com.example.pruebas_tfg.Adapter.SpinnerEntrenadorAdapter
import com.example.pruebas_tfg.Adapter.SpinnerEquiposAdapter
import com.example.pruebas_tfg.Model.Equipo
import com.example.pruebas_tfg.Model.Jugador
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class activity_crear_jugador : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_jugador)
        var itemSeleccionado : Equipo? = null
        val nombreJugador : EditText = findViewById(R.id.nombreJugador)
        val tarjetasAmarillas : EditText= findViewById(R.id.tarjetas_amarillas)
        val tarjetasRojas: EditText=findViewById(R.id.Tarjetas_Rojas)
        val Goles:EditText=findViewById(R.id.Goles)
        val asistencias:EditText=findViewById(R.id.asistencias)
        val spinner: Spinner=findViewById(R.id.spinnerEquipo)
        val btn:Button=findViewById(R.id.btnCrearJugador)
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("http://192.168.2.211:8080/equipos")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("Hola mundo", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {

                val responseData = response.body!!.string()
                val jugadores = ArrayList<Equipo>()
                try {
                    val jsonArray = JSONArray(responseData)
                    for (i in 0 until jsonArray.length()) {
                        val equipoJson = jsonArray.getJSONObject(i)
                        val idEquipo = equipoJson.getInt("idEquipo")
                        val nombreEquipo = equipoJson.getString("nombreEquipo")
                        val ciudad = equipoJson.getString("ciudad")
                        val idEntrenador = equipoJson.getInt("idEntrenador")
                        val imagen = equipoJson.getString("imagen")

                        jugadores.add(
                            Equipo(
                                idEquipo,
                                nombreEquipo,
                                ciudad,
                                idEntrenador,
                                imagen
                            )
                        )
                        runOnUiThread {
                            val adapter = SpinnerEquiposAdapter(baseContext, jugadores)
                            spinner.adapter = adapter
                        }
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

        })
        btn.setOnClickListener {
            val gson = Gson()
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                if(parent != null){
                    itemSeleccionado = parent.getItemAtPosition(position) as Equipo
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                    print("")
            }

        }
    }
}



