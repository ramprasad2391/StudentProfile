package com.example.ramprasad.studentprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class StudentEdit extends AppCompatActivity {

    EditText name;
    EditText email;
    TextView text;
    RadioGroup radioGroup;
    TextView account_label;
    TextView moodLabel;
    SeekBar mood;
    Student student;
    Switch aswitch;
    LinearLayout account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        text = (TextView) findViewById(R.id.text);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        account_label = (TextView) findViewById(R.id.accountlabel);
        moodLabel = (TextView) findViewById(R.id.moodLabel);
        mood = (SeekBar) findViewById(R.id.seekBar);
        aswitch = (Switch) findViewById(R.id.searchSwitch);
        account = (LinearLayout) findViewById(R.id.accountLayout);

        if (getIntent().getExtras() != null) {
            student = getIntent().getExtras().getParcelable(StudentAppConstants.INTENT_KEY);
            showUserEditItem(student);

        }
    }

    public void showUserEditItem(Student student) {


        name.setText(student.getName());
        email.setText(student.getEmail());

        if (student.getLanguage().equals(StudentAppConstants.JAVA_LABEL)) {
            radioGroup.check(R.id.java);
        } else if (student.getLanguage().equals(StudentAppConstants.C_LABEL)) {
            radioGroup.check(R.id.c);
        } else {
            radioGroup.check(R.id.csharp);
        }
        if (student.isSearch()) {
            aswitch.setChecked(true);
        } else {
            aswitch.setChecked(false);
        }
        mood.setProgress(student.getMood());


        int count = student.getEdit_counter();
        if (count == 0) {
            name.setVisibility(View.VISIBLE);
        } else if (count == 1) {
            email.setVisibility(View.VISIBLE);
        } else if (count == 2) {
            text.setVisibility(View.VISIBLE);
            radioGroup.setVisibility(View.VISIBLE);
        } else if (count == 3) {
            account.setVisibility(View.VISIBLE);
            account_label.setVisibility(View.VISIBLE);
            aswitch.setVisibility(View.VISIBLE);
        } else {
            moodLabel.setVisibility(View.VISIBLE);
            mood.setVisibility(View.VISIBLE);
        }


    }


    public void onSave(View view) {
        Student edited_student = new Student();
        edited_student.setName(name.getText().toString());
        edited_student.setEmail(email.getText().toString());
        setLanguage(edited_student);
        setSearchPref(edited_student);
        edited_student.setMood(mood.getProgress());
        sendResult(edited_student);
    }

    public void sendResult(Student edited_student) {
        Intent intent = new Intent();
        intent.putExtra(StudentAppConstants.INTENT_KEY, edited_student);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
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
        if (aswitch.isChecked()) {
            student.setSearch(true);
        } else {
            student.setSearch(false);
        }
        return student;
    }
}
