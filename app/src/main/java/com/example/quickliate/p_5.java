package com.example.quickliate;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import Models.Mensaje;
import adapter.MensajeAdapter;


public class p_5 extends AppCompatActivity implements View.OnClickListener {
    String [][] Datos =new String[10][10];
TextView D;
Button bfecha;
EditText efecha;
TextView ef;
private int dia,mes,ano;
private String fechaIngreso;
private DatabaseReference mibase;
private MensajeAdapter madapter;
private RecyclerView mrecyclerView;
private ArrayList<Mensaje> mMensajeList = new ArrayList<>();

    private MensajeAdapter madapter1;
    private RecyclerView mrecyclerView1;
    private ArrayList<Mensaje> mMensajeList1 = new ArrayList<>();

    private MensajeAdapter madapter2;
    private RecyclerView mrecyclerView2;
    private ArrayList<Mensaje> mMensajeList2 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p5);

        bfecha=(Button) findViewById(R.id.fecha);
        efecha=(EditText) findViewById(R.id.fecha_edit);
        bfecha.setOnClickListener(this);
        ef=findViewById(R.id.fe);
        mrecyclerView = (RecyclerView) findViewById(R.id.mesajes);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mrecyclerView1 = (RecyclerView) findViewById(R.id.mesajes2);
        mrecyclerView1.setLayoutManager(new LinearLayoutManager(this));

        mrecyclerView2 = (RecyclerView) findViewById(R.id.mesajes3);
        mrecyclerView2.setLayoutManager(new LinearLayoutManager(this));











    }
    public void casa(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }

    @Override
    public void onClick(View v) {
        if (v==bfecha){
            final Calendar c = Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    efecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    fechaIngreso=(year+"-"+(month+1)+"-0"+dayOfMonth);

                    //////////////////////////////////////////
                    //FirebaseDatabase database = FirebaseDatabase.getInstance();
                   // DatabaseReference myRef = database.getReference("2021-11-05");
                    // Read from the database

                  //mibase .child("2021-11-05").addValueEventListener(new ValueEventListener() {
                  //    @Override
                  //    public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //    if(snapshot.exists()){
                     //        String humedad =snapshot.child("Hum").getValue().toString();
                     //        D.setText(humedad);
                     //    }

                     // }

                     // @Override
                    // public void onCancelled(@NonNull DatabaseError error) {

                     // }});

                    /////////////////Adapter////

                   getMensajesFromFirebaseHumedad();
                    getMensajesFromFirebaseTemp();
                    getMensajesFromFirebaseHora();






                    ////////////////////////////

                }
            }
            ,dia,mes,ano);
            datePickerDialog.show();

        }

    }


    private void getMensajesFromFirebaseHumedad(){
        mibase=  FirebaseDatabase.getInstance().getReference();
        mibase .child(fechaIngreso).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    mMensajeList.clear();
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String temperatures =ds.child("Hum").getValue().toString();

                        mMensajeList.add(new  Mensaje(temperatures));
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


    //////////////////////////temp///////////////
    private void getMensajesFromFirebaseTemp(){
        mibase=  FirebaseDatabase.getInstance().getReference();
        mibase .child(fechaIngreso).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    mMensajeList1.clear();
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String hummed = Objects.requireNonNull(ds.child("Temp").getValue()).toString();

                        mMensajeList1.add(new  Mensaje(hummed));
                    }
                    madapter1 = new MensajeAdapter(mMensajeList1,R.layout.menasaje_ver);
                    mrecyclerView1.setAdapter(madapter1);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }});

    }

    //////////////////////////Hora///////////////
    //pendiente roooooonalllllllllllll ojooooo////////////////////////////////////////////
    private void getMensajesFromFirebaseHora(){
        mibase=  FirebaseDatabase.getInstance().getReference();
        mibase .child(fechaIngreso).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    mMensajeList2.clear();
                    for(DataSnapshot ds:snapshot.getChildren()){

                        //String hummed = Objects.requireNonNull(ds.child("Temp").getValue()).toString();
                        String hora = Objects.requireNonNull(ds.child("hora").getValue()).toString();


                        mMensajeList2.add(new  Mensaje(hora));
                    }
                    madapter2 = new MensajeAdapter(mMensajeList2,R.layout.menasaje_ver);
                    mrecyclerView2.setAdapter(madapter2);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }});

    }

}