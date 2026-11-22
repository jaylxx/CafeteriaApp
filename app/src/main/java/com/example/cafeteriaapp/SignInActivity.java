package com.example.cafeteriaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class SignInActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnSignIn;
    CheckBox cbRememberMe;
    TextView tvGoToSignUp;
    String savedUsername, savedPassword;
    SharedPreferences prefs;

    static final String PREFS_NAME = "login_prefs";
    static final String KEY_USERNAME = "username";
    static final String KEY_PASSWORD = "password";
    static final String KEY_REMEMBER = "remember_me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        cbRememberMe = findViewById(R.id.cbRememberMe);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvGoToSignUp = findViewById(R.id.tvGoToSignUp);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        Intent intent = getIntent();
        savedUsername = intent.getStringExtra("username");
        savedPassword = intent.getStringExtra("password");

        if (savedUsername != null) {
            etUsername.setText(savedUsername);
        }

        loadUser();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignInActivity.this, "יש למלא את כל השדות", Toast.LENGTH_SHORT).show();
                } else if (savedUsername != null && username.equals(savedUsername) && password.equals(savedPassword)) {
                    if (cbRememberMe.isChecked()) {
                        saveUser(username, password);
                    }
                    Toast.makeText(SignInActivity.this, "התחברת בהצלחה", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignInActivity.this, HomeActivity.class));
                    finish();
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
                    if (prefs.getBoolean(KEY_REMEMBER, false)) {
                        showForgetSnackbar();
                    } else {
                        Toast.makeText(SignInActivity.this, "זכור אותי בוטל", Toast.LENGTH_SHORT).show();
                    }
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

    private void saveUser(String username, String password) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.putBoolean(KEY_REMEMBER, true);
        editor.apply();
    }

    private void loadUser() {
        if (prefs.getBoolean(KEY_REMEMBER, false)) {
            etUsername.setText(prefs.getString(KEY_USERNAME, ""));
            etPassword.setText(prefs.getString(KEY_PASSWORD, ""));
            cbRememberMe.setChecked(true);
            if (savedUsername == null) {
                savedUsername = prefs.getString(KEY_USERNAME, "");
                savedPassword = prefs.getString(KEY_PASSWORD, "");
            }
        }
    }

    private void showForgetSnackbar() {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "לשכוח את פרטי ההתחברות השמורים?", Snackbar.LENGTH_LONG);
        snackbar.setAction("כן, לשכוח", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.edit().clear().apply();
                Toast.makeText(SignInActivity.this, "הפרטים נמחקו", Toast.LENGTH_SHORT).show();
            }
        });
        snackbar.setActionTextColor(getColor(R.color.orange));
        snackbar.addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                if (event != DISMISS_EVENT_ACTION) {
                    cbRememberMe.setChecked(true);
                }
            }
        });
        snackbar.show();
    }
}
