package com.cyzy.system.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IToolService {
    Object[] generateCaptchaImage(int width,int height);

    void sentVideoFile(HttpServletRequest request, HttpServletResponse response,String path);
}
