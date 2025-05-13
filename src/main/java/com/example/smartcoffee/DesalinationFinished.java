package com.example.smartcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DesalinationFinished extends AppCompatActivity {

    ImageButton goHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desalination_finished);

        goHome = (ImageButton) findViewById(R.id.buttonHome);
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DesalinationFinished.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
