package com.example.pruebas_tfg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pruebas_tfg.Model.User
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class activity_login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var registrarse = findViewById<Button>(R.id.registrarse)
        var login = findViewById<Button>(R.id.login)
        var input_usuario = findViewById<EditText>(R.id.input_usuario)
        var input_contraseña = findViewById<EditText>(R.id.input_contraseña)
        val client = OkHttpClient()
        login.setOnClickListener {
            if (input_contraseña.text.isEmpty() || input_usuario.text.isEmpty()) {
                Toast.makeText(this, "No se puede dejar ningún campo vacío.", Toast.LENGTH_LONG).show()
            } else {
                val request = Request.Builder()
                    .url("http://192.168.2.211:8080/usuarios/login?login=" + input_usuario.text.toString() + "&password=" + input_contraseña.text.toString())
                    .build()
                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        Log.e("Error al realizar la solicitud. ", e.message.toString())
                    }

                    override fun onResponse(call: Call, response: Response) {
                        if (!response.isSuccessful || response.code == 350) {
                            runOnUiThread {
                                Toast.makeText(baseContext, "Error al encontrar el usuario.", Toast.LENGTH_LONG)
                                    .show()
                            }
                            return
                        } else {
                            val body = response.body?.string()
                            val gson = Gson()
                            val user: User = gson.fromJson(body, User::class.java)
                            runOnUiThread {
                                Toast.makeText(
                                    baseContext,
                                    "Usuario encontrado, entrando en la aplicación...",
                                    Toast.LENGTH_LONG
                                ).show()
                                var a = Intent(applicationContext, activity_menu_principal::class.java)
                                a.putExtra("usuario",user)
                                startActivity(a)
                            }
                        }
                    }
                }
                )
            }

        }

        registrarse.setOnClickListener {
            var a = Intent(baseContext, activity_registrarse::class.java)
            startActivity(a)
        }
    }
}