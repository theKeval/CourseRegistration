package com.thekeval.courseregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvWelcomeMsg, tvCourseFees, tvCourseHours, tvTotalFees, tvTotalHours;
    Button btnAdd, btnReset;
    RadioButton rbGraduate, rbUnderGraduate;
    CheckBox cbAccommodations, cbMedInsurance;
    Spinner spinnerCourses;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}