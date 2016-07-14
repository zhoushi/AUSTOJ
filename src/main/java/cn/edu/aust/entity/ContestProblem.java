package cn.edu.aust.entity;

/**
 * 保存竞赛题目的实体类,竞赛题目和普通题目有映射关系
 */
public class ContestProblem {

    private int id;

    private int problem_id;

    private int contest_id;

    private String title;

    private String num;

    private int point;

    private int accepted;

    private int solved;

    private int submit;

    private int submit_user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(int problem_id) {
        this.problem_id = problem_id;
    }

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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public int getSolved() {
        return solved;
    }

    public void setSolved(int solved) {
        this.solved = solved;
    }

    public int getSubmit() {
        return submit;
    }

    public void setSubmit(int submit) {
        this.submit = submit;
    }

    public int getSubmit_user() {
        return submit_user;
    }

    public void setSubmit_user(int submit_user) {
        this.submit_user = submit_user;
    }
}
