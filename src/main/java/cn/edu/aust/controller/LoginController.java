package cn.edu.aust.controller;

import cn.edu.aust.dao.UserDao;
import cn.edu.aust.entity.User;
import cn.edu.aust.util.DecriptUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 登录,退出,注册控制器
 */
@Controller
public class LoginController {

    private Logger logger = Logger.getLogger(LoginController.class);

    @Resource(name = "userDao")
    private UserDao userDao;

    /**
     * 用户登录控制
     * @param user
     * @param attr 重定向传参数
     * @param session
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user,String codevalidate, RedirectAttributes attr, HttpSession session, HttpServletResponse response) throws IOException {
        if (user.getUsername() == null || user.getPassword() == null){
            attr.addFlashAttribute("error","请输入用户名或密码");
            return "redirect:/login";
        }
        //去掉空格
        user.setUsername(user.getUsername().trim());
        user.setPassword(user.getPassword().trim());
        //添加到要显示的信息中
        attr.addFlashAttribute("username",user.getUsername().trim());
        //验证码验证
        String code = (String) session.getAttribute("codeValidate");
        if (codevalidate == null || !codevalidate.equalsIgnoreCase(code)){
            attr.addFlashAttribute("error","验证码错误");
            return "redirect:/login";
        }
        //用户名密码错误
        user.setPassword(DecriptUtil.SHA1(user.getPassword().trim()));
        user = userDao.findUserByLogin(user);
        if (user == null){
            attr.addFlashAttribute("error","用户名或密码错误");
            return "redirect:/login";
        }
        //登录成功加入session
        session.setAttribute("userLogin",user);
        //获取用户做过的题目
        List<Integer> userAC = userDao.findUserACPro(user.getId());
        session.setAttribute("userAC",userAC);
        //更新登录时间
        userDao.updateDateById(user.getId());
        //跳转到用户主页
        return "redirect:/user/"+user.getId();
    }


    /**
     * 退出的方法,要清除session
     * @param session
     * @return
     */
    @RequestMapping(value = "/loginout",method = RequestMethod.GET)
    public String loginOut(HttpSession session){
        //清除session
        session.invalidate();
        return "redirect:/login";
    }

    /**
     * 用户注册管理,包含数据验证
     * @param user
     * @param password2
     * @param attr
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String regirster(@Validated User user, BindingResult br, String password2,String codevalidate, RedirectAttributes attr,HttpSession session){
        if (br.hasErrors()){
            br.getFieldErrors().forEach(p->{
                attr.addFlashAttribute(p.getField(),p.getDefaultMessage());
            });
            return "redirect:/register";
        }
        //验证码检查
        String code = (String) session.getAttribute("codeValidate");
        if (codevalidate == null || !codevalidate.equalsIgnoreCase(code)){
            attr.addFlashAttribute("codeerror","验证码错误");
            return "redirect:/register";
        }

        attr.addFlashAttribute("username",user.getUsername());
        attr.addFlashAttribute("email",user.getEmail());
        //用户名检查
        if (!userDao.judgeUserByName(user.getUsername())){
            attr.addFlashAttribute("username","用户名已被占用");
            return "redirect:/register";
        }
        //密码检查
        if (!user.getPassword().equals(password2)){
            attr.addFlashAttribute("password2","两次输入密码不一致");
            return "redirect:/register";
        }
        //到这开始插入用户
        user.setPassword(DecriptUtil.SHA1(user.getPassword().trim()));
        userDao.addUser(user);
        logger.info("用户"+user.getUsername()+"已注册");
        return "redirect:/login";
    }

}
