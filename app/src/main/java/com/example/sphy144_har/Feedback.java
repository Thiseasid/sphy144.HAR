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
                int flagsubmit = 0;
                String [] errors={"","Συμπληρώστε το όνομά σας.","Συμπληρώστε το επίθετό σας.","Συμπληρώστε το τηλέφωνό σας.", "Μη έγκυρος αριθμός τηλεφώνου.", "Συμπληρώστε το email σας.", "Μη έγκυρη διεύθυνση email.", "Συμπληρώστε τα σχόλια σας.", "Παρακαλώ αποδεχτείτε τους όρους." };

                //Mail

                // Παίρνουμε τα στοιχεία που συμπλήρωσε ο χρήστης
                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String emailUser = etEmail.getText().toString().trim();
                String comments = etComments.getText().toString().trim();
                String feedbackType = spinner_feedback_type.getSelectedItem().toString();

                // Ελέγχουμε αν είναι κενά
                if (!checkbox_consent.isChecked()) {
                    flagsubmit = 8;
                }
                if (comments.isEmpty()) {
                    flagsubmit = 7;

                }
                if (!Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()) {
                    flagsubmit = 6;

                }
                if (emailUser.isEmpty()) {
                    flagsubmit = 5;

                }
                if (!TextUtils.isDigitsOnly(phone) || phone.length() != 10) {
                    flagsubmit = 4;
                }
                if (phone.isEmpty()) {
                    flagsubmit = 3;

                }
                if (lastName.isEmpty()) {
                    flagsubmit = 2;

                }
                if (firstName.isEmpty()) {
                    flagsubmit = 1;

                }

                if (flagsubmit ==0) {
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

                } else{
                    Toast.makeText(Feedback.this, errors[flagsubmit], Toast.LENGTH_SHORT).show();
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