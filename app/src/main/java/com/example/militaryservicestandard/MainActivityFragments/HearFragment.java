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

public class HearFragment extends Fragment {

    private EditText et_leftEar,et_rightEar;
    private Button btn_check,btn_clear;
    private TextView tv_result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main_fragment_hear,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FindView(view);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double left,right;
                if (et_leftEar.getText().toString().equals("")){
                    left = 0;
                }else {
                    left = Double.parseDouble(et_leftEar.getText().toString());
                }
                if (et_rightEar.getText().toString().equals("")){
                    right = 0;
                }else {
                    right = Double.parseDouble(et_rightEar.getText().toString());
                }
                int result = CheckStandard(left,right);
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
                    tv_result.setText("資料錯誤或尚未規定");
                    tv_result.setTextColor(0xff000000);
                }
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_leftEar.setText("");
                et_rightEar.setText("");
                tv_result.setText("尚未計算");
                tv_result.setTextColor(0xff000000);
            }
        });
    }

    private void FindView(View view){
        et_leftEar = view.findViewById(R.id.Hear_EditText_LeftEar);
        et_rightEar = view.findViewById(R.id.Hear_EditText_RightEar);
        btn_check = view.findViewById(R.id.Hear_Button_Check);
        btn_clear = view.findViewById(R.id.Hear_Button_Clear);
        tv_result = view.findViewById(R.id.Hear_TextView_Result);
    }

    private int CheckStandard(double left,double right){
        double better = Math.max(left,right);
        //兩耳閾值均逾六十分貝者
        if (left > 60 && right > 60){
            return 3;
        }
        //一耳閾值九十分貝以上者
        if(left >= 90 || right >= 90){
            return 3;
        }
        //兩耳閾值逾二十分貝者
        if (left > 20 && right > 20){
            //兩耳均逾二十分貝且優耳(較好耳)未達四十五分貝者
            if (better < 45 ){
                return 1;
            }
            //一耳閾值逾二十分貝且另耳逾七十貝者
            if (better > 70){
                return 3;
            }
            //兩耳均在四十五分貝以上且優耳(較好耳)在六十分貝以下者
            if (left > 45 && right > 45){
                if (better <= 60){
                    return 2;
                }
            }
        }
        //一耳在二十分貝以下
        if(left <= 20 || right <= 20){
            //另耳逾二十分貝在七十分貝以下者
            if(better > 20 && better <= 70){
                return 1;
            }
            //另耳逾七十分貝者
            if (better > 70){
                return 2;
            }
        }

        return 0;
    }
}
