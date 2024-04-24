package com.example.pruebas_tfg

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Adapter.ImagenAdapter
import java.io.File
import java.util.*

class activity_seleccion_imagen : AppCompatActivity() {
    var imagenSeleccionada: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccion_imagen)
        val imagenes = File("/data/data/com.example.pruebas_tfg/files/files")
        val arra = ArrayList<File>()
        val array = imagenes.listFiles()
        val lista = findViewById<ListView>(R.id.listaImagenes)
        val button = findViewById<Button>(R.id.seleccionarImagen)
        arra.addAll(Arrays.asList(*array))
        lista.adapter = ImagenAdapter(this, arra)

        lista.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            imagenSeleccionada = lista.getItemAtPosition(position) as File
        }

        button.setOnClickListener {
            if (imagenSeleccionada != null) {
                val intent = Intent()
                intent.setData(Uri.parse(imagenSeleccionada!!.path))
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(baseContext, "No se ha seleccionado ninguna imagen", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
