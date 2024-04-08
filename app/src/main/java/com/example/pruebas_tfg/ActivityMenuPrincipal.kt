package com.example.pruebas_tfg

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ActivityMenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menuprincipal)
        var clasificacion = findViewById<Button>(R.id.btnLiga)
        clasificacion.setOnClickListener {
            var menu = Intent(baseContext, activityClasificacionLiga::class.java)
            startActivity(menu)
        }
    }
}