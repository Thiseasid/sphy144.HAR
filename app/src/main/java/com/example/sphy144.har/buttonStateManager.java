package com.example.har;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageButton;

public class buttonStateManager {
    private int buttonState = 0;

    public void rotateButton(ImageButton button) {
        float startRotation = 30f * buttonState;
        buttonState = (buttonState + 1) % 5;  // Cycle through states
        ObjectAnimator rotation = ObjectAnimator.ofFloat(button, "rotation", startRotation, startRotation + 90f);
        rotation.setDuration(300);
        rotation.start();
    }

    public void saveButtonState(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ButtonStates", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, buttonState);
        editor.apply();
    }

    public void loadButtonState(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ButtonStates", Context.MODE_PRIVATE);
        buttonState = sharedPreferences.getInt(key, 0);
    }

    public void resetButton(ImageButton button) {
        buttonState = 0;
        button.setRotation(0);  // Reset rotation
    }

}
