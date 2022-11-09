package com.example.fittapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Inicio extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private TextView metas, peso, saludo, edad;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference BASE_DE_DATOS;
    private String id_usuario, uid;
    private Button cerrar_sesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        ActionBar actionBar= getSupportActionBar();
        assert  actionBar !=null;

        actionBar.setTitle("Inicio");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Instanciar Firebase
        BASE_DE_DATOS = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        cerrar_sesion = (Button) findViewById(R.id.btn_cerrar);


        //Instanciar los textview
        metas = (TextView) findViewById(R.id.meta1);
        peso = (TextView) findViewById(R.id.peso1);
        saludo = (TextView) findViewById(R.id.tit_saludo);
        edad = (TextView) findViewById(R.id.edad1);
        cerrar_sesion = (Button) findViewById(R.id.btn_cerrar);

        //Cerrar Sesión

        cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarSesion();
            }
        });


        //Validación y setText de datos personales
        BASE_DE_DATOS.child("USUARIOS_DE_APP").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                uid = firebaseUser.getUid().toString();

                if (snapshot.exists()) {

                    String edadx = snapshot.child(uid).child("eedad").getValue().toString();
                    edad.setText(edadx);
                    String name = snapshot.child(uid).child("nombres").getValue().toString();
                    saludo.setText("Hola " + name + " ;)");
                    String meta = snapshot.child(uid).child("mmetas").getValue().toString();
                    metas.setText(meta);
                    String pesox = snapshot.child(uid).child("peso").getValue().toString();
                    peso.setText(pesox);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void irrecetas(View view){
        Intent i =new Intent(this,RecetasActivity.class);
        startActivity(i);
    }
    public void irrecomendar(View view){
        Intent i =new Intent(this,recomendaciones.class);
        startActivity(i);
    }


    private void cerrarSesion(){
        firebaseAuth.signOut();
        Toast.makeText(this, "Ha cerrado sesión", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Inicio.this, IngresoActivity.class));
    }


}