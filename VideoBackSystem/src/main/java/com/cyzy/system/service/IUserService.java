package com.cyzy.system.service;

import com.cyzy.system.entity.bo.UserBo;
import com.cyzy.system.entity.po.UserPo;

/**
 * 用户服务类
 */
public interface IUserService {
    UserBo login(String account, String password);

}
