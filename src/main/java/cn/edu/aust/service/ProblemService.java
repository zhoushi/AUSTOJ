package cn.edu.aust.service;

import cn.edu.aust.dao.ProblemDao;
import cn.edu.aust.entity.Problem;
import cn.edu.aust.util.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Niu Li
 * @date 2016/8/7
 */
@Service("problemService")
public class ProblemService {
    @Resource(name = "problemDao")
    private ProblemDao problemDao;

    /**
     * 查找出start阶段的题目
     * @return
     */
    public List<Problem> findStageProblem(PageUtil pageUtil){
        return problemDao.findStageProblem(pageUtil);
    }

    /**
     * 查找出指定目录下的全部题目
     * @param pageUtil
     * @return
     */
    public List<Problem> findCateProblem(PageUtil pageUtil){
        return problemDao.findCateProblem(pageUtil);
    }
    /**
     * 根据id查询出指定的题目
     */
    public Problem findProblemById(int id){
        return problemDao.findProblemById(id);
    }

}
