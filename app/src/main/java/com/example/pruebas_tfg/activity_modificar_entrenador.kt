package com.example.pruebas_tfg

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Model.Entrenador
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class activity_modificar_entrenador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_entrenador)
        val entrenador = intent.getSerializableExtra("entrenador") as Entrenador
        val client = OkHttpClient()
        val nombre = findViewById<EditText>(R.id.nombre)
        val apellido = findViewById<EditText>(R.id.apellidos)
        val nacionalidad = findViewById<EditText>(R.id.nacionalidad)
        val fechaNacimiento = findViewById<EditText>(R.id.fechaDeNacimiento)
        val button = findViewById<Button>(R.id.btnModificar)
        nombre.setText(entrenador.nombre)
        apellido.setText(entrenador.apellido)
        nacionalidad.setText(entrenador.nacionalidad)
        fechaNacimiento.setText(entrenador.fechaDeNacimiento)

        button.setOnClickListener {
            if(nombre.text.isEmpty() || apellido.text.isEmpty() || nacionalidad.text.isEmpty() || fechaNacimiento.text.isEmpty()){
                Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show()
            }else{
                val nuevo = Entrenador(
                    entrenador.idEntrenador,
                    nombre.text.toString(),
                    apellido.text.toString(),
                    nacionalidad.text.toString(),
                    fechaNacimiento.text.toString()
                )
                val json = Gson().toJson(nuevo)
                val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
                val body = RequestBody.create(mediaType, json)
                val request = Request.Builder().url("https://proyecyotfc.zeabur.app/entrenadores").put(body).build()
                client.newCall(request).enqueue(object : Callback{
                    override fun onFailure(call: Call, e: IOException) {

                    }

                    override fun onResponse(call: Call, response: Response) {
                        if(response.code == 200){
                            runOnUiThread {
                                Toast.makeText(this@activity_modificar_entrenador, "Entrenador modificado", Toast.LENGTH_SHORT).show()
                            }
                            finish()
                        }
                    }
                })
            }
        }
    }
}