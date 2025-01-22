package com.example.sphy144_har.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageButton;

import com.example.sphy144_har.MainActivity;
import com.example.sphy144_har.R;

public class buttonManagerGlobal {

    public static void handleButton_mainMenu_Click(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            ((Activity) context).finish();
        }
    }

    public static void handleButton_rotation(Activity activity, ImageButton imageButton, int step, int limit, int startPos){

        float rotation = imageButton.getRotation();
        rotation += step;
        if (step>0){
            if (rotation >limit){
                imageButton.setRotation(startPos);
            }else{
                imageButton.setRotation(rotation);
            }
        }else{
            if (rotation <limit){
                imageButton.setRotation(startPos);
            }else{
                imageButton.setRotation(rotation);
            }
        }

    }

}
