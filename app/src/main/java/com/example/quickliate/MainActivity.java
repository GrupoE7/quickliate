package com.example.quickliate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //Constante para modificar el tiempo destinado para la bienvenida
    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Bloquear la rotación de pantalla, quedará vertical obligatoriamente
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Ocultar la barra de notificaciones
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        //Ocultar ActionBar
        getSupportActionBar().hide();

        //Crear tarea de temporización
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //Llamar a la siguiente actividad
                Intent newIntent = new Intent(getApplicationContext(), LoginActivity.class);
                //Lanzar la actividad
                startActivity(newIntent);
                //Cerrar splash screen
                finish();
            }
        };

        //Lanzar el temporizador
        Timer timer = new Timer();
        timer.schedule(timerTask, SPLASH_SCREEN_DELAY);

        setContentView(R.layout.activity_main);
    }
}