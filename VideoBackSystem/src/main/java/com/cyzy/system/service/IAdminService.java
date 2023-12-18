package com.cyzy.system.service;

import com.cyzy.system.entity.bo.AdminBo;
import com.cyzy.system.entity.bo.CharacterBo;

/**
 * 用户服务类
 */
public interface IAdminService {
    AdminBo login(String account, String password);

    CharacterBo getMenuMap(Integer characterId);
}
