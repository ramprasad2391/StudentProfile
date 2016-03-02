package com.example.ramprasad.studentprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StudentDisplay extends AppCompatActivity {

    TextView name;
    TextView email;
    TextView language;
    TextView account;
    TextView mood;
    LinearLayout parent;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_display);

        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        language = (TextView) findViewById(R.id.lang);
        account = (TextView) findViewById(R.id.account);
        mood = (TextView) findViewById(R.id.mood);
        parent = (LinearLayout) findViewById(R.id.parent);


        if (getIntent().getExtras() != null) {
            student = getIntent().getExtras().getParcelable(StudentAppConstants.INTENT_KEY);
            display(student);
        }
    }


    public void display(Student student) {

        name.setText(student.getName());
        email.setText(student.getEmail());
        language.setText(student.getLanguage());

        if (student.isSearch()) {
            account.setText(StudentAppConstants.ACCOUNT_TRUE);
        } else {
            account.setText(StudentAppConstants.ACCOUNT_FALSE);
        }

        int mood_progress = student.getMood();
        if (mood_progress >= 50) {
            mood.setText(mood_progress + "%" + StudentAppConstants.POSITIVE);
        } else {
            mood.setText(mood_progress + "%" + StudentAppConstants.NEGATIVE);
        }


    }


    public void onEdit(View view) {
        ImageView imageView = (ImageView) view;
        if (imageView.getId() == R.id.name_edit) {
            student.setEdit_counter(0);
        } else if (imageView.getId() == R.id.email_edit) {
            student.setEdit_counter(1);
        } else if (imageView.getId() == R.id.lang_edit) {
            student.setEdit_counter(2);
        } else if (imageView.getId() == R.id.account_edit) {
            student.setEdit_counter(3);
        } else {
            student.setEdit_counter(4);
        }
        Log.d("Hello",student.toString());
        showEdit(student);
    }

    public void showEdit(Student student) {
        Intent intent = new Intent(StudentDisplay.this, StudentEdit.class);
        intent.putExtra(StudentAppConstants.INTENT_KEY, student);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                if (data.getExtras() != null) {
                    student = data.getExtras().getParcelable(StudentAppConstants.INTENT_KEY);
                    display(student);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
