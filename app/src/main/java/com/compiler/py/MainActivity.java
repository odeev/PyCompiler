package com.compiler.py;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private SharedPreferences self;
    EditText code;
    Button run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To Save The Code
        self = getSharedPreferences("codes", Activity.MODE_PRIVATE);

        code = findViewById(R.id.code);
        run = findViewById(R.id.run);

        // For Button Text-Case
        run.setTransformationMethod(null);

        // Set The Code to EditText
        code.setText(self.getString("code", ""));

        run.setOnClickListener(view -> {
            // Saving The Code
            self.edit().putString("code", code.getText().toString()).apply();

            // Starting OutPut Activity
            startActivity(new Intent(getApplicationContext(), OutputActivity.class));
        });

    }
}