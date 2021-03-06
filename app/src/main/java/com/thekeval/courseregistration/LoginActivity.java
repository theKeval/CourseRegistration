package com.thekeval.courseregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etStudentName, etUsername, etPassword;
    Button btnLogin, btnClear;

    public static String student_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etStudentName = findViewById(R.id.etStudentName);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnClear = findViewById(R.id.btnClear);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etStudentName.setText("");
                etUsername.setText("");
                etPassword.setText("");
            }
        });

        Log.d("LoginActivity", "onCreate executed");
    }

    private void login() {

        if (etUsername.getText().toString().toLowerCase().equals("student1") &&
                etPassword.getText().toString().toLowerCase().equals("123456")) {

            // proceed for login and navigate
            student_name = etStudentName.getText().toString();

            etStudentName.setText("");
            etUsername.setText("");
            etPassword.setText("");

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }
        else {
            Toast.makeText(this, "Invalid username & password!", Toast.LENGTH_LONG).show();
        }

    }
}