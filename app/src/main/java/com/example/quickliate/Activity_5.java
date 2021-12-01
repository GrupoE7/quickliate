package com.example.quickliate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

import Models.Mensaje2;
import adapter.MensajeAdapter2;

public class Activity_5 extends AppCompatActivity {

    String[][] Datos = new String[10][10];
    TextView D;
    Button bfecha;
    EditText efecha;
    TextView ef;
    private int dia, mes, ano;
    private String fechaIngreso;
    private DatabaseReference mibase;
    private MensajeAdapter2 madapter2;
    private RecyclerView mrecyclerView;
    private ArrayList<Mensaje2> mMensajeList = new ArrayList<>();
    private EditText enombre_sensor;
    private String nombresensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
        enombre_sensor = (EditText) findViewById(R.id.nombre_sensor);

        bfecha = (Button) findViewById(R.id.fecha);
        efecha = (EditText) findViewById(R.id.fecha_edit);
        bfecha.setOnClickListener((View.OnClickListener) this);

        mrecyclerView = (RecyclerView) findViewById(R.id.mesajes);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void casa(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }


    public void onClick(View v) {
        if (v == bfecha) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    efecha.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    fechaIngreso = (year + "-" + (month + 1) + "-" + dayOfMonth);


                    getMensajesFromFirebaseHumedad();


                }
            }
                    , dia, mes, ano);
            datePickerDialog.show();
        }

    }


    private void getMensajesFromFirebaseHumedad() {
        mibase = FirebaseDatabase.getInstance().getReference();
        nombresensor = enombre_sensor.getText().toString();

        mibase.child("Sensor").child(nombresensor).child(fechaIngreso).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    mMensajeList.clear();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        String Hum = ds.child("Hum").getValue().toString();
                        String Temp = ds.child("Temp").getValue().toString();
                        String Hora = ds.child("hora").getValue().toString();

                        mMensajeList.add(new Mensaje2(Hum, Temp, Hora));
                    }
                    madapter2 = new MensajeAdapter2(mMensajeList, R.layout.menasaje_ver1);
                    mrecyclerView.setAdapter(madapter2);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /////////////////////////////temp//////////////


    }


//////////////////////////temp///////////////


//////////////////////////Hora///////////////
}

