package com.example.quickliate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    //Se crean las variables para enlazarlas
    EditText et1, et2;
    TextView tv1;
    private FirebaseAuth mAut;
    private String user ="";
    private String pass ="";
    private DatabaseReference mDatabase;
    private TextView nver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAut=FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Ocultar ActionBar
        getSupportActionBar().hide();

        et1 = (EditText) findViewById(R.id.correo);
        et2 = (EditText) findViewById(R.id.Password);
        tv1 = (TextView) findViewById(R.id.textView9);
        nver =(TextView) findViewById(R.id.ver);
        //Cambios de diseño tv1
        //tv1 permita un click
        tv1.setClickable(true);
        //Para que se vea como un link
        String texto = "<a href=''>Recuperar contraseña </a>";
        //expandir el código html
        tv1.setText(Html.fromHtml(texto));
        //Ir a pantalla Recuperar Contraseña, escribir que tv1 admita un click
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(getApplicationContext(), p_3.class);
                startActivity(newIntent);
                finish();
            }
        });
    }

    //Crear metodo Login, el View para activarlo desde diseño
    //Ir al onClick del boton para que cargue ese metodo
    public void Login(View view){
        //1. Comparar que los textos tengan datos
        user = et1.getText().toString();
        pass = et2.getText().toString();
        //Si los datos tienen contenido
        if(!user.isEmpty() && !pass.isEmpty()){
            loginUser();

        }
        else
        {
            Toast.makeText(LoginActivity.this,"Ingrese Datos",Toast.LENGTH_SHORT).show();

        }
    }
    private void loginUser(){
        mAut.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //String id  = mAut.getCurrentUser().getUid();
                   // nver.setText(id);
                   datosUsuario();


                }

            }
        });

    }
    private void datosUsuario(){
        String id  = mAut.getCurrentUser().getUid();
        mDatabase.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String cargo = snapshot.child("cargo ").getValue().toString();
                    String cargoAdministrador="Administrador";
                    String cargoTrabajador="Trabajador";
                    nver.setText(cargo);
                    if (cargo.equals(cargoAdministrador) ){
                        startActivity(new Intent(LoginActivity.this,Activity7.class));
                        finish();
                    }
                    else if(cargo.equals(cargoTrabajador)){
                        startActivity(new Intent(LoginActivity.this,Activity13.class));
                        finish();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}