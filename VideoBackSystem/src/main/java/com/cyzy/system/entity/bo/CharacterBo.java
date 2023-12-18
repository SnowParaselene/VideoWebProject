package com.cyzy.system.entity.bo;

import com.cyzy.system.entity.po.AuthorityPo;
import com.cyzy.system.entity.po.CharacterPo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class CharacterBo {
    private CharacterPo characterPo;
    public Map getMenuMap(){
        //转换为map
        ArrayList<AuthorityPo> authorities = characterPo.getAuthorities();
        Map map = new HashMap<>();
        for (AuthorityPo authority :
                authorities) {
            map.put(authority.getAuthorityValue(), authority.getAuthorityLabel());
        }
        return map;
    }
}
