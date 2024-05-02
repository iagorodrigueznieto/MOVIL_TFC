package com.example.pruebas_tfg.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pruebas_tfg.Model.Entrenador;

import java.util.ArrayList;

public class SpinnerEntrenadorAdapter extends ArrayAdapter<Entrenador> {

    private Context context;
    private ArrayList<Entrenador> entrenadores;

    public SpinnerEntrenadorAdapter(Context context, ArrayList<Entrenador> entrenadores) {
        super(context, android.R.layout.simple_spinner_item, entrenadores);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=super.getView(position, convertView, parent);
        Entrenador entrenador=getItem(position);
        ((TextView) view.findViewById(android.R.id.text1)).setText(entrenador.getNombre());
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=super.getDropDownView(position, convertView, parent);
        Entrenador entrenador=getItem(position);
        ((TextView) view.findViewById(android.R.id.text1)).setText(entrenador.getNombre());
        return view;
    }
}
