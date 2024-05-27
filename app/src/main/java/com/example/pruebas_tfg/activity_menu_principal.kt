package com.example.pruebas_tfg

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
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
        val btnCrearEntrenador = findViewById<Button>(R.id.crearEntrenador)

        val equipos = findViewById<Button>(R.id.btnEquipos)
        val btnCrearJugador = findViewById<Button>(R.id.btnCrearJugador)
        var clasificacion = findViewById<Button>(R.id.btnLiga)
        val crearEquipo= findViewById<Button>(R.id.btnCrearEquipo)
        val btnBuscarEntrenador = findViewById<Button>(R.id.BuscarEntrenador)
        if (usuario != null) {

        }
        if (usuario != null) {

            try {
                if (!usuario.imagen.isEmpty()){
                    val file : File = File(usuario.imagen)
                    val fi=FileInputStream(file)
                    imagen.setImageBitmap(BitmapFactory.decodeStream(fi))
                }

                login.setText(usuario.login)


            } catch (Exception: Exception) {

            }


        }
        btnCrearEntrenador.setOnClickListener {
            val a = Intent(baseContext, activity_crear_entrenador::class.java)
            startActivity(a)
        }
        btnCrearJugador.setOnClickListener {
            val a = Intent(baseContext, activity_crear_jugador::class.java)
            startActivity(a)
        }
        clasificacion.setOnClickListener {
            var menu = Intent(baseContext, activity_ver_ligas::class.java)
            startActivity(menu)
        }

        equipos.setOnClickListener {
            val a = Intent(baseContext, activity_buscar_equipos::class.java)
            startActivity(a)
        }
        var BuscarJugador= findViewById<Button>(R.id.btnJugadores)
        BuscarJugador.setOnClickListener {
            val a = Intent(baseContext, activity_buscar_jugadores::class.java)
            startActivity(a)
        }

        crearEquipo.setOnClickListener {
            val a= Intent(baseContext, activity_crear_equipo::class.java)
            startActivity(a)
        }

        btnBuscarEntrenador.setOnClickListener {
            val a= Intent(baseContext, activity_buscar_entrenador::class.java)
            startActivity(a)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal,menu)
        return super.onCreateOptionsMenu(menu)
    }
}