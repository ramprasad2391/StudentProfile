package com.example.ramprasad.studentprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText email;
    Switch search_switch;
    SeekBar seekBar;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        search_switch = (Switch) findViewById(R.id.searchSwitch);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

    }


    public void onSubmit(View view) {

        if (doValidation()) {
            Student student = new Student();
            student.setName(name.getText().toString());
            student.setEmail(email.getText().toString());
            setLanguage(student);
            setSearchPref(student);
            student.setMood(seekBar.getProgress());

            openDisplay(student);
        }
    }


    public boolean doValidation() {

        if (name.getText().length() == 0 || email.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), StudentAppConstants.NAME_EMAIL_MISSING, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }


    public Student setLanguage(Student student) {
        if (radioGroup.getCheckedRadioButtonId() == R.id.java) {
            student.setLanguage(StudentAppConstants.JAVA_LABEL);
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.c) {
            student.setLanguage(StudentAppConstants.C_LABEL);
        } else {
            student.setLanguage(StudentAppConstants.CSHARP_LABEL);
        }

        return student;
    }

    public Student setSearchPref(Student student) {
        if (search_switch.isChecked()) {
            student.setSearch(true);
        } else {
            student.setSearch(false);
        }
        return student;
    }

    public void openDisplay(Student student) {
        Intent intent = new Intent(MainActivity.this, StudentDisplay.class);
        intent.putExtra(StudentAppConstants.INTENT_KEY, student);
        startActivity(intent);
    }
}
