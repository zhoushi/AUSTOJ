package cn.edu.aust.controller;

import cn.edu.aust.dao.ProblemDao;
import cn.edu.aust.entity.Problem;
import cn.edu.aust.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 有关题目的控制器
 */
@Controller
@RequestMapping(value = "/problem")
public class ProblemController {

    @Resource(name = "problemDao")
    private ProblemDao problemDao;

    /**
     * 查询出对应阶段的全部题目
     * @param pageUtil
     * @param stage
     * @return
     */
    @RequestMapping(value = "/findStageProblem/{stage}",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> findStageProblem(@RequestBody PageUtil pageUtil,@PathVariable("stage") int stage){
        Map<String,Object> maps = new HashMap<>();
        pageUtil.setStage(stage);
        PageHelper.startPage(pageUtil.getOffset()/pageUtil.getLimit()+1,pageUtil.getLimit());
        List<Problem> lists = problemDao.findStageProblem(pageUtil);
        PageInfo<Problem> info = new PageInfo<Problem>(lists);
        maps.put("total",info.getTotal());
        maps.put("rows",lists);
        return maps;
    }
    /**
     * 查询出指定目录下全部题目
     * @param pageUtil
     * @param catelog
     * @return
     */
    @RequestMapping(value = "/findCateProblem/{catelog}",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> findCateProblem(@RequestBody PageUtil pageUtil,@PathVariable("catelog") int catelog){
        Map<String,Object> maps = new HashMap<>();
        pageUtil.setStage(catelog);
        PageHelper.startPage(pageUtil.getOffset()/pageUtil.getLimit()+1,pageUtil.getLimit());
        List<Problem> lists = problemDao.findCateProblem(pageUtil);
        PageInfo<Problem> info = new PageInfo<Problem>(lists);
        maps.put("total",info.getTotal());
        maps.put("rows",lists);
        return maps;
    }

    /**
     * 前往具体的目录题目页面
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(value = "/catepro/{id}/{name}",method = RequestMethod.GET)
    public ModelAndView toCatelogProblem(@PathVariable("id") int id,@PathVariable("name") String name){
        ModelAndView model = new ModelAndView();
        model.setViewName("catelogproblem");
        model.addObject("catename",name);
        model.addObject("cateid",id);
        return model;
    }

    /**
     * 前往具体的题目详情页面
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ModelAndView toProblemDeatil(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView();
        model.setViewName("problem");
        if (id>0){
            Problem p = problemDao.findProblemById(id);
            if (p != null){
                model.addObject("problem",p);
            }else {
                model.addObject("error","获取题目失败,题目不存在");
            }
        }else {
            model.addObject("error","获取题目失败,题目不存在");
        }
        return model;
    }
}
