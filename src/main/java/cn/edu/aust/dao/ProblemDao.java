package cn.edu.aust.dao;

import cn.edu.aust.entity.Problem;
import cn.edu.aust.util.PageUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 具体负责题目和数据库的DAO
 */
@Repository("problemDao")
public class ProblemDao {
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 查找出start阶段的题目
     * @return
     */
    public List<Problem> findStageProblem(PageUtil pageUtil){
        return sqlSessionTemplate.selectList("problemMapper.findStageProblem",pageUtil);
    }

    /**
     * 查找出指定目录下的全部题目
     * @param pageUtil
     * @return
     */
    public List<Problem> findCateProblem(PageUtil pageUtil){
        return sqlSessionTemplate.selectList("problemMapper.findCateProblem",pageUtil);
    }
    /**
     * 根据id查询出指定的题目
     */
    public Problem findProblemById(int id){
        return sqlSessionTemplate.selectOne("problemMapper.findProblemById",id);
    }

}
