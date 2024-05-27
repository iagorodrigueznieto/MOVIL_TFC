package com.example.pruebas_tfg

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class activity_pantalla_de_carga : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_carga)
        val imageView= findViewById<ImageView>(R.id.cargarGif)
        val requestOptions= RequestOptions().frame(0)
        val handler= Handler()
        handler.postDelayed({
            val intent= Intent(this,activity_login::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}