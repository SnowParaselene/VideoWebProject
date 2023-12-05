import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import type { User } from '@/config/User'

export const useUserStore = defineStore('user', () => {
    let isLogin = ref<boolean>(false);

    let uid = ref<string>("");
    let account = ref<string>("");
    let userName = ref<string>("");
    let token = ref<string>("");
    let headImage = ref<string>("");
    let vip = ref<boolean>(false);
    return {isLogin, uid, account, userName, token, headImage, vip }
})
