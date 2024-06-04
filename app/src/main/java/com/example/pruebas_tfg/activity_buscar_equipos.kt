package com.example.pruebas_tfg

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Adapter.BuscarEquiposAdapterLista
import com.example.pruebas_tfg.Adapter.EquiposAdapterLiga
import com.example.pruebas_tfg.Adapter.JugadorAdaptadorLista
import com.example.pruebas_tfg.Adapter.ListaNormalEquiposAdapter
import com.example.pruebas_tfg.Model.Equipo
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

class activity_buscar_equipos: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_equipos)
        var itemSeleccionado : Equipo? = null
        val client = OkHttpClient()
        val buscar = findViewById<EditText>(R.id.etBuscarEquipo)
        val lista = findViewById<ListView>(R.id.listaEquipos)
        buscar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                print("iiiioi")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                print("hhh")
            }

            override fun afterTextChanged(s: Editable?) {
                val buscar2 = buscar.text.toString()

                val request = Request.Builder()
                    .url("http://192.168.2.211:8080/equipos/nombre?nombre=$buscar2")
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
                                val presidente = equipoJson.getString("presidente")
                                if(idEquipo!=3333){
                                    jugadores.add(
                                        Equipo(
                                            idEquipo,
                                            nombreEquipo,
                                            ciudad,
                                            idEntrenador,
                                            imagen,
                                            presidente
                                        )
                                    )
                                }
                                runOnUiThread {
                                    val adapter = ListaNormalEquiposAdapter(baseContext, jugadores)
                                    lista.adapter = adapter
                                }
                            }
                        } catch (e: JSONException) {
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
                itemSeleccionado= lista.getItemAtPosition(position) as Equipo
                val intent = Intent(baseContext, activity_info_equipo::class.java)
                intent.putExtra("equipo",itemSeleccionado)
                startActivity(intent)
            }
            lastClickTime = clickTime
        }
    }
}
