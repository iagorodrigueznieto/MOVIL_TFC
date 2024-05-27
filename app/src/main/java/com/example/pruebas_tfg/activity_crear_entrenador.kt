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

class activity_crear_entrenador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_entrenador)
        val button = findViewById<Button>(R.id.crearEntrenador)
        val nombre = findViewById<EditText>(R.id.nombre)
        val nacionalidad = findViewById<EditText>(R.id.nacionalidad)
        val fechaNacimiento = findViewById<EditText>(R.id.fechaDeNacimiento)
        val client= OkHttpClient()
        val apellidos = findViewById<EditText>(R.id.apellidos)

        button.setOnClickListener {
            if (nombre.text.toString().isNotEmpty() && nacionalidad.text.toString()
                    .isNotEmpty() && fechaNacimiento.text.toString()
                    .isNotEmpty() && apellidos.text.toString().isNotEmpty()
            ) {
                if (fechaNacimiento.text.toString().esFormatoFechaValido()) {
                    val entrenador = Entrenador(

                        nombre.text.toString(),
                        apellidos.text.toString(),
                        nacionalidad.text.toString(),
                        fechaNacimiento.text.toString(),

                        )
                    val json = Gson().toJson(entrenador)
                    val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
                    val body = RequestBody.create(mediaType, json)
                   val request = Request.Builder().url("http://192.168.2.211:8080/entrenadores").post(body).build()
                    client.newCall(request).enqueue(object : Callback{
                        override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                            Toast.makeText(this@activity_crear_entrenador, "Error", Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(call: Call, response: Response) {
                            if (response.code==200){
                                runOnUiThread {
                                    Toast.makeText(baseContext,"Entrenador creado",Toast.LENGTH_SHORT).show()
                                }
                                finish()
                            }
                        }
                    })
                }else{
                    Toast.makeText(this, "Fecha no valida", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Faltan datos por introducir", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun String.esFormatoFechaValido(): Boolean {
        val patron = Regex("""^\d{4}/(0[1-9]|1[0-2])/([0-2][0-9]|3[01])$""")
        return patron.matches(this)
    }
}