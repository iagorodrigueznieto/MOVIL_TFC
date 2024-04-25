package com.example.pruebas_tfg

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Adapter.LigaAdaptadorLista
import com.example.pruebas_tfg.Model.Liga
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class activityVerLigas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var liga :Liga
        setContentView(R.layout.activity_ligas)
        val lista = findViewById<ListView>(R.id.listaJugadores)
        val client = OkHttpClient()
        val request= Request.Builder().url("http://192.168.2.211:8080/liga").build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Error al realizar la solicitud", e.message!!)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful && response.body != null) {
                    val responseData = response.body!!.string()
                    val ligas = ArrayList<Liga>()
                    try {
                        val jsonArray = JSONArray(responseData)
                        for (i in 0 until jsonArray.length()) {
                            val jugadorJson = jsonArray.getJSONObject(i)
                            val codLiga = jugadorJson.getInt("codLiga")
                            val nombre = jugadorJson.getString("nombre")
                            val nacional = jugadorJson.getBoolean("nacional")

                            ligas.add(
                                Liga(
                                    codLiga,
                                    nombre,
                                    nacional
                                )
                            )
                        }

                        runOnUiThread {
                            val adapter = LigaAdaptadorLista(baseContext, ligas)
                            lista.adapter = adapter
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    var lastClickTime = 0L

                   lista.onItemClickListener= AdapterView.OnItemClickListener { parent, view, position, id ->
                    val doubleClickTimeDelta=300L
                       val clickTime = System.currentTimeMillis()
                       if (clickTime - lastClickTime < doubleClickTimeDelta) {
                           liga = lista.getItemAtPosition(position) as Liga

                           val intent = Intent(this@activityVerLigas, ActivityClasificacionLiga::class.java)
                           intent.putExtra("liga",liga)
                           startActivity(intent)
                       }
                       lastClickTime = clickTime
                   }



                } else {
                    println("No se pudo completar la solicitud")
                }
            }
        })
    }
}
