package com.example.sphy144_har;

import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        EditText etFirstName = findViewById(R.id.et_first_name);
        EditText etLastName = findViewById(R.id.et_last_name);
        EditText etPhone = findViewById(R.id.et_phone);
        EditText etEmail = findViewById(R.id.et_email);
        EditText etComments = findViewById(R.id.et_comments);
        Button feedbackButton = findViewById(R.id.SUBMIT);
        TextView text_confirmation = findViewById(R.id.text_confirmation);
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_confirmation.setVisibility(VISIBLE);

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Μεταφορά στην MainActivity όταν ο χρήστης πατήσει πίσω
        Intent intent = new Intent(Feedback.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
