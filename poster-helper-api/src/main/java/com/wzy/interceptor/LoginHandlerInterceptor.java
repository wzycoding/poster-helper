package com.wzy.interceptor;

import com.wzy.holder.RequestHolder;
import com.wzy.pojo.Users;
import com.wzy.utils.ErrorEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 */
@Configuration
public class LoginHandlerInterceptor implements HandlerInterceptor {
    public static final String TOKEN_PREFIX = "_token";
    public static final String VERIFY_PREFIX = "_code";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            // 用户未登录，直接抛出异常
            ErrorEnum.NOT_LOGIN.throwException();
        }
        for (Cookie cookie : cookies) {
            if (TOKEN_PREFIX.equals(cookie.getName())) {
                token = cookie.getValue();
            }
        }
        if (StringUtils.isEmpty(token)) {
            // 用户未登录，直接抛出异常
            ErrorEnum.NOT_LOGIN.throwException();
        }
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute(TOKEN_PREFIX + token);
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
