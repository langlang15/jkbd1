package com.example.administrator.biz;

import com.example.administrator.dao.ExamDao;
import com.example.administrator.dao.IExamDao;

/**
 * Created by Administrator on 2017/7/1.
 */

public class ExamBiz implements IExamBiz {
    IExamDao dao;

    public ExamBiz() {
        this.dao = new ExamDao();
    }

    @Override
    public void beginExam() {
        dao.loadExanImfo();
        dao.loadQuestionLists();
    }

    @Override
    public void nextQuestion() {

    }

    @Override
    public void preQuestion() {

    }

    @Override
    public void commitExam() {

    }
}
