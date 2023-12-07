<script lang="ts" setup>
import { useUserStore } from '@/stores/user';
import icon from '@/components/icons/icon.png';
import { storeToRefs } from 'pinia';
import { ref, onBeforeMount } from 'vue';
import router from '@/router';
import LoginAndRegisterDialog from '@/components/LoginAndRegisterDialog.vue';

//参数

//导航栏参数
//默认激活标签
const activeIndex = ref<string>("persenal");
const userStore = useUserStore();

//事件回调函数
// onBeforeMount(()=>{
//     if(!isLogin.value){
//         router.push('/Login');
//     }
// });

const loginDialog = ref<InstanceType<typeof LoginAndRegisterDialog> | null>(null);

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

</script>

<template>
    <el-container>
        <login-and-register-dialog ref="loginDialog"></login-and-register-dialog>
        <el-header>
            <!-- 导航栏 -->
            <top-menu-bar :activeIndex="activeIndex" :webSiteIcon="icon"></top-menu-bar>
        </el-header>
        <el-main>
            这是个人中心
        </el-main>

    </el-container>
</template>

<style lang="scss" scoped></style>