package com.example.fittapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Recetas_Adapter extends RecyclerView.Adapter<Recetas_Adapter.MyviewHolder> {
    Context context;
    ArrayList<OBJ_Recetas> recetasArrayList;
    ImageView Imagen;

    public Recetas_Adapter(Context context, ArrayList<OBJ_Recetas> recetasArrayList) {
        this.context = context;
        this.recetasArrayList = recetasArrayList;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.item_recetas,parent,false);
        return new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        //Recetas receta = recetasArrayList.get(position);
        /*holder.Nombre.setText(recetas.Nombre);
        holder.Dificultad.setText(recetas.Dificultad);
        holder.Tiempo.setText(recetas.Tiempo);
        holder.Calorias.setText(recetas.Calorias);*/
        holder.Carga(recetasArrayList.get(position));



    }




    @Override
    public int getItemCount() {
        return recetasArrayList.size();
    }
    public class  MyviewHolder extends  RecyclerView.ViewHolder{

        TextView Nombre,Dificultad,Tiempo,Calorias,Url;
        ImageView Imagen;


        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            Nombre=itemView.findViewById(R.id.tvnombre);
            Dificultad=itemView.findViewById(R.id.tvdificultad);
            Tiempo=itemView.findViewById(R.id.tvtiempo);
            Calorias=itemView.findViewById(R.id.tvcalorias);
            //Url=itemView.findViewById(R.id.tvurl);
            Imagen=itemView.findViewById(R.id.image);


        }
        public void Carga(OBJ_Recetas receta){
            Nombre.setText(receta.getNombre());
            Dificultad.setText(receta.Dificultad);
            Tiempo.setText(receta.Tiempo);
            Calorias.setText(receta.Calorias);
            Picasso.get()
                    .load(receta.getUrl())
                    .resize(50, 50)
                    .centerCrop()
                    .error(R.drawable.ic_launcher_background)
                    .into(Imagen);
        }
    }

}
