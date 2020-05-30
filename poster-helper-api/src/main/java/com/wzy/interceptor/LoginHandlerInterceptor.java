package com.wzy.interceptor;

import com.wzy.holder.RequestHolder;
import com.wzy.pojo.Users;
import com.wzy.utils.ErrorEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 */
@Configuration
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            // 用户未登录，直接抛出异常
            ErrorEnum.NOT_LOGIN.throwException();
        }
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute(token);
        if (user == null) {
            // 存在token，但是用户信息为空，抛出异常
            ErrorEnum.LOGIN_ILLEGAL.throwException();
        }
        // 存token到ThreadLocal中
        RequestHolder.add(token);
        // 存request请求到ThreadLocal中
        RequestHolder.add(request);
        // 存用户信息到ThreadLocal中
        RequestHolder.add(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        removeThreadLocalInfo();
    }

    /**
     * 移除ThreadLocal的用户信息和request信息
     */
    private void removeThreadLocalInfo() {
        RequestHolder.remove();
    }
}
