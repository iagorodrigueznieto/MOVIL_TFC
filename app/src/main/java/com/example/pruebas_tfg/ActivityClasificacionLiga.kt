package com.example.pruebas_tfg

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Model.Equipo
import com.example.pruebas_tfg.Model.Liga
import okhttp3.*
import java.io.IOException

class ActivityClasificacionLiga : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clasificacion_liga)
        val client= OkHttpClient()
        val equipos = ArrayList<Equipo>()
        val liga : Liga?  = intent.getSerializableExtra("liga") as Liga?
        val nombreLiga =findViewById<TextView>(R.id.nombreLiga)
        if (liga != null) {
            nombreLiga.setText(liga.nombre)
            val request = Request.Builder()
                .url("http://192.168.2.211:8080/equipos/clasificacion?codLiga="+ (liga.codLiga))
                .build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }
                override fun onResponse(call: Call, response: Response) {

                }
            })
        }




    }
}