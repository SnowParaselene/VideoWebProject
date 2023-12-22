package com.cyzy.system.service;

import java.io.IOException;
import java.io.OutputStream;

public interface IToolService {
    Object[] generateCaptchaImage(int width,int height);

    void getVideoFile(String path, OutputStream fos) throws IOException;
}
