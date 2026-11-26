package com.example.cafeteriaapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafeteriaapp.databinding.ActivityFoodItemDetailsBinding;
import com.example.cafeteriaapp.model.FoodItem;

public class FoodItemDetailsActivity extends AppCompatActivity {

    ActivityFoodItemDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodItemDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FoodItem item = new FoodItem("טוסט גבינה", "כריכים", 12.0, 5, true);

        binding.tvName.setText(item.getName());
        binding.tvCategory.setText(item.getCategory());
        binding.tvPrice.setText(item.getPrice() + " ₪");
        binding.tvPrepTime.setText("זמן הכנה: " + item.getPrepTime() + " דק'");
        if (item.isAvailable()) {
            binding.tvAvailable.setText("זמין");
        } else {
            binding.tvAvailable.setText("אזל");
        }
    }
}
