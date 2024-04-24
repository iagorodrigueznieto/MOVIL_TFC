package com.example.pruebas_tfg.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.pruebas_tfg.Model.Equipo;
import com.example.pruebas_tfg.R;

import java.util.ArrayList;

public class EquiposAdapterLiga extends BaseAdapter {

    private Context context;
    private ArrayList<Equipo> equipos;


    public EquiposAdapterLiga(Context context, ArrayList<Equipo> equipos) {
        super();
        this.context = context;
        this.equipos = equipos;
    }

    @Override
    public int getCount() {
        return equipos.size();
    }

    @Override
    public Object getItem(int position) {
        return equipos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mostreado=LayoutInflater.from(context);
        View vista=mostreado.inflate(R.layout.list_item_equipos_ligas,null);
        TextView nombre=vista.findViewById(R.id.nombreEquipo);
        nombre.setText(equipos.get(position).getNombreEquipo());
        return vista;
    }
}
