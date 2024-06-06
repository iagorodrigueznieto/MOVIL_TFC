package com.example.pruebas_tfg;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pruebas_tfg.Model.User;
import com.google.gson.Gson;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class activity_registrarse extends AppCompatActivity {
    private static final int CODIGO_ACTIVIDAD_SECUNDARIA = 1;
    String ruta="";

    EditText login;
    EditText contrasenia;
    EditText contraseña;
    EditText mail;
    ImageView imagen;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
         login=findViewById(R.id.nombreUsuario);
         contrasenia=findViewById(R.id.contraseña);
        contraseña=findViewById(R.id.contraseña2);
        mail=findViewById(R.id.mail);
        Button registrarse=findViewById(R.id.btnRegistro);
        Button selImagen=findViewById(R.id.selImagen);



        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contrasenia.getText().toString().isEmpty()||contraseña.getText().toString().isEmpty()||login.getText().toString().isEmpty()||mail.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(), "Por favor, rellene todos los campos", Toast.LENGTH_LONG).show();
                    return;
                }
                if(ruta.isEmpty()){
                    Toast.makeText(getBaseContext(), "Por favor, seleccione una imagen", Toast.LENGTH_LONG).show();
                    return;
                }
                User user=new User(login.getText().toString(), mail.getText().toString(),contrasenia.getText().toString(),2, ruta);
                Gson gson=new Gson();
                String json=gson.toJson(user);
                String url="https://proyecyotfc.zeabur.app/usuarios";
                MediaType mediaType=MediaType.parse("application/json; charset=utf-8");
                RequestBody body=RequestBody.create(json, mediaType);
                Request request=new Request.Builder().url(url).post(body).build();
                OkHttpClient client=new OkHttpClient();


                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(response.code()==200){
                                    Toast.makeText(getBaseContext(),"Usuario creado correctamente",Toast.LENGTH_LONG).show();
                                    finish();
                                }
                                else{
                                    Toast.makeText(getBaseContext(), "El usuario ya existe, por lo que no se puede insertar", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                });

                Intent a=new Intent(getBaseContext(), activity_login.class);
                startActivity(a);



            }

        });

        selImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), activity_seleccion_imagen.class);
                startActivityForResult(intent,CODIGO_ACTIVIDAD_SECUNDARIA);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagen=findViewById(R.id.imagen);
       if((requestCode==CODIGO_ACTIVIDAD_SECUNDARIA)&&(resultCode==RESULT_OK)){
           try{
               if(data.getDataString()!=null){
                   ruta=data.getDataString();
                   FileInputStream inputStream=new FileInputStream(new File(data.getDataString()));
                   Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                   imagen.setImageBitmap(bitmap);
               }
           }catch (Exception e){
               System.out.println("No se pudo seleccionar ninguna imagen. ");
           }
       }
    }

}
