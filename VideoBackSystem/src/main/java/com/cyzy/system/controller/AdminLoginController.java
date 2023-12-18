package com.cyzy.system.controller;

import com.cyzy.system.entity.bo.AdminBo;
import com.cyzy.system.entity.bo.CharacterBo;
import com.cyzy.system.entity.dto.BaseDto;
import com.cyzy.system.entity.po.AdminPo;
import com.cyzy.system.entity.vo.AdminVo;
import com.cyzy.system.service.IAdminService;
import com.cyzy.system.utils.MyTools;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 管理员登录控制器
 */
@RestController
@RequestMapping("/admin")
public class AdminLoginController {

    @Resource
    IAdminService adminService;

    @Resource
    MyTools myTools;

    @GetMapping
    public AdminVo login(@RequestParam(name = "account") String account,
                         @RequestParam(name = "password") String password,
                         @RequestParam(name = "captcha") String captcha,
                         HttpServletRequest request,
                         HttpServletResponse response) {
        //验证码校验
        boolean check = myTools.authCaptcha(captcha, request);
        if (!check) return loginFailed();

        //参数校验
        if ("".equals(account) || "".equals(password)) return loginFailed();

        //登录
        AdminBo adminBo = adminService.login(account, password);
        if (adminBo == null) return loginFailed();
        CharacterBo characterBo = adminService.getMenuMap(adminBo.getAdmin().getCharacterId());
        adminBo.setMenuMap(characterBo.getMenuMap());

        //token保存
        if (!myTools.saveToken(request)) return loginFailed();

        return loginSuccess(adminBo);
    }

    public AdminVo loginFailed() {
        AdminVo adminVo = new AdminVo();
        adminVo.setStatus(400);
        adminVo.setMessage("登录失败");
        return adminVo;
    }

    public AdminVo loginSuccess(AdminBo admin) {
        AdminVo adminVo = admin.getAdminVO();
        adminVo.setStatus(200);
        adminVo.setMessage("登录成功");
        return adminVo;
    }

}
