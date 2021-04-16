package com.thekeval.courseregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvWelcomeMsg, tvCourseFees, tvCourseHours, tvTotalFees, tvTotalHours;
    Button btnAdd, btnReset;
    RadioButton rbGraduate, rbUnderGraduate;
    CheckBox cbAccommodations, cbMedInsurance;
    Spinner spinnerCourses;

    ArrayList<Course> lstCourses = new ArrayList<>();
    ArrayList<String> courseNames = new ArrayList<>();
    String studentName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // fill the data
        fillCourses();
        studentName = LoginActivity.student_name;

        // get the UI elements references
        tvWelcomeMsg = findViewById(R.id.tvWelcomeMsg);
        tvCourseFees = findViewById(R.id.tvCourseFees);
        tvCourseHours = findViewById(R.id.tvCourseHours);
        tvTotalFees = findViewById(R.id.tvTotalFees);
        tvTotalHours = findViewById(R.id.tvTotalHours);

        btnAdd = findViewById(R.id.btnAdd);
        btnReset = findViewById(R.id.btnReset);

        rbGraduate = findViewById(R.id.rbGraduate);
        rbUnderGraduate = findViewById(R.id.rbUnderGraduated);
        cbAccommodations = findViewById(R.id.cbAccomodation);
        cbMedInsurance = findViewById(R.id.cbMedInsurance);

        spinnerCourses = findViewById(R.id.spinner_courses);

        // set the data
        tvWelcomeMsg.setText("Welcome " + studentName + ",");
        fillCourseNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, courseNames);
        spinnerCourses.setAdapter(adapter);
        spinnerCourses.setSelection(0, true);

        // set the event listeners
        spinnerCourses.setOnItemSelectedListener(new SpinnerSelectionListeners());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check for conditions and add the course
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // reset all the fields and selection to default
            }
        });


    }

    // fill the course data
    public void fillCourses() {
        lstCourses.add(new Course("Java", 1300, 6));
        lstCourses.add(new Course("Swift", 1500, 5));
        lstCourses.add(new Course("iOS", 1350, 5));
        lstCourses.add(new Course("Android", 1400, 7));
        lstCourses.add(new Course("Database", 1000, 4));
    }

    // method to fill the course names from list of courses for use in spinner
    public void fillCourseNames() {
        for (Course item : lstCourses) {
            courseNames.add(item.getName());
        }
    }

    // class to set the selection events for spinner
    public class SpinnerSelectionListeners implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            tvCourseFees.setText("$ " +lstCourses.get(i).getName());
            tvCourseHours.setText(lstCourses.get(i).getHours() + "  hours/week");
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            // do nothing
        }
    }

//    public class ButtonListeners implements View.OnClickListener {
//
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.btnAdd:
//                    break;
//
//                case R.id.btnClear:
//                    break;
//
//                default:
//                    break;
//            }
//        }
//    }


}