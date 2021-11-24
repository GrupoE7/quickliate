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
import android.widget.Toast;


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


import Models.Mensaje2;

import adapter.MensajeAdapter2;


public class p_5 extends AppCompatActivity implements View.OnClickListener {
    String [][] Datos =new String[10][10];
TextView D;
Button bfecha;
EditText efecha;
TextView ef;
private int dia,mes,ano;
private String fechaIngreso;
private DatabaseReference mibase;
private MensajeAdapter2 madapter2;
private RecyclerView mrecyclerView;
private ArrayList<Mensaje2> mMensajeList = new ArrayList<>();
private EditText enombre_sensor;
private String nombresensor;
    private TextView fechaver;

    private RecyclerView mrecyclerView2;
    private ArrayList<Mensaje2> mMensajeList2 = new ArrayList<>();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p5);
        enombre_sensor =(EditText) findViewById(R.id.nombre_sensor);
        fechaver=(TextView) findViewById(R.id.Fechaselc) ;

        bfecha=(Button) findViewById(R.id.fecha);
        efecha=(EditText) findViewById(R.id.fecha_edit);
        bfecha.setOnClickListener(this);

        mrecyclerView = (RecyclerView) findViewById(R.id.mesajes);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mrecyclerView2 = (RecyclerView) findViewById(R.id.recVerTemp);
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

                    fechaver.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    fechaIngreso=(year+"-"+(month+1)+"-"+dayOfMonth);

                        getMensajesFromFirebaseHumedad();
                        getMensajesFromFirebaseTemp();









                }
            }
            ,dia,mes,ano);
            datePickerDialog.show();
        }

    }


    private void getMensajesFromFirebaseHumedad(){
        mibase=  FirebaseDatabase.getInstance().getReference();
        nombresensor=enombre_sensor.getText().toString();

        mibase .child("Sensor").child(nombresensor).child(fechaIngreso).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    mMensajeList.clear();
                    for(DataSnapshot ds:snapshot.getChildren()){
                        String Hum =ds.child("Hum").getValue().toString();
                       // String Temp =ds.child("Temp").getValue().toString();

                        String Temp ="dentro";
                        String Hora =ds.child("hora").getValue().toString();

                        mMensajeList.add(new  Mensaje2(Temp,Hum,Hora));
                    }
                    madapter2 = new MensajeAdapter2(mMensajeList,R.layout.menasaje_ver1);
                    mrecyclerView.setAdapter(madapter2);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(p_5.this,"Verifique los datos e intente de nuevo ",Toast.LENGTH_SHORT).show();


            }});



        /////////////////////////////temp//////////////



    }


    //////////////////////////temp///////////////
    private void getMensajesFromFirebaseTemp(){
        mibase=  FirebaseDatabase.getInstance().getReference();
        nombresensor=enombre_sensor.getText().toString();

        mibase .child("Sensor").child(nombresensor).child(fechaIngreso).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    mMensajeList2.clear();
                    for(DataSnapshot ds:snapshot.getChildren()){
                        //String Hum =ds.child("Hum").getValue().toString();
                        String Temp =ds.child("Temp").getValue().toString();
                        //int Hum=Integer.parseInt(ds.child("Hum").getValue().toString());
                         String Hum ="dentro";
                        //String Temp ="temp";
                        String Hora =ds.child("hora").getValue().toString();

                        mMensajeList2.add(new  Mensaje2(Hum,Temp,Hora));
                    }
                    madapter2 = new MensajeAdapter2(mMensajeList2,R.layout.menasaje_ver1);
                    mrecyclerView2.setAdapter(madapter2);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }});



        /////////////////////////////temp//////////////



    }


    //////////////////////////Hora///////////////


}