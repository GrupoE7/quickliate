package com.example.quickliate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AlertasActivity extends AppCompatActivity {

    //Se crean las variables para enlazarlas
    EditText etN1, etN2, etN3, etN4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertas);
        //Activar boton volver en la ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etN1 = (EditText) findViewById(R.id.editTextNumber1);
        etN2 = (EditText) findViewById(R.id.editTextNumber2);
        etN3 = (EditText) findViewById(R.id.editTextNumber3);
        etN4 = (EditText) findViewById(R.id.editTextNumber4);
    }

    //Finalizar la actividad al activar el boton volver
    public void onBackPressed(){
        //Llamar a la anterior actividad
        Intent newIntent = new Intent(getApplicationContext(), Activity7.class);
        //Lanzar la actividad
        startActivity(newIntent);
        finish();
    }

    //Metodo para averiguar que hizo el usuario, si oprimio el boton
    public boolean onOptionsItemSelected(MenuItem menuItem){
        //Cuando el usuario pulsa en el ActionBar, se genera un codigo
        //Devuelve un numero que se guarda en id
        int id = menuItem.getItemId();
        //Comparar si los codigos estan asociados, para el caso con el boton retornar
        if(id == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    //Crear metodo Guardardatos1, el View para activarlo desde diseño
    //Ir al onClick del boton para que cargue ese metodo
    public void Guardardatos1(View view){
        //1. Comparar que los textos tengan datos
        String max1 = etN1.getText().toString();
        String min1 = etN2.getText().toString();
        //Si los datos tienen contenido
        if(max1.equals("") || min1.equals("")) {
            Toast.makeText(this, "Por favor ingrese valores para la variable temperatura", Toast.LENGTH_LONG).show();
            //Foco vuelva al texto 1 de Temperatura máxima
            etN1.requestFocus();
        }
        else{
            Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show();
        }
    }

    public void Guardardatos2(View view){
        //1. Comparar que los textos tengan datos
        String max2 = etN3.getText().toString();
        String min2 = etN4.getText().toString();
        //Si los datos tienen contenido
        if(max2.equals("") || min2.equals("")) {
            Toast.makeText(this, "Por favor ingrese valores para la variable humedad", Toast.LENGTH_LONG).show();
            //Foco vuelva al texto 1 de Temperatura máxima
            etN3.requestFocus();
        }
        else{
            Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show();
        }
    }
}