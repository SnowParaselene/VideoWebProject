package com.cyzy.system.entity.bo;

import com.cyzy.system.entity.po.BalancePo;
import com.cyzy.system.entity.po.UserPo;
import com.cyzy.system.entity.po.VipPo;
import com.cyzy.system.entity.vo.UserVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBo {
    private UserPo userPo;
    private VipPo vipPo;
    private BalancePo balancePo;

    public UserBo(UserPo userPo) {
        this.userPo = userPo;
    }

    public UserVo getUserVoFull() {
        UserVo userVo = new UserVo(userPo.getUserId(), userPo.getUserName(), userPo.getUserAccount(),
                userPo.getUserHeadImg(), balancePo.getRemainingSum(), false);
        if (vipPo == null) return userVo;
        userVo.setVip(true);
        userVo.setExpirationTime(vipPo.getExpirationTime());
        return userVo;
    }
    public UserVo getUserVo() {
        UserVo userVo = new UserVo(userPo.getUserId(), userPo.getUserName(), userPo.getUserAccount(),
                userPo.getUserHeadImg(), false);
        if (vipPo == null) return userVo;
        userVo.setVip(true);
        userVo.setExpirationTime(vipPo.getExpirationTime());
        return userVo;
    }

}
