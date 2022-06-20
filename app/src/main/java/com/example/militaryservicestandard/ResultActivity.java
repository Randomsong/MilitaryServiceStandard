package com.example.militaryservicestandard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {

    private TextView tv_height,tv_weight,tv_bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        FindView();

        Double height = Double.valueOf(intent.getStringExtra("Height")) / 100;
        Double weight = Double.valueOf(intent.getStringExtra("Weight"));
        Double BMI =  weight / (height * height);
        tv_height.setText("身高：" + (height * 100) + "cm");
        tv_weight.setText("體重：" + weight + "kg");
        tv_bmi.setText("BMI：" + BMI);

    }
    private void FindView(){
        tv_height = findViewById(R.id.Result_TextView_Height);
        tv_weight = findViewById(R.id.Result_TextView_Weight);
        tv_bmi = findViewById(R.id.Result_TextView_BMI);
    }
}