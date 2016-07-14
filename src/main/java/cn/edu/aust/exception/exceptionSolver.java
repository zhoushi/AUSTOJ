package cn.edu.aust.exception;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义异常处理类
 */
public class exceptionSolver implements HandlerExceptionResolver {

    private Logger logger = Logger.getLogger(exceptionSolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.warn("==============异常开始=============");
        logger.error("异常:",ex);
        logger.warn("==============异常结束=============");
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
        mv.setViewName("404");
        return mv;
    }
}
