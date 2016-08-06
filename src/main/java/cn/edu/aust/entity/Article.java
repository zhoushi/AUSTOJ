package cn.edu.aust.entity;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable{

    private static final long serialVersionUID = -3019799583900284339L;

    private Integer id;

    private String title;

    private Integer user_id;

    private String nickname;

    private String summary;

    private String content;

    private String tags;

    private String catelog;

    private Date start_time;

    private Boolean totop;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public String getCatelog() {
        return catelog;
    }

    public void setCatelog(String catelog) {
        this.catelog = catelog == null ? null : catelog.trim();
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Boolean getTotop() {
        return totop;
    }

    public void setTotop(Boolean totop) {
        this.totop = totop;
    }
}