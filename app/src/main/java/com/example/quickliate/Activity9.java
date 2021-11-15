package com.example.quickliate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Activity9 extends AppCompatActivity {
    private EditText mnombre;
    private EditText mcorreo;
    private EditText mpassword;
    private EditText mcargo;
    private EditText mrecidencia;
    private EditText mdepartamento;
    private EditText mmunicipio;
    private EditText mtel;
    private Button bregistrar;
    private RadioButton RbAdministrador,RbTrabajador;

    ////////datos
    private String nombre = "";
    private String correo = "";
    private String contrasena = "";
    private String cargo = "";
    private String recidencia = "";
    private String departamento = "";
    private String municipio = "";
    private String telefono = "";
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_9);

        mAuth=  FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mnombre=(EditText)  findViewById(R.id.nombre_apellido);
        mcorreo=(EditText)  findViewById(R.id.correo);
        mpassword=(EditText)  findViewById(R.id.Password);

        mrecidencia =(EditText) findViewById(R.id.direccion_recidencia);
        mdepartamento=(EditText) findViewById(R.id.departamento);
        mmunicipio=(EditText) findViewById(R.id.municipio);
        mtel=(EditText) findViewById(R.id.telefono);
        bregistrar=(Button) findViewById(R.id.guardar);
        RbAdministrador=(RadioButton) findViewById(R.id.rbAdministrador);
        RbTrabajador=(RadioButton) findViewById(R.id.rbTrabajador);

        bregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = mnombre.getText().toString();
                correo = mcorreo.getText().toString();
                contrasena = mpassword.getText().toString();

                recidencia = mrecidencia.getText().toString();
                departamento = mdepartamento.getText().toString();
                municipio = mmunicipio.getText().toString();
                telefono = mtel.getText().toString();

                if(!nombre.isEmpty() && !correo.isEmpty() && !contrasena.isEmpty()  &&
                        !recidencia.isEmpty() && !departamento.isEmpty() && !municipio.isEmpty() && !telefono.isEmpty()){

                   if(contrasena.length() >=6){

                       if (RbAdministrador.isChecked()==true){
                           cargo="Administrador";
                           registrarUsuario();
                       }else if(RbTrabajador.isChecked()==true){
                           cargo="Trabajador";
                           registrarUsuario();
                       }



                   }
                   else{
                       Toast.makeText(Activity9.this,"Ingrese como minimo 6 caracteres",Toast.LENGTH_SHORT).show();


                   }



                }
                else{
                    Toast.makeText(Activity9.this,"Ingrese todos los campos",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private void registrarUsuario(){
        mAuth.createUserWithEmailAndPassword(correo,contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Map<String,Object> map = new HashMap<>();
                    map.put("nombre ", nombre);
                    map.put("correo ", correo);
                    map.put("contraseña ", contrasena);
                    map.put("cargo ", cargo);
                    map.put("direccion ", recidencia);
                    map.put("departamento ", departamento);
                    map.put("municipio ", municipio);
                    map.put("telefono ", telefono);

                    String id = mAuth.getCurrentUser().getUid();
                mDatabase.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task2) {
                        if (task2.isSuccessful()){
                            Toast.makeText(Activity9.this,"Usuario creado",Toast.LENGTH_SHORT).show();


                        }
                        else {
                            Toast.makeText(Activity9.this,"No se pudo crear los datos",Toast.LENGTH_SHORT).show();

                        }

                    }
                });

                }
                else {
                    Toast.makeText(Activity9.this,"no se pudo registrar el usuario",Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}