<script lang="ts" setup>
import TopMenuBar from '@/components/TopMenuBar.vue';
import testIcon from '@/assets/icon.png'
import { useMenuMapStore } from '@/stores/menuMap';
import { storeToRefs } from 'pinia';
import router from '@/router';

const testWebSiteIcon = testIcon;

const menuMapStore = useMenuMapStore();
const { menuMap } = storeToRefs(menuMapStore);

//返回首页回调函数
const goBack = ()=>{
    console.log("返回首页");
    router.push('/')
}
console.log(router.currentRoute.value.fullPath);
console.log(router.currentRoute.value.path);

</script>

<template>
    <div>
        <el-container class="body">
            <el-header>
                <top-menu-bar :web-site-icon="testWebSiteIcon"></top-menu-bar>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu :default-active="router.currentRoute.value.fullPath" unique-opened router>
                        <template v-for="menuItem in menuMap">
                            <el-sub-menu v-if="menuItem.options && menuItem.options.length > 0" :index="menuItem.value">
                                <template #title>
                                    <el-icon>
                                        <component v-if="menuItem.icon" :is="menuItem.icon" style="width: 16px;height: 16px;"></component>
                                    </el-icon>
                                    <span>{{ menuItem.label }}</span>
                                </template>
                                <el-menu-item v-for="option in menuItem.options" :index="option.value">
                                    <el-icon>
                                        <component v-if="option.icon" :is="option.icon" style="width: 16px;height: 16px;"></component>
                                    </el-icon>
                                    <span>{{ option.label }}</span>
                                </el-menu-item>
                            </el-sub-menu>
                            <el-menu-item v-else :index="menuItem.value">
                                <el-icon>
                                        <component v-if="menuItem.icon" :is="menuItem.icon" style="width: 16px;height: 16px;"></component>
                                    </el-icon>
                                    <span>{{ menuItem.label }}</span>
                            </el-menu-item>
                        </template>
                    </el-menu>
                </el-aside>
                <el-main>
                    <RouterView @goBack="goBack"/>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<style lang="scss" scoped>
.body {
    width: 100vw;
    height: 100vh;
}

.el-aside .el-menu {
    height: 100%;
}

.el-main {
    height: 100%;
}
</style>