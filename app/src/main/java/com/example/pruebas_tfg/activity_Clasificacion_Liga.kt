package com.example.pruebas_tfg

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Adapter.EquiposAdapterLiga
import com.example.pruebas_tfg.Model.Equipo
import com.example.pruebas_tfg.Model.Liga
import com.example.pruebas_tfg.dto.ClasificacionInputDto
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class activity_Clasificacion_Liga : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clasificacion_liga)
        val listView = findViewById<ListView>(R.id.ListaEquipos)
        val client = OkHttpClient()
        val arrayEquipos = ArrayList<ClasificacionInputDto>()
        var itemSeleccionado :ClasificacionInputDto? = null
        val liga: Liga? = intent.getSerializableExtra("liga") as Liga?
        val nombreLiga = findViewById<TextView>(R.id.nombreLiga)
        if (liga != null) {
            nombreLiga.setText(liga.nombre)
            val request = Request.Builder()
                .url("http://192.168.2.211:8080/equipos/clasificacion?codLiga=" + (liga.codLiga))
                .build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val body = response.body
                        val jsonString = body?.string()
                        val gson = Gson()
                        val equipos = gson.fromJson(jsonString, Array<ClasificacionInputDto>::class.java).toList()
                        for (i in equipos.indices) {
                            arrayEquipos.add(equipos[i])
                        }
                        runOnUiThread {
                            val adapter = EquiposAdapterLiga(baseContext, arrayEquipos)
                            listView.adapter = adapter
                        }


                    }
                }
            })
        }
        var lastClickTime = 0L
        listView.setOnItemClickListener { parent, view, position, id ->

            val doubleClickTime = 300L
            val clickTime = System.currentTimeMillis()
            if (clickTime - lastClickTime < doubleClickTime) {
                itemSeleccionado = listView.getItemAtPosition(position) as ClasificacionInputDto
                val intent = Intent(baseContext, activity_info_1_equipo_1_liga::class.java)
                intent.putExtra("liga",liga)
                intent.putExtra("equipo",itemSeleccionado)
                startActivity(intent)
            }
            lastClickTime = clickTime

        }
    }
}