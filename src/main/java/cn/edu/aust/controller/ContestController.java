package cn.edu.aust.controller;

import cn.edu.aust.entity.Contest;
import cn.edu.aust.entity.ContestProblem;
import cn.edu.aust.entity.Problem;
import cn.edu.aust.entity.User;
import cn.edu.aust.service.ContestService;
import cn.edu.aust.util.Contants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 具体关于比赛的控制器,目前写的相当臃肿,应该想一个同意验证的方法,然后用拦截器实现
 */
@Controller
@RequestMapping(value = "/contest")
public class ContestController {

    @Resource(name = "contestService")
    private ContestService contestService;

    /**
     * 前往竞赛页面
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ModelAndView toContest(Integer offset){
        ModelAndView model = new ModelAndView();
        model.setViewName("contest");
        List<Contest> listsValid = contestService.findContestByvalid();
        if(offset == null){
            offset = 1;
        }
        PageHelper.startPage(offset, Contants.CONTEST_NUM);
        List<Contest> listsInvalid = contestService.findContestByInvalid();
        PageInfo<Contest> info = new PageInfo<>(listsInvalid);
        model.addObject("listsValid",listsValid);
        model.addObject("info",info);
        return model;
    }

    /**
     * 比赛点击判断是否可以访问,或者显示模态框
     * @param contest_id
     * @param type
     * @param session
     * @return
     */
    @RequestMapping(value = "/{contest_id}/{type}",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> toContestJudeg(@PathVariable("contest_id") int contest_id, @PathVariable("type") int type, HttpSession session) throws IOException {
        Map<String,Object> model = new HashMap<>();
        Contest contest = contestService.findContestById(contest_id);
//        判断是否现在是比赛时间,前提比赛有效
        if (contestService.JudgeTime(contest) == Contants.CONTEST_UNSTART){
                model.put("time",1);
                return model;
        }
//        判断是否有密码
        if (StringUtils.isNotEmpty(contest.getPassword())){
            model.put("public",1);
            session.setAttribute("contest_id",contest_id);//得到授权
            return model;
        }
        model.put("private",1);
        return model;
    }

    /**
     * 前往不需要密码题目的类
     * @param contest_id
     * @param session
     * @return
     */
    @RequestMapping(value = "/{contest_id}/{type}",method = RequestMethod.GET)
    public ModelAndView toContestPub(@PathVariable("contest_id") int contest_id,@PathVariable("type") int type,HttpSession session){
        ModelAndView model = new ModelAndView();
        //时间检验
        Contest contest = contestService.findContestById(contest_id);
       if (contestService.JudgeTime(contest) == Contants.CONTEST_UNSTART){
           model.setViewName("redirect:/contest");
           return model;
       }

//        开始策略查找出一系列信息
        List<ContestProblem> list = contestService.findCPList(contest_id);
        //更改做过的题目状态
        contestService.packContest(list,contest_id,((User) session.getAttribute("userLogin")).getId());

        model.addObject("CP",list);
        model.addObject("contest",contest);
        model.setViewName("contestdetail");
        return model;
    }

    /**
     * 提交密码判断
     * @param contest_id
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/piv",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> toContestPiv(int contest_id,String password,HttpSession session){
        Map<String,Object> model = new HashMap<>();
        //时间检验
        Contest contest = contestService.findContestById(contest_id);
        if (contestService.JudgeTime(contest) == Contants.CONTEST_UNSTART){
            model.put("time",1);
            return model;
        }
        if (password.trim().equals(contest.getPassword())){
            model.put("result",1);
            session.setAttribute("contest_id",contest_id);//得到授权
        }else {
            model.put("result",0);
        }
        return model;
    }

    /**
     * 查看具体题目
     * @param contest_id
     * @param problem_id
     * @return
     */
    @RequestMapping(value = "/{contest_id}/pro/{problem_id}",method = RequestMethod.GET)
    public ModelAndView toContestProDetail(@PathVariable("contest_id") int contest_id,@PathVariable("problem_id") int problem_id
                                        ){
        ModelAndView model = new ModelAndView();
        if (contest_id <= 0 || problem_id <= 0){
            model.addObject("error","题目不存在");
            model.setViewName("error");
            return model;
        }
        //时间检验
        Contest contest = contestService.findContestById(contest_id);
        if (contestService.JudgeTime(contest) == 1){
            model.setViewName("redirect:/contest");
            return model;
        }
        //开始查找题目
        Map<String,Integer> maps = new HashMap<>();
        maps.put("contest_id",contest_id);
        maps.put("problem_id",problem_id);
        Problem problem = contestService.findProblemById(maps);
        model.addObject("problem",problem);
        model.addObject("consubmit",contest_id);
        model.setViewName("problem");
        return model;
    }
}
