package com.example.smartcoffee;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class CustomCoffee extends AppCompatActivity {

    private TextView textViewPercent; //new
    private SeekBar WaterBar;
    private ImageButton ChooseDateButton;
    private ImageButton ChooseCoffeeButton;
    private Button saveCustom;
    private TextView resultDate;
    private TextView resultHour;
    TextView repeat;
    RadioGroup radioGroup;
    RadioButton radioButton;
    String label;
    EditText labelInput;
    private TextView type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_coffee);

        type = findViewById(R.id.coffee_type);
        Intent intent = getIntent();
        type.setText(intent.getStringExtra("name"));

        labelInput = (EditText) findViewById(R.id.labelName);

        ChooseCoffeeButton = (ImageButton) findViewById(R.id.type_bottom);
        ChooseCoffeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChooseCoffee();
            }
        });

        WaterBar = (SeekBar) findViewById(R.id.WaterBar);
        textViewPercent = (TextView) findViewById(R.id.water_percent); //new
        WaterBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewPercent.setText("Water percent: " + progress + "%"); //new
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
//
            }
        });

        //Schedule
        ChooseDateButton = (ImageButton) findViewById(R.id.ChooseDateButton);
        resultDate = (TextView) findViewById(R.id.resultDate);
        resultHour = (TextView) findViewById(R.id.resultHour);
        ChooseDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTimeButton();
                handleDateButton();
            }
        });

        //Repeat Options
        repeat = (TextView) findViewById(R.id.repeat);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        saveCustom = (Button) findViewById(R.id.button_Save);
        saveCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                label = labelInput.getText().toString();
                showToast(label);
                CustomCoffee.this.finish();
            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(CustomCoffee.this, "Coffee " + text + " saved", Toast.LENGTH_SHORT).show();
    }

    private void handleDateButton() {

        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int date) {
                String dateString = date + "/" + month + "/" + year;
                resultDate.setText(dateString);

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);

                CharSequence dateCharSequence = DateFormat.format("EEEE, dd MMM yyyy", calendar1);
                resultDate.setText(dateCharSequence);
            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();
    }

    private void handleTimeButton() {
        final Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);

        boolean is24HourFormat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);

                CharSequence charSequence = DateFormat.format("hh:mm a", calendar1);
                resultHour.setText(charSequence);
            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();
    }

    //Coffee Type
    public void openChooseCoffee() { //new einai Coffee
        Intent intent = new Intent(this, CoffeeType.class);
        startActivity(intent);
    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

    }

}
