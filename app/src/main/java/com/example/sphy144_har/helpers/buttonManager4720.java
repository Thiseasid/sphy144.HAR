package com.example.sphy144_har.helpers;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.sphy144_har.R;

public class buttonManager4720 {

    private final Activity activity;

    public buttonManager4720(Activity activity) {
        this.activity = activity;
    }

    public void setupButtons(){

        // Menu Buttons
        Button button_4720_reserved = activity.findViewById(R.id.button_4720_reserved);
        button_4720_reserved.setOnClickListener(v -> handleButton_4720_reserved_Click());
        Button button_4720_preset = activity.findViewById(R.id.button_4720_preset);
        button_4720_preset.setOnClickListener(v -> handleButton_4720_preset_Click());
        Button button_4720_main_menu = activity.findViewById(R.id.button_4720_main_menu);
        button_4720_main_menu.setOnClickListener(v -> buttonManagerGlobal.handleButton_mainMenu_Click(activity));

        // Rotation Buttons
        Button button_4720_volume = activity.findViewById(R.id.button_4720_volume);
        button_4720_volume.setOnTouchListener((v, event) -> {
            handleButton_4720_volume_Click(v,event);
            return true;
        });
        Button button_4720_channel = activity.findViewById(R.id.button_4720_channel);
        button_4720_channel.setOnTouchListener((v, event) -> {
            handleButton_4720_channel_Click(v,event);
            return true;
        });

        // Racal Buttons
        Button button_4720_ptt = activity.findViewById(R.id.button_4720_ptt);
        button_4720_ptt.setOnClickListener(v -> handleButton_ptt_Click());
    }

    public void handleButton_4720_reserved_Click(){

    }

    public void handleButton_4720_preset_Click(){

    }

    public void handleButton_ptt_Click(){

    }

    public void handleButton_4720_volume_Click(View v, MotionEvent event){
        float x = event.getX();
        float width = v.getWidth();

        if (event.getAction() == MotionEvent.ACTION_UP){
            if(x<width/2){
                if (activity.findViewById(R.id.imageButton_4720_volume).getRotation() != 0){
                    buttonManagerGlobal.handleButton_rotation(activity, activity.findViewById(R.id.imageButton_4720_volume), -36,0,288);
                }

            }else{
                if (activity.findViewById(R.id.imageButton_4720_volume).getRotation() != 288){
                    buttonManagerGlobal.handleButton_rotation(activity, activity.findViewById(R.id.imageButton_4720_volume), 36,288,0);
                }

            }
        }


    }

    public void handleButton_4720_channel_Click(View v, MotionEvent event) {
        float x = event.getX();
        float width = v.getWidth();

        if (event.getAction() == MotionEvent.ACTION_UP){
            if(x<width/2){
                buttonManagerGlobal.handleButton_rotation(activity, activity.findViewById(R.id.imageButton_4720_channel), -36,0,325);
            }else{
                buttonManagerGlobal.handleButton_rotation(activity, activity.findViewById(R.id.imageButton_4720_channel), 36,325,0);
            }
        }

    }

}
