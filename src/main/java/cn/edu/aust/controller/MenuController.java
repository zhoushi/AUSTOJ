package cn.edu.aust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 各个页面之间get跳转的控制器
 */
@Controller
public class MenuController {

    /**
     * 前往主页
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String toIndex(){
        return "index";
    }

    /**
     * 前往起步页面
     * @return
     */
    @RequestMapping(value = "/start",method = RequestMethod.GET)
    public String toStart(){
        return "start";
    }
    /**
     * 前往训练页面
     */
    @RequestMapping(value = "/practice",method = RequestMethod.GET)
    public String toPractice(){
        return "practice";
    }
    /**
     * 前往进阶页面
     */
    @RequestMapping(value = "/master",method = RequestMethod.GET)
    public String toMaster(){
        return "master";
    }
    /**
     * 前往排名页面
     */
    @RequestMapping(value = "/rank",method = RequestMethod.GET)
    public String toRank(){
        return "rank";
    }
    /**
     * 前往注册页面
     */
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String toRegister(String email, Model model){
        if (StringUtils.hasText(email)){
            model.addAttribute("email",email);
        }
        return "register";
    }

}
