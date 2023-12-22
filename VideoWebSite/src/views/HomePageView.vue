<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { VideoPlay } from '@element-plus/icons-vue'
import { useUserStore } from '@/stores/user'
import { storeToRefs } from 'pinia'
import TopMenuBar from '@/components/TopMenuBar.vue'
import LoginAndRegisterDialog from '@/components/LoginAndRegisterDialog.vue'
import icon from '@/components/icons/icon.png'
import type { Video } from '@/config/Video'
import axios from "@/config/axios"

const router = useRouter();
const userStore = useUserStore();
const { } = storeToRefs(userStore);

const videoStringList = ref<string[]>();
const videoList = ref<Video[]>();

//子组件参数
//是否加载中
const loading = ref<boolean>(true);
//对话框组件
const loginDialog = ref<InstanceType<typeof LoginAndRegisterDialog> | null>(null);
//默认激活标签
const activeIndex = ref<string>("");

//子组件事件回调函数

//点击登录按钮
const loginClick = () => {
    // userStore.isLogin = true;
    if (!userStore.isLogin) {
        // dialogVisible.value=true;
        if (loginDialog.value) {
            loginDialog.value.visible = true;
        }
    }
}
//点击进入视频
const videoClickHandler = (video: Video) => {
    console.log(video.title);
    router.push("/player/" + video.vid);
}

onMounted(() => {
    //获取视频列表
    videoStringList.value = ["a1"];
    axios.get("/api/video/"+"v1").then((res)=>{
        videoList.value = [res.data, res.data, res.data, res.data, res.data, res.data, res.data, res.data, res.data, res.data];
        loading.value=false;
    })
    

})

</script>

<template>
    <el-container>
        <login-and-register-dialog ref="loginDialog"></login-and-register-dialog>
        <el-header>
            <!-- 导航栏 -->
            <top-menu-bar :activeIndex="activeIndex" :webSiteIcon="icon" @login-click="loginClick"></top-menu-bar>

        </el-header>
        <el-main>
            <div class="video-columns">
                <p>分栏1</p>
                <el-divider />
                <el-row>
                    <el-col v-for="(videoI, index) in 10" :key="index" :span="4" :offset="index % 5 != 0 ? 1 : 0">
                        <el-skeleton :loading="loading" animated>
                            <template #template>
                                <div class="cover">
                                    <el-skeleton-item variant="text" class="vipLabel"
                                        style="width: 30px;"></el-skeleton-item>
                                    <el-skeleton-item variant="image" class="image" />
                                    <el-skeleton-item variant="text" class="time" style="width: 80px;"></el-skeleton-item>
                                </div>
                                <div class="discibe">
                                    <el-skeleton-item variant="text" class="title" style="width: 50%;" /><br>
                                    <el-skeleton-item variant="text" class="sub-title" style="width: 60%;" />
                                </div>
                            </template>
                            <template #default>
                                <el-card :body-style="{ padding: '0px' }">
                                    <div class="cover">
                                        <span v-if="videoList![index].vipOnly" class="vipLabel">VIP</span>
                                        <img :src="videoList![index].cover" class="image" @click="videoClickHandler(videoList![index])" />
                                        <span class="time">{{ videoList![index].time }}</span>
                                        <div class="covermask">
                                            <VideoPlay class="play-icon" />
                                        </div>
                                    </div>

                                    <div class="discribe">
                                        <span class="title" @click="videoClickHandler(videoList![index])">{{ videoList![index].title }}</span>
                                        <br>
                                        <span class="sub-title" @click="videoClickHandler(videoList![index])">{{ videoList![index].subTitle
                                        }}</span>
                                    </div>
                                </el-card>
                            </template>
                        </el-skeleton>
                    </el-col>
                </el-row>
            </div>
        </el-main>
    </el-container>
</template>

<style scoped>
.video-columns {
    margin: 0 30px 0 30px;
}

.video-columns .el-card {
    margin-bottom: 20px;
}

.video-columns .cover {
    height: 150px;
    position: relative;
}

.video-columns .cover .covermask {
    position: absolute;
    opacity: 0;
    pointer-events: none;
    background: rgba(101, 101, 101, 0.6);
    color: #ffffff;
    top: 0;
    right: 0;
    width: 100%;
    height: 100%;
    pointer-events: none;
}

.video-columns .cover:hover .covermask {
    opacity: 1;
    display: flex;
    justify-content: center;
    align-items: center;
}

.video-columns .cover .image {
    width: 100%;
    height: 150px;
}

.video-columns .cover .vipLabel {
    position: absolute;
    left: 0;
    top: 0;
    color: white;
    font-style: oblique;
    padding: 2px 5px 2px 2px;
    background-color: rgba(255, 0, 0, 1)
}

.video-columns .cover .time {
    position: absolute;
    left: 0;
    bottom: 0;
    color: white;
    background-color: rgba(0, 0, 0, 0.2);
}

.video-columns .covermask .play-icon {
    width: 50px;
    height: 50px;

}

.video-columns .discribe {
    padding: 14px 14px 14px 24px
}

.video-columns .discribe .title {
    font-size: medium;
    font-weight: bold;
}

.video-columns .discribe .title:hover {
    color: dodgerblue;
}

.video-columns .discribe .sub-title {
    line-height: 30px;
    padding-left: 20px;
    color: gray;
}

.video-columns .discribe .sub-title:hover {
    color: dodgerblue;
}
</style>

