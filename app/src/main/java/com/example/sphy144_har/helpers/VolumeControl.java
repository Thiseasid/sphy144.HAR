package com.example.sphy144_har.helpers;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.example.sphy144_har.R;

public class VolumeControl {
    private final AudioManager audioManager;
    private MediaPlayer mediaPlayer;
    private final Context context;

    public VolumeControl(Context context){
        this.context = context;
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

    public void playStatic(){
        if (mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(context, R.raw.static_sound);
            mediaPlayer.setLooping(true);
        }
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start(); // Start the sound
        }
    }

    public void stopStatic(){
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null; // Reset MediaPlayer
        }
    }

}
