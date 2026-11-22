package com.example.cafeteriaapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
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

        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showClearDialog();
            }
        });
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
            showExitDialog();
            return true;
        } else if (item.getItemId() == R.id.action_cart) {
            binding.tvStatus.setText("העגלה שלך ריקה כרגע");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showClearDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ניקוי הודעה");
        builder.setMessage("לנקות את ההודעה שעל המסך?");
        builder.setPositiveButton("כן", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                binding.tvStatus.setText("");
            }
        });
        builder.setNegativeButton("לא", null);
        builder.show();
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("יציאה");
        builder.setMessage("לצאת מהאפליקציה?");
        builder.setPositiveButton("כן", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("לא", null);
        builder.show();
    }
}
