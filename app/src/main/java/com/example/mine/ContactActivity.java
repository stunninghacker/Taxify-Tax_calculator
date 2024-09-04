package com.example.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText emailInput;
    private EditText messageInput;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("feedback");

        nameInput = findViewById(R.id.contact_name);
        emailInput = findViewById(R.id.contact_email);
        messageInput = findViewById(R.id.contact_message);
        Button sendButton = findViewById(R.id.contact_send_button);
        Button websiteButton = findViewById(R.id.contact_website_button);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendContactInfo();
            }
        });

        websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebsite();
            }
        });
    }

    private void sendContactInfo() {
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String message = messageInput.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Store data in Firebase
        String id = databaseReference.push().getKey();
        ContactInfo contactInfo = new ContactInfo(name, email, message);
        if (id != null) {
            databaseReference.child(id).setValue(contactInfo);
        }

        // Show a thank-you message using Snackbar
        Snackbar.make(findViewById(R.id.contact_send_button), "Thank you for your feedback, " + name + "!", Snackbar.LENGTH_LONG).show();

        // Clear the form fields
        nameInput.setText("");
        emailInput.setText("");
        messageInput.setText("");
    }

    private void openWebsite() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://taxfy.netlify.app"));
        startActivity(browserIntent);
    }

    // ContactInfo class to hold the feedback data
    public static class ContactInfo {
        public String name;
        public String email;
        public String message;

        public ContactInfo() {
            // Default constructor required for calls to DataSnapshot.getValue(ContactInfo.class)
        }

        public ContactInfo(String name, String email, String message) {
            this.name = name;
            this.email = email;
            this.message = message;
        }
    }
}
