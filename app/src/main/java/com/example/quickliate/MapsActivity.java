package com.example.quickliate;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.quickliate.databinding.ActivityMapsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private DatabaseReference miBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        miBD= FirebaseDatabase.getInstance().getReference();
        miBD.child("Nombre Sensor").child("sensor cordoba").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String gpsSensor=snapshot.child("Gps").getValue().toString();
                String nombreSensor=snapshot.child("Ubicacion").getValue().toString();
                String latitud = gpsSensor.substring(0,9);
                String longitud = gpsSensor.substring(9,18);
                double number = Double.parseDouble(latitud);
                double number2 = Double.parseDouble(longitud);


                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(number,number2);
                mMap.addMarker(new MarkerOptions().position(sydney).title(nombreSensor));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        miBD.child("Nombre Sensor").child("sensor guajira").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String gpsSensor=snapshot.child("Gps").getValue().toString();
                String nombreSensor=snapshot.child("Ubicacion").getValue().toString();
                String latitud = gpsSensor.substring(0,9);
                String longitud = gpsSensor.substring(9,18);
                double number = Double.parseDouble(latitud);
                double number2 = Double.parseDouble(longitud);


                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(number,number2);
                mMap.addMarker(new MarkerOptions().position(sydney).title(nombreSensor));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}