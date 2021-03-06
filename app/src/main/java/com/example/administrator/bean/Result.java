package com.example.administrator.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */

public class Result {

    /**
     * error_code : 0
     * reason : ok
     * Result : [{"id":9,"question":"这个标志是何含义？","
     * answer":"2","item1":"距有人看守铁路道口50米","item2":
     * "距无人看守铁路道口100米","item3":"距有人看守铁路道口100米",
     * "item4":"距无人看守铁路道口50米","explains":"一道红线是50米，二道100米。",
     * "url":"http://images.juheapi.com/jztk/c1c2subject1/9.jpg"}]
     */

    private int error_code;
    private String reason;
    private List<Question> result;

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResult(List<Question> result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public String getReason() {
        return reason;
    }

    public List<Question> getResult() {
        return result;
    }

    public static class ResultEntity {
        /**
         * id : 9
         * question : 这个标志是何含义？
         * answer : 2
         * item1 : 距有人看守铁路道口50米
         * item2 : 距无人看守铁路道口100米
         * item3 : 距有人看守铁路道口100米
         * item4 : 距无人看守铁路道口50米
         * explains : 一道红线是50米，二道100米。
         * url : http://images.juheapi.com/jztk/c1c2subject1/9.jpg
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
}
