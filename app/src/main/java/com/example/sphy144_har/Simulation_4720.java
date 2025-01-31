package com.example.sphy144_har;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sphy144_har.helpers.FirebaseHelper;
import com.example.sphy144_har.helpers.buttonManager4720;
import com.google.firebase.database.DatabaseReference;

import java.util.Queue;

public class Simulation_4720 extends AppCompatActivity {

    private FirebaseHelper firebaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_simulation4720);

        firebaseHelper = new FirebaseHelper(this,"4720");
        buttonManager4720 buttonManager = new buttonManager4720(this,firebaseHelper);
        buttonManager.setupButtons();
        firebaseHelper.signInAnonymously();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Simulation_4720.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (firebaseHelper != null) {
            firebaseHelper.removeAudioListener();
        }
        MediaRecorder mediaRecorder = firebaseHelper.getMediaRecorder();
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }
        Queue<DatabaseReference> q1 = firebaseHelper.getToDeleteMessages();
        while (q1 != null && !q1.isEmpty()){
            q1.poll().removeValue();
        }
    }

}