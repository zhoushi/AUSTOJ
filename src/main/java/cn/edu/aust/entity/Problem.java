package cn.edu.aust.entity;

import java.io.Serializable;

public class Problem implements Serializable {

    private static final long serialVersionUID = 1428278415533462742L;

    private Integer problem_id;

    private String title;

    private String description;

    private String input;

    private String output;

    private String sample_input;

    private String sample_output;

    private String hint;

    private String tag;

    private Integer catelog;

    private Integer stage;

    private Integer spj;

    private Integer time_limit;

    private Integer memory_limit;

    private Integer accepted;

    private Integer solved;

    private Integer submit;

    private Integer submit_user;

    private String author;

    private Integer author_id;

    private Integer contest_id;

    private String oj_name;

    private Integer oj_pid;

    private Integer isvirtual;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Integer getCatelog() {
        return catelog;
    }

    public void setCatelog(Integer catelog) {
        this.catelog = catelog;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public Integer getSpj() {
        return spj;
    }

    public void setSpj(Integer spj) {
        this.spj = spj;
    }


    public Integer getAccepted() {
        return accepted;
    }

    public void setAccepted(Integer accepted) {
        this.accepted = accepted;
    }

    public Integer getSolved() {
        return solved;
    }

    public void setSolved(Integer solved) {
        this.solved = solved;
    }

    public Integer getSubmit() {
        return submit;
    }

    public void setSubmit(Integer submit) {
        if (submit == 0){
            submit = 1;
        }
        this.submit = submit;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(Integer problem_id) {
        this.problem_id = problem_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getSample_input() {
        return sample_input;
    }

    public void setSample_input(String sample_input) {
        this.sample_input = sample_input;
    }

    public String getSample_output() {
        return sample_output;
    }

    public void setSample_output(String sample_output) {
        this.sample_output = sample_output;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public Integer getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(Integer time_limit) {
        this.time_limit = time_limit;
    }

    public Integer getMemory_limit() {
        return memory_limit;
    }

    public void setMemory_limit(Integer memory_limit) {
        this.memory_limit = memory_limit;
    }

    public Integer getSubmit_user() {
        return submit_user;
    }

    public void setSubmit_user(Integer submit_user) {
        this.submit_user = submit_user;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public Integer getContest_id() {
        return contest_id;
    }

    public void setContest_id(Integer contest_id) {
        this.contest_id = contest_id;
    }

    public String getOj_name() {
        return oj_name;
    }

    public void setOj_name(String oj_name) {
        this.oj_name = oj_name;
    }

    public Integer getOj_pid() {
        return oj_pid;
    }

    public void setOj_pid(Integer oj_pid) {
        this.oj_pid = oj_pid;
    }

    public Integer getIsvirtual() {
        return isvirtual;
    }

    public void setIsvirtual(Integer isvirtual) {
        this.isvirtual = isvirtual;
    }
}