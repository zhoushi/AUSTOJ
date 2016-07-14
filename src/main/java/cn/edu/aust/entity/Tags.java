package cn.edu.aust.entity;

import java.io.Serializable;

/**
 * 侧边栏标签实体类
 */
public class Tags implements Serializable {
    private int id;//标识
    private String tag;//标签内容
    private int count;//点击次数

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
