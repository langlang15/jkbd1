package com.example.administrator.jkbd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.ExamApplication;
import com.example.administrator.bean.ExamInfo;
import com.example.administrator.bean.Question;
import com.example.administrator.biz.ExamBiz;
import com.example.administrator.biz.IExamBiz;
import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2017/6/29.
 */

public class ExamActivity extends AppCompatActivity {
    TextView tvExamInfo,tvExamTitle,tvOp1,tvOp2,tvOp3,tvOp4,tvload,tvNo,tv03,tv04;
    CheckBox cb01,cb02,cb03,cb04;
    LinearLayout layoutLoading,layout03,layout04;
    ProgressBar dialog;
    ImageView mImageView;
    IExamBiz biz;
    boolean isLoadExamInfo=false;
    boolean isLoadQuestions=false;
    LoadQuestionBroadcast mLoadQuestionBroadcast;
    LoadExamBroadcast mLoadExamBroadcast;

    boolean isLoadExamInfoRecevier=false;
    boolean isLoadQuestionsRecevier=false;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState ) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_exam);
        mLoadExamBroadcast=new LoadExamBroadcast();
        mLoadQuestionBroadcast=new LoadQuestionBroadcast();
        setListener();
        initView();
        laodData();
        biz=new ExamBiz();

    }

    private void setListener() {
        registerReceiver(mLoadExamBroadcast,new IntentFilter(ExamApplication.LOAD_EXAM_INFO));
        registerReceiver(mLoadQuestionBroadcast,new IntentFilter(ExamApplication.LOAD_EXAM_QUESTION));
    }

    private void laodData() {
        layoutLoading.setEnabled(false);//加载时不能点击
        new Thread(new Runnable(){
            @Override
            public void run() {
                biz.beginExam();
            }
        }).start();
    }

    private void initView() {
        dialog= (ProgressBar) findViewById(R.id.load_dialog);
        layoutLoading= (LinearLayout) findViewById(R.id.layout_loading);
        layout03= (LinearLayout) findViewById(R.id.layout_03);
        layout04= (LinearLayout) findViewById(R.id.layout_04);

        tvExamInfo = (TextView)findViewById(R.id.tv_examinfo);
        tvExamTitle = (TextView)findViewById(R.id.tv_exam_title);
        tvOp1 = (TextView)findViewById(R.id.tv_op1);
        tvOp2 = (TextView)findViewById(R.id.tv_op2);
        tvOp3 = (TextView)findViewById(R.id.tv_op3);
        tvOp4 = (TextView)findViewById(R.id.tv_op4);
        mImageView= (ImageView) findViewById(R.id.im_exam_image);
        tvload= (TextView) findViewById(R.id.tv_load);
        tvNo= (TextView) findViewById(R.id.tv_exam_no);

        cb01= (CheckBox) findViewById(R.id.cb_01);
        cb02= (CheckBox) findViewById(R.id.cb_02);
        cb03= (CheckBox) findViewById(R.id.cb_03);
        cb04= (CheckBox) findViewById(R.id.cb_04);

        tv03= (TextView) findViewById(R.id.tv_03);
        tv04= (TextView) findViewById(R.id.tv_04);

        layoutLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laodData();
                dialog.setVisibility(View.VISIBLE);//隐藏正在加载的图片
                tvload.setText("下载数据...");
            }
        });
    }

    private void initData() {
        if(isLoadQuestionsRecevier&&isLoadExamInfoRecevier){
            if(isLoadQuestions&&isLoadExamInfo){

                layoutLoading.setVisibility(View.GONE);
                ExamInfo examInfo=ExamApplication.getInstance().getExamInfo();
                if(examInfo!=null){
                    showData(examInfo);
                }
                showQuestion( biz.getExam());

            }else {
                layoutLoading.setEnabled(true);
                dialog.setVisibility(View.GONE);//隐藏正在加载的图片
                tvload.setText("下载失败，点击重新");
            }

        }

    }

    private void showQuestion(Question exam) {
        Log.e("showQuestion","showQuestion,exam="+exam);
        if(exam!=null){
            tvNo.setText(biz.getExamIndex());
            tvExamTitle.setText(exam.getQuestion());
            tvOp1.setText(exam.getItem1());
            tvOp2.setText(exam.getItem2());
            tvOp3.setText(exam.getItem3());
            tvOp4.setText(exam.getItem4());

            layout03.setVisibility(exam.getItem3().equals("")?View.GONE:View.VISIBLE);
            cb03.setVisibility(exam.getItem3().equals("")?View.GONE:View.VISIBLE);


            layout04.setVisibility(exam.getItem3().equals("")?View.GONE:View.VISIBLE);
            cb04.setVisibility(exam.getItem3().equals("")?View.GONE:View.VISIBLE);


            if(exam.getUrl()!=null&&!exam.getUrl().equals(""))
            {
                mImageView.setVisibility(View.VISIBLE);
                Picasso.with(ExamActivity.this).load(exam.getUrl()).into(mImageView);

            }else {
                mImageView.setVisibility(View.GONE);
            }


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mLoadExamBroadcast!=null){
            unregisterReceiver(mLoadExamBroadcast);
        }
        if(mLoadQuestionBroadcast!=null){
            unregisterReceiver(mLoadQuestionBroadcast);
        }
    }

    private void showData(ExamInfo examInfo) {
        tvExamInfo.setText(examInfo.toString());

    }

    public void preExam(View view) {
        showQuestion(biz.preQuestion());
    }

    public void next(View view) {
        showQuestion(biz.nextQuestion());
    }

    class LoadExamBroadcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
           boolean isSuccess= intent.getBooleanExtra(ExamApplication.LOAD_DATA_SUCESS,false);
            Log.e("LoadExamBroadcast","LoadExamBroadcast,isSuccss"+isSuccess);
            if(isSuccess){
                isLoadExamInfo=true;
            }
            isLoadExamInfoRecevier=true;
            initData();
        }
    }
    class LoadQuestionBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isSuccess = intent.getBooleanExtra(ExamApplication.LOAD_DATA_SUCESS, false);
            Log.e("LoadQuestionBroadcast","LoadQuestionBroadcast,isSuccss"+isSuccess);
            if (isSuccess) {
                isLoadQuestions = true;
            }
            isLoadQuestionsRecevier=true;
            initData();
        }
    }
}
