package com.example.sphy144_har.helpers;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.example.sphy144_har.R;

public class FirebaseHelper {
    // ____________________________ Variables ____________________________
    private FirebaseAuth mAuth;
    private DatabaseReference database;
    private ChildEventListener audioListener;
    private String audioFilePath;
    private MediaRecorder mediaRecorder;
    private long minPressTime = 500;
    private long startTime = 0;
    private String radioType;
    private Queue<DatabaseReference> toDeleteMessages = new LinkedList<>();
    private final Activity activity;

    // ____________________________ Constructor ____________________________
    public FirebaseHelper(Activity activity, String radioType) {
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        this.radioType = radioType;
        this.activity = activity;
    }

    // ____________________________ Getters ____________________________
    public String getUserId() {
        if (mAuth.getCurrentUser() != null) {
            return mAuth.getCurrentUser().getUid();
        }
        return null;
    }

    public Queue getToDeleteMessages() {
        return toDeleteMessages;
    }

    public MediaRecorder getMediaRecorder() {
        return mediaRecorder;
    }

    public ChildEventListener getAudioListener(){
        return audioListener;
    }

    public void setAudioListener(ChildEventListener listener){
        audioListener = listener;
    }

    // ____________________________ Connect to Firebase ____________________________
    public void signInAnonymously() {
        Button button = activity.findViewById(R.id.button_4720_ConnectionStatus);
        mAuth.signInAnonymously().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                button.setBackgroundColor(Color.parseColor("#4CAF50"));
            } else {
                button.setBackgroundColor(Color.RED);
                button.setText("Status: No Connection");
                Log.e("FirebaseAuth", "signInAnonymously: Failed!", task.getException());
            }
        });
    }

    public void startRecording() {
        startTime = System.currentTimeMillis();
        try {
            audioFilePath = activity.getExternalFilesDir(null).getAbsolutePath() + "/audio.3gp"; // Set file path
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_RECOGNITION);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setAudioEncodingBitRate(64000);
            mediaRecorder.setOutputFile(audioFilePath);
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            Toast.makeText(activity, "Error!\nStarting recording", Toast.LENGTH_SHORT).show();
            Log.e("Simulation_4720", "Error: " + e.getMessage());
        }

    }

    // Stop recording and send the audio as Base64 to Firebase
    public void stopRecordingAndSend(String freqSave) {
        long pressDuration = System.currentTimeMillis() - startTime;
        if (mediaRecorder != null) {
            if (pressDuration >= minPressTime) {
                try {
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                } catch (RuntimeException e) {
                    Toast.makeText(activity, "Error!\nStopping Recording", Toast.LENGTH_SHORT).show();
                    Log.e("Simulation_4720", "Error: " + e.getMessage());
                }
                try {
                    // Convert the audio file to Base64
                    String base64Audio = convertAudioToBase64(audioFilePath);
                    String userId = getUserId(); // Get the Firebase user ID
                    sendAudioMessage(userId, freqSave, base64Audio);  // Send to Firebase
                } catch (IOException e) {
                    Toast.makeText(activity, "Error!\nEncoding audio", Toast.LENGTH_SHORT).show();
                    Log.e("Simulation_4720", "Error: " + e.getMessage());
                }
            } else {
                mediaRecorder.release();
                mediaRecorder = null;
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

    // Send an audio message as Base64 encoded string
    public void sendAudioMessage(String userId,String freqSave , String base64Audio) {
        String messageId = database.child("messages").child(freqSave).push().getKey();
        DatabaseReference message = database.child("messages").child(freqSave).child(messageId);
        if (messageId != null) {
            Map<String, Object> audioMessageData = new HashMap<>();
            audioMessageData.put("audio", base64Audio);  // Store the Base64 audio
            audioMessageData.put("timestamp", System.currentTimeMillis());
            audioMessageData.put("downloads", 0);
            audioMessageData.put("radio", radioType);
            Map<String, Boolean> clients = new HashMap<>();
            clients.put(userId, true); // DEBUG FOR SINGLE PHONE TESTING
            audioMessageData.put("clients", clients);
            message.setValue(audioMessageData)
                    .addOnCompleteListener(task -> {
                        // Data added successfully, now schedule deletion
                        toDeleteMessages.add(message);
                        new Handler().postDelayed(() -> {
                            message.removeValue();
                        }, 20000); // 10,000 milliseconds = 10 seconds
                        toDeleteMessages.poll();
                    });
        }
    }

    // Listen for new messages and play them
    public void listenForAudioMessages(String freqLoad) {
        stopListeningForAudioMessages(freqLoad);
        String userId = getUserId();
        DatabaseReference userMessagesRef = database.child("messages").child(freqLoad);
        audioListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot messageSnapshot, String previousChildName) {
                String messageId = messageSnapshot.getKey();
                Log.d("MessageId", messageId);
                String base64Audio = messageSnapshot.child("audio").getValue(String.class);
                Map<String, Boolean> clients = (Map<String, Boolean>) messageSnapshot.child("clients").getValue();
                if(base64Audio != null){
                    if (!clients.containsKey(userId)) {
                        clients.put(userId, true);
                        database.child("messages").child(freqLoad).child(messageId).child("clients").setValue(clients);
                        updateDownloadCount(freqLoad, messageId);
                        playReceivedAudio(base64Audio);
                    }else if (!clients.get(userId)) {
                        clients.put(userId, true);
                        database.child("messages").child(freqLoad).child(messageId).child("clients").setValue(clients);
                        updateDownloadCount(freqLoad, messageId);
                        playReceivedAudio(base64Audio);
                    }
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}
            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(activity, "Error!\nListening Firebase", Toast.LENGTH_SHORT).show();
            }};
        userMessagesRef.addChildEventListener(audioListener);
    }

    // Convert Base64 to audio file and play it
    private void playReceivedAudio(String base64Audio) {
        try {
            byte[] decodedBytes = Base64.decode(base64Audio, Base64.DEFAULT);
            File audioFile = File.createTempFile("received_audio", ".3gp");

            // NECESSARY! to write to a file for normal playback
            FileOutputStream fos = new FileOutputStream(audioFile);
            fos.write(decodedBytes);
            fos.close();

            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(audioFile.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.start();

            mediaPlayer.setOnCompletionListener(mp -> {
                mp.release();
                audioFile.delete();
            });

        } catch (IOException e) {
            Toast.makeText(activity, "Error!\nPlaying audio", Toast.LENGTH_SHORT).show();
            Log.e("Simulation_4720", "Error: " + e.getMessage());
        }
    }

    // Update downloads for Debugging
    private void updateDownloadCount(String freqSave, String messageId) {
        DatabaseReference messageRef = database.child("messages").child(freqSave).child(messageId);

        messageRef.child("downloads").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Integer downloads = task.getResult().getValue(Integer.class);
                if (downloads == null) {
                    downloads = 0;
                }
                messageRef.child("downloads").setValue(downloads + 1);
            }
        });
    }

    public void stopListeningForAudioMessages(String freqLoad) {
        if (audioListener != null) {
            database.child("messages").child(freqLoad).removeEventListener(audioListener);
            audioListener = null;
        }
    }

}
