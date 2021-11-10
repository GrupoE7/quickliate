package com.example.quickliate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //Se crean las variables para enlazarlas
    EditText et1, et2;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Ocultar ActionBar
        getSupportActionBar().hide();

        et1 = (EditText) findViewById(R.id.editTextTextPersonName);
        et2 = (EditText) findViewById(R.id.editTextTextPassword);
        tv1 = (TextView) findViewById(R.id.textView9);
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
        String user = et1.getText().toString();
        String pass = et2.getText().toString();
        //Si los datos tienen contenido
        if(!user.equals("") && !pass.equals("")) {
            if (user.equals("admin") && pass.equals("admin")) {
                Intent newIntent = new Intent(this, p_7.class);
                startActivity(newIntent);
                finish();
            }
            else {
                Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG).show();
                //Foco vuelva al texto 1 de usuario
                et1.requestFocus();
            }
        }
        else{
            if(user.equals("")){
                et1.requestFocus();
                Toast.makeText(this, "Por favor ingrese el usuario", Toast.LENGTH_LONG).show();
            }
            else if(pass.equals("")){
                et2.requestFocus();
                Toast.makeText(this, "Por favor ingrese la contraseña", Toast.LENGTH_LONG).show();
            }
        }
    }
}