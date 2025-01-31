package com.example.sphy144_har.helpers;

import static com.example.sphy144_har.helpers.buttonManagerGlobal.*;

import android.app.Activity;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.sphy144_har.R;

public class buttonManager4720 {

    private final Activity activity;
    private FirebaseHelper firebaseHelper;
    private Handler handler = new Handler();
    private VolumeControl volumeControl;
    private String mode4720 = "LISTEN";
    private int channel = 0;
    private int channelToSave = 0;
    private boolean flagEditPTT = false;
    private boolean flagEditChannel = false;
    private int[] channelFreqListen4720 = {30000, 33125, 35975, 43025, 47050, 55000, 63500, 70000, 80000, 87975};
    private int[] channelFreqTalk4720 = {30000, 33125, 35975, 43025, 47050, 55000, 63500, 80000, 70000, 65000};
    private char[] digits = {' ', ' ', ' ', ' ', ' '};
    private int currentPosition = 0;

    public buttonManager4720(Activity activity,FirebaseHelper firebaseHelper) {
        this.activity = activity;
        this.firebaseHelper = firebaseHelper;
        this.volumeControl = new VolumeControl(activity);
    }

    public void setupButtons() {

        // Menu Buttons
        Button button_4720_ConnectionStatus = activity.findViewById(R.id.button_4720_ConnectionStatus);
        button_4720_ConnectionStatus.setOnClickListener(v -> handleButton_4720_ConnectionStatus_Click());
        Button button_4720_clear = activity.findViewById(R.id.button_4720_clear);
        button_4720_clear.setOnClickListener(v -> handleButton_4720_clear_Click());
        Button button_4720_preset = activity.findViewById(R.id.button_4720_preset);
        button_4720_preset.setOnClickListener(v -> handleButton_4720_preset_Click());
        Button button_4720_main_menu = activity.findViewById(R.id.button_4720_main_menu);
        button_4720_main_menu.setOnClickListener(v -> handleButton_mainMenu_Click(activity));

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

        // PTT Button
        Button button_4720_ptt = activity.findViewById(R.id.button_4720_ptt);
        button_4720_ptt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        activity.findViewById(R.id.layout_4720_frame_background).setBackground(ContextCompat.getDrawable(activity, R.drawable.racal_prm4720b_button));
                        handleButton_ptt_Click();
                        return true;
                    case MotionEvent.ACTION_UP:
                        activity.findViewById(R.id.layout_4720_frame_background).setBackground(ContextCompat.getDrawable(activity, R.drawable.racal_prm4720b));
                        handleButton_ptt_Release();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    // ____________________________ Menu Buttons ____________________________
    public void handleButton_4720_ConnectionStatus_Click() {
        //ADD RESERVERD FUNCTION
        if (mode4720.equals("LISTEN")) {
            Toast.makeText(activity, "Connection is Established.", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(activity, "Edit Mode", Toast.LENGTH_SHORT).show();
        }
    }

    public void handleButton_4720_preset_Click(){
        firebaseHelper.stopListeningForAudioMessages(String.valueOf(channelFreqListen4720[channel]));
        channelFreqListen4720 = new int[] {30000, 33125, 35975, 43025, 47050, 55000, 63500, 70000, 80000, 87975};
        channelFreqTalk4720 = new int[] {30000, 33125, 35975, 43025, 47050, 55000, 63500, 80000, 70000, 65000};

        displayFreqListen4720();
    }

    public void handleButton_4720_clear_Click() {
        resetEdit();
        for (int i = 0; i < 10; i++) {
            channelFreqListen4720[i] = 0;
            channelFreqTalk4720[i] = 0;
        }
        firebaseHelper.stopListeningForAudioMessages(String.valueOf(channelFreqListen4720[channel]));
        displayFreqListen4720();
    }

    // ____________________________ Rotational Buttons ____________________________
    public void handleButton_4720_volume_Click(View v, MotionEvent event) {
        float x = event.getX();
        float width = v.getWidth();
        float rotation = activity.findViewById(R.id.imageButton_4720_volume).getRotation();
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (x < width / 2) {
                if (rotation == 0) {
                    return;
                }else{
                    handleButton_rotation(activity, activity.findViewById(R.id.imageButton_4720_volume), -36, 0, 288);
                    arrowFade(activity.findViewById(R.id.arrowVolumeLeft));
                }

            } else {
                if (rotation == 288) {
                    return;
                }else{
                    handleButton_rotation(activity, activity.findViewById(R.id.imageButton_4720_volume), 36, 288, 0);
                    arrowFade(activity.findViewById(R.id.arrowVolumeRight));
                }
            }
            switch ((int) (activity.findViewById(R.id.imageButton_4720_volume).getRotation())) {
                case 0: //OFF
                    mode4720 = "OFF";
                    undisplayScreen4720();
                    activity.findViewById(R.id.button_4720_ptt).setEnabled(false);
                    firebaseHelper.stopListeningForAudioMessages(String.valueOf(channelFreqListen4720[channel]));
                    volumeControl.setVolumeToPresetLevel(0);
                    break;
                case 36: //vol 1
                    activity.findViewById(R.id.button_4720_ptt).setEnabled(true);
                    volumeControl.setVolumeToPresetLevel(1);
                    mode4720 = "LISTEN";
                    firebaseHelper.listenForAudioMessages(String.valueOf(channelFreqListen4720[channel]));
                    displayScreen4720();
                    break;
                case 72: //vol 2
                    volumeControl.setVolumeToPresetLevel(2);
                    break;
                case 108: //vol 3
                    volumeControl.setVolumeToPresetLevel(3);
                    break;
                case 144: //vol 4
                    volumeControl.setVolumeToPresetLevel(4);
                    volumeControl.stopStatic();
                    break;
                case 180: //vol 5 *
                    mode4720 = "LISTEN";
                    // ADD STATIC
                    volumeControl.playStatic();
                    firebaseHelper.listenForAudioMessages(String.valueOf(channelFreqListen4720[channel]));
                    break;
                case 216: // PR
                    mode4720 = "EDIT_PR";
                    volumeControl.stopStatic();
                    firebaseHelper.stopListeningForAudioMessages(String.valueOf(channelFreqListen4720[channel]));
                    break;
                case 252: // PTR
                    mode4720 = "EDIT_PTR";
                    firebaseHelper.stopListeningForAudioMessages(String.valueOf(channelFreqListen4720[channel]));
                    handler.postDelayed(() -> {
                        activity.findViewById(R.id.layout_4720_frame_background).setBackground(ContextCompat.getDrawable(activity, R.drawable.racal_prm4720b));
                    }, 5000);
                    break;
                case 288: // LIGHT
                    mode4720 = "LISTEN";
                    firebaseHelper.listenForAudioMessages(String.valueOf(channelFreqTalk4720[channel]));
                    handler.removeCallbacksAndMessages(null);
                    activity.findViewById(R.id.layout_4720_frame_background).setBackground(ContextCompat.getDrawable(activity, R.drawable.racal_prm4720b_light));
                    break;
            }
            resetEdit();
            displayFreqListen4720();
            displayChannel(channel);
        }
    }

    public void handleButton_4720_channel_Click(View v, MotionEvent event) {
        firebaseHelper.stopListeningForAudioMessages(String.valueOf(channelFreqListen4720[channel]));
        float x = event.getX();
        float width = v.getWidth();
        float rotation = activity.findViewById(R.id.imageButton_4720_channel).getRotation();
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (x < width / 2) {
                if (rotation == 36) {
                    return;
                }else{
                    handleButton_rotation(activity, activity.findViewById(R.id.imageButton_4720_channel), -36, 0, 324);
                    channel -= 1;
                    arrowFade(activity.findViewById(R.id.arrowChannelLeft));
                }
            } else {
                if (rotation == 0) {
                    return;
                }else{
                    handleButton_rotation(activity, activity.findViewById(R.id.imageButton_4720_channel), 36, 324, 0);
                    channel += 1;
                    arrowFade(activity.findViewById(R.id.arrowChannelRight));
                }
            }

            if (mode4720.equals("LISTEN")) {
                firebaseHelper.listenForAudioMessages(String.valueOf(channelFreqListen4720[channel]));
                displayChannel(channel);
            } else {
                if (!flagEditPTT) {
                    flagEditChannel = true;
                    displayChannel(channel);
                } else {
                    displayChannel(channelToSave);
                    if (currentPosition < 3) {
                        digits[currentPosition] = (char) ('0' + channel);
                    } else if (currentPosition == 3) {
                        setLastDigits();
                    }
                }
            }
            displayFreqListen4720();
        }
    }

    // ____________________________ PTT Button ____________________________
    public void handleButton_ptt_Click() {
        if (mode4720.equals("LISTEN")) {
            displayFreqTalk4720();
            displayDots4720();
            //ADD VOICE TALK FUNCTION
            firebaseHelper.startRecording();
        } else {
            if (flagEditChannel && !flagEditPTT) {
                flagEditPTT = true;
                channelToSave = channel;
                displayFreqListen4720();
            } else if (flagEditPTT) {
                if (currentPosition < 3) {
                    currentPosition += 1;
                    displayFreqListen4720();
                } else if (currentPosition == 3) {
                    currentPosition += 1;
                    int newFreq = Integer.parseInt(new String(digits));
                    if (newFreq >= 30000 && newFreq < 88000) {
                        if (mode4720.equals("EDIT_PTR")) {
                            channelFreqListen4720[channelToSave] = newFreq;
                            channelFreqTalk4720[channelToSave] = newFreq;
                        } else if (mode4720.equals("EDIT_PR")) {
                            channelFreqTalk4720[channelToSave] = newFreq;
                        }
                        displayFreqListen4720();
                    } else {
                        Toast.makeText(activity, "Λάθος Εισαγωγή!\nΣυχνότητα από 30.000 έως 87.975!", Toast.LENGTH_SHORT).show();
                        showVariableValue(activity, "Λάθος Εισαγωγή", "Ο ασύρματος λειτουργεί από τη συχνότητα 30.000 έως 87.975");
                        resetEdit();
                    }
                }
            }
        }
    }

    public void handleButton_ptt_Release() { // To add PTT VOICE ON RELEASE
        if (mode4720.equals("LISTEN")) {
            displayFreqListen4720();
            undisplayDots4720();
            //ADD VOICE FUNCTION
            firebaseHelper.stopRecordingAndSend(String.valueOf(channelFreqTalk4720[channel]));
        }
    }

    // ____________________________ Helper Functions ____________________________
    public void displayChannel(int channel) {
        TextView chan = activity.findViewById(R.id.textView_4720_channel);
        chan.setText(String.valueOf(channel));
    }

    public void displayFreqTalk4720() {
        TextView screen = activity.findViewById(R.id.textView_4720_freq);
        screen.setText(String.valueOf(channelFreqTalk4720[channel]));
    }

    public void displayFreqListen4720() {
        TextView screen = activity.findViewById(R.id.textView_4720_freq);
        if (mode4720.equals("LISTEN") || !flagEditPTT) {
            if (channelFreqListen4720[channel] >= 30000) {
                screen.setText(String.valueOf(channelFreqListen4720[channel]));
            } else {
                screen.setText("     ");
            }
        } else if (flagEditPTT) {
            screen.setText(new String(digits));
        }
    }

    public void displayDots4720() {
        activity.findViewById(R.id.textView_4720_dots).setVisibility(View.VISIBLE);
    }

    public void undisplayDots4720() {
        activity.findViewById(R.id.textView_4720_dots).setVisibility(View.INVISIBLE);
    }

    public void displayScreen4720() {
        activity.findViewById(R.id.textView_4720_freq).setVisibility(View.VISIBLE);
        activity.findViewById(R.id.textView_4720_P).setVisibility(View.VISIBLE);
        activity.findViewById(R.id.textView_4720_channel).setVisibility(View.VISIBLE);
    }

    public void undisplayScreen4720() {
        activity.findViewById(R.id.textView_4720_freq).setVisibility(View.INVISIBLE);
        activity.findViewById(R.id.textView_4720_channel).setVisibility(View.INVISIBLE);
        activity.findViewById(R.id.textView_4720_P).setVisibility(View.INVISIBLE);
        activity.findViewById(R.id.textView_4720_dots).setVisibility(View.INVISIBLE);
    }

    public void resetEdit() {
        for (int i = 0; i < 5; i++) {
            digits[i] = ' ';
        }
        currentPosition = 0;
        flagEditChannel = false;
        flagEditPTT = false;
    }

    public void setLastDigits() {
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
    }

    public void arrowFade(ImageView arrow) {
        final Animation fadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in);
        final Animation fadeOut = AnimationUtils.loadAnimation(activity, R.anim.fade_out);
        arrow.setVisibility(View.VISIBLE);
        arrow.startAnimation(fadeOut);
        arrow.postDelayed(new Runnable() {
            @Override
            public void run() {
                arrow.setVisibility(View.GONE); // Hide the image after fade-out
            }
        }, 500); // Hide after fade-out duration (500ms)
    }

}
