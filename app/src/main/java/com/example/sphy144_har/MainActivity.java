package com.example.sphy144_har;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sphy144_har.helpers.buttonManagerMainMenu;
import com.example.sphy144_har.helpers.firebaseHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class MainActivity extends AppCompatActivity {


    //__ TEST AUDIO SEND
    //_ TEST AUDIO SEND
    // TEST AUDIO SEND
    private static final String TAG = "MainActivity";
    private MediaRecorder mediaRecorder;
    private String audioFilePath;
    private firebaseHelper firebaseHelper;
    // TEST AUDIO SEND
    //_ TEST AUDIO SEND
    //__ TEST AUDIO SEND

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonManagerMainMenu buttonManager = new buttonManagerMainMenu(this);
        buttonManager.setupButtons();


        //__ TEST AUDIO SEND
        //_ TEST AUDIO SEND
        // TEST AUDIO SEND

        // Request RECORD_AUDIO permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        firebaseHelper = new firebaseHelper();
        firebaseHelper.signInAnonymously();  // Sign in anonymously
        Button recordButton = findViewById(R.id.button_Recording);

        recordButton.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startRecording();
                    return true;

                case MotionEvent.ACTION_UP:
                    stopRecordingAndSend();
                    return true;
            }
            return false;
        });
    }
    private void startRecording() {
        try {
            audioFilePath = getExternalFilesDir(null).getAbsolutePath() + "/audio.3gp"; // Set file path

            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(audioFilePath);
            mediaRecorder.prepare();
            mediaRecorder.start();
            Log.d(TAG, "Recording started");
        } catch (IOException e) {
            Log.e(TAG, "Error starting recording", e);
        }
    }

    // Stop recording and send the audio as Base64 to Firebase
    private void stopRecordingAndSend() {
        if (mediaRecorder != null) {
            try {
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
            } catch (RuntimeException e) {
                Log.e("MediaRecorder", "stop() failed: " + e.getMessage());
            }
            Log.d(TAG, "Recording stopped");

            try {
                // Convert the audio file to Base64
                String base64Audio = convertAudioToBase64(audioFilePath);
                String userId = firebaseHelper.getUserId(); // Get the Firebase user ID
                firebaseHelper.sendAudioMessage(userId, base64Audio);  // Send to Firebase
            } catch (IOException e) {
                Log.e(TAG, "Error encoding audio to Base64", e);
            }
        }
    }

    // Convert audio file to Base64 string
    private String convertAudioToBase64(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytesArray = new byte[(int) file.length()];
        fileInputStream.read(bytesArray);
        fileInputStream.close();
        return Base64.encodeToString(bytesArray, Base64.DEFAULT);
    }


        // TEST AUDIO SEND
        //_ TEST AUDIO SEND
        //__ TEST AUDIO SEND

    }
