package cn.edu.aust.entity;

import java.io.Serializable;

/**
 * 对应SolutionSource的实体类
 */
public class SolutionSource implements Serializable{

    private static final long serialVersionUID = 6313715026527090493L;

    private int solution_id;
    private String source;

    public int getSolution_id() {
        return solution_id;
    }

    public void setSolution_id(int solution_id) {
        this.solution_id = solution_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
