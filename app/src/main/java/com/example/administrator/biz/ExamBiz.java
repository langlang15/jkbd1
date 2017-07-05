package com.example.administrator.biz;

import com.example.administrator.ExamApplication;
import com.example.administrator.bean.Question;
import com.example.administrator.dao.ExamDao;
import com.example.administrator.dao.IExamDao;

import java.util.List;

/**
 * Created by Administrator on 2017/7/1.
 */

public class ExamBiz implements IExamBiz {
    IExamDao dao;
    int examIndex=0;
    List<Question> examList=null;

    public ExamBiz() {
        this.dao = new ExamDao();
    }

    @Override
    public void beginExam() {
        examIndex=0;
        dao.loadExanImfo();
        dao.loadQuestionLists();

    }
    @Override
    public Question getExam(){
        examList= ExamApplication.getInstance().getmExamList();

        if(examList!=null){
            return examList.get(examIndex);
        }else {
            return null;
        }

    }
    @Override
    public Question nextQuestion() {
        if(examList!=null&&examIndex<examList.size()-1){
            examIndex++;
            return examList.get(examIndex);

        }else {
            return null;
        }
    }

    @Override
    public Question preQuestion() {
        if(examList!=null&&examIndex>0){
            examIndex--;
            return examList.get(examIndex);

        }else {
            return null;
        }
    }

    @Override
    public int commitExam() {
        int s=0;
        for(Question exam:examList){

            String userAnswer =exam.getUaserAnswer();
            if(userAnswer!=null&& !userAnswer.equals("")){
                if(exam.getAnswer().equals(userAnswer)){
                    s++;
                }
            }
        }
        return s;
    }

    @Override
    public Question getExam(int index) {
        examList= ExamApplication.getInstance().getmExamList();
        examIndex=index;
        if(examList!=null){
            return examList.get(examIndex);
        }else {
            return null;
        }
    }

    @Override
    public String getExamIndex() {
        return (examIndex+1)+".";
    }
}
