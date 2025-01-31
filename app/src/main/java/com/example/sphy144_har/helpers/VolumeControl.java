package com.example.sphy144_har.helpers;

import android.app.Activity;
import android.content.Context;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;

public class VolumeControl {
    private AudioManager audioManager;

    public VolumeControl(Context context){
        audioManager = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
    }

    public void setVolumeToPresetLevel(int level){
        if(!audioManager.isVolumeFixed()) {
            int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); //15
            int finalVol = (int) (1.0/4*level * maxVolume);
            if (finalVol > maxVolume) {
                finalVol = maxVolume; // Ensure we don't exceed the max
            }
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, finalVol, AudioManager.FLAG_SHOW_UI);

        }
    }

}
