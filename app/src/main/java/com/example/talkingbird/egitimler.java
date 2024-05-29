package com.example.talkingbird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class egitimler extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egitimler);
    }
    public void babacik(View view){
        intent = new Intent(egitimler.this, babacik.class);
        startActivity(intent);
    }
    public void cicikus(View view){
        intent = new Intent(egitimler.this, cicikus.class);
        startActivity(intent);
    }
    public void askim(View view){
        intent = new Intent(egitimler.this, askim.class);
        startActivity(intent);
    }
}