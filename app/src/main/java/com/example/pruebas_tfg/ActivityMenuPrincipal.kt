package com.example.pruebas_tfg

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Model.User
import java.io.File
import java.io.FileInputStream

class ActivityMenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menuprincipal)
        val intent = intent
        val imagen: ImageView = findViewById(R.id.ImagenUsuario)
        val usuario: User? = intent.getSerializableExtra("usuario") as? User
        val login = findViewById<TextView>(R.id.login)
        if (usuario != null) {
            try {

                val file : File = File(usuario.imagen)
                val fi=FileInputStream(file)
                imagen.setImageBitmap(BitmapFactory.decodeStream(fi))
                login.setText(usuario.login)


            } catch (Exception: Exception) {

            }


        }
        var clasificacion = findViewById<Button>(R.id.btnLiga)
        clasificacion.setOnClickListener {
            var menu = Intent(baseContext, activityVerLigas::class.java)
            startActivity(menu)
        }

    }
}