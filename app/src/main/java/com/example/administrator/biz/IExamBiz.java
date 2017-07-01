package com.example.administrator.biz;

/**
 * Created by Administrator on 2017/7/1.
 */

public interface IExamBiz {
    void beginExam();
    void nextQuestion();
    void preQuestion();
    void commitExam();
}
