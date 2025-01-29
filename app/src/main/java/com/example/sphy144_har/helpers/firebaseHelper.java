package com.example.sphy144_har.helpers;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FirebaseHelper {

    // Initialize Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference database;
    private static final String TAG = "FirebaseHelper";
    private int clientsCount = 10; // 🔹 Change this to match the number of clients

    private final Activity activity;

    // Constructor
    public FirebaseHelper(Activity activity) {
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        this.activity = activity;
    }

    public void signInAnonymously() {
        mAuth.signInAnonymously()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Log.d(TAG, "User signed in: " + user.getUid());
                    } else {
                        Log.e(TAG, "signInAnonymously: failure", task.getException());
                    }
                });
    }

    // Send an audio message as Base64 encoded string
    public void sendAudioMessage(String userId, String base64Audio) {
        String messageId = database.child("messages").child(userId).push().getKey();
        if (messageId != null) {
            Map<String, Object> audioMessageData = new HashMap<>();
            audioMessageData.put("audio", base64Audio);  // Store the Base64 audio
            audioMessageData.put("timestamp", System.currentTimeMillis());

            database.child("messages").child(userId).child(messageId).setValue(audioMessageData)
                    .addOnCompleteListener(task -> {
                        // Data added successfully, now schedule deletion
                        new Handler().postDelayed(() -> {
                            // Delete the data after 10 seconds
                            database.child("messages").child(userId).child(messageId).removeValue()
                                    .addOnSuccessListener(aVoid1 -> {
                                        // Deletion succeeded
                                        Log.d("YourActivity", "Data deleted successfully.");
                                    })
                                    .addOnFailureListener(e -> {
                                        // Deletion failed
                                        Log.e("YourActivity", "Data deletion failed.", e);
                                    });
                        }, 10000); // 10,000 milliseconds = 10 seconds
                    }).addOnFailureListener(e -> {
                        // Data addition failed
                        Log.e("YourActivity", "Data addition failed.", e);
                    });;

        }
    }

    // Listen for new messages and play them
    public void listenForAudioMessages(String userId) {
        database.child("messages").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot messageSnapshot : snapshot.getChildren()) {
                    String messageId = messageSnapshot.getKey();
                    String base64Audio = messageSnapshot.child("audio").getValue(String.class);

                    if (base64Audio != null) {
                        updateDownloadCounter(userId, messageId);
                        playReceivedAudio(base64Audio);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                buttonManagerGlobal.showVariableValue(activity, "ERROR","Cancelled Download audio");
            }
        });
    }

    // Convert Base64 to audio file and play it
    private void playReceivedAudio(String base64Audio) {
        try {
            byte[] decodedBytes = Base64.decode(base64Audio, Base64.DEFAULT);
            File audioFile = File.createTempFile("received_audio", ".3gp");

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
            buttonManagerGlobal.showVariableValue(activity, "ERROR","Playing audio");
        }
    }

    // 🔹 Keep track of downloads and delete after all clients received
    private void updateDownloadCounter(String userId, String messageId) {
        DatabaseReference messageRef = database.child("messages").child(userId).child(messageId);

        messageRef.child("downloads").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Integer downloads = task.getResult().getValue(Integer.class);
                if (downloads == null) downloads = 0;

                int newCount = downloads + 1;
                messageRef.child("downloads").setValue(newCount);

//                if (newCount >= clientsCount) {
//                    messageRef.removeValue();
//                    Log.d(TAG, "Message deleted after all clients downloaded");
//                }
            }
        });
    }

    // Get user ID
    public String getUserId() {
        if (mAuth.getCurrentUser() != null) {
            return mAuth.getCurrentUser().getUid();
        }
        return null;
    }
}
