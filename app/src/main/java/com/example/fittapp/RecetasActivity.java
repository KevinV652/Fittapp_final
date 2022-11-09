package com.example.fittapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class RecetasActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Recomen_Adapter.Myadapter myadapter;
    ArrayList<OBJ_Recetas> recetasArrayList;
    FirebaseFirestore db;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Buscando la informacion");
        progressDialog.show();

        recyclerView=findViewById(R.id.recyclerview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        db = FirebaseFirestore.getInstance();
        recetasArrayList = new ArrayList<OBJ_Recetas>();
        myadapter = new Recomen_Adapter.Myadapter(RecetasActivity.this,recetasArrayList);

        recyclerView.setAdapter(myadapter);

        EventChangeListener();


    }

    private void EventChangeListener() {
        db.collection("Recetas").orderBy("Nombre", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }
                        for(DocumentChange dc: value.getDocumentChanges()){

                            if(dc.getType()==DocumentChange.Type.ADDED){
                                recetasArrayList.add(dc.getDocument().toObject(OBJ_Recetas.class));

                            }
                            myadapter.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                        }

                    }
                });
    }
    public void irinicio(View view){
        Intent x =new Intent(this, Inicio.class);
        startActivity(x);
    }
    public void irrecomendar(View view){
        Intent i =new Intent(this,recomendaciones.class);
        startActivity(i);
    }
}