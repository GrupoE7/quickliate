package com.example.quickliate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class Activity7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7);
    }
    public void usuarios(View view){
        Intent i = new Intent(this,p_8.class);
        startActivity(i);

    }
    public void datos(View view){
        Intent a = new Intent(this,p_5.class);
        startActivity(a);

    }
    public void alarma(View view){
        Intent b = new Intent(this,AlertasActivity.class);
        startActivity(b);

    }
    public void irconfigurasensor(View view){
        Intent c = new Intent(this,p_11.class);
        startActivity(c);

    }
    public void irubica(View view){
        Intent d = new Intent(this,Activity12.class);
        startActivity(d);

    }
    public void irconfigura(View view){
        Intent e = new Intent(this,activity_4.class);
        startActivity(e);

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
                            Activity7.this.finish();
                        }
                    }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
