import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import type { Admin } from '@/config/Admin';

export const useAdminStore = defineStore('admin', () => {
    const isLogin = ref<boolean>(false);
    const token = ref<string>('');

    const aid = ref<string>('');
    const adminName = ref<string>('');
    const adminAccount = ref<string>('');
    const adminHeadImage = ref<string>("");

    const menuMap = ref<String[]>([]);

    const setAdminInfo = (adminInfo: Admin) => {
        token.value = adminInfo.token;
        isLogin.value = true;
        aid.value = adminInfo.aid;
        adminName.value = adminInfo.name;
        adminAccount.value = adminInfo.account;
        adminHeadImage.value = adminInfo.headImage;
        menuMap.value = adminInfo.menuMap;

    }

    return { isLogin, token, aid, adminName, adminAccount, adminHeadImage, menuMap ,setAdminInfo };
},
    {
        persist: true
    }
)
