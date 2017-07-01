package com.example.administrator.jkbd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.ExamApplication;
import com.example.administrator.bean.ExamInfo;
import com.example.administrator.bean.Question;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public class ExamActivity extends AppCompatActivity {
    TextView tvExamInfo,tvExamTitle,tvOp1,tvOp2,tvOp3,tvOp4;
    ImageView mImageView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState ) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_exam);
        initView();
        initData();
    }

    private void initView() {
        tvExamInfo = (TextView)findViewById(R.id.tv_examinfo);
        tvExamTitle = (TextView)findViewById(R.id.tv_exam_title);
        tvOp1 = (TextView)findViewById(R.id.tv_op1);
        tvOp2 = (TextView)findViewById(R.id.tv_op2);
        tvOp3 = (TextView)findViewById(R.id.tv_op3);
        tvOp4 = (TextView)findViewById(R.id.tv_op4);
        mImageView= (ImageView) findViewById(R.id.im_exam_image);
    }

    private void initData() {
        ExamInfo examInfo=ExamApplication.getInstance().getExamInfo();
        if(examInfo!=null){
            showData(examInfo);
        }
        List<Question>examList=ExamApplication.getInstance().getmExamList();
        if(examList!=null){
            showQuestion(examList);
        }
    }

    private void showQuestion(List<Question> examList) {
        Question exam= examList.get(0);
        if(exam!=null){
            tvExamTitle.setText(exam.getQuestion());
            tvOp1.setText(exam.getItem1());
            tvOp2.setText(exam.getItem2());
            tvOp3.setText(exam.getItem3());
            tvOp4.setText(exam.getItem4());
            Picasso.with(ExamActivity.this).load(exam.getUrl()).into(mImageView);
        }
    }

    private void showData(ExamInfo examInfo) {
        tvExamInfo.setText(examInfo.toString());

    }
}
