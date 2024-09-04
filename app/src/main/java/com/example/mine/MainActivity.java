package com.example.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText ageInput;
    private EditText assessmentYearInput;
    private EditText incomeInput;
    private EditText deductionsInput;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ageInput = findViewById(R.id.age_input);
        assessmentYearInput = findViewById(R.id.assessment_year_input);
        incomeInput = findViewById(R.id.income_input);
        deductionsInput = findViewById(R.id.deductions_input);
        result = findViewById(R.id.result);
        Button calculateButton = findViewById(R.id.calculate_button);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTax();
            }
        });

        findViewById(R.id.nav_about_us).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
            }
        });

        findViewById(R.id.nav_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContactActivity.class));
            }
        });
    }

    private void calculateTax() {
        try {
            int age = Integer.parseInt(ageInput.getText().toString());
            int assessmentYear = Integer.parseInt(assessmentYearInput.getText().toString());
            double income = Double.parseDouble(incomeInput.getText().toString());
            double deductions = Double.parseDouble(deductionsInput.getText().toString());

            // Simple dummy tax calculation
            double taxableIncome = income - deductions;
            double tax = taxableIncome * 0.1; // 10% tax rate

            // Format the tax value to 2 decimal places
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            String formattedTax = decimalFormat.format(tax);

            // Display result
            result.setText("Tax: ₹" + formattedTax);
            result.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        } catch (NumberFormatException e) {
            result.setText("Please enter valid numbers.");
            result.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }
}
