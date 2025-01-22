package com.example.sphy144_har;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sphy144_har.helpers.buttonStateManager;

public class Simulation_9200 extends AppCompatActivity {

    // Top Buttons
    Button button_9200_insert_remove_fillgun;
    Button button_9200_reserved;
    Button button_9200_preset;
    Button button_9200_main_menu;

    // Bottom Buttons
    Button button_9200_1;
    Button button_9200_2;
    Button button_9200_3;
    // Button button_9200_4 ;
    // Button button_9200_5 ;
    // Button button_9200_6 ;
    // Button button_9200_7 ;
    // Button button_9200_8 ;
    // Button button_9200_9 ;
    // Button button_9200_0 ;
    Button button_9200_mode;
    // Button button_9200_auth ;
    // Button button_9200_serv ;
    // Button button_9200_menu ;
    // Button button_9200_up ;
    // Button button_9200_right ;
    // Button button_9200_enter ;
    // Button button_9200_hlc ;
    // Button button_9200_light ;
    // Button button_9200_alrt ;
    //
    // // Other Buttons
    // Button button_9200_erase;
    // Button button_9200_ptt;
    ImageButton imageButton_9200_power;
    ImageButton imageButton_9200_channel;
    ImageButton imageButton_9200_volume;

    TextView textView_9200_screen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_simulation9200);

        // Top Buttons
        Button button_9200_insert_remove_fillgun = findViewById(R.id.button_9200_insert_remove_fillgun);
        Button button_9200_reserved = findViewById(R.id.button_9200_reserved);
        Button button_9200_preset = findViewById(R.id.button_9200_preset);
        Button button_9200_main_menu = findViewById(R.id.button_9200_main_menu);

        // Bottom Buttons
        Button button_9200_1 = findViewById(R.id.button_9200_1);
        Button button_9200_2 = findViewById(R.id.button_9200_2);
        Button button_9200_3 = findViewById(R.id.button_9200_3);
//    Button button_9200_4 = findViewById(R.id.button_9200_4);
//    Button button_9200_5 = findViewById(R.id.button_9200_5);
//    Button button_9200_6 = findViewById(R.id.button_9200_6);
//    Button button_9200_7 = findViewById(R.id.button_9200_7);
//    Button button_9200_8 = findViewById(R.id.button_9200_8);
//    Button button_9200_9 = findViewById(R.id.button_9200_9);
//    Button button_9200_0 = findViewById(R.id.button_9200_0);
        Button button_9200_mode = findViewById(R.id.button_9200_mode);
//    Button button_9200_auth = findViewById(R.id.button_9200_auth);
//    Button button_9200_serv = findViewById(R.id.button_9200_serv);
//    Button button_9200_menu = findViewById(R.id.button_9200_menu);
//    Button button_9200_up = findViewById(R.id.button_9200_up);
//    Button button_9200_right = findViewById(R.id.button_9200_right);
//    Button button_9200_enter = findViewById(R.id.button_9200_enter);
//    Button button_9200_hlc = findViewById(R.id.button_9200_hlc);
//    Button button_9200_light = findViewById(R.id.button_9200_light);
//    Button button_9200_alrt = findViewById(R.id.button_9200_alrt);
//
//    // Other Buttons
//    Button button_9200_erase = findViewById(R.id.button_9200_erase);
//    Button button_9200_ptt = findViewById(R.id.button_9200_ptt);
        ImageButton imageButton_9200_power = findViewById(R.id.imageButton_9200_power);
        ImageButton imageButton_9200_channel = findViewById(R.id.imageButton_9200_channel);
        ImageButton imageButton_9200_volume = findViewById(R.id.imageButton_9200_volume);

        TextView textView_9200_screen = findViewById(R.id.textView_9200_screen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        buttonStateManager buttonPowerManager = new buttonStateManager();
        button_9200_main_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Simulation_9200.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });





    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Simulation_9200.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}