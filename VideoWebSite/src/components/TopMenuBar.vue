<script setup lang="ts">
const props = defineProps({
    activeIndex: String,
    webSiteIcon: String,
    isLogin: Boolean,
    headImg: String,
    userName: String,
})

const emit = defineEmits(["topMenuBarClick","loginClick","logoutClick"]);

const handleSelect = (key: string, keyPath: string[]) => {
    emit('topMenuBarClick');
}

const handlerLogin = ()=>{
    emit('loginClick');
}

const handlerLogout =()=>{
    emit('logoutClick');
}



</script>

<template>
    <el-menu :default-active="activeIndex" mode="horizontal" :ellipsis="false" @select="handleSelect">
        <el-menu-item index="home">
            <img style="width: 100px;height: 50px;" :src="webSiteIcon" alt="Video Website logo" />
        </el-menu-item>
        <el-sub-menu index="videos">
            <template #title>视频库</template>
            <el-menu-item index="movie">电影</el-menu-item>
            <el-menu-item index="teleplay">电视剧</el-menu-item>
            <el-menu-item index="variety">综艺</el-menu-item>
        </el-sub-menu>
        <el-menu-item index="player">播放器</el-menu-item>
        <el-menu-item>个人中心</el-menu-item>
        <div class="flex-grow" />
        <el-sub-menu v-if="isLogin" index="personal-center">
            <template #title>
                <el-image :src="headImg" style="width: 40px; height: 40px;border-radius: 20px;margin-right: 10px;"
                fix="cover" />
                {{ userName }}
            </template>
            <el-menu-item index="log-out" @click="handlerLogout">退出</el-menu-item>
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
