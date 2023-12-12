<script lang="ts" setup>
import router from '@/router';
import { useAdminStore } from '@/stores/admin';
import { ref } from 'vue'
import type { Admin } from '@/config/Admin';


const account = ref<string>("");
const password = ref<string>("");
const captcha = ref<string>("");
const store = useAdminStore();

//点击登录按钮
const loginClickHandler = () => {
    //进行输入校验
    console.log("进行输入校验")
    // console.log(account.value);
    if (!account.value) {
        ElMessage.error('账号不能为空！');
        return;
    };
    if (!password.value) {
        ElMessage.error("密码不能为空！");
        return;
    };
    if (!captcha.value) {
        ElMessage.error("验证码不能为空！");
        return;
    };
    //进行md5加密
    console.log("密码进行md5加密")
    //登录校验
    console.log("进行登录校验")

    const testAdmin = <Admin>{
        name:"管理员1",
        headImage:"/headImg.png",
    };
    store.isLogin = true;
    store.setAdminInfo(testAdmin);
    router.push("/");
}
</script>

<template>
    <div class="container">
        <div class="login-container">
            <h3>登 &nbsp; 录</h3>
            <el-input placeholder="请输入账号" v-model="account"></el-input>
            <el-input placeholder="请输入密码" v-model="password" type="password"></el-input>
            <el-input placeholder="请输入验证码" v-model="captcha"></el-input>
            <el-image></el-image>
            <el-button @click="loginClickHandler">登 录</el-button>
        </div>

    </div>
</template>

<style lang="scss" scoped>
.container {
    width: 100vw;
    height: 100vh;
    margin: 0;
    padding: 0;
    background-image: url(/src/assets/loginPageBackground.png);
    background-repeat: no-repeat;
    background-position: center center;
    background-attachment: fixed;
    background-size: cover;
}

.login-container {
    width: 400px;
    height: 500px;
    display: flex;
    flex-direction: column;
    position: absolute;
    right: 10%;
    top: 30%;
    justify-content: center;
    align-items: center;
    border: 1px black solid;
    border-radius: 5px;
    box-sizing: border-box;
    padding: 30px;
    background-color: rgba(0, 0, 0, 0.425);

}

.login-container * {
    margin-bottom: 10px;
}

.login-container h3 {
    font-size: x-large;
    margin-bottom: 40px;
    color: white;
    border-bottom: 1px white solid;
}

.login-container .el-image {
    width: 100px;
    height: 50px;
}
</style>