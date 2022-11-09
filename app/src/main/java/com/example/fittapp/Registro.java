package com.example.fittapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registro extends AppCompatActivity {

    EditText Correo , contrasena,nombre,apellidos,edad,metas,peso;
    Button Registrou;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        ActionBar actionBar= getSupportActionBar();
        assert  actionBar !=null;
        actionBar.setTitle("Registro");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Correo=findViewById(R.id.correo);
        contrasena=findViewById(R.id.contrasena);

        nombre=findViewById(R.id.nombres);
        apellidos=findViewById(R.id.apellidos);
        edad=findViewById(R.id.edad);
        metas=findViewById(R.id.meta);
        peso=findViewById(R.id.peso);

        Registrou=findViewById(R.id.btregistro);

        firebaseAuth = FirebaseAuth.getInstance();

        Registrou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = Correo.getText().toString();
                String pass = contrasena.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
                        Correo.setError("Correo no valido");
                        Correo.setFocusable(true);

                }else  if(pass.length()<6){
                    contrasena.setError("Contraseña debe ser mayor 6");
                    contrasena.setFocusable(true);

                }else {
                    REGISTRAR(correo,pass);
                }
            }
        });



    }

    private void REGISTRAR(String correo, String pass) {
        firebaseAuth.createUserWithEmailAndPassword(correo, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    assert user != null;
                    String uid=user.getUid();

                    String correo = Correo.getText().toString();
                    String pass = contrasena.getText().toString();
                    String nombres = nombre.getText().toString();
                    String aapellido = apellidos.getText().toString();
                    String eedad = edad.getText().toString();
                    String mmetas = metas.getText().toString();
                    String ppeso = peso.getText().toString();

                    HashMap<Object,String> DatosUsuario = new HashMap<>();
                    DatosUsuario.put("uid",uid);
                    DatosUsuario.put("correo",correo);
                    DatosUsuario.put("pass",pass);
                    DatosUsuario.put("nombres",nombres);
                    DatosUsuario.put("aapellidos",aapellido);
                    DatosUsuario.put("eedad",eedad);
                    DatosUsuario.put("mmetas",mmetas);
                    DatosUsuario.put("ppeso",ppeso);

                    DatosUsuario.put("imagen","");

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference("USUARIOS_DE_APP");

                    reference.child(uid).setValue(DatosUsuario);
                    Toast.makeText(Registro.this,"se registro correctamente",Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(Registro.this, Login.class));

                }else {
                    Toast.makeText(Registro.this,"Algo ha salido mal  ",Toast.LENGTH_SHORT).show();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Registro.this,e.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
