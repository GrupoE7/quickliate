package com.example.quickliate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Models.Mensaje;
import adapter.MensajeAdapter;

public class p_8 extends AppCompatActivity {

    private DatabaseReference mibase;
    private ArrayList<Mensaje> mMensajeList = new ArrayList<>();
    private MensajeAdapter madapter;
    private RecyclerView mrecyclerView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p8);
        mrecyclerView = (RecyclerView) findViewById(R.id.personas);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));


            mibase=  FirebaseDatabase.getInstance().getReference();
            mibase .child("Usuarios").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        mMensajeList.clear();
                        for(DataSnapshot ds:snapshot.getChildren()){
                            String cargo =ds.child("cargo ").getValue().toString();//cargo//
                            String nombre =ds.child("nombre ").getValue().toString();//nombre//
                            String contrasena =ds.child("contraseña ").getValue().toString();//contraseña//

                            mMensajeList.add(new  Mensaje(contrasena,cargo,nombre));
                        }
                        madapter = new MensajeAdapter(mMensajeList,R.layout.menasaje_ver);
                        mrecyclerView.setAdapter(madapter);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }});



            /////////////////////////////temp//////////////



        }
        public void agregarUsuario(View view){
            Intent cambio= new Intent(this, Activity9.class);//pára pásar a  la  siguiente  activity//
            startActivity(cambio);

        }
    }
