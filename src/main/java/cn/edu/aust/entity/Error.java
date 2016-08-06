package cn.edu.aust.entity;

import java.io.Serializable;

/**
 * 用于封装错误信息的类
 */
public class Error implements Serializable{

    private static final long serialVersionUID = -6783328378333152397L;

    private int errorcode;

    private String error;

    public Error(int errorcode, String error) {
        this.errorcode = errorcode;
        this.error = error;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
