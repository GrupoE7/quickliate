package com.example.quickliate;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.quickliate.databinding.ActivityP7Binding;

public class p_7 extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityP7Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityP7Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);



       // NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_p7);
        //appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


    }


   // @Override
   // public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_p7);
      //  return NavigationUI.navigateUp(navController, appBarConfiguration)
      //          || super.onSupportNavigateUp();
   // }



    public void irap5(View view){
        Intent i = new Intent(this,p_9.class);
        startActivity(i);

    }
}