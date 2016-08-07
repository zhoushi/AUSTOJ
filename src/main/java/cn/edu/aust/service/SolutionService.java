package cn.edu.aust.service;

import cn.edu.aust.dao.SolutionDao;
import cn.edu.aust.entity.Solution;
import cn.edu.aust.entity.SolutionSource;
import cn.edu.aust.util.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Niu Li
 * @date 2016/8/7
 */
@Service("solutionService")
public class SolutionService {
    @Resource(name = "solutionDao")
    private SolutionDao solutionDao;

    /**
     * 查询出用户提交的题目
     * @param pageUtil
     * @return
     */
    public List<Solution> findUserCmit(PageUtil pageUtil){
        return solutionDao.findUserCmit(pageUtil);
    }

    /**
     * 插入预判题数据
     * @param solution
     * @param solutionSource
     * @return
     */
    public int InsertPrepare(Solution solution, SolutionSource solutionSource){
        return solutionDao.InsertPrepare(solution,solutionSource);
    }
}
