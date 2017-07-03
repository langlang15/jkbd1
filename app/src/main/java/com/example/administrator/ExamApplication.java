package com.example.administrator;

import android.app.Application;

import com.example.administrator.bean.ExamInfo;
import com.example.administrator.bean.Question;
import com.example.administrator.biz.IExamBiz;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */

public class ExamApplication extends Application {
    public  static String LOAD_EXAM_INFO="load_exam_info";
    public  static String LOAD_EXAM_QUESTION="load_exam_question";
    public  static String LOAD_DATA_SUCESS="load_data_sucess";
    ExamInfo mExamInfo;
    List<Question>mExamList;
    private  static ExamApplication instance;
    IExamBiz biz;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }

    public static ExamApplication getInstance(){
        return instance;
    }

    public ExamInfo getExamInfo() {
        return mExamInfo;
    }

    public void setmExamInfo(ExamInfo mExamInfo) {
        this.mExamInfo = mExamInfo;
    }

    public List<Question> getmExamList() {
        return mExamList;
    }

    public void setmExamList(List<Question> mExamList) {
        this.mExamList = mExamList;
    }
}
