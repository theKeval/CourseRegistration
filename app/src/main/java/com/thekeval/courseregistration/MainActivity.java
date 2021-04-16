package com.thekeval.courseregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // variables to hold the refence of UI elements
    TextView tvWelcomeMsg, tvCourseFees, tvCourseHours, tvTotalFees, tvTotalHours;
    Button btnAdd, btnReset, btnLogout;
    RadioButton rbGraduate, rbUnderGraduate;
    CheckBox cbAccommodations, cbMedInsurance;
    Spinner spinnerCourses;

    // variables to use in the logic
    ArrayList<Course> lstCourses = new ArrayList<>();
    ArrayList<Course> addedCourses = new ArrayList<>();
    ArrayList<String> courseNames = new ArrayList<>();
    String studentName = "";
    Course selectedCourse;
    static double totalFees = 0;
    static int totalHours = 0;

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
        btnLogout = findViewById(R.id.btnLogout);

        rbGraduate = findViewById(R.id.rbGraduate);
        rbUnderGraduate = findViewById(R.id.rbUnderGraduated);
        cbAccommodations = findViewById(R.id.cbAccomodation);
        cbMedInsurance = findViewById(R.id.cbMedInsurance);

        spinnerCourses = findViewById(R.id.spinner_courses);

        // set the data
        tvWelcomeMsg.setText("Welcome " + studentName + ",");
        rbUnderGraduate.setChecked(true);

        fillCourseNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, courseNames);
        spinnerCourses.setAdapter(adapter);
        spinnerCourses.setSelection(0, true);

        // set the event listeners
        spinnerCourses.setOnItemSelectedListener(new SpinnerSelectionListeners());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check for conditions and add the course if condition satisfy
                if (shouldAdd()) {
                    addedCourses.add(selectedCourse);

                    totalFees += selectedCourse.getFees();
                    totalHours += selectedCourse.getHours();

                    tvTotalFees.setText("$ " + totalFees);
                    tvTotalHours.setText(totalHours + "  hours/week");
                }
                else {
                    Toast.makeText(MainActivity.this, "You can't add this course. maximum hours limit reached", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // reset all the fields and selection to default
                rbUnderGraduate.setChecked(true);
                cbAccommodations.setChecked(false);
                cbMedInsurance.setChecked(false);
                spinnerCourses.setSelection(0, true);

                totalFees = 0;
                totalHours = 0;
                addedCourses = new ArrayList<>();

                tvCourseFees.setText("$ 0");
                tvCourseHours.setText("0  hours/week");
                tvTotalFees.setText("$ " + String.format("%.0f", totalFees));
                tvTotalHours.setText(totalHours + "  hours/week");
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivity", "logout clicked");
                onBackPressed();
            }
        });

        cbAccommodations.setOnCheckedChangeListener(new CheckboxListeners());
        cbMedInsurance.setOnCheckedChangeListener(new CheckboxListeners());

    }

    private boolean shouldAdd() {
        if (rbGraduate.isChecked()) {
            if (totalHours + selectedCourse.getHours() <= 21) {
                // add
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if (totalHours + selectedCourse.getHours() <= 19) {
                // add
                return true;
            }
            else {
                return false;
            }
        }
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
            tvCourseFees.setText("$ " +lstCourses.get(i).getFees());
            tvCourseHours.setText(lstCourses.get(i).getHours() + "  hours/week");

            selectedCourse = lstCourses.get(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            // do nothing
        }
    }


    public class CheckboxListeners implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            switch (compoundButton.getId()) {
                case R.id.cbAccomodation:

                    if (compoundButton.isChecked()) {
                        totalFees += 1000;
                    }
                    else {
                        totalFees -= 1000;
                    }

                    break;

                case R.id.cbMedInsurance:

                    if (compoundButton.isChecked()) {
                        totalFees += 700;
                    }
                    else {
                        totalFees -= 700;
                    }

                    break;

                default:
                    break;
            }

            tvTotalFees.setText("$ " + totalFees);
        }
    }


}