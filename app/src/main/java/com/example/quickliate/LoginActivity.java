package com.example.quickliate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
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
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Bloquear la rotación de pantalla, quedará vertical obligatoriamente
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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
                Intent newIntent = new Intent(getApplicationContext(), RecuperarActivity.class);
                startActivity(newIntent);
                finish();
            }
        });
    }

    //Crear metodo Login, el View para activarlo desde diseño
    //Ir al onClick del boton para que cargue ese metodo
    public void Login(View view){
        //Si no hay conexión se muestra mensaje
        if(!comprobarConexion(this)){
            new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert)
                    //Icono que se va a mostrar, icono siguiente
                    .setIcon(android.R.drawable.ic_dialog_info)
                    //Titulo que debe poner al dialogo
                    .setTitle("Información")
                    //Mensaje que debe poner al cuadro de dialogo
                    .setMessage("Por favor conéctate a Internet para continuar")
                    .setPositiveButton("Conectar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_SETTINGS));
                        }
                    })
                    .setNegativeButton("Cancelar", null).show();
        }

        //Comparar que los textos tengan datos
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

    //Comprobar si hay conexión a Internet
    private boolean comprobarConexion(LoginActivity recuperarActivity) {
        //Crear componente de tipo Connectivity Manager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        //Crear variable de tipo NetworkInfo
        //La variable contiene toda la información de la red
        NetworkInfo wifiConexion = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo movilConexion = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if((wifiConexion!= null && wifiConexion.isConnectedOrConnecting() || movilConexion!= null && movilConexion.isConnectedOrConnecting())){
            return true;
        }
        else{
            return false;
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
         id  = mAut.getCurrentUser().getUid();
        mDatabase.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String cargo = snapshot.child("cargo ").getValue().toString();
                    String cargoAdministrador="Administrador";
                    String cargoTrabajador="Trabajador";
                    nver.setText(cargo);
                    if (cargo.equals(cargoAdministrador) ){
                        Intent a = new Intent(LoginActivity.this,Activity7.class);

                        //startActivity(new Intent(LoginActivity.this,Activity7.class));
                        a.putExtra("sitio",id);
                        startActivity(a);
                        finish();
                    }
                    else if(cargo.equals(cargoTrabajador)){
                        Intent i = new Intent(LoginActivity.this,Activity13.class);
                        //startActivity(new Intent(LoginActivity.this,Activity13.class));
                        i.putExtra("sitio",id);
                        startActivity(i);
                        finish();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Metodo para confirmar si el usuario quiere realmente salir de la aplicación
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
                    .setMessage("¿Desea salir de la aplicación?")
                    //Boton negativo, parametros: que dira el boton, y que hace al seleccionar
                    .setNegativeButton("No", null)
                    //Boton positivo, new DialogInter... es un metodo escuchador
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LoginActivity.this.finish();
                        }
                    }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}