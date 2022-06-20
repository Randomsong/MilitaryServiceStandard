package com.example.militaryservicestandard.MainActivityFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.militaryservicestandard.R;

public class LookFragment extends Fragment {

    private RadioGroup rg_leftEye,rg_rightEye;
    private EditText et_leftEye,et_leftEyeLight,et_rightEye,et_rightEyeLight;
    private Button btn_check,btn_clear;
    private TextView tv_result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main_fragment_look,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FindView(view);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int leftEyeType = 0,rightEyeType = 0;
                double leftEye = 0,leftEyeLight,leftEyeDiopter,rightEye = 0,rightEyeLight,rightEyeDiopter;
                switch (rg_leftEye.getCheckedRadioButtonId()){
                    case R.id.Look_RadioButton_LeftEyeShort:
                        leftEyeType = 0;
                        break;
                    case R.id.Look_RadioButton_LeftEyeFar:
                        leftEyeType = 1;
                        break;
                }
                switch (rg_rightEye.getCheckedRadioButtonId()){
                    case R.id.Look_RadioButton_RightEyeShort:
                        rightEyeType = 0;
                        break;
                    case R.id.Look_RadioButton_RightEyeFar:
                        rightEyeType = 1;
                        break;
                }
                if (et_leftEye.getText().toString().equals("")){
                    leftEye = 0;
                }else{
                    if (leftEyeType == 0){
                        leftEye = 0 - Double.parseDouble(et_leftEye.getText().toString());
                    }
                    if (leftEyeType == 1){
                        leftEye = Double.parseDouble(et_leftEye.getText().toString());
                    }

                }
                if (et_leftEyeLight.getText().toString().equals("")){
                    leftEyeLight = 0;
                }else{
                    leftEyeLight = Double.parseDouble(et_leftEyeLight.getText().toString());
                }
                if (et_rightEye.getText().toString().equals("")){
                    rightEye = 0;
                }else{
                    if (rightEyeType == 0){
                        rightEye = 0 - Double.parseDouble(et_rightEye.getText().toString());
                    }
                    if (rightEyeType == 1){
                        rightEye = Double.parseDouble(et_rightEye.getText().toString());
                    }
                }
                if (et_rightEyeLight.getText().toString().equals("")){
                    rightEyeLight = 0;
                }else{
                    rightEyeLight = Double.parseDouble(et_rightEyeLight.getText().toString());
                }
                leftEyeDiopter = (leftEye - (leftEyeLight / 2)) / 100;
                rightEyeDiopter = (rightEye - (rightEyeLight / 2)) / 100;
                int result = CheckStandard(leftEyeDiopter,rightEyeDiopter);
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
                et_leftEye.setText("");
                et_leftEyeLight.setText("");
                et_rightEye.setText("");
                et_rightEyeLight.setText("");
                tv_result.setText("尚未計算");
                tv_result.setTextColor(0xff000000);
            }
        });
    }

    private void FindView(View view){
        rg_leftEye = view.findViewById(R.id.Look_RadioGroup_LeftEyeType);
        rg_rightEye = view.findViewById(R.id.Look_RadioGroup_RightEyeType);
        et_leftEye = view.findViewById(R.id.Look_EditText_LeftEye);
        et_leftEyeLight = view.findViewById(R.id.Look_EditText_LeftEyeLight);
        et_rightEye = view.findViewById(R.id.Look_EditText_RightEye);
        et_rightEyeLight = view.findViewById(R.id.Look_EditText_RightEyeLight);
        btn_check = view.findViewById(R.id.Look_Button_Check);
        btn_clear = view.findViewById(R.id.Look_Button_Clear);
        tv_result = view.findViewById(R.id.Look_TextView_Result);
    }

    private int CheckStandard(double leftEyeDiopter,double rightEyeDiopter){
        double DiopterDiffer = Math.abs(leftEyeDiopter - rightEyeDiopter);
        leftEyeDiopter = Math.abs(leftEyeDiopter);
        rightEyeDiopter = Math.abs(rightEyeDiopter);
        //兩眼散瞳後，驗光度數相差逾五屈光度者
        if (DiopterDiffer > 5){
            return 3;
        }
        //兩眼散瞳後，驗光度數相差逾四屈光度，在五屈光度以下者
        if (4 < DiopterDiffer && DiopterDiffer <= 5 ){
            return 2;
        }
        //一眼散瞳後驗光度數逾十一屈光度者
        if (leftEyeDiopter > 11 || rightEyeDiopter > 11){
            return 3;
        }
        //兩眼散瞳後，一眼或兩眼驗光度數逾十屈光度，在十一屈光度以下者
        if ((10 < leftEyeDiopter && leftEyeDiopter <= 11)||(10 < rightEyeDiopter && rightEyeDiopter <= 11)){
            return 2;
        }
        //兩眼散瞳後，驗光度數均在十屈光度以下者
        if (leftEyeDiopter < 10 && rightEyeDiopter < 10){
            return 1;
        }
        return 0;
    }
}
