package com.example.sphy144_har.helpers;

import static androidx.core.content.ContextCompat.startActivity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sphy144_har.MainActivity;
import com.example.sphy144_har.R;
import com.example.sphy144_har.Simulation_9200;

public class buttonManager9200 {

    private final Activity activity;

    public buttonManager9200(Activity activity) {
        this.activity = activity;
    }


    public void setupButtons(){
        // Top Buttons
        Button button_9200_insert_remove_fillgun = activity.findViewById(R.id.button_9200_insert_remove_fillgun);
        button_9200_insert_remove_fillgun.setOnClickListener(v -> handleButton_9200_insert_remove_fillgun_Click());
        Button button_9200_reserved = activity.findViewById(R.id.button_9200_reserved);
        button_9200_reserved.setOnClickListener(v -> handleButton_9200_reserved_Click());
        Button button_9200_preset = activity.findViewById(R.id.button_9200_preset);
        button_9200_preset.setOnClickListener(v -> handleButton_9200_preset_Click());
        Button button_9200_main_menu = activity.findViewById(R.id.button_9200_main_menu);
        button_9200_main_menu.setOnClickListener(v -> buttonManagerGlobal.handleButton_mainMenu_Click(activity));

        // Bottom Buttons
        Button button_9200_1 = activity.findViewById(R.id.button_9200_1);
        button_9200_1.setOnClickListener(v -> handleButton_9200_1_Click());
        Button button_9200_2 = activity.findViewById(R.id.button_9200_2);
        button_9200_2.setOnClickListener(v -> handleButton_9200_2_Click());
        Button button_9200_3 = activity.findViewById(R.id.button_9200_3);
        button_9200_3.setOnClickListener(v -> handleButton_9200_3_Click());
        Button button_9200_4 = activity.findViewById(R.id.button_9200_4);
        button_9200_4.setOnClickListener(v -> handleButton_9200_4_Click());
        Button button_9200_5 = activity.findViewById(R.id.button_9200_5);
        button_9200_5.setOnClickListener(v -> handleButton_9200_5_Click());
        Button button_9200_6 = activity.findViewById(R.id.button_9200_6);
        button_9200_6.setOnClickListener(v -> handleButton_9200_6_Click());
        Button button_9200_7 = activity.findViewById(R.id.button_9200_7);
        button_9200_7.setOnClickListener(v -> handleButton_9200_7_Click());
        Button button_9200_8 = activity.findViewById(R.id.button_9200_8);
        button_9200_8.setOnClickListener(v -> handleButton_9200_8_Click());
        Button button_9200_9 = activity.findViewById(R.id.button_9200_9);
        button_9200_9.setOnClickListener(v -> handleButton_9200_9_Click());
        Button button_9200_0 = activity.findViewById(R.id.button_9200_0);
        button_9200_0.setOnClickListener(v -> handleButton_9200_0_Click());
        Button button_9200_mode = activity.findViewById(R.id.button_9200_mode);
        button_9200_mode.setOnClickListener(v -> handleButton_9200_mode_Click());
        Button button_9200_auth = activity.findViewById(R.id.button_9200_auth);
        button_9200_auth.setOnClickListener(v -> handleButton_9200_auth_Click());
        Button button_9200_serv = activity.findViewById(R.id.button_9200_serv);
        button_9200_serv.setOnClickListener(v -> handleButton_9200_serv_Click());
        Button button_9200_menu = activity.findViewById(R.id.button_9200_menu);
        button_9200_menu.setOnClickListener(v -> handleButton_9200_menu_Click());
        Button button_9200_up = activity.findViewById(R.id.button_9200_up);
        button_9200_up.setOnClickListener(v -> handleButton_9200_up_Click());
        Button button_9200_x = activity.findViewById(R.id.button_9200_x);
        button_9200_x.setOnClickListener(v -> handleButton_9200_x_Click());
        Button button_9200_entr = activity.findViewById(R.id.button_9200_entr);
        button_9200_entr.setOnClickListener(v -> handleButton_9200_entr_Click());
        Button button_9200_hlc = activity.findViewById(R.id.button_9200_hlc);
        button_9200_hlc.setOnClickListener(v -> handleButton_9200_hlc_Click());
        Button button_9200_light = activity.findViewById(R.id.button_9200_light);
        button_9200_light.setOnClickListener(v -> handleButton_9200_light_Click());
        Button button_9200_alrt = activity.findViewById(R.id.button_9200_alrt);
        button_9200_alrt.setOnClickListener(v -> handleButton_9200_alrt_Click());

        // Other Buttons
        Button button_9200_erase = activity.findViewById(R.id.button_9200_erase);
        button_9200_erase.setOnClickListener(v -> handleButton_9200_erase_Click());
        Button button_9200_ptt = activity.findViewById(R.id.button_9200_ptt);
        button_9200_ptt.setOnClickListener(v -> handleButton_9200_ptt_Click());
        ImageButton imageButton_9200_power = activity.findViewById(R.id.imageButton_9200_power);
        imageButton_9200_power.setOnClickListener(v -> handleButton_9200_power_Click());
        ImageButton imageButton_9200_channel = activity.findViewById(R.id.imageButton_9200_channel);
        imageButton_9200_channel.setOnClickListener(v -> handleButton_9200_channel_Click());
        ImageButton imageButton_9200_volume = activity.findViewById(R.id.imageButton_9200_volume);
        imageButton_9200_volume.setOnClickListener(v -> handleButton_9200_volume_Click());

        TextView textView_9200_screen = activity.findViewById(R.id.textView_9200_screen);
    }

    private void handleButton_9200_insert_remove_fillgun_Click() {

    }

    private void handleButton_9200_reserved_Click() {

    }

    private void handleButton_9200_preset_Click() {

    }

    private void handleButton_9200_main_menu_Click() {

    }

    // Bottom Buttons
    private void handleButton_9200_1_Click() {

    }

    private void handleButton_9200_2_Click() {

    }

    private void handleButton_9200_3_Click() {

    }

    private void handleButton_9200_4_Click() {

    }

    private void handleButton_9200_5_Click() {

    }

    private void handleButton_9200_6_Click() {

    }

    private void handleButton_9200_7_Click() {

    }

    private void handleButton_9200_8_Click() {

    }

    private void handleButton_9200_9_Click() {

    }

    private void handleButton_9200_0_Click() {

    }

    private void handleButton_9200_mode_Click() {

    }

    private void handleButton_9200_auth_Click() {

    }

    private void handleButton_9200_serv_Click() {

    }

    public void handleButton_9200_menu_Click(){

    }

    private void handleButton_9200_up_Click() {

    }

    private void handleButton_9200_x_Click() {

    }

    private void handleButton_9200_entr_Click() {

    }

    private void handleButton_9200_hlc_Click() {

    }

    private void handleButton_9200_light_Click() {

    }

    private void handleButton_9200_alrt_Click() {

    }

    // Other Buttons
    private void handleButton_9200_erase_Click() {

    }

    private void handleButton_9200_ptt_Click() {

    }

    private void handleButton_9200_power_Click2() {
        ImageButton imageButton_9200_power  = activity.findViewById(R.id.imageButton_9200_power);
        float rotation = imageButton_9200_power.getRotation();
        rotation+=35;
        if (rotation >33){
            imageButton_9200_power.setRotation(-107);
        }else{
            imageButton_9200_power.setRotation(rotation);
        }


    }

    private void handleButton_9200_power_Click() {
        buttonManagerGlobal.handleButton_rotation(activity, activity.findViewById(R.id.imageButton_9200_power), 35,33,-107);
    }
    private void handleButton_9200_channel_Click() {
        ImageButton imageButton_9200_channel  = activity.findViewById(R.id.imageButton_9200_channel);
        float rotation = imageButton_9200_channel.getRotation();
        rotation+=35;
        if (rotation >210){
            imageButton_9200_channel.setRotation(-35);
        }else{
            imageButton_9200_channel.setRotation(rotation);
        }
    }

    private void handleButton_9200_volume_Click() {
        ImageButton imageButton_9200_volume  = activity.findViewById(R.id.imageButton_9200_volume);
        float rotation = imageButton_9200_volume.getRotation();
        rotation+=35;
        if (rotation >70){
            imageButton_9200_volume.setRotation(-180);
        }else{
            imageButton_9200_volume.setRotation(rotation);
        }
    }








}
