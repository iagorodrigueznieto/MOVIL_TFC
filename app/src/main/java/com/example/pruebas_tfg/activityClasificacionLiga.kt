package com.example.pruebas_tfg

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Model.Equipo
import com.example.pruebas_tfg.Model.Jugador
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.time.LocalDate


class activityClasificacionLiga : AppCompatActivity() {
    lateinit var lista: ListView
    var arrayList_details: ArrayList<Jugador> = ArrayList();

    val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clasificacion_liga)
        lista = findViewById<ListView>(R.id.listaJugadores)
        val objAdapter: com.example.pruebas_tfg.Adapter.ListAdapter
        llamada("http://192.168.2.211:8080/jugadores")

    }

    fun llamada(url: String) {
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, ca: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                var str_response = response.body()!!.string()
                var json: JSONObject = JSONObject(str_response)

                var jsonarray_info: JSONArray = json.getJSONArray("")

                var i: Int = 0
                var size: Int = jsonarray_info.length()
                arrayList_details = ArrayList()
                for (i in 0..size - 1) {
                    var json_Objectdetail: JSONObject = jsonarray_info.getJSONObject(i)
                    var jugador: Jugador = Jugador(
                        Integer.parseInt(json_Objectdetail.getString("id")),
                        json_Objectdetail.getString("nombre"),
                        json_Objectdetail.getString("apellidos"),
                        LocalDate.parse("2007-02-02"),
                        Equipo(1, "Deportivo", "Coruña", 1, 2, 3, 4, "2"),
                        2,
                        2,
                        4,
                        2,
                        2,
                        ""
                    )
                    arrayList_details.add(jugador)

                }
                runOnUiThread {
                    val objAdapter = com.example.pruebas_tfg.Adapter.ListAdapter(applicationContext, arrayList_details)
                    lista.adapter = objAdapter
                }

            }
        }
        )
    }
}