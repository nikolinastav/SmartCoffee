package com.example.smartcoffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CoffeeChoice extends AppCompatActivity {

    private TextView name;
    private ImageView image;
    private Button brewNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_choice);

        name = findViewById(R.id.image_description);
        image = findViewById(R.id.choice_image);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        image.setImageResource(intent.getIntExtra("image", 0));

        ImageButton waterSettings = (ImageButton)findViewById(R.id.water_settings);
        waterSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CoffeeChoice.this, WaterSettings.class));
            }
        });

        brewNow = (Button)findViewById(R.id.makeNow_button);
        brewNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoffeeChoice.this, StepOne.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bluetooth_item:
                Toast.makeText(this, "Opening Bluetooth", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.customCoffee_item:
                Intent open_customCoffee = new Intent(CoffeeChoice.this, CustomCoffee.class);
                startActivity(open_customCoffee);
                return false;
            case R.id.favorite_item:
                Intent open_favorites = new Intent(CoffeeChoice.this, Favorite.class);
                startActivity(open_favorites);
                return false;
//            case R.id.settings_item:
//                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
