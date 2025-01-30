package com.example.sphy144_har.helpers;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.sphy144_har.Feedback;
import com.example.sphy144_har.MainActivity;
import com.example.sphy144_har.R;
import com.example.sphy144_har.Simulation_4720;
import com.example.sphy144_har.Simulation_9200;
import com.example.sphy144_har.Study;

public class buttonManagerMainMenu {

    private final Activity activity;

    public buttonManagerMainMenu(Activity activity) {
        this.activity = activity;
    }

    public void setupButtons(){


    Button button_4720 = activity.findViewById(R.id.button_4720);
    Button button_9200 = activity.findViewById(R.id.button_9200);
    Button button_study = activity.findViewById(R.id.button_study);
    Button button_feedback = activity.findViewById(R.id.button_feedback);
    Button button_exit = activity.findViewById(R.id.button_exit);

        button_exit.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            activity.finish();
            System.exit(0);
        }
    });

        button_4720.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(activity, Simulation_4720.class);
            activity.startActivity(intent);
            activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            activity.finish();
        }
    });

        button_9200.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(activity, Simulation_9200.class);
            activity.startActivity(intent);
            activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            activity.finish();
        }
    });

        button_study.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(activity, Study.class);
            activity.startActivity(intent);
            activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            activity.finish();
        }
    });

        button_feedback.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(activity, Feedback.class);
            activity.startActivity(intent);
            activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            activity.finish();
        }
    });
    }
}
