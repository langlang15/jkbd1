package com.example.administrator.jkbd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.administrator.ExamApplication;
import com.example.administrator.bean.ExamInfo;

/**
 * Created by Administrator on 2017/6/29.
 */

public class ExamActivity extends AppCompatActivity {
    TextView tvExamInfo;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState ) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_exam);
        initView();
        initData();
    }

    private void initView() {
        tvExamInfo = (TextView)findViewById(R.id.tv_examinfo);
    }

    private void initData() {
        ExamInfo examInfo=ExamApplication.getInstance().getExamInfo();
        if(examInfo!=null){
            showData(examInfo);
        }
    }

    private void showData(ExamInfo examInfo) {
        tvExamInfo.setText(examInfo.toString());

    }
}
