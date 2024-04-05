package com.example.pruebas_tfg

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.BBDD.UsuarioHelper

class ActivityRegistrarse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)
        val nombreUsuario = findViewById<EditText>(R.id.nombreUsuario)
        val contraseña = findViewById<EditText>(R.id.contraseña)
        val contraseña2 = findViewById<EditText>(R.id.contraseña2)
        val mail = findViewById<EditText>(R.id.mail)
        val db=UsuarioHelper(this,null)
        val registrarse = findViewById<Button>(R.id.btnRegistro)

        registrarse.setOnClickListener{

            if(nombreUsuario.text.isEmpty()||contraseña.text.isEmpty()||contraseña2.text.isEmpty()||mail.text.isEmpty()){
                Toast.makeText(baseContext, "No puede dejar vacío ninguno de los campos. ", Toast.LENGTH_LONG).show()
            }
            if(contraseña.text.toString() != contraseña2.text.toString()){
                Toast.makeText(baseContext, "Las contraseñas no son iguales, revisa alguna de ellas. ", Toast.LENGTH_LONG).show()
            }
            else{
                db.addName(nombreUsuario.text.toString(), contraseña.text.toString(), null.toString(), mail.text.toString())
                Toast.makeText(this, "Usuario insertado con éxito. ", Toast.LENGTH_LONG).show()
            }

        }
    }
}