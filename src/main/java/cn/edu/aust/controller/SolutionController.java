package cn.edu.aust.controller;

import cn.edu.aust.entity.Solution;
import cn.edu.aust.entity.SolutionSource;
import cn.edu.aust.entity.User;
import cn.edu.aust.service.SolutionService;
import cn.edu.aust.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
 * 控制具体题目提交的控制器
 */
@Controller
@RequestMapping(value = "/problem")
public class SolutionController {
    @Resource(name = "solutionService")
    private SolutionService solutionService;
    @Resource(name = "taskExecutor")
    private TaskExecutor taskExecutor;

    @RequestMapping(value = "/sub",method = RequestMethod.GET)
    public String toUserComit(){
        return "submit";
    }
    /**
     * 请求用户所解决的题目
     * @param pageUtil
     * @return
     */
    @RequestMapping(value = "/sub",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> getAllUserComit(@RequestBody PageUtil pageUtil, HttpSession session){
        Map<String,Object> maps = new HashMap<>();

        User user = (User) session.getAttribute("userLogin");
        pageUtil.setStage(user.getId());//保证得到的是当前用户的提交

        PageHelper.startPage(pageUtil.getOffset()/pageUtil.getLimit()+1,pageUtil.getLimit());

        List<Solution> lists = solutionService.findUserCmit(pageUtil);
        PageInfo<Solution> info = new PageInfo<Solution>(lists);
        maps.put("total",info.getTotal());
        maps.put("rows",lists);

        return maps;
    }

    @RequestMapping(value = "/judge",method = RequestMethod.POST)
    public ModelAndView Judge(int problem_id,int language,int contest_id,String source,HttpSession session){
        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:/problem/sub");
        User user = (User) session.getAttribute("userLogin");

        Solution solution = new Solution();
                solution.setProblem_id(problem_id);
                solution.setUsername(user.getUsername());
                solution.setUser_id(user.getId());
                solution.setLanguage(language);
                solution.setContest_id(contest_id);

        SolutionSource solutionSource = new SolutionSource();
        solutionSource.setSource(source);
        //放入数据库
        int solution_id = solutionService.InsertPrepare(solution,solutionSource);
        if (solution_id <= 0){
            model.addObject("error","未知提交错误!");
            model.setViewName("error");
            return model;
        }
        //启动判题客户端
        taskExecutor.execute(()->{
            String[] cmd = {"D:\\\\OJ\\Client.exe",Integer.toString(solution_id),Integer.toString(language),"D:\\\\OJ\\conf\\config.ini"};
            try {
                Runtime.getRuntime().exec(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return model;
    }
}
