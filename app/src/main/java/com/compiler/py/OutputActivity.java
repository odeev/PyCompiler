package com.compiler.py;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class OutputActivity extends AppCompatActivity {

    TextView output;
    Button close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.output);

        output = findViewById(R.id.output);
        close = findViewById(R.id.close);
        close.setTransformationMethod(null);

        // If Python Is Not Started: Start Python
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        // To Get The Code
        SharedPreferences self = getSharedPreferences("codes", Activity.MODE_PRIVATE);

        // Calling Python
        Python python = Python.getInstance();

        // Calling Python Script "pyscript" Without The Extenstion name (.py)
        PyObject pymodule = python.getModule("pyscript");

        // Calling A Function Or Method Inside The Script
        PyObject obj = pymodule.callAttr("main", self.getString("code", ""));

        // Setting Returned Value Of Our Python Script Function To output TextView
        output.setText(obj.toString());

        // Finish The Activity
        close.setOnClickListener(view -> {
            finish();
        });
    }
}