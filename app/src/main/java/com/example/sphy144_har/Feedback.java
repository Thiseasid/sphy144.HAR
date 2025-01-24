package com.example.sphy144_har;

import static android.view.View.INVISIBLE;
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

        TextView tv_feedback_prompt = findViewById(R.id.tv_feedback_prompt);
        EditText etFirstName = findViewById(R.id.et_first_name);
        EditText etLastName = findViewById(R.id.et_last_name);
        EditText etPhone = findViewById(R.id.et_phone);
        EditText etEmail = findViewById(R.id.et_email);
        EditText etComments = findViewById(R.id.et_comments);
        Button feedbackButton = findViewById(R.id.SUBMIT);
        Spinner spinner_feedback_type = findViewById(R.id.spinner_feedback_type);
        TextView text_confirmation = findViewById(R.id.text_confirmation);
        CheckBox checkbox_consent = findViewById(R.id.checkbox_consent);
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkbox_consent.isChecked()) {
                    text_confirmation.setVisibility(VISIBLE);
                    tv_feedback_prompt.setVisibility(INVISIBLE);
                    etFirstName.setVisibility(INVISIBLE);
                    etLastName.setVisibility(INVISIBLE);
                    etPhone.setVisibility(INVISIBLE);
                    etEmail.setVisibility(INVISIBLE);
                    etComments.setVisibility(INVISIBLE);
                    feedbackButton.setVisibility(INVISIBLE);
                    spinner_feedback_type.setVisibility(INVISIBLE);
                    checkbox_consent.setVisibility(INVISIBLE);

                    // Να στεαλεί το Mail
                    // ..
                } else {
                    Toast.makeText(Feedback.this, "Παρακαλώ αποδεχτείτε τους όρους.", Toast.LENGTH_SHORT).show();
                }
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