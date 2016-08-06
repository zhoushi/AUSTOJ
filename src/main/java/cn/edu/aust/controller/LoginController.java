package cn.edu.aust.controller;

import cn.edu.aust.entity.User;
import cn.edu.aust.exception.MyException;
import cn.edu.aust.service.UserService;
import cn.edu.aust.util.DecriptUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 登录,退出,注册控制器
 */
@Controller
public class LoginController {

    private Logger logger = Logger.getLogger(LoginController.class);

    @Resource(name = "userService")
    private UserService userService;

    /**
     * 前往登录页面
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String toLogin(HttpServletRequest request) throws Exception {
        //获取登录前的链接
        String referer = request.getHeader("referer");
        if (StringUtils.isNotEmpty(referer)){
            request.getSession().setAttribute("referer",referer);
        }
        return "login";
    }

    /**
     * 用户登录控制
     * @param user
     * @param attr    重定向传参数
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, BindingResult br, String codevalidate, String rmb_me,
                        RedirectAttributes attr, HttpSession session) throws MyException {
        //校验主要在客户端,所以服务端只需要简单判断
        if (br.hasErrors()) {
            throw new MyException("请误尝试非法登录");
        }
        //验证码验证
        String code = (String) session.getAttribute("codeValidate");
        if (!StringUtils.equalsIgnoreCase(code, codevalidate)) {
            attr.addFlashAttribute("error", "验证码错误");
            return "redirect:/login";
        }
        //验证账户
        user.setUsername(user.getUsername().trim());
        user.setPassword(user.getPassword().trim());
        user.setPassword(DecriptUtil.SHA1(user.getPassword().trim()));
        user = userService.findUserByLogin(user);
        if (user == null) {
            attr.addFlashAttribute("error", "用户名或密码错误");
            return "redirect:/login";
        }
        //登录成功加入session
        session.setAttribute("userLogin", user);
        logger.info(user.getUsername()+"已登录");
        //获取用户做过的题目,并更新登录时间
        try {
            List<Integer> userAC = userService.findUserACPro(user.getId());
            session.setAttribute("userAC", userAC);
            userService.updateDateById(user.getId());
        } catch (Exception e) {
            throw new MyException("查询用户用户相关题目信息出错", e);
        }
        //跳转到之前的页面
        String redirect = (String) session.getAttribute("referer");
        return StringUtils.isNotEmpty(redirect)?("redirect:" + redirect):("redirect:/user/" + user.getId());
    }


    /**
     * 退出的方法,要清除session
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/loginout", method = RequestMethod.GET)
    public String loginOut(HttpSession session) {
        //清除session
        session.invalidate();
        return "redirect:/login";
    }

    /**
     * 用户注册管理,包含数据验证
     *
     * @param user
     * @param password2
     * @param attr
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String regirster(@Validated User user, BindingResult br, String password2, String codevalidate, RedirectAttributes attr, HttpSession session) throws Exception {
        if (br.hasErrors()) {
            throw new MyException("请误尝试非法注册");
        }
        //验证码检查
        String code = (String) session.getAttribute("codeValidate");
        if (!StringUtils.equalsIgnoreCase(code, codevalidate)) {
            attr.addFlashAttribute("error", "验证码错误");
            return "redirect:/register";
        }

        attr.addFlashAttribute("username", user.getUsername());
        attr.addFlashAttribute("email", user.getEmail());
        //用户名检查
        if (!userService.findUserByName(user.getUsername())) {
            attr.addFlashAttribute("error", "用户名已被占用");
            return "redirect:/register";
        }
        //密码检查
        if (!user.getPassword().equals(password2)) {
            attr.addFlashAttribute("error", "两次输入密码不一致");
            return "redirect:/register";
        }
        //插入用户
        user.setPassword(DecriptUtil.SHA1(user.getPassword().trim()));
        userService.addUser(user);
        logger.info("用户" + user.getUsername() + "已注册");
        return "redirect:/login";
    }

    /**
     * 检查用户名是否存在
     * @param username
     * @return
     */
    @RequestMapping(value = "/check/{username}",method = RequestMethod.POST)
    public @ResponseBody boolean checkUsername(@PathVariable("username") String username) throws Exception {
        if (StringUtils.isEmpty(username)){
            return false;
        }
        return !userService.findUserByName(username);
    }

}
