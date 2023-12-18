package com.cyzy.system.utils;

import com.sun.istack.internal.NotNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class MyTools {
    @Resource
    RedisTemplate redisTemplate;

    /**
     * 校验验证码
     * @param captcha
     * @param request
     * @return
     */
    public boolean authCaptcha(@NotNull String captcha, HttpServletRequest request) {
        //判断是否携带token
        String token = getToken(request);
        if (token == null) return false;

        //判断token是否有效
        //String hasToken = (String) redisTemplate.opsForHash().get("token", token);
        //if (hasToken == null) return false;

        //判断captcha是否存在且有效
        String currentCaptcha = (String) redisTemplate.opsForHash().get("captcha", token);
        if (!captcha.equals(currentCaptcha)) return false;

        //验证结束
        return true;
    }

    /**
     * 获取请求的token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request) {
        //判断是否有token
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) return null;
        String token = null;
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
        //if (!hasToken) return false;
        return token;
    }

    /**
     * 保存临时token为有效身份码
     * @param request
     * @return
     */
    public boolean saveToken(HttpServletRequest request) {
        String token = getToken(request);
        if (token == null) return false;

        redisTemplate.opsForSet().add("login",token);
        return true;
    }
}
