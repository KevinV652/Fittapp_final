package com.example.fittapp;

import android.widget.ImageView;

public class OBJ_Recetas {
    String Calorias,Dificultad,Nombre,Tiempo,Url;
    String ImagenR;
    public OBJ_Recetas(){

    }
    //CONSTRUCTOR
    public OBJ_Recetas(String calorias, String dificultad, String nombre, String tiempo, String url, String imagen) {
        Calorias = calorias;
        Dificultad = dificultad;
        Nombre = nombre;
        Tiempo = tiempo;
        Url=url;
        ImagenR= imagen;


    }
    //GETTERS AND SETTERS
    public String getCalorias() {
        return Calorias;
    }
    public void setCalorias(String calorias) {
        Calorias = calorias;
    }


    public String getDificultad() {
        return Dificultad;
    }
    public void setDificultad(String dificultad) {
        Dificultad = dificultad;
    }


    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }


    public String getTiempo() {
        return Tiempo;
    }
    public void setTiempo(String tiempo) {
        Tiempo = tiempo;
    }


    public String getUrl() {return Url;}
    public void setUrl(String url) {Url = url;
    }

    public String getImagenR() {

        return ImagenR;
    }

    public void setImagenR(String imagen) {
        ImagenR = imagen;
    }
}
