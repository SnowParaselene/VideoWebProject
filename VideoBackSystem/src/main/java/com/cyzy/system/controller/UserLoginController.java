package com.cyzy.system.controller;

import com.cyzy.system.entity.bo.AdminBo;
import com.cyzy.system.entity.bo.UserBo;
import com.cyzy.system.entity.vo.AdminVo;
import com.cyzy.system.entity.vo.UserVo;
import com.cyzy.system.service.IUserService;
import com.cyzy.system.service.IVipService;
import com.cyzy.system.utils.MyTools;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录控制器
 */
@RestController
@RequestMapping("/user")
public class UserLoginController {
    @Resource
    MyTools myTools;

    @Resource
    IUserService userService;

    @Resource
    IVipService vipService;

    @GetMapping
    public UserVo login(@RequestParam(name = "account") String account,
                        @RequestParam(name = "password") String password,
                        @RequestParam(name = "captcha") String captcha,
                        HttpServletRequest request,
                        HttpServletResponse response){
        //校验验证码
        boolean authCaptcha = myTools.authCaptcha(captcha, request);
        if (!authCaptcha) return loginFailed();
        //查找用户
        UserBo userBo = userService.login(account,password);
        if (userBo==null)return loginFailed();
        //保存token
        myTools.saveToken(request);
        //查找vip信息
        userBo = vipService.getUserVipInfo(userBo);
        return loginSuccess(userBo);
    }

    public UserVo loginFailed() {
        UserVo userVo = new UserVo();
        userVo.setStatus(400);
        userVo.setMessage("登录失败");
        return userVo;
    }

    public UserVo loginSuccess(UserBo user) {
        UserVo userVo = user.getUserVo();
        userVo.setStatus(200);
        userVo.setMessage("登录成功");
        return userVo;
    }
}
