package com.shayankabiri.myorisk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;




public class MainActivity extends AppCompatActivity {
    ImageView logoTop;
    ImageView logoBottom;
    TextView tv;
    Button patientRecordButton;
    Button newPatientButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //this is all you need for python. write your script and get arguments and get the result
        if (!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        Python py = Python.getInstance();
        PyObject pyobj = py.getModule("script");
        PyObject obj = pyobj.callAttr("main");
        //here you have your final python result

        logoTop = (ImageView)findViewById(R.id.top_logo);
        logoBottom = (ImageView)findViewById(R.id.bottom_logo);
        Animation bounce = AnimationUtils.loadAnimation(this,R.anim.bounce);
        Animation blink = AnimationUtils.loadAnimation(this,R.anim.blink_anim);
        logoTop.startAnimation(bounce);
        logoBottom.startAnimation(bounce);

        patientRecordButton = (Button)findViewById(R.id.patients_record);
        patientRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent patientRecordActivity = new Intent(MainActivity.this, patient_record.class);
                startActivity(patientRecordActivity);
            }
        });
        newPatientButton = (Button)findViewById(R.id.new_patient);
        newPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newPatientActivity = new Intent(MainActivity.this, new_patient.class);
                startActivity(newPatientActivity);
            }
        });


    }
}