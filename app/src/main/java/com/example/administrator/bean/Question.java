package com.example.administrator.bean;

/**
 * Created by Administrator on 2017/6/28.
 */

public class Question {

    /**
     * id : 758
     * question : 年龄在60周岁以上的驾驶人多长时间提交一次身体条件证明？
     * answer : 4
     * item1 : 每3年
     * item2 : 每2年
     * item3 : 每6个月
     * item4 : 每1年
     * explains : 记住吧
     * url :
     */

    private int id;
    private String question;
    private String answer;
    private String item1;
    private String item2;
    private String item3;
    private String item4;
    private String explains;
    private String url;

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getItem1() {
        return item1;
    }

    public String getItem2() {
        return item2;
    }

    public String getItem3() {
        return item3;
    }

    public String getItem4() {
        return item4;
    }

    public String getExplains() {
        return explains;
    }

    public String getUrl() {
        return url;
    }
}
