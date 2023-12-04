<script setup lang="ts">
import { VideoPlay } from '@element-plus/icons-vue'
import icon from '@/components/icons/icon.png'
import { ref } from 'vue'
import testHead from '@/assets/headImage.jpg'
import TopMenuBar from '@/components/TopMenuBar.vue'

//默认激活标签
const activeIndex = ref<string>("1");
const topMenuBarClick = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
}
const headImg = testHead;

</script>

<template>
    <el-container>
        <el-header>
            <!-- 导航栏 -->
            <TopMenuBar :activeIndex="activeIndex" :webSiteIcon="icon" :headImg="headImg"
                @topMenuBarClick="topMenuBarClick"></TopMenuBar>

        </el-header>
        <el-main>
            <div class="videoColumns">
                <p>分栏1</p>
                <el-divider />
                <el-row>
                    <el-col v-for="(video, index) in 10" :key="video" :span="4" :offset="index % 5 != 0 ? 1 : 0">
                        <el-skeleton style="width: 240px" :loading="false" animated>
                            <template #template>
                                <el-skeleton-item variant="image" style="width: 240px; height: 240px" />
                                <div style="padding: 14px">
                                    <el-skeleton-item variant="h3" style="width: 50%" />
                                    <div style="
                                        display: flex;
                                        align-items: center;
                                        justify-items: space-between;
                                        margin-top: 16px;
                                        height: 16px;
                                        ">
                                        <el-skeleton-item variant="text" style="margin-right: 16px" />
                                        <el-skeleton-item variant="text" style="width: 30%" />
                                    </div>
                                </div>
                            </template>
                            <template #default>
                                <el-card :body-style="{ padding: '0px', marginBottom: '1px' }">
                                    <div class="cover">
                                        <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
                                            class="image" />
                                        <span class="time">11:11:11</span>
                                        <div class="covermask">
                                            <VideoPlay class="playIcon"/>
                                        </div>
                                    </div>

                                    <div class="discribe">
                                        <span>Delicious hamburger</span>
                                        <div class="bottom card-header">
                                            <el-button text class="button">Operation button</el-button>
                                        </div>
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
.videoColumns {
    margin: 0 30px 0 30px;
}

.videoColumns .el-card {
    margin-bottom: 20px;
}

.videoColumns .el-card .cover {
    height: 150px;
    position: relative;
}

.videoColumns .el-card .covermask {
    position: absolute;
    opacity: 0;
    pointer-events: none;
    background: rgba(101, 101, 101, 0.6);
    color: #ffffff;
    top: 0;
    right: 0;
    width: 100%;
    height: 100%;
    pointer-events:none;
}

.videoColumns .el-card:hover .covermask{
    opacity: 1;
    display: flex;
    justify-content: center;
    align-items: center;
}

.videoColumns .el-card .cover .image {
    width: 100%;
    height: 150px;
}

.videoColumns .el-card .cover .time {
    position: absolute;
    left: 0;
    bottom: 0;
    color: white;
    background-color: rgba(0, 0, 0, 0.2);
}
.videoColumns .el-card .covermask .playIcon{
    width: 50px;
    height: 50px;
    
}

.videoColumns .el-card .discribe {
    padding: 14px
}
</style>

