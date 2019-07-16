package Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DemoInterceptor implements HandlerInterceptor {
    //进入控制器之前执行
    //return false 阻止进入控制器
    //handler:拦截的控制器名称
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(handler);
        return true;
    }

    //控制器完成后 进入jsp之前 执行
    //return false 阻止进入控制器
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println(modelAndView.getViewName());
        modelAndView.getModel().put("model","gangangan");
        System.out.println(modelAndView.getModelMap().get("model"));
    }

    //jsp执行后
    //ex:异常类型
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
