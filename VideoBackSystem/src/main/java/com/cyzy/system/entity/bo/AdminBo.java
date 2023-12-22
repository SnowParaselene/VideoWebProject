package com.cyzy.system.entity.bo;

import com.cyzy.system.entity.po.AdminPo;
import com.cyzy.system.entity.vo.AdminVo;
import lombok.Data;

import java.util.Map;

@Data
public class AdminBo{
    private AdminPo admin;
    private Map<String,Object> menuMap;

    public AdminBo(AdminPo admin) {
        this.admin = admin;

    }

    //转换为vo对象
    public AdminVo getAdminVO() {
        return new AdminVo(admin.getAdminId(),admin.getAdminName(),admin.getAdminAccount(),admin.getAdminHeadImg(),getMenuMap());
    }
}
