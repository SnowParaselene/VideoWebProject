<script lang="ts" setup>
import { useUserStore } from '@/stores/user';
import type { TabsPaneContext } from 'element-plus';
import { storeToRefs } from 'pinia';
import { ref } from 'vue';

import testHead from '@/assets/headImage.jpg'

const emit = defineEmits(['dialogClose', 'dialogLogin', 'dialogRegist'])
const userStore = useUserStore();
const {} = storeToRefs(userStore);

//对话框是否可见
const visible = ref<boolean>(false);

//当前面板
const activeName = ref<string | number | undefined>("login");

//登录界面，账号、密码、验证码
const loginAccount = ref<string | number | null | undefined>();
const loginPassword = ref<string | number | null | undefined>();
const loginCapture = ref<string | number | null | undefined>();

//注册界面，账号、密码、验证码
const registerAccount = ref<string | number | null | undefined>();
const registerPassword = ref<string | number | null | undefined>();
const registerCapture = ref<string | number | null | undefined>();

//面板切换
const handleClick = (pane: TabsPaneContext, ev: Event) => {
    console.log(pane.paneName);
    activeName.value = pane.paneName;
}
//登录
const handleLogin = () => {
    // emit('dialogLogin', loginAccount, loginPassword, loginCapture);
    //测试数据
    userStore.headImage = testHead;
    userStore.userName = "用户2";
    userStore.isLogin = true;

    visible.value = false;
}
//注册
const handleRegist = () => {
    // emit('dialogRegist', registerAccount, registerPassword, registerCapture);
}

defineExpose({visible}) ;
</script>

<template>
    <div>
        <el-dialog v-model="visible"  :show-close="false">
            <el-tabs v-model="activeName" @tab-click="handleClick" type="card" :stretch="true" class="tab">
                <el-tab-pane label="登录" name="login" class="loginDialog">
                    <h3 style="margin: 20px; font-size:x-large;">欢迎你，游客！ 请先登录</h3>
                    <div class="loginInput">
                        <div style="margin-bottom: 20px;">
                            <span>账号：</span> 
                            <el-input v-model="loginAccount" placeholder="请输入账号"></el-input>
                        </div>
                        <div style="margin-bottom: 20px;">
                            <span>密码:</span>
                            <el-input v-model="loginPassword" placeholder="请输入密码" type="password"></el-input>
                        </div>
                        <div class="captchaInput">
                            <img class="captchaImage">
                            <el-input v-model="loginCapture" placeholder="请输入验证码"></el-input>
                        </div>
                    </div>
                    <el-button type="primary" style="width: 60%;height:40px;" @click="handleLogin">登 录</el-button>
                </el-tab-pane>
                <el-tab-pane label="注册" name="register" class="registerDialog">注册界面</el-tab-pane>
            </el-tabs>
        </el-dialog>
    </div>
</template>

<style lang="scss" scoped>
:deep(.el-dialog) {
    width: 30%;
    border-radius: 10px;
}

:deep(.el-dialog .el-dialog__header) {
    padding: 0;
}

:deep(.el-dialog .el-dialog__body){
    padding: 10px;
}

.tab .loginDialog {
    height: 300px;
    text-align: center;
}

.tab .loginDialog .loginInput{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.tab .loginDialog .loginInput span{
    display: inline-block;
    width: 50px;
}

.tab .loginDialog .loginInput .el-input {
    display: inline;
}
.tab .loginDialog .loginInput .captchaInput{
    margin-bottom: 30px;
}

.tab .loginDialog .loginInput .captchaInput .el-input :deep(.el-input__inner){
    height: 40px;
    width: 100px;
}
.tab .loginDialog .loginInput .captchaInput .captchaImage{
    // display: block;
    width: 100px;
    height: 40px;
    margin-right: 20px;
    vertical-align: middle;
}

.loginDialog .registerDialog {}
</style>