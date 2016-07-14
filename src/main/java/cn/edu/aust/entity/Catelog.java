package cn.edu.aust.entity;

import java.io.Serializable;

/**
 * 题目分类实体类
 */
public class Catelog implements Serializable{
    private int id;//标识
    private String catename;//目录名

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }
}
