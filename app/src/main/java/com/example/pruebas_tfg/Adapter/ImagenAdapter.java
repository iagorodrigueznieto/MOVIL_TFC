package com.example.pruebas_tfg.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.pruebas_tfg.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class ImagenAdapter extends BaseAdapter {
    private Context context;
    private List<File> imagenes;

    public ImagenAdapter(Context context, List<File> imagenes) {
        super();
        this.context = context;
        this.imagenes = imagenes;
    }

    @Override
    public int getCount() {
        return imagenes.size();
    }

    @Override
    public Object getItem(int position) {
        return imagenes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mostreado=LayoutInflater.from(context);
        View vista=mostreado.inflate(R.layout.imagen_lista_item,null);
        TextView nombre=vista.findViewById(R.id.nombreImagen);
        nombre.setText(imagenes.get(position).getName());
        File imagen=new File(imagenes.get(position).getAbsolutePath());
        FileInputStream fi= null;
        try {
            fi = new FileInputStream(imagen);
            Bitmap bitmap= BitmapFactory.decodeStream(fi);
            ImageView imageView=vista.findViewById(R.id.imagen);
            imageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return vista;
    }
}
