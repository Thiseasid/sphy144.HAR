package com.example.sphy144_har;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sphy144_har.helpers.buttonStateManager;

public class Simulation_4720 extends AppCompatActivity {

    Button button_4720_reserved;
    Button button_4720_preset;
    Button button_4720_main_menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_simulation4720);

        Button button_4720_reserved = findViewById(R.id.button_4720_reserved);
        Button button_4720_preset = findViewById(R.id.button_4720_preset);
        Button button_4720_main_menu = findViewById(R.id.button_4720_main_menu);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        buttonStateManager buttonPowerManager = new buttonStateManager();
        button_4720_main_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Simulation_4720.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }


        });


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Simulation_4720.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}