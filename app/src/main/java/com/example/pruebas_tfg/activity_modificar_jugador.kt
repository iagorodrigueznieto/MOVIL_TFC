package com.example.pruebas_tfg

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebas_tfg.Model.Jugador
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody

class activity_modificar_jugador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_jugador)
        val intent = intent
        val jugador = intent.getSerializableExtra("jugador") as Jugador
        val client  = OkHttpClient()
        val spinner = findViewById<Spinner>(R.id.SpinnerEquipo)
        val btn = findViewById<Button>(R.id.btnmodificarJugador)
        val spinnerPosicion = findViewById<Spinner>(R.id.spinnerPosicion)
        val array  = arrayOf("Portero","Defensa","Mediocentro","Delantero")
        spinnerPosicion.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,array )

        val nombre = findViewById<EditText>(R.id.nombreJugador)
        val goles = findViewById<EditText>(R.id.goles)
        val asistencias = findViewById<EditText>(R.id.asistencias)
        val tarjetasAmarillas = findViewById<EditText>(R.id.tarjetasAmarillas)
        val tarjetasRojas=findViewById<EditText>(R.id.tarjetasRojas)
        val chexkBox  : CheckBox = findViewById(R.id.checkbox)

        if(jugador!=null){
            goles.setText(jugador.goles.toString())
            nombre.setText(jugador.nombre.toString())
            asistencias.setText(jugador.asistencias.toString())
            tarjetasAmarillas.setText(jugador.tarjetasAmarillas.toString())
            tarjetasRojas.setText(jugador.tarjetasRojas.toString())
        }

        btn.setOnClickListener {
            if(chexkBox.isChecked){
             jugador.idEquipo = null
            }
            jugador.idJugador = jugador.idJugador
            jugador.nombre = nombre.text.toString()
            jugador.goles = goles.text.toString().toInt()
            jugador.asistencias = asistencias.text.toString().toInt()
            jugador.tarjetasAmarillas = tarjetasAmarillas.text.toString().toInt()
            jugador.tarjetasRojas = tarjetasRojas.text.toString().toInt()
            jugador.partidosJugados = tarjetasRojas.text.toString().toInt()
            val json = Gson().toJson(jugador)
            val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())
            val request = okhttp3.Request.Builder()
                .url("http://192.168.2.211:8080/jugadores")
                .put(requestBody)
                .build()

            client.newCall(request).enqueue(object : okhttp3.Callback {
                override fun onFailure(call: okhttp3.Call, e: java.io.IOException) {
                    runOnUiThread{
                        Toast.makeText(baseContext, "Error al modificar jugador", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    if(response.code == 200){
                        runOnUiThread {
                            Toast.makeText(baseContext, "Jugador modificado", Toast.LENGTH_SHORT).show()
                            finish()
                        }

                    }
                }

            })


        }


        spinner.onItemSelectedListener = object : AdapterView
                .OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (array[position]=="Portero"){
                    jugador.codPosicion = 1
                }else if(array[position]=="Defensa"){
                    jugador.codPosicion = 2

                }else if (array[position]=="Mediocentro"){
                    jugador.codPosicion = 3

                }else if (array[position]=="Delantero"){
                    jugador.codPosicion = 4

                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }



    }

}