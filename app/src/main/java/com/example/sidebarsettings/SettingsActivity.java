package com.example.sidebarsettings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {
    //Initialize all used widgets and initialize the data transfer object
    private SettingsObject object = null;
    CheckBox box;
    CheckBox caps;
    Button button;
    EditText size;
    EditText gravity;
    EditText style;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //Get all views by ID
        box = findViewById(R.id.checkBox);
        button = findViewById(R.id.button);
        caps = findViewById(R.id.caps);
        size = findViewById(R.id.size);
        gravity = findViewById(R.id.gravity);
        style = findViewById(R.id.style);
        //Fetch what was sent in the intent (In this case the data object)
        object = (SettingsObject) getIntent().getSerializableExtra("object");
        //Check whether the checkbox for editing was checked before, if it was set it as checked in settings
        Boolean check = object.getEditable();
        box.setChecked(!check);
        //Button listener to send edits to the main activity
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonPress();
            }
        });
    }

    public void buttonPress(){
        //Check whether editing box was checked or not, and set to object correct values
        if(box.isChecked()){
            object.setEditable(false);
        }else{
            object.setEditable(true);
        }
        //Set gravity from gravity text field
        object.setGravity(gravity.getText().toString());
        //Set text style from gravity text field
        object.setStyle(style.getText().toString());
        //Quick check for whether anything was given to field font size, if not then set to 0
        if(!size.getText().toString().equals("Font size")) {
            object.setFont_size(Integer.parseInt(size.getText().toString()));
        }else{
            object.setFont_size(0);
        }
        //Check whether all caps checkbox was checked
        if(caps.isChecked()){
            object.setCaps(true);
        }else{
            object.setCaps(false);
        }
        //Open main activity
        Intent intent = new Intent();
        intent.putExtra("object", object);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed(){
        //On back press, if something was added to settings it doesn't get sent to main activity
        Intent intent = new Intent();
        intent.putExtra("object", object);
        setResult(RESULT_OK, intent);
        finish();
    }
}