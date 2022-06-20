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

public class FootFragment extends Fragment {

    private RadioGroup rg_type;
    private EditText et_angle;
    private Button btn_check,btn_clear;
    private TextView tv_result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main_fragment_foot,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FindView(view);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = "";
                double angle;
                switch (rg_type.getCheckedRadioButtonId()){
                    case R.id.Foot_RadioButton_Flat:
                        type = "扁平足";
                        break;
                    case R.id.Foot_RadioButton_Hollow:
                        type = "空凹足";
                        break;
                }
                if (et_angle.getText().toString().equals("")){
                    angle = 0;
                }else{
                    angle = Double.parseDouble(et_angle.getText().toString());
                }
                int result = CheckStandard(type,angle);
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
                et_angle.setText("");
                tv_result.setText("尚未計算");
                tv_result.setTextColor(0xff000000);
            }
        });
    }

    private void FindView(View view){
        rg_type = view.findViewById(R.id.Foot_RadioGroup_Type);
        et_angle = view.findViewById(R.id.Foot_EditText_Angle);
        btn_check = view.findViewById(R.id.Foot_Button_Check);
        btn_clear = view.findViewById(R.id.Foot_Button_Clear);
        tv_result = view.findViewById(R.id.Foot_TextView_Result);
    }

    private int CheckStandard(String type,double angle){
        switch (type){
            case "扁平足":
                if (angle > 168){
                    return 3;
                }else if (angle > 165){
                    return 2;
                }else {
                    return 1;
                }
            case "空凹足":
                if (angle > 90){
                    return 3;
                }else if (angle > 60){
                    return 2;
                }else {
                    return 1;
                }
        }
        return 0;
    }
}
