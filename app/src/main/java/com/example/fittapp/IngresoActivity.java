package com.example.fittapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IngresoActivity extends AppCompatActivity {
    Button bt1,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);


    }
    public void irniciar(View view){
        Intent i =new Intent(this, Login.class);
        startActivity(i);
    }
    public void irregistro(View view){
        Intent i =new Intent(this,Registro.class);
        startActivity(i);
    }
}