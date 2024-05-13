package com.example.pruebas_tfg.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pruebas_tfg.Model.Entrenador;
import com.example.pruebas_tfg.R;

import java.util.List;

public class EntrenadorListaAdapter extends BaseAdapter {

    private Context context;
    private List<Entrenador> entrenadores;

    public EntrenadorListaAdapter(Context context, List<Entrenador> entrenadores) {
        super();
        this.context = context;
        this.entrenadores = entrenadores;
    }

    @Override
    public int getCount() {
        return entrenadores.size();
    }

    @Override
    public Object getItem(int position) {
        return entrenadores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mostrado = LayoutInflater.from(context);
        View view = mostrado.inflate(R.layout.list_item_entrenador, null);
        TextView nombre = view.findViewById(R.id.nombreEntrenador);
        nombre.setText(entrenadores.get(position).getNombre()+" "+entrenadores.get(position).getApellido());
        return view;
    }
}
