package cn.edu.aust.dao;

import cn.edu.aust.entity.Solution;
import cn.edu.aust.entity.SolutionSource;
import cn.edu.aust.util.PageUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理提交判题和数据库交互的dao
 */
@Repository("solutionDao")
public class SolutionDao {
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 查询出用户提交的题目
     * @param pageUtil
     * @return
     */
    public List<Solution> findUserCmit(PageUtil pageUtil){
        return sqlSessionTemplate.selectList("solutionMapper.findUserCmit",pageUtil);
    }

    public int JudgePrepare(Solution solution, SolutionSource solutionSource){
        sqlSessionTemplate.insert("solutionMapper.addSolution",solution);
        solutionSource.setSolution_id(solution.getSolution_id());
        sqlSessionTemplate.insert("solutionMapper.addSolutionSource",solutionSource);
        return solution.getSolution_id();
    }
}
