package com.example.modafitness;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button aboutButton, callusButton, existButton,buttonBook;
    EditText editTextName, editTextDate;
    RadioGroup radioGroupTrainers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutButton = findViewById(R.id.aboutButton);
        callusButton = findViewById(R.id.callusButton);
        existButton = findViewById(R.id.existButton);
        buttonBook = findViewById(R.id.buttonBook);

        editTextName = findViewById(R.id.editTextName);
        editTextDate = findViewById(R.id.editTextDate);
        radioGroupTrainers = findViewById(R.id.radioGroupTrainers);

        aboutButton.setOnClickListener(v -> {
           startActivity(new Intent(MainActivity.this,AboutActivity.class));
        });

        callusButton.setOnClickListener(v -> {
            callus();

        });

        existButton.setOnClickListener(v-> {
            Toast.makeText(MainActivity.this,"Long-press to exist",Toast.LENGTH_SHORT).show();

        });

        existButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                finish();
                return false;
            }
        });
        buttonBook.setOnClickListener(v -> {
            bookAppointment();

        });
    }

    private void callus(){
        String phoneNumber = "tel:0557638821";

        Intent deilIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber));

        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1 );
        }else {
            startActivity(deilIntent);
        }
    }
    private void bookAppointment() {
        String name = editTextName.getText().toString();
        String date = editTextDate.getText().toString();

        int selectedTrainerId = radioGroupTrainers.getCheckedRadioButtonId();
        RadioButton selectedTrainer = findViewById(selectedTrainerId);
        String trainerName = selectedTrainer.getText().toString();

        String message = "Name : "  + name + "\n booking date: " + date + "\n Coach:"+ trainerName+" \n Your reservation has been completed successfully";
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}


