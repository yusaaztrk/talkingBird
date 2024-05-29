package com.example.talkingbird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void egitimler(View view) {
        intent = new Intent(MainActivity.this, egitimler.class);
        startActivity(intent);
    }

    public void kulanimKlavuzu(View view){
        intent = new Intent(MainActivity.this, kullanimKlavuzu.class);
        startActivity(intent);
    }

    public void kuslarHakkinda(View view){
        intent = new Intent(MainActivity.this , kuslarHakkinda.class);
        startActivity(intent);
    }
}