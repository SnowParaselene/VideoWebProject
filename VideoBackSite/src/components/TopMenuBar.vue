<script setup lang="ts">
import router from '@/router';
import { useAdminStore } from '@/stores/admin';
import { storeToRefs } from 'pinia';


const props = defineProps({
    webSiteIcon: String,
})
const emit = defineEmits(["loginClick"]);
const adminStore = useAdminStore();
const {isLogin,adminHeadImage,adminName} = storeToRefs(adminStore);

const handlerLogin = ()=>{
    emit('loginClick');
}

//菜单项被点击
const topMenuBarClick = (key: string, keyPath: string[]) => {
    console.log(key, keyPath);
    if (key=="player" || key == "log-out") {
        return;
    }
    router.push("/"+key);
}

//登出被点击
const logoutClick = () => {
    adminStore.isLogin = false;
    router.push("/login");
}


</script>

<template>
    <el-menu mode="horizontal" :ellipsis="false" @select="topMenuBarClick">
        <el-menu-item index="">
            <img style="width: 100px;height: 50px;" :src="webSiteIcon" alt="Video Website logo" />
        </el-menu-item>
        <div class="flex-grow" />
        <el-sub-menu v-if="isLogin" index="personal-center">
            <template #title>
                <el-image :src="adminHeadImage" style="width: 40px; height: 40px;border-radius: 20px;margin-right: 10px;"
                fix="cover" />
                {{ adminName }}
            </template>
            <el-menu-item index="log-out" @click="logoutClick">退出</el-menu-item>
        </el-sub-menu>
        <div v-else class="login">
            <el-button type="primary" round @click="handlerLogin">登录</el-button>
        </div>
    </el-menu>
</template>

<style scoped>
.login{
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
