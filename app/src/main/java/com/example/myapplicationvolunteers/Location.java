package com.example.myapplicationvolunteers;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Location extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        if(Build.VERSION.SDK_INT>=23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{ Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                //req loc permission
                startService();
            }
        }else{
            startService();
        }
    }
    void  startService() {
        Intent intent = new Intent(Location.this, LocationService.class);
        startService(intent);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case 1:
                if(  grantResults [0]==PackageManager.PERMISSION_GRANTED){
                    startService();

                }
                else{
                    Toast.makeText(this, "Give me permisions",Toast.LENGTH_LONG).show();
                }
        }
    }
}
