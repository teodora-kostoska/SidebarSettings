package com.example.sidebarsettings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private SettingsObject object = new SettingsObject();
    TextView text;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Get views
        text = findViewById(R.id.textView3);
        editText = findViewById(R.id.editTextTextPersonName);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        implementNavigation();
    }

    //Set up the sidebar and make it possible to go to different activity when navigation item selected
    public void implementNavigation(){
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navView = (NavigationView) findViewById(R.id.navigation); // initiate a Navigation View
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //loadSettings to go to the second activity
                loadSettings();
                return true;
            }
        });
    }

    public void loadSettings(){
        //Send object to second activity and wait for result from activity
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        intent.putExtra("object", object);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Check whether request code is same and result code is correct
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //Get object from intent
                object = (SettingsObject) data.getSerializableExtra("object");
                //Set the writable field as inactive if checkbox was selected
                Boolean edit = object.getEditable();
                editText.setEnabled(edit);
                if (edit == false) {
                    text.setText(editText.getText());
                }
                //Make all caps if checkbox was selected
                text.setAllCaps(object.getCaps());
                //Check that there are other values given than the default ones, only works if all are changed cause too much work if all checked one by one
                if (!object.getStyle().equals("Font (BOLD,ITALIC, NORMAL)") && !object.getGravity().equals("Gravity (LEFT, RIGHT, CENTER, TOP, BOTTOM)") && object.getFont_size() != 0) {
                    //Set the text style to bold, italic or normal
                    if (object.getStyle().equals("BOLD")) {
                        text.setTypeface(text.getTypeface(), Typeface.BOLD);
                    } else if (object.getStyle().equals("ITALIC")) {
                        text.setTypeface(text.getTypeface(), Typeface.ITALIC);
                    } else {
                        text.setTypeface(text.getTypeface(), Typeface.NORMAL);
                    }
                    //Set font size
                    text.setTextSize(object.getFont_size());
                    //Set gravity according to user input
                    if (object.getGravity().equals("RIGHT")) {
                        text.setGravity(Gravity.RIGHT);
                    } else if (object.getGravity().equals("LEFT")) {
                        text.setGravity(Gravity.LEFT);
                    } else if (object.getGravity().equals("CENTER")) {
                        text.setGravity(Gravity.CENTER);
                    } else if (object.getGravity().equals("TOP")) {
                        text.setGravity(Gravity.TOP);
                    } else if (object.getGravity().equals("BOTTOM")) {
                        text.setGravity(Gravity.BOTTOM);
                    } else {
                        text.setGravity(Gravity.NO_GRAVITY);
                    }
                }
            }
        }
    }
    //This makes the side bar open and close on click
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}