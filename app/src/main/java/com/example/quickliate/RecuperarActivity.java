package com.example.quickliate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class RecuperarActivity extends AppCompatActivity {

    //Para hacer referencia a lo que ingresa el usuario
    EditText mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);
        //Cargar EditText mensaje
        mensaje = findViewById(R.id.editTextTextPersonName);
        //Activar boton volver en la ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void ComunicarAdmin(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_VIEW);
        String cel = "573152194736";
        String info = "whatsapp://send?phone="+cel+"&text="+mensaje.getText().toString();
        sendIntent.setData(Uri.parse(info));
        //Enviar el mensaje que esta contenido en la caja de texto
        //sendIntent.putExtra(Intent.EXTRA_TEXT,  mensaje.getText().toString());
        //sendIntent.setType("text/plain");
        //sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);
    }


    //Finalizar la actividad al activar el boton volver
    public void onBackPressed(){
        //Llamar a la anterior actividad
        Intent newIntent = new Intent(getApplicationContext(), LoginActivity.class);
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
}