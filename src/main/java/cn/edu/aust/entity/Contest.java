package cn.edu.aust.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 竞赛对应的实体类
 */
public class Contest implements Serializable{

    private static final long serialVersionUID = -2229517079645054233L;

    private int contest_id;
    private String title;
    private Date start_time;
    private Date end_time;
    private String description;
    private int type;
    private String password;
    private String create_user;
    private int user_id;
    private boolean defunct;

    public int getContest_id() {
        return contest_id;
    }

    public void setContest_id(int contest_id) {
        this.contest_id = contest_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isDefunct() {
        return defunct;
    }

    public void setDefunct(boolean defunct) {
        this.defunct = defunct;
    }
}
