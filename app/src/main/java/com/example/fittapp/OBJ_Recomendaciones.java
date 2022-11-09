package com.example.fittapp;

public class OBJ_Recomendaciones {
    String Azucar,Calorias,Carbohidratos,Grasas,Nombre,Proteinas,Url;


    public OBJ_Recomendaciones(){

    }

    public OBJ_Recomendaciones(String azucar, String calorias, String carbohidratos, String grasas, String nombre, String proteinas, String url) {
        Azucar = azucar;
        Calorias = calorias;
        Carbohidratos = carbohidratos;
        Grasas = grasas;
        Nombre = nombre;
        Proteinas = proteinas;
        Url=url;
    }

    public String getAzucar() {
        return Azucar;
    }

    public void setAzucar(String azucar) {
        Azucar = azucar;
    }

    public String getCalorias() {
        return Calorias;
    }

    public void setCalorias(String calorias) {
        Calorias = calorias;
    }

    public String getCarbohidratos() {
        return Carbohidratos;
    }

    public void setCarbohidratos(String carbohidratos) {
        Carbohidratos = carbohidratos;
    }

    public String getGrasas() {
        return Grasas;
    }

    public void setGrasas(String grasas) {
        Grasas = grasas;
    }

    public String getNombree() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getProteinas() {
        return Proteinas;
    }

    public void setProteinas(String proteinas) {
        Proteinas = proteinas;
    }


    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getNombre() {
        return Nombre;
    }
}
