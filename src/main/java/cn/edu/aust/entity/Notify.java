package cn.edu.aust.entity;

import java.io.Serializable;

/**
 * 具体通知对应的实体类
 */
public class Notify implements Serializable {
    private int id;
    private String notify_name;
    private int article_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotify_name() {
        return notify_name;
    }

    public void setNotify_name(String notify_name) {
        this.notify_name = notify_name;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }
}
