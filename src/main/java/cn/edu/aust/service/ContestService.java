package cn.edu.aust.service;

import cn.edu.aust.dao.ContestDao;
import cn.edu.aust.entity.Contest;
import cn.edu.aust.entity.ContestProblem;
import cn.edu.aust.entity.Problem;
import cn.edu.aust.util.Contants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 具体比赛的策略类
 */
@Service("contestService")
public class ContestService {

    private Logger logger = Logger.getLogger(ContestService.class);

    @Resource(name = "contestDao")
    private ContestDao contestDao;

    /**
     * 查询出有效的比赛
     * @return
     */
    public List<Contest> findContestByvalid(){
        return contestDao.findContestByvalid();
    }

    /**
     * 查询出无效的比赛
     * @return
     */
    public List<Contest> findContestByInvalid(){
        return contestDao.findContestByInvalid();
    }

    /**
     * 根据id查询一个比赛
     * @param contest_id
     * @return
     */
    public Contest findContestById(int contest_id){
        return contestDao.findContestById(contest_id);
    }
    /**
     * 根据id查询一个比赛下的题目
     * @param contest_id
     * @return
     */
    public List<ContestProblem> findCPList(int contest_id){
        return contestDao.findCPList(contest_id);
    }
    /**
     * 设置一个竞赛无效
     * @param contest_id
     * @return
     */
    public boolean updateDefunct(int contest_id){
        return contestDao.updateDefunct(contest_id);
    }

    /**
     * 查找出一个用户做过的竞赛题
     * @return
     */
    public List<String> findUserACcon(Map<String,Integer> hashmap){
        return contestDao.findUserACcon(hashmap);
    }

    /**
     * 根据竞赛id和题目id查询出相应的比赛题目
     * @param hashmap
     * @return
     */
    public Problem findProblemById(Map<String,Integer> hashmap){
        return contestDao.findProblemById(hashmap);
    }

    /**
     * 判断该比赛是否符合时间
     * @param contest
     * @return 1 未开始 2 可以访问 3 已过期
     */
    public int JudgeTime(Contest contest){
        //如果题目已经过期,则可以直接访问
        if (!contest.isDefunct()){
            return Contants.CONTEST_START;
        }
        Date date = new Date();
        if (date.getTime() < contest.getStart_time().getTime()) {
            return Contants.CONTEST_UNSTART;
        }
        else if (date.getTime() > contest.getEnd_time().getTime()) {
            if (updateDefunct(contest.getContest_id())){
                logger.info(contest.getTitle()+"已经过期,数据库已更新更新");
            }else {
                logger.error(contest.getTitle()+"已经过期,数据库更新失败");
            }

            return Contants.CONTEST_OVERDUE;
        }
        return Contants.CONTEST_START;
    }

    /**
     * 对竞赛题进行包装,用户完成的要显示颜色
     * @param lists
     */
    public void packContest(List<ContestProblem> lists, int contest_id, int user_id){
        Map<String,Integer> maps = new HashMap<>();
        maps.put("contest_id",contest_id);
        maps.put("id",user_id);
        List<String> nums = contestDao.findUserACcon(maps);
        if (nums!= null && nums.size()!=0){
            lists.forEach(p->{
                if (nums.contains(p.getNum())){
                    p.setNum("<span class='text-success'>"+p.getNum()+"</span>");
                }
            });
        }
    }
}
