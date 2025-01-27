package com.example.sphy144_har;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.util.Patterns;
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Ρυθμίσεις για SMTP (STARTTLS, port 587)
                    Properties props = new Properties();
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "587");

                    //Αυθεντικοποίηση: Χρησιμοποιούμε το Gmail + App Password
                    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(
                                    "te47st@gmail.com",
                                    "wtzpefawmrcvvepf"      // Τον 16-ψήφιο App Password
                            );
                        }
                    });

                    //  Δημιουργία μηνύματος
                    Message message = new MimeMessage(session);
                    // Από ποιο email στέλνεται
                    message.setFrom(new InternetAddress("te47st@gmail.com"));
                    // Σε ποιον παραλήπτη στέλνω (μπορείς να βάλεις όσους θέλω)
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse("te47st@gmail.com, alex_kar1996@hotmail.com, theseusid@gmail.com, zeppou96@gmail.com"));

                    // Θέμα και σώμα email
                    message.setSubject(subject);
                    message.setText(body);

                    // Αποστολή
                    Transport.send(message);

                    //  ενημερώνω το χρηστη μετά την επιτυχία
                    runOnUiThread(() -> {
                        Toast.makeText(Feedback.this, "Η πρόταση καταχωρήθηκε επιτυχώς!", Toast.LENGTH_SHORT).show();
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    // Σε περίπτωση σφάλματος, ενημερώνω το χρηστη
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
                boolean flagsubmit = false;

                //Mail

                // Παίρνουμε τα στοιχεία που συμπλήρωσε ο χρήστης
                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String emailUser = etEmail.getText().toString().trim();
                String comments = etComments.getText().toString().trim();
                String feedbackType = spinner_feedback_type.getSelectedItem().toString();

                // Ελέγχουμε αν είναι κενά
                if (firstName.isEmpty()) {
                    Toast.makeText(Feedback.this, "Συμπληρώστε το όνομά σας.", Toast.LENGTH_SHORT).show();
                    flagsubmit = true;

                }
                if (lastName.isEmpty()) {
                    Toast.makeText(Feedback.this, "Συμπληρώστε το επίθετό σας.", Toast.LENGTH_SHORT).show();
                    flagsubmit = true;

                }
                if (phone.isEmpty()) {
                    Toast.makeText(Feedback.this, "Συμπληρώστε το τηλέφωνό σας.", Toast.LENGTH_SHORT).show();
                    flagsubmit = true;

                }
                if (!TextUtils.isDigitsOnly(phone) || phone.length() != 10) {
                    Toast.makeText(Feedback.this, "Μη έγκυρος αριθμός τηλεφώνου.", Toast.LENGTH_SHORT).show();
                    flagsubmit = true;
                }

                if (emailUser.isEmpty()) {
                    Toast.makeText(Feedback.this, "Συμπληρώστε το email σας.", Toast.LENGTH_SHORT).show();
                    flagsubmit = true;

                }
                if (!Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()) {
                    Toast.makeText(Feedback.this, "Μη έγκυρη διεύθυνση email.", Toast.LENGTH_SHORT).show();
                    flagsubmit = true;

                }
                if (comments.isEmpty()) {
                    Toast.makeText(Feedback.this, "Συμπληρώστε τα σχόλια σας.", Toast.LENGTH_SHORT).show();
                    flagsubmit = true;

                }
                if (!checkbox_consent.isChecked()) {
                    flagsubmit = true;
                    Toast.makeText(Feedback.this, "Παρακαλώ αποδεχτείτε τους όρους.", Toast.LENGTH_SHORT).show();

                }

                if (!flagsubmit) {
                    // θέμα και κείμενο email
                    String subject = "Νέο Feedback από " + firstName + " " + lastName
                            + " [" + feedbackType + "]";
                    String body = "Όνομα: " + firstName + "\n"
                            + "Επίθετο: " + lastName + "\n"
                            + "Τηλέφωνο: " + phone + "\n"
                            + "Email: " + emailUser + "\n"
                            + "Τύπος Feedback: " + feedbackType + "\n"
                            + "Σχόλια:\n" + comments;


                    sendEmailInBackground(subject, body);
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