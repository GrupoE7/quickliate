package com.example.quickliate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_4 extends AppCompatActivity {
    private ImageButton btnCamara;
    private ImageView imgView ;
    private FirebaseAuth mAut;
    private TextView tNombre;
    private TextView tcargo;
    private TextView tcorreo;
    private String nombre;
    private DatabaseReference miBD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_4);
        tNombre= findViewById(R.id.nombreCliente);
        tcargo= findViewById(R.id.cargo);
        tcorreo= findViewById(R.id.correoid);
        btnCamara = findViewById(R.id.btnCamara);
        imgView = findViewById(R.id.imageView23);
        //String id  = mAut.getCurrentUser().getUid();
        String sitioVer = getIntent().getStringExtra("id");

        miBD= FirebaseDatabase.getInstance().getReference();
        miBD.child("Usuarios").child(sitioVer).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nombre =snapshot.child("nombre ").getValue().toString();
                        String cargo= snapshot.child("cargo ").getValue().toString();
                                String correo=snapshot.child("correo ").getValue().toString();
                tNombre.setText(nombre);
                tcargo.setText(cargo);
                tcorreo.setText(correo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //tNombre.setText(sitioVer);





        btnCamara.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        abrirCamara();
                    }
                });
    }

    private void abrirCamara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, 1);
        }
    }
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            imgView.setImageBitmap(imgBitmap);
        }
    }



}