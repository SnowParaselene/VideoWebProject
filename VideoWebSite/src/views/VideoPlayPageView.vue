<script lang="ts" setup>
import TopMenuBar from '@/components/TopMenuBar.vue';
import { ref, onMounted, watch } from 'vue'
import { useUserStore } from '@/stores/user';
import { storeToRefs } from 'pinia';
import icon from '@/components/icons/icon.png'
import type { Video } from '@/config/Video';

const userStore = useUserStore();
const { isLogin, headImage, userName } = storeToRefs(userStore);
const props = defineProps({
    vid: String,
});
const activeIndex = "player";

const video = <Video>{
    vid: "v1",
    cover: "https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png",
    title: "这里是标题",
    subTitle: "这里是副标题",
    time: "22:22:22",
    vipOnly: false,
    updateTime: "2001-03-21 08:00:00",
    describe: "这里是简介这里是简介这里是简介这里是简介这里是简介这里是简介\n\n\n这里是简介这里是简介这里是简介这里是简介这里是简介这里是简介这里是简介这里是简介这里是简介这里是简介这里是简介这里是简介这里是简介这里是简介",
    playTimes: "1万",
    video: "静态视频资源地址",
}

const describeTextArea = ref<HTMLTextAreaElement>();

onMounted(() => {
    //创建大小变化的监视器
    const resizeObserver = new ResizeObserver((entries) => {
        for (let entry of entries) {
            if (entry.contentBoxSize) {
                //重置describe区域的高度
                adjustDescribeAreaHeight();
            }
        }
    });

    //指定监视器需要监视的对象
    if (describeTextArea.value) {
        resizeObserver.observe(describeTextArea.value);
    }

    return () => {
        resizeObserver.disconnect();
    };
})
//重置describe区域的高度
const adjustDescribeAreaHeight = () => {
    if (describeTextArea.value) {
        describeTextArea.value.style.height = 'auto';
        describeTextArea.value.style.height = describeTextArea.value.scrollHeight + 'px';
    }
}
//顶部菜单栏点击
const topMenuBarClick = (key: String, keyPath: String[]) => {
    console.log(key, keyPath);
}
//点击登录按钮
const loginClick = () => {
    isLogin.value = true;
}
//点击退出按钮
const logoutClick = () => {
    isLogin.value = false;
}


</script>

<template>
    <el-container>
        <el-header>
            <!-- 导航栏 -->
            <top-menu-bar :activeIndex="activeIndex" :webSiteIcon="icon" :isLogin="isLogin" :headImg="headImage"
                :user-name="userName" @topMenuBarClick="topMenuBarClick" @login-click="loginClick"
                @logout-click="logoutClick"></top-menu-bar>

        </el-header>
        <el-container>
            <el-main>
                <div class="videoInfo">
                    <h3>{{ video.title }}</h3>
                    <p>发布时间：{{ video.updateTime }} &nbsp;&nbsp; 播放量：{{ video.playTimes }}</p>
                </div>
                <div class="videoPlayer">
                    <video :src="video.video"></video>
                </div>
                <textarea class="videoDescribe" readonly wrap="hard" v-model="video.describe"
                    ref="describeTextArea"></textarea>
            </el-main>
            <el-aside>
                <div class="videoSuggestion">
                    视频推荐
                </div>
            </el-aside>
        </el-container>
    </el-container>
</template>

<style lang="scss" scoped>
.el-main {
    width: 1200px;
    box-sizing: border-box;
}

.videoPlayer {
    width: 100%;
    height: 600px;
    margin-bottom: 40px;
    box-sizing: border-box;
    display: flex;
    justify-content: center;
    align-items: center;
}

.videoPlayer video {
    height: 100%;
    width: 100%;
    border: 1px black solid;
    border-radius: 5px;
    padding: 10px;
    box-sizing: border-box;
}

.videoDescribe {
    width: 100%;
    height: auto;
    resize: none;
    overflow: hidden;
    border-radius: 5px;
    box-sizing: border-box;
    padding: 10px;
}

.el-aside{
    width: 20%;
}

.videoSuggestion {
    width: 100%;
    padding: 10px;
    box-sizing: border-box;
}
</style>
