package com.example.militaryservicestandard.MainActivityFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.militaryservicestandard.R;

public class BMIFragment extends Fragment {

    private EditText et_height,et_weight;
    private Button btn_check,btn_clear;
    private TextView tv_bmi,tv_result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main_fragment_bmi,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        FindView(view);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double height,weight,bmi;
                if (et_height.getText().toString().equals("")){
                    height = 0;
                }else {
                    height = Double.parseDouble(et_height.getText().toString()) / 100;
                }
                if (et_weight.getText().toString().equals("")){
                    weight = 0;
                }else {
                    weight = Double.parseDouble(et_weight.getText().toString());
                }
                bmi = weight / (height * height);
                tv_bmi.setText("BMI：" + bmi);
                int result = CheckStandard(bmi);
                if (result == 1){
                    tv_result.setText("常備役");
                    tv_result.setTextColor(0xff303f9f);
                }else if (result == 2){
                    tv_result.setText("替代役");
                    tv_result.setTextColor(0xff00c853);
                }else if (result == 3){
                    tv_result.setText("免役");
                    tv_result.setTextColor(0xffd50000);
                }else {
                    tv_result.setText("資料錯誤");
                    tv_result.setTextColor(0xff000000);
                }
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_height.setText("");
                et_weight.setText("");
                tv_bmi.setText("請點選計算來取得BMI");
                tv_result.setText("尚未計算");
                tv_result.setTextColor(0xff000000);
            }
        });
    }

    private void FindView(View view){
        et_height = view.findViewById(R.id.BMI_EditTextNumber_Height);
        et_weight = view.findViewById(R.id.BMI_EditTextNumber_Weight);
        btn_check = view.findViewById(R.id.BMI_Button_Check);
        btn_clear = view.findViewById(R.id.BMI_Button_Clear);
        tv_bmi = view.findViewById(R.id.BMI_TextView_BMI);
        tv_result = view.findViewById(R.id.BMI_TextView_Result);
    }

    private int CheckStandard(double bmi){
        if (17 <= bmi && bmi <= 31){
            return 1;
        }else if ((16.5 <= bmi && bmi < 17) || (31 < bmi && bmi <= 31.5)){
            return 2;
        }else if (bmi < 16.5 || 31.5 < bmi){
            return 3;
        }
        return 0;
    }
}
