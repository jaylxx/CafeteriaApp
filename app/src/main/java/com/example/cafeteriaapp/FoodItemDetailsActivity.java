package com.example.cafeteriaapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        FoodItem item = new FoodItem("נקניקיה", "כריכים", 18.0, 5, true);

        binding.tvName.setText(item.getName());
        binding.tvCategory.setText(item.getCategory());

        double price = item.getPrice();
        if (price == (int) price) {
            binding.tvPrice.setText((int) price + " ₪");
        } else {
            binding.tvPrice.setText(String.format("%.2f ₪", price));
        }

        binding.tvPrepTime.setText("זמן הכנה: " + item.getPrepTime() + " דק'");

        if (item.isAvailable()) {
            binding.tvAvailable.setText("זמין");
            binding.tvAvailable.setBackgroundResource(R.drawable.chip_available);
            binding.tvAvailable.setTextColor(getColor(R.color.green));
        } else {
            binding.tvAvailable.setText("אזל");
            binding.tvAvailable.setBackgroundResource(R.drawable.chip_unavailable);
            binding.tvAvailable.setTextColor(getColor(R.color.red));
            binding.btnAddToCart.setEnabled(false);
        }

        binding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FoodItemDetailsActivity.this, "המנה נוספה לעגלה", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
