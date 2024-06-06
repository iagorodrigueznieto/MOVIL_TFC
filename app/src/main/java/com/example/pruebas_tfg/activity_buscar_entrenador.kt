package com.example.pruebas_tfg

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Adapter.EntrenadorListaAdapter
import com.example.pruebas_tfg.Model.Entrenador
import com.example.pruebas_tfg.Model.Jugador
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class activity_buscar_entrenador : AppCompatActivity () {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_lista_entrenadores)
        val client = OkHttpClient()
        val lista =findViewById<ListView>(R.id.ListaEntrenadores)
        val editText = findViewById<EditText>(R.id.BuscarEntrenador)
        var itemseleccionado : Entrenador ?= null

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val buscar = editText.text.toString()
                val request2 = Request.Builder().url("https://proyecyotfc.zeabur.app/entrenadores/nombre?nombre=$buscar").build()
                client.newCall(request2).enqueue(object : Callback{
                    override fun onFailure(call: Call, e: IOException) {

                    }

                    override fun onResponse(call: Call, response: Response) {
                        if(response.code==200){
                            val json = response.body!!.string()
                            val listaEntrenadores = Gson().fromJson(json, Array<Entrenador>::class.java).toList()
                            runOnUiThread {
                                lista.adapter = EntrenadorListaAdapter(this@activity_buscar_entrenador, listaEntrenadores)
                            }
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
                itemseleccionado = lista.getItemAtPosition(position) as Entrenador
                val intent = Intent(baseContext, activity_info_entrenador::class.java)
                intent.putExtra("entrenador",itemseleccionado)
                startActivity(intent)
            }
            lastClickTime = clickTime
        }

    }
}