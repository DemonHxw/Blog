package com.gmz.project.intercept;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice//对所有的controller进行统一拦截进行处理
public class ExceptionIntercept {

    //定义日志
    private Logger logger = LoggerFactory.getLogger(ExceptionIntercept.class);

    @ExceptionHandler(Exception.class)//标注对异常进行统一处理
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception{
        logger.error("Request URL : {}, Exception : {}", request.getRequestURL(), e);
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }
        //也就是将所有的除了404和500的异常，划分成一类异常去进行处理
        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURL());
        mv.addObject("exception", e);
        mv.setViewName("error/error");
        return mv;
    }
}
