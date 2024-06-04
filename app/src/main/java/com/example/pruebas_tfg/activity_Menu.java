package com.example.pruebas_tfg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.pruebas_tfg.Model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Locale;

import kotlin.jvm.internal.Intrinsics;

public class activity_Menu extends AppCompatActivity {
        protected void onCreate(@org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.setContentView(R.layout.activity_menu_principal);
            Intent intent = this.getIntent();
            View var10000 = this.findViewById(R.id.ImagenUsuario);
            Intrinsics.checkNotNullExpressionValue(var10000, "findViewById(R.id.ImagenUsuario)");
            ImageView imagen = (ImageView)var10000;
            Serializable var18 = intent.getSerializableExtra("usuario");
            if (!(var18 instanceof User)) {
                var18 = null;
            }
            SwitchCompat swLang = (SwitchCompat)this.findViewById(R.id.idioma);
            String savedLang = getSavedLanguage(this);
            changeLocale(getBaseContext(), savedLang);
            swLang.setChecked(!"es".equals(savedLang));

            swLang.setOnCheckedChangeListener((buttonView, isChecked) -> {
                String newLang = isChecked ? "gl" : "es";
                changeLocale(activity_Menu.this, newLang);
                recreate();
            });


            User usuario = (User)var18;
            TextView login = (TextView)this.findViewById(R.id.login);
            Button btnCrearEntrenador = (Button)this.findViewById(R.id.crearEntrenador);
            Button equipos = (Button)this.findViewById(R.id.btnEquipos);
            Button btnCrearJugador = (Button)this.findViewById(R.id.btnCrearJugador);
            Button clasificacion = (Button)this.findViewById(R.id.btnLiga);
            Button crearEquipo = (Button)this.findViewById(R.id.btnCrearEquipo);
            Button btnBuscarEntrenador = (Button)this.findViewById(R.id.BuscarEntrenador);

            if (usuario != null) {
                try {
                    CharSequence var13 = (CharSequence)usuario.getImagen();
                    if (var13.length() != 0) {
                        File file = new File(usuario.getImagen());
                        FileInputStream fi = new FileInputStream(file);
                        imagen.setImageBitmap(BitmapFactory.decodeStream((InputStream)fi));
                    }

                    login.setText((CharSequence)usuario.getLogin());
                } catch (Exception var15) {
                }
            }

            btnCrearEntrenador.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                public final void onClick(View it) {
                    Intent a = new Intent(activity_Menu.this.getBaseContext(), activity_crear_entrenador.class);
                    activity_Menu.this.startActivity(a);
                }
            }));
            btnCrearJugador.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                public final void onClick(View it) {
                    Intent a = new Intent(activity_Menu.this.getBaseContext(), activity_crear_jugador.class);
                    activity_Menu.this.startActivity(a);
                }
            }));
            clasificacion.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                public final void onClick(View it) {
                    Intent menu = new Intent(activity_Menu.this.getBaseContext(), activity_ver_ligas.class);
                    activity_Menu.this.startActivity(menu);
                }
            }));
            equipos.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                public final void onClick(View it) {
                    Intent a = new Intent(activity_Menu.this.getBaseContext(), activity_buscar_equipos.class);
                    activity_Menu.this.startActivity(a);
                }
            }));
            Button BuscarJugador = (Button)this.findViewById(R.id.btnJugadores);
            BuscarJugador.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                public final void onClick(View it) {
                    Intent a = new Intent(activity_Menu.this.getBaseContext(), activity_buscar_jugadores.class);
                    activity_Menu.this.startActivity(a);
                }
            }));
            crearEquipo.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                public final void onClick(View it) {
                    Intent a = new Intent(activity_Menu.this.getBaseContext(), activity_crear_equipo.class);
                    activity_Menu.this.startActivity(a);
                }
            }));
            btnBuscarEntrenador.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                public final void onClick(View it) {
                    Intent a = new Intent(activity_Menu.this.getBaseContext(), activity_buscar_entrenador.class);
                    activity_Menu.this.startActivity(a);
                }
            }));
        }

    private void changeLocale(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.createConfigurationContext(config);
        } else {
            resources.updateConfiguration(config, resources.getDisplayMetrics());
        }

        // Guarda el idioma seleccionado en las preferencias
        SharedPreferences sharedPref = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("language", language);
        editor.apply();
    }

    private String getSavedLanguage(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
        return sharedPref.getString("language", Locale.getDefault().getLanguage());
    }


}
