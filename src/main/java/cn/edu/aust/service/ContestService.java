package cn.edu.aust.service;

import cn.edu.aust.dao.ContestDao;
import cn.edu.aust.entity.Contest;
import cn.edu.aust.entity.ContestProblem;
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
     * 判断该比赛是否符合时间
     * @param contest
     * @return 1 未开始 2 可以访问 3 已过期
     */
    public int JudgeTime(Contest contest){
        //如果题目已经过期,则可以直接访问
        if (!contest.isDefunct()){
            return 2;
        }
        Date date = new Date();
        if (date.getTime() < contest.getStart_time().getTime()) {
            return 1;
        }
        else if (date.getTime() > contest.getEnd_time().getTime()) {
            if (contestDao.updateDefunct(contest.getContest_id())){
                logger.info(contest.getTitle()+"已经过期,数据库已更新更新");
            }else {
                logger.error(contest.getTitle()+"已经过期,数据库更新失败");
            }

            return 3;
        }
        return 2;
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
