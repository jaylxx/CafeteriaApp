package com.example.cafeteriaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnSignIn;
    CheckBox cbRememberMe;
    TextView tvGoToSignUp;
    String savedUsername, savedPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        cbRememberMe = findViewById(R.id.cbRememberMe);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvGoToSignUp = findViewById(R.id.tvGoToSignUp);

        Intent intent = getIntent();
        savedUsername = intent.getStringExtra("username");
        savedPassword = intent.getStringExtra("password");

        if (savedUsername != null) {
            etUsername.setText(savedUsername);
        }

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignInActivity.this, "יש למלא את כל השדות", Toast.LENGTH_SHORT).show();
                } else if (savedUsername != null && username.equals(savedUsername) && password.equals(savedPassword)) {
                    Toast.makeText(SignInActivity.this, "התחברת בהצלחה", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignInActivity.this, "שם משתמש או סיסמה שגויים", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cbRememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(SignInActivity.this, "זכור אותי מופעל", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignInActivity.this, "זכור אותי בוטל", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvGoToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
