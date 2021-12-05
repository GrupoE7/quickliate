package com.example.quickliate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class Activity13 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_13);
    }

    public void irdatos(View view) {
        Intent a = new Intent(this, p_5.class);
        startActivity(a);
    }
    public void iralarm(View view) {
        Intent b = new Intent(this, AlertasActivity.class);
        startActivity(b);
    }
    public void iraconfi(View view) {
        Intent b = new Intent(this, activity_4.class);
        startActivity(b);
    }

    //Metodo para confirmar si el usuario quiere realmente cerrar sesión
    public boolean onKeyDown(int keyCode, KeyEvent event){
        //Averiguar si el evento es pulsar boton de devolverse
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            //Crear en tiempo de ejecución, cuadro de dialogo
            new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert)
                    //Icono que se va a mostrar, icono siguiente
                    .setIcon(android.R.drawable.ic_dialog_info)
                    //Titulo que debe poner al dialogo
                    .setTitle("Información")
                    //Mensaje que debe poner al cuadro de dialogo
                    .setMessage("¿Desea cerrar sesión?")
                    //Boton negativo, parametros: que dira el boton, y que hace al seleccionar
                    .setNegativeButton("No", null)
                    //Boton positivo, new DialogInter... es un metodo escuchador
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Cerrar la aplicacion
                            Activity13.this.finish();
                        }
                    }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}