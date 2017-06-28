package com.example.administrator.bean;

/**
 * Created by Administrator on 2017/6/28.
 */

public class result {
    /**
     * error_code : 0
     * reason : ok
     * result : 0
     */

    private int error_code;
    private String reason;
    private String result;

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public String getReason() {
        return reason;
    }

    public String getResult() {
        return result;
    }
}
