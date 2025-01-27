package com.example.sphy144_har.helpers;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sphy144_har.R;

public class buttonManager4720 {

    private final Activity activity;
    private String mode4720 = "LISTEN";
    private int channel = 0;
    private int channelToSave = 0;


    private int[] channelFreqListen4720 = {47000, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int[] channelFreqTalk4720 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private char[] digits = {' ', ' ', ' ', ' ', ' '};
    private int currentPosition = 0;

    public buttonManager4720(Activity activity) {
        this.activity = activity;
    }

    public void setupButtons() {

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
            handleButton_4720_volume_Click(v, event);
            return true;
        });
        Button button_4720_channel = activity.findViewById(R.id.button_4720_channel);
        button_4720_channel.setOnTouchListener((v, event) -> {
            handleButton_4720_channel_Click(v, event);
            return true;
        });

        // Racal Buttons
        Button button_4720_ptt = activity.findViewById(R.id.button_4720_ptt);
        button_4720_ptt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        handleButton_ptt_Click();
                        return true;
                    case MotionEvent.ACTION_UP:
                        handleButton_ptt_Release();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    public void handleButton_4720_reserved_Click() {
        //ADD RESERVERD FUNCTION
    }

    public void handleButton_4720_preset_Click() {
        //ADD PRESETS FUNCTION
    }

    public void handleButton_ptt_Click() { // To add PTT VOICE ON CLICK
        if (mode4720.equals("LISTEN")) {
            //ADD VOICE FUNCTION
        } else {
            if (digits[0] == ' ' && channel == channelToSave) {

            } else {
                if (currentPosition < 3) {
                    currentPosition += 1;
                    displayFreq4720();
                } else if (currentPosition < 4) {
                    switch (channel) {
                        case 0:
                            digits[currentPosition] = (char) ('0');
                            digits[currentPosition + 1] = (char) ('0');
                            break;
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                            digits[currentPosition] = (char) ('2');
                            digits[currentPosition + 1] = (char) ('5');
                            break;
                        case 5:
                            digits[currentPosition] = (char) ('5');
                            digits[currentPosition + 1] = (char) ('0');
                            break;
                        default:
                            digits[currentPosition] = (char) ('7');
                            digits[currentPosition + 1] = (char) ('5');
                            break;
                    }
                    currentPosition += 1;
                    int newFreq = Integer.parseInt(new String(digits));
                    if (newFreq >= 30000 && newFreq < 88000) {
                        if (mode4720.equals("EDIT_PTR")){
                            channelFreqListen4720[channelToSave] = newFreq;
                        }else if (mode4720.equals("EDIT_PR")){
                            channelFreqTalk4720[channelToSave] = newFreq;
                        }
                        displayFreq4720();
                    } else {
                        resetEdit();
                    }
                }
            }
        }
    }

    public void handleButton_ptt_Release() { // To add PTT VOICE ON RELEASE
        if (mode4720.equals("LISTEN")) {
            //ADD VOICE FUNCTION
        }
    }

    public void handleButton_4720_volume_Click(View v, MotionEvent event) {
        float x = event.getX();
        float width = v.getWidth();

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (x < width / 2) {
                if (activity.findViewById(R.id.imageButton_4720_volume).getRotation() != 0) {
                    buttonManagerGlobal.handleButton_rotation(activity, activity.findViewById(R.id.imageButton_4720_volume), -36, 0, 288);
                }

            } else {
                if (activity.findViewById(R.id.imageButton_4720_volume).getRotation() != 288) {
                    buttonManagerGlobal.handleButton_rotation(activity, activity.findViewById(R.id.imageButton_4720_volume), 36, 288, 0);
                }
            }
        }
        switch ((int) (activity.findViewById(R.id.imageButton_4720_volume).getRotation())) {
            case 0: //OFF
                activity.findViewById(R.id.textView_4720_freq).setVisibility(View.INVISIBLE);
                activity.findViewById(R.id.textView_4720_channel).setVisibility(View.INVISIBLE);
                activity.findViewById(R.id.textView_4720_dots).setVisibility(View.INVISIBLE);
                break;
            case 36: //vol 1
            case 72: //vol 2
            case 108: //vol 3
            case 144: //vol 4
            case 180: //vol 5 *
                mode4720 = "LISTEN";
                resetEdit();
                activity.findViewById(R.id.textView_4720_freq).setVisibility(View.VISIBLE);
                activity.findViewById(R.id.textView_4720_channel).setVisibility(View.VISIBLE);
                resetEdit();
                displayFreq4720();
                break;
            case 216: // PR
                mode4720 = "EDIT_PR";
                channelToSave = channel;
                resetEdit();
                displayFreq4720();
                break;
            case 252: // PTR
                mode4720 = "EDIT_PTR";
                channelToSave = channel;
                activity.findViewById(R.id.imageLight4720).setVisibility(View.INVISIBLE);
                resetEdit();
                displayFreq4720();
                break;
            case 288: // LIGHT
                mode4720 = "LISTEN";
                activity.findViewById(R.id.imageLight4720).setVisibility(View.VISIBLE);
                resetEdit();
                break;
        }
    }

    public void displayFreq4720() {
        TextView screen = activity.findViewById(R.id.textView_4720_freq);
        if (mode4720.equals("LISTEN")) {
            if (channelFreqListen4720[channel] > 30000) {
                screen.setText(String.valueOf(channelFreqListen4720[channel]));
            }else{
                screen.setText("     ");
            }
        } else {
            if (digits[0] == ' ') {
                if (channelFreqListen4720[channel] > 30000) {
                    screen.setText(String.valueOf(channelFreqListen4720[channel]));
                }else{
                    screen.setText("     ");
                }
            } else {
                screen.setText(new String(digits));


            }
        }
    }

    public void displayDots4720() {
        if (mode4720.equals("LISTEN")) {
            activity.findViewById(R.id.textView_4720_dots).setVisibility(View.VISIBLE);
        }
    }


    public void resetEdit() {
        for (int i = 0; i < 5; i++) {
            digits[i] = ' ';
        }
        currentPosition = 0;
    }

    public void handleButton_4720_channel_Click(View v, MotionEvent event) {
        float x = event.getX();
        float width = v.getWidth();
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (x < width / 2) {
                buttonManagerGlobal.handleButton_rotation(activity, activity.findViewById(R.id.imageButton_4720_channel), -36, 0, 325);
                if (activity.findViewById(R.id.imageButton_4720_channel).getRotation() == 0) {
                    channel = 9;
                } else {
                    channel -= 1;
                }
            } else {
                buttonManagerGlobal.handleButton_rotation(activity, activity.findViewById(R.id.imageButton_4720_channel), 36, 325, 0);
                if (activity.findViewById(R.id.imageButton_4720_channel).getRotation() == 36) {
                    channel = 0;
                } else {
                    channel += 1;
                }
            }

            TextView c = activity.findViewById(R.id.textView_4720_channel);
            if (mode4720.equals("LISTEN")){
                c.setText(String.valueOf(channel));
                displayFreq4720();
            }else{
                c.setText(String.valueOf(channelToSave));
                digits[currentPosition] = (char) ('0' + channel);
                displayFreq4720();
            }

        }
    }

}
