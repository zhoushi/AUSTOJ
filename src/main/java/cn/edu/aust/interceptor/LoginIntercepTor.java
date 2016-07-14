package cn.edu.aust.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 竞赛相关信息查看拦截器
 */
public class LoginIntercepTor extends HandlerInterceptorAdapter {

    /**
     * 该方法将在Controller处理之前进行调用,拦截器针对的是非法访问的用户
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("userLogin") != null){
                return true;
            }
        //用户没登录直接转到登录页面,并提示错误
        request.setAttribute("othererror","请登录后再操作!");
        request.getRequestDispatcher("/login").forward(request,response);
        return false;
    }

}
