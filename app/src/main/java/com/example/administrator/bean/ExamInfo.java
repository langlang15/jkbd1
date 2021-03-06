package com.example.administrator.bean;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ExamInfo {
    /**
     * subjectTitle : c1
     * uid : 1
     * limitTime : 10
     * questionCount : 100
     */

    private String subjectTitle;
    private int uid;
    private int limitTime;
    private int questionCount;

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }

    @Override
    public String toString() {
        return "考试科目：" +subjectTitle+"\n"+
                "考题数量：" + questionCount +"\n"+
                "考试时间：" + limitTime +"分钟";

    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;

    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public int getUid() {
        return uid;
    }

    public int getLimitTime() {
        return limitTime;
    }

    public int getQuestionCount() {
        return questionCount;
    }
}
