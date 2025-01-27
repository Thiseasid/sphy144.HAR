package com.example.sphy144_har.helpers;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sphy144_har.R;

import java.util.Arrays;

public class buttonManager4720 {

    private final Activity activity;
    private String mode4720 = "LISTEN";
    private int channel = 0;

    private int[] channelFreqListen4720;
    private int[] channelFreqTalk4720;

    private char[] digits = {' ', ' ', ' ', ' ', ' '};
    private int currentPosition = 0;

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
        button_4720_ptt.setOnClickListener(v -> handleButton_ptt_Click(mode4720));
    }

    public void handleButton_4720_reserved_Click(){

    }

    public void handleButton_4720_preset_Click(){

    }

    public void handleButton_ptt_Click(String mode4720){
        if (mode4720.equals("LISTEN")){
            displayDots4720();
        }else if (mode4720.equals("EDIT")){
            enterDigit();
        }
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
        switch ((int)(activity.findViewById(R.id.imageButton_4720_volume).getRotation())){
            case 0: //OFF
                mode4720="LISTEN";
                activity.findViewById(R.id.textView_4720_screen).setVisibility(View.INVISIBLE);
                break;
            case 36: //vol 1
            case 72: //vol 2
            case 108: //vol 3
            case 144: //vol 4
            case 180: //vol 5 *
                mode4720="LISTEN";
                activity.findViewById(R.id.textView_4720_screen).setVisibility(View.VISIBLE);
                resetEdit();
                display4720();
                break;
            case 216: // PR
                mode4720="EDIT";
                resetEdit();
                display4720();
                break;
            case 252: // PTR
                mode4720="EDIT";
                activity.findViewById(R.id.imageLight4720).setVisibility(View.INVISIBLE);
                resetEdit();
                display4720();
                break;
            case 288: // LIGHT
                mode4720="LISTEN";
                activity.findViewById(R.id.imageLight4720).setVisibility(View.VISIBLE);
                resetEdit();
                break;
        }
    }

        public void display4720(){
        TextView screen = activity.findViewById(R.id.textView_4720_screen);
        if (mode4720.equals("LISTEN")){
            screen.setText(channelFreqListen4720[channel]);
        }else{
            if (digits[0] == ' '){
                screen.setText(channelFreqListen4720[channel]);
            }else{
                digits[currentPosition] = (char)channel;
                screen.setText(Arrays.toString(digits));
            }
        }
    }

    public void displayDots4720(){
        TextView screen = activity.findViewById(R.id.textView_4720_screen);

        if (mode4720.equals("LISTEN")){
            char[] tempDigits = {'.', ' ',' ','.',' ',' ',' ','.'};
            tempDigits[1] = screen.getText().charAt(0);
            tempDigits[2] = screen.getText().charAt(1);
            tempDigits[4] = screen.getText().charAt(2);
            tempDigits[5] = screen.getText().charAt(3);
            tempDigits[6] = screen.getText().charAt(4);
            screen.setText(String.copyValueOf(tempDigits));
        }
    }

    public void resetEdit(){
        for (int i = 0; i<5;i++){
            digits[i] = ' ';
        }
        currentPosition = 0;
    }

    public void handleButton_4720_channel_Click(View v, MotionEvent event) {
        float x = event.getX();
        float width = v.getWidth();
        if (event.getAction() == MotionEvent.ACTION_UP){
            if(x<width/2){
                buttonManagerGlobal.handleButton_rotation(activity, activity.findViewById(R.id.imageButton_4720_channel), -36,0,325);
                if (activity.findViewById(R.id.imageButton_4720_channel).getRotation() == 0){
                    channel = 9;
                }else{
                    channel -= 1;
                }
            }else{
                buttonManagerGlobal.handleButton_rotation(activity, activity.findViewById(R.id.imageButton_4720_channel), 36,325,0);
                if (activity.findViewById(R.id.imageButton_4720_channel).getRotation() == 36){
                    channel = 0;
                }else{
                    channel += 1;
                }
            }
            // to add code for edit
        }
    }

}
