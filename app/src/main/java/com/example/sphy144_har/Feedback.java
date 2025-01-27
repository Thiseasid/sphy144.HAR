package com.example.sphy144_har;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Feedback extends AppCompatActivity {

    private void sendEmailInBackground(String subject, String body) {
        // Τρέχουμε σε νέο Thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 1. Ρυθμίσεις για SMTP (STARTTLS, port 587)
                    Properties props = new Properties();
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "587");

                    // 2. Αυθεντικοποίηση: Χρησιμοποιούμε το Gmail + App Password
                    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(
                                    "te47st@gmail.com",     // Βάλε το πλήρες Gmail
                                    "wtzpefawmrcvvepf"      // Τον 16-ψήφιο App Password (χωρίς κενά)
                            );
                        }
                    });

                    // 3. Δημιουργία μηνύματος
                    Message message = new MimeMessage(session);
                    // Από ποιο email στέλνεται
                    message.setFrom(new InternetAddress("te47st@gmail.com"));
                    // Σε ποιον παραλήπτη στέλνεις (μπορείς να βάλεις όσους θέλεις)
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("te47st@gmail.com"));

                    // Θέμα και σώμα email
                    message.setSubject(subject);
                    message.setText(body);

                    // 4. Αποστολή
                    Transport.send(message);

                    // 5. Προαιρετικά, ενημέρωσε το UI μετά την επιτυχία
                    runOnUiThread(() -> {
                        Toast.makeText(Feedback.this, "Το email στάλθηκε επιτυχώς!", Toast.LENGTH_SHORT).show();
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    // Σε περίπτωση σφάλματος, ενημερώνουμε το UI
                    runOnUiThread(() -> {
                        Toast.makeText(Feedback.this, "Σφάλμα: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    });
                }
            }
        }).start();
    }

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

                    //Mail

                    // Παίρνουμε τα στοιχεία που συμπλήρωσε ο χρήστης
                    String firstName = etFirstName.getText().toString().trim();
                    String lastName = etLastName.getText().toString().trim();
                    String phone = etPhone.getText().toString().trim();
                    String emailUser = etEmail.getText().toString().trim();
                    String comments = etComments.getText().toString().trim();

                    // Αν θέλεις και τον τύπο feedback από το Spinner
                    String feedbackType = spinner_feedback_type.getSelectedItem().toString();

                    // Φτιάχνουμε θέμα και κείμενο email
                    String subject = "Νέο Feedback από " + firstName + " " + lastName
                            + " [" + feedbackType + "]";
                    String body = "Όνομα: " + firstName + "\n"
                            + "Επίθετο: " + lastName + "\n"
                            + "Τηλέφωνο: " + phone + "\n"
                            + "Email: " + emailUser + "\n"
                            + "Τύπος Feedback: " + feedbackType + "\n"
                            + "Σχόλια:\n" + comments;

                    // Καλούμε τη μέθοδο αποστολής (θα τρέξει σε ξεχωριστό Thread)
                    sendEmailInBackground(subject, body);

                    // Μετά, κάνουμε αλλαγές στο UI (visibility)
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