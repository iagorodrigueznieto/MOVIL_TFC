package com.example.pruebas_tfg.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.pruebas_tfg.Model.Liga
import com.example.pruebas_tfg.R

class LigaAdaptadorLista(private val context: Context, private val ligas: ArrayList<Liga>) : BaseAdapter() {
    override fun getCount(): Int {
        return ligas.size
    }

    override fun getItem(position: Int): Any {
        return ligas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View
        val viewHolder : ViewHolder
        if(convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.liga_lista_item_layout, parent, false)
            viewHolder =ViewHolder(view)
            view.tag =viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val liga = ligas.get(position)
        viewHolder.nombreTextView.text=liga.nombre
        return view
    }

    private class ViewHolder(view: View) {
        val nombreTextView: TextView = view.findViewById(R.id.nombreLiga)
    }
}
