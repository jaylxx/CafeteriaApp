package com.example.cafeteriaapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafeteriaapp.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            binding.tvStatus.setText("בחרת: הגדרות");
            return true;
        } else if (item.getItemId() == R.id.action_about) {
            binding.tvStatus.setText("בחרת: אודות");
            return true;
        } else if (item.getItemId() == R.id.action_exit) {
            binding.tvStatus.setText("בחרת: יציאה");
            return true;
        } else if (item.getItemId() == R.id.action_cart) {
            binding.tvStatus.setText("העגלה שלך ריקה כרגע");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
