<script lang="ts" setup>
import TopMenuBar from '@/components/TopMenuBar.vue';
import { ref, onMounted, watch } from 'vue'
import { useUserStore } from '@/stores/user';
import { storeToRefs } from 'pinia';
import icon from '@/components/icons/icon.png'
import type { Video } from '@/config/Video';
import LoginAndRegisterDialog from '@/components/LoginAndRegisterDialog.vue';

const userStore = useUserStore();
const { } = storeToRefs(userStore);
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
    video: "/刀剑神域BD特典1动漫_手机乐视视频 超清.mp4",
}

const describeTextArea = ref<HTMLTextAreaElement>();
const loginDialog = ref<InstanceType<typeof LoginAndRegisterDialog>| null>(null);

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
//点击登录按钮
const loginClick = () => {
    // userStore.isLogin = true;
    if(!userStore.isLogin){
        // dialogVisible.value=true;
        if(loginDialog.value){
            loginDialog.value.visible = true;
        }
    }
}

</script>

<template>
    <el-container class="body">
        <login-and-register-dialog ref="loginDialog"></login-and-register-dialog>
        <el-header>
            <!-- 导航栏 -->
            <top-menu-bar :activeIndex="activeIndex" :webSiteIcon="icon" @login-click="loginClick"></top-menu-bar>

        </el-header>
        <el-container class="contant-body">
            <el-main>
                <div class="video-info">
                    <h3>{{ video.title }}</h3>
                    <p>发布时间：{{ video.updateTime }} &nbsp;&nbsp; 播放量：{{ video.playTimes }}</p>
                </div>
                <div class="video-player">
                    <video controls>
                        <source :src="video.video">
                    </video>
                </div>
                <textarea class="video-describe" readonly wrap="hard" v-model="video.describe"
                    ref="describeTextArea"></textarea>
            </el-main>
            <el-aside>
                <div class="video-suggestion">
                    视频推荐
                </div>
            </el-aside>
        </el-container>
    </el-container>
</template>

<style lang="scss" scoped>
.body{
    display: inline-block;
    height: 100vh;
}
.contant-body{
    padding: 0;
    margin: 0;
    display: flex;
    flex-direction: row;
    box-sizing: border-box;
}

.contant-body .el-aside{
    margin: 0;
    padding: 0;
    
}
.el-main {
    // width: 1200px;
    width: 100%;
    height: 100%;
    // box-sizing: border-box;
    overflow: hidden;
    
}

.video-player {
    width: 100%;
    aspect-ratio: 16/9;
    // height: 600px;
    margin-bottom: 40px;
    box-sizing: border-box;
    // display: flex;
    justify-content: center;
    align-items: center;
}

.video-player video {
    height: 100%;
    width: 100%;
    // border: 1px black solid;
    border-radius: 5px;
    box-sizing: border-box;
    background-color: black;
}

.video-describe {
    width: 100%;
    height: 100%;
    resize: none;
    overflow: hidden;
    border-radius: 5px;
    box-sizing: border-box;
    padding: 10px;
}

.el-aside{
    width: 20%;
    // box-sizing: border-box;
    
}

.video-suggestion {
    width: 100%;
    padding: 10px;
    box-sizing: border-box;
    
}
</style>
