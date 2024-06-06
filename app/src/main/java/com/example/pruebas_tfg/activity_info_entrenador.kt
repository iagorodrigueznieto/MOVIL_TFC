package com.example.pruebas_tfg

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Model.Entrenador
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class activity_info_entrenador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_entrenador)
        val nacionalidad = findViewById<TextView>(R.id.nacionalidad)
        val nombre = findViewById<TextView>(R.id.nombre)
        val client = OkHttpClient()
        val apellido = findViewById<TextView>(R.id.apellido)
        val fechaNacimiento = findViewById<TextView>(R.id.fechaDeNacimiento)
        val entrenador = intent.getSerializableExtra("entrenador") as Entrenador
        nacionalidad.text = entrenador.nacionalidad
        nombre.text = entrenador.nombre
        apellido.text = entrenador.apellido
        fechaNacimiento.text = entrenador.fechaDeNacimiento
        val button = findViewById<Button>(R.id.modificar)
        button.setOnClickListener{
            val intent = Intent(this, activity_modificar_entrenador::class.java)
            intent.putExtra("entrenador", entrenador)
            startActivity(intent)
        }
        val eliminar = findViewById<Button>(R.id.btnEliminar)
        eliminar.setOnClickListener{
          var entrenadoresLibres: List<Entrenador> = ArrayList()
          val request= Request.Builder().url("https://proyecyotfc.zeabur.app/entrenadores/libres").build()
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                    val json = response.body?.string()
                    entrenadoresLibres = Gson().fromJson(json, Array<Entrenador>::class.java).toList()
                        val request2 = Request.Builder().url("https://proyecyotfc.zeabur.app/entrenadores?id=${entrenador.idEntrenador}").delete().build()
                        client.newCall(request2).enqueue(object : Callback{
                            override fun onResponse(call: Call, response: Response) {
                                if(response.code==200){
                                    runOnUiThread {
                                        Toast.makeText(this@activity_info_entrenador, "Entrenador Eliminado", Toast.LENGTH_SHORT).show()
                                    }
                                    finish()
                                }else{
                                    runOnUiThread {
                                        Toast.makeText(this@activity_info_entrenador, "Este entrenador está en un equipo. ", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                            override fun onFailure(call: Call, e: IOException) {

                            }
                        })
                }
            })




        }


    }
}