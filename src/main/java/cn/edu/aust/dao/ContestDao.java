package cn.edu.aust.dao;

import cn.edu.aust.entity.Contest;
import cn.edu.aust.entity.ContestProblem;
import cn.edu.aust.entity.Problem;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 主管竞赛的DAO类
 */
@Repository("contestDao")
public class ContestDao {
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 查询出有效的比赛
     * @return
     */
    public List<Contest> findContestByvalid(){
        return sqlSessionTemplate.selectList("contestMapper.findContestByvalid");
    }

    /**
     * 查询出无效的比赛
     * @return
     */
    public List<Contest> findContestByInvalid(){
        return sqlSessionTemplate.selectList("contestMapper.findContestByInvalid");
    }

    /**
     * 根据id查询一个比赛
     * @param contest_id
     * @return
     */
    public Contest findContestById(int contest_id){
        return sqlSessionTemplate.selectOne("contestMapper.findContestById",contest_id);
    }
    /**
     * 根据id查询一个比赛下的题目
     * @param contest_id
     * @return
     */
    public List<ContestProblem> findCPList(int contest_id){
        return sqlSessionTemplate.selectList("contestMapper.findCPList",contest_id);
    }
    /**
     * 设置一个竞赛无效
     * @param contest_id
     * @return
     */
    public boolean updateDefunct(int contest_id){
        int k = sqlSessionTemplate.update("contestMapper.updateDefunct",contest_id);
        return k>0;
    }

    /**
     * 查找出一个用户做过的竞赛题
     * @return
     */
    public List<String> findUserACcon(Map<String,Integer> hashmap){
        return sqlSessionTemplate.selectList("contestMapper.findUserACcon",hashmap);
    }

    /**
     * 根据竞赛id和题目id查询出相应的比赛题目
     * @param hashmap
     * @return
     */
    public Problem findProblemById(Map<String,Integer> hashmap){
        return sqlSessionTemplate.selectOne("contestMapper.findProblemById",hashmap);
    }
}
