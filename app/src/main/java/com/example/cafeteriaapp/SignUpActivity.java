package com.example.cafeteriaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    EditText etUsername, etPassword, etConfirm;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirm = findViewById(R.id.etConfirm);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirm = etConfirm.getText().toString().trim();

                if (username.isEmpty()) {
                    etUsername.setError("יש להזין שם משתמש");
                    return;
                }
                if (password.length() < 6) {
                    etPassword.setError("הסיסמה צריכה להכיל לפחות 6 תווים");
                    return;
                }
                if (!confirm.equals(password)) {
                    etConfirm.setError("הסיסמאות לא תואמות");
                    return;
                }

                Toast.makeText(SignUpActivity.this, "נרשמת בהצלחה", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                startActivity(intent);
                finish();
            }
        });
    }
}
