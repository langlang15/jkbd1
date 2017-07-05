package com.example.administrator.biz;

import com.example.administrator.bean.Question;

/**
 * Created by Administrator on 2017/7/1.
 */

public interface IExamBiz {
    void beginExam();
    Question getExam();
    Question nextQuestion();
    Question preQuestion();
    int commitExam();
    Question getExam(int index);
    String getExamIndex();
}
