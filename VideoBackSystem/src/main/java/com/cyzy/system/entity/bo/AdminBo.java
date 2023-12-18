package com.cyzy.system.entity.bo;

import com.cyzy.system.entity.po.AdminPo;
import com.cyzy.system.entity.vo.AdminVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
public class AdminBo{
    private AdminPo admin;
    private Map menuMap;

    public AdminBo(AdminPo admin) {
        this.admin = admin;

    }

    public AdminBo(Map menuMap) {
        this.menuMap = menuMap;
    }

    //转换为vo对象
    public AdminVo getAdminVO() {
        return new AdminVo(admin.getAdminId(),admin.getAdminName(),admin.getAdminAccount(),admin.getAdminHeadImg(),getMenuMap());
    }
}
