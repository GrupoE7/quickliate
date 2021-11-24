package com.example.quickliate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Models.Mensaje;
import adapter.MensajeAdapter;

public class p_11 extends AppCompatActivity {
    private MensajeAdapter madapter;

    private RecyclerView mrecyclerView;
    private ArrayList<Mensaje> mMensajeList = new ArrayList<>();
    private DatabaseReference mibase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p11);
        mrecyclerView = (RecyclerView) findViewById(R.id.verSensore);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mibase=  FirebaseDatabase.getInstance().getReference();

        mibase .child("Sensor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    mMensajeList.clear();
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String Hum =ds.child("Hum").getValue().toString();
                        String Temp =ds.child("Temp").getValue().toString();
                        String Hora =ds.child("hora").getValue().toString();

                        mMensajeList.add(new  Mensaje(Hum,Temp,Hora));
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
}