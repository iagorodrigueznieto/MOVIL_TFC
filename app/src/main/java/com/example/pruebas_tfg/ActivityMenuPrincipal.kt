package com.example.pruebas_tfg

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Model.User

class ActivityMenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menuprincipal)
        val intent = intent
        val usuario: User? = intent.getSerializableExtra("usuario") as? User
        val login = findViewById<TextView>(R.id.login)
        if (usuario != null) {
            login.setText(usuario.login)
        }
        var clasificacion = findViewById<Button>(R.id.btnLiga)
        clasificacion.setOnClickListener {
            var menu = Intent(baseContext, activityClasificacionLiga::class.java)
            startActivity(menu)
        }

    }
}