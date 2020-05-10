package com.example.kajianislam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void masukCatatanKajian(View view) {
        Intent intent=new Intent(this,CatatanKajian.class);
        startActivity(intent);
    }

    public void masukBuatCatatan(View view) {
        Intent intent=new Intent(this,BuatCatatanBaru.class);
        startActivity(intent);
    }
}
