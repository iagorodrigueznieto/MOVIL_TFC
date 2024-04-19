package com.example.pruebas_tfg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.pruebas_tfg.BBDD.UsuarioHelper
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var registrarse = findViewById<Button>(R.id.registrarse)
        var login = findViewById<Button>(R.id.login)
        var input_usuario = findViewById<EditText>(R.id.input_usuario)
        var input_contraseña = findViewById<EditText>(R.id.input_contraseña)
        val client = OkHttpClient()
        val request= Request.Builder().url("http://192.168.2.211:8080/usuarios/login"+"?"+"").build()
        val db = UsuarioHelper(this, null)

        login.setOnClickListener {
            if (input_contraseña.text.isEmpty() || input_usuario.text.isEmpty()) {
                Toast.makeText(this, "No se puede dejar ningún campo vacío.", Toast.LENGTH_LONG).show()
            } else {
                    client.newBuilder()
            }

        }

        registrarse.setOnClickListener {
            var a = Intent(baseContext, ActivityRegistrarse::class.java)
            startActivity(a)
        }
    }
}