package cn.edu.aust.entity;

/**
 * 用于封装错误信息的类
 */
public class Error {
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
