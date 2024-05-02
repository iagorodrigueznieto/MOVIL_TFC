package com.example.pruebas_tfg

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.pruebas_tfg.Model.User
import java.io.File
import java.io.FileInputStream

class activity_menu_principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
        val intent = intent
        val imagen: ImageView = findViewById(R.id.ImagenUsuario)
        val usuario: User? = intent.getSerializableExtra("usuario") as? User
        val login = findViewById<TextView>(R.id.login)
        val btnCrearLiga = findViewById<Button>(R.id.btnCrearLigas)
        val crearEquipo= findViewById<Button>(R.id.btnCrearEquipo)
        if (usuario != null) {
            if (usuario.cod_Rol!=1){
                btnCrearLiga.isVisible=false
            }
        }
        if (usuario != null) {

            try {
                if (usuario.imagen.isEmpty()){
                    val file : File = File(usuario.imagen)
                    val fi=FileInputStream(file)
                    imagen.setImageBitmap(BitmapFactory.decodeStream(fi))
                }

                login.setText(usuario.login)


            } catch (Exception: Exception) {

            }


        }
        var clasificacion = findViewById<Button>(R.id.btnLiga)
        clasificacion.setOnClickListener {
            var menu = Intent(baseContext, activity_ver_ligas::class.java)
            startActivity(menu)
        }
        val equipos = findViewById<Button>(R.id.btnEquipos)
        equipos.setOnClickListener {
            val a = Intent(baseContext, activity_equipos_Estadisticas::class.java)
            startActivity(a)
        }
        var BuscarJugador= findViewById<Button>(R.id.btnJugadores)
        BuscarJugador.setOnClickListener {
            val a = Intent(baseContext, activity_Jugador_Estadistica::class.java)
            startActivity(a)
        }

        crearEquipo.setOnClickListener {
            val a= Intent(baseContext, activity_crear_equipo::class.java)
            startActivity(a)
        }

    }
}