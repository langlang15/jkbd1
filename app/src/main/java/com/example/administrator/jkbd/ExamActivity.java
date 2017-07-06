package com.example.administrator.jkbd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.ExamApplication;
import com.example.administrator.bean.ExamInfo;
import com.example.administrator.bean.Question;
import com.example.administrator.biz.ExamBiz;
import com.example.administrator.biz.IExamBiz;
import com.example.administrator.view.QuestionAdapter;
import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/29.
 */

public class ExamActivity extends AppCompatActivity {


    TextView []tvOps=new TextView[4];
    CheckBox[] cbs = new CheckBox[4];

    QuestionAdapter mAdapter;


    IExamBiz biz;
    boolean isLoadExamInfo = false;
    boolean isLoadQuestions = false;
    LoadQuestionBroadcast mLoadQuestionBroadcast;
    LoadExamBroadcast mLoadExamBroadcast;

    boolean isLoadExamInfoRecevier = false;
    boolean isLoadQuestionsRecevier = false;
    @BindView(R.id.load_dialog)ProgressBar dialog;
    @BindView(R.id.tv_load)TextView tvload;
    @BindView(R.id.layout_loading)LinearLayout layoutLoading;
    @BindView(R.id.tv_examinfo)TextView tvExamInfo;
    @BindView(R.id.tv_time)TextView tvTime;
    @BindView(R.id.tv_exam_no)TextView tvNo;
    @BindView(R.id.tv_exam_title)TextView tvExamTitle;
    @BindView(R.id.im_exam_image)ImageView mImageView;
    @BindView(R.id.tv_op1)TextView tvOp1;
    @BindView(R.id.tv_op2)TextView tvOp2;
    @BindView(R.id.tv_03)TextView tv03;
    @BindView(R.id.tv_op3)TextView tvOp3;
    @BindView(R.id.layout_03)LinearLayout layout03;
    @BindView(R.id.tv_04)TextView tv04;
    @BindView(R.id.tv_op4)TextView tvOp4;
    @BindView(R.id.layout_04)LinearLayout layout04;
    @BindView(R.id.cb_01)CheckBox cb01;
    @BindView(R.id.cb_02)CheckBox cb02;
    @BindView(R.id.cb_03)CheckBox cb03;
    @BindView(R.id.cb_04)CheckBox cb04;
    @BindView(R.id.gallery)Gallery mGallery;
    @BindView(R.id.btnnext)Button btnnext;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_exam);
        ButterKnife.bind(this);
        mLoadExamBroadcast = new LoadExamBroadcast();
        mLoadQuestionBroadcast = new LoadQuestionBroadcast();
        setListener();
        initView();
        laodData();
        biz = new ExamBiz();

    }

    private void setListener() {
        registerReceiver(mLoadExamBroadcast, new IntentFilter(ExamApplication.LOAD_EXAM_INFO));
        registerReceiver(mLoadQuestionBroadcast, new IntentFilter(ExamApplication.LOAD_EXAM_QUESTION));
    }

    private void laodData() {
        layoutLoading.setEnabled(false);//加载时不能点击
        new Thread(new Runnable() {
            @Override
            public void run() {
                biz.beginExam();
            }
        }).start();
    }

    private void initView() {
//        dialog = (ProgressBar) findViewById(R.id.load_dialog);
//        layoutLoading = (LinearLayout) findViewById(R.id.layout_loading);
//        layout03 = (LinearLayout) findViewById(R.id.layout_03);
//        layout04 = (LinearLayout) findViewById(R.id.layout_04);
//        tvTime = (TextView) findViewById(R.id.tv_time);
//        tvExamInfo = (TextView) findViewById(R.id.tv_examinfo);
//        tvExamTitle = (TextView) findViewById(R.id.tv_exam_title);
//        tvOp1 = (TextView) findViewById(R.id.tv_op1);
//        tvOp2 = (TextView) findViewById(R.id.tv_op2);
//        tvOp3 = (TextView) findViewById(R.id.tv_op3);
//        tvOp4 = (TextView) findViewById(R.id.tv_op4);
//        mImageView = (ImageView) findViewById(R.id.im_exam_image);
//        tvload = (TextView) findViewById(R.id.tv_load);
//        tvNo = (TextView) findViewById(R.id.tv_exam_no);
//
//        cb01 = (CheckBox) findViewById(R.id.cb_01);
//        cb02 = (CheckBox) findViewById(R.id.cb_02);
//        cb03 = (CheckBox) findViewById(R.id.cb_03);
//        cb04 = (CheckBox) findViewById(R.id.cb_04);
//
//        mGallery = (Gallery) findViewById(R.id.gallery);
//        tv03 = (TextView) findViewById(R.id.tv_03);
//        tv04 = (TextView) findViewById(R.id.tv_04);


        cbs[0] = cb01;
        cbs[1] = cb02;
        cbs[2] = cb03;
        cbs[3] = cb04;
        tvOps[0]=tvOp1;
        tvOps[1]=tvOp2;
        tvOps[2]=tvOp3;
        tvOps[3]=tvOp4;

//        layoutLoading.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                laodData();
//                dialog.setVisibility(View.VISIBLE);//隐藏正在加载的图片
//                tvload.setText("下载数据...");
//            }
//        });
        cb01.setOnCheckedChangeListener(listener);
        cb02.setOnCheckedChangeListener(listener);
        cb03.setOnCheckedChangeListener(listener);
        cb04.setOnCheckedChangeListener(listener);
    }
    @OnClick(R.id.layout_loading)void onLoadClick(){
        laodData();
    }

    CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                int userAnswer = 0;
                switch (buttonView.getId()) {
                    case R.id.cb_01:
                        userAnswer = 1;
                        break;
                    case R.id.cb_02:
                        userAnswer = 2;
                        break;
                    case R.id.cb_03:
                        userAnswer = 3;
                        break;
                    case R.id.cb_04:
                        userAnswer = 4;
                        break;
                }
                if (userAnswer > 0) {
                    for (CheckBox cb : cbs) {
                        cb.setChecked(false);
                    }
                    cbs[userAnswer - 1].setChecked(true);
                }
            }

        }
    };

    private void initData() {
        if (isLoadQuestionsRecevier && isLoadExamInfoRecevier) {
            if (isLoadQuestions && isLoadExamInfo) {

                layoutLoading.setVisibility(View.GONE);
                ExamInfo examInfo = ExamApplication.getInstance().getExamInfo();
                if (examInfo != null) {
                    showData(examInfo);
                    initTimer(examInfo);
                }
                initGallery();
                showQuestion(biz.getExam());


            } else {
                layoutLoading.setEnabled(true);
                dialog.setVisibility(View.GONE);//隐藏正在加载的图片
                tvload.setText("下载失败，点击重新");
            }

        }

    }

    private void initGallery() {
        mAdapter = new QuestionAdapter(this);
        mGallery.setAdapter(mAdapter);
        mGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                saveUserAnswer();
                showQuestion(biz.getExam(position));
            }
        });
    }

    private void initTimer(ExamInfo examInfo) {
        int sumTime = examInfo.getLimitTime() * 60 * 1000;
        //int sumTime=60*1000;

        final long overTime = (sumTime + (System.currentTimeMillis()));
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long l = overTime - System.currentTimeMillis();
                final long min = (l / 1000 / 60);
                final long sec = (l / 1000 % 60);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvTime.setText("剩余时间：" + min + "分" + sec + "秒");
                    }
                });
            }
        }, 0, 1000);//没1000毫秒刷新时间
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        commit(null);
                    }
                });
            }
        }, sumTime);
    }


    private void showQuestion(Question exam) {
        Log.e("showQuestion", "showQuestion,exam=" + exam);
        if (exam != null) {
            tvNo.setText(biz.getExamIndex());
            tvExamTitle.setText(exam.getQuestion());
            tvOp1.setText(exam.getItem1());
            tvOp2.setText(exam.getItem2());
            tvOp3.setText(exam.getItem3());
            tvOp4.setText(exam.getItem4());
            layout03.setVisibility(exam.getItem3().equals("") ? View.GONE : View.VISIBLE);
            cb03.setVisibility(exam.getItem3().equals("") ? View.GONE : View.VISIBLE);
            layout04.setVisibility(exam.getItem3().equals("") ? View.GONE : View.VISIBLE);
            cb04.setVisibility(exam.getItem3().equals("") ? View.GONE : View.VISIBLE);
            if (exam.getUrl() != null && !exam.getUrl().equals("")) {
                mImageView.setVisibility(View.VISIBLE);
                Picasso.with(ExamActivity.this).load(exam.getUrl()).into(mImageView);

            } else {
                mImageView.setVisibility(View.GONE);
            }
            resetOption();
            String userAnswer = exam.getUaserAnswer();
            if (userAnswer != null && !userAnswer.equals("")) {
                int userCB = Integer.parseInt(userAnswer) - 1;
                cbs[userCB].setChecked(true);
                setOptions(true);
                setAnswerTextColor(userAnswer,exam.getAnswer());
            } else {
                setOptions(false);
                setOptionsColoe();
            }
        }
    }

    private void setOptionsColoe() {
        for(TextView tvOp:tvOps)
        {
            tvOp.setTextColor(getResources()
                    .getColor(R.color.black));
        }
    }

    private void setAnswerTextColor(String userAnswer, String answer) {
        int ra=Integer.parseInt(answer)-1;
        for(int i=0;i<tvOps.length;i++){
            if(i==ra){
                tvOps[i].setTextColor(getResources()
                        .getColor(R.color.green));
            }else {
                if(!userAnswer.equals(answer)){
                    int ua=Integer.parseInt(userAnswer)-1;
                    if(i==ua){
                        tvOps[i].setTextColor(getResources()
                                .getColor(R.color.red));
                    }else {
                        tvOps[i].setTextColor(getResources()
                                .getColor(R.color.black));
                    }

                }
            }
        }

    }

    private void setOptions(boolean hasAnswer) {
        for (CheckBox cb : cbs) {
            cb.setEnabled(!hasAnswer);
        }
    }

    private void resetOption() {
        for (CheckBox cb : cbs) {
            cb.setChecked(false);
        }
    }

    private void saveUserAnswer() {

        for (int i = 0; i < cbs.length; i++) {
            if (cbs[i].isChecked()) {
                biz.getExam().setUaserAnswer(String.valueOf(i + 1));
                setOptions(true);
                mAdapter.notifyDataSetChanged();

                return;
            }
        }
        biz.getExam().setUaserAnswer("");
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLoadExamBroadcast != null) {
            unregisterReceiver(mLoadExamBroadcast);
        }
        if (mLoadQuestionBroadcast != null) {
            unregisterReceiver(mLoadQuestionBroadcast);
        }
    }

    private void showData(ExamInfo examInfo) {
        tvExamInfo.setText(examInfo.toString());

    }

    public void preExam(View view) {
        saveUserAnswer();
        showQuestion(biz.preQuestion());
    }

    public void next(View view) {
        saveUserAnswer();
        showQuestion(biz.nextQuestion());
    }

    public void commit(View view){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("交卷").setMessage("你还有剩余时间，确认交卷吗？")
                .setPositiveButton("确认", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        commit();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.setCancelable(false);
        builder.create().show();
    }

    public void commit() {
        saveUserAnswer();
        int s = biz.commitExam();
        View inflate = View.inflate(this, R.layout.layout_result, null);
        TextView tvResult = (TextView) inflate.findViewById(R.id.tv_result);
        tvResult.setText("你的分数为\n" + s + "分");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.exam_commit32x32).setTitle("交卷")
                .setView(inflate)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        builder.create().show();

    }

    class LoadExamBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isSuccess = intent.getBooleanExtra(ExamApplication.LOAD_DATA_SUCESS, false);
            Log.e("LoadExamBroadcast", "LoadExamBroadcast,isSuccss" + isSuccess);
            if (isSuccess) {
                isLoadExamInfo = true;
            }
            isLoadExamInfoRecevier = true;
            initData();
        }
    }

    class LoadQuestionBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isSuccess = intent.getBooleanExtra(ExamApplication.LOAD_DATA_SUCESS, false);
            Log.e("LoadQuestionBroadcast", "LoadQuestionBroadcast,isSuccss" + isSuccess);
            if (isSuccess) {
                isLoadQuestions = true;
            }
            isLoadQuestionsRecevier = true;
            initData();
        }
    }
}
