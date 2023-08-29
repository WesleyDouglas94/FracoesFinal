package com.example.fracoesfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashArt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_art);

        // Handler é uma classe que permite que você execute código em uma thread separada da thread principal.
        Handler handler = new Handler();

        // O método postDelayed() permite que você execute um código em um determinado intervalo de tempo.
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Depois de um segundo, inicie a MainActivity.
                startActivity(new Intent(SplashArt.this, Login.class));

                // Termine a SplashActivity.
                finish();
            }
        }, 2000);

    }
}