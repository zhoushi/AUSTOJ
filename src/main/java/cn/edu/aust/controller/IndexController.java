package cn.edu.aust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Niu Li
 * @date 2016/8/6
 */
@Controller
public class IndexController {
    /**
     * 前往主页
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String toIndex(){
        return "index";
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
