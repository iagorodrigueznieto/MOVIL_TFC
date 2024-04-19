package com.example.pruebas_tfg.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.pruebas_tfg.Model.Jugador
import com.example.pruebas_tfg.R

class AdaptadorLista(
    private val context: Context,
    private val jugadores: List<Jugador>
) : BaseAdapter() {
    override fun getCount(): Int {
        return jugadores.size
    }

    override fun getItem(position: Int): Any {
        return jugadores.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View
        val viewHolder : ViewHolder
        if(convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false)
            viewHolder =ViewHolder(view)
            view.tag =viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val jugador = jugadores.get(position)
        viewHolder.nombreTextView.text=jugador.nombre
        viewHolder.edadTextView.text=Integer.toString(jugador.goles)
        return view
    }

    private class ViewHolder(view: View) {
        val nombreTextView: TextView = view.findViewById(R.id.nombreTextView)
        val edadTextView: TextView = view.findViewById(R.id.edadTextView)
    }

}