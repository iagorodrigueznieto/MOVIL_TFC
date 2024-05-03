package com.example.pruebas_tfg.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pruebas_tfg.Model.Entrenador;
import com.example.pruebas_tfg.Model.Equipo;

import java.util.ArrayList;

public class SpinnerEquiposAdapter extends ArrayAdapter<Equipo> {
    private Context context;
    private ArrayList<Equipo> equipos;

    public SpinnerEquiposAdapter(Context context, ArrayList<Equipo> equipos) {
        super(context, android.R.layout.simple_spinner_item, equipos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=super.getView(position, convertView, parent);
        Equipo equipo=getItem(position);
        ((TextView) view.findViewById(android.R.id.text1)).setText(equipo.getNombreEquipo());
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=super.getDropDownView(position, convertView, parent);
        Equipo equipo=getItem(position);
        ((TextView) view.findViewById(android.R.id.text1)).setText(equipo.getNombreEquipo());
        return view;
    }
}
