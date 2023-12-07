<script lang="ts" setup>
import { useUserStore } from '@/stores/user';
import { ref } from 'vue';

import testHead from '@/assets/headImage.jpg'
import router from '@/router';

const props = defineProps({
    redirect: String,
})

// 获取标签对象
const switchPanel = ref<any>(null);
const registerLoginPanel = ref<any>(null);
const loginSwitchPanel = ref<any>(null);
const registerSwitchPanel = ref<any>(null);
const registerPanel = ref<any>(null);
const loginPanel = ref<any>(null);

//pinia存储
const userStore = useUserStore();

//切换状态
const switchHidden = ref<boolean>(true);

//输入信息
//登录界面，账号、密码、验证码
const loginAccount = ref<string | number | null | undefined>();
const loginPassword = ref<string | number | null | undefined>();
const loginCapture = ref<string | number | null | undefined>();

//注册界面，账号、密码、验证码
const registerAccount = ref<string | number | null | undefined>();
const registerPassword = ref<string | number | null | undefined>();
const registerCapture = ref<string | number | null | undefined>();

/**
 * 切换样式
 * 
 * 通过 toggle 方法来 增加 / 移除 定义好的用来 移动 / 隐藏 元素的样式
 * 
 */
const switchStyle = () => {
    switchHidden.value = switchHidden.value ? false : true;
}

//点击登录按钮
const loginClickHandler = () => {
    // userStore.setUser();
    //测试数据
    userStore.headImage = testHead;
    userStore.userName = "用户2";
    userStore.isLogin = true;

    if (props.redirect!=undefined && props.redirect != "") {
        router.push(props.redirect);
    }
    router.push("/");
}

</script>

<template>
    <div class="body">
        <div class="container">
            <!-- 点击会切换的那个面板 -->
            <div ref="switchPanel" class="switch-panel" :class="{ 'switch-panel-slide': !switchHidden }">
                <!-- 登录 -->
                <div ref="loginSwitchPanel" class="switch-box" :class="{ 'switch-hidden': switchHidden }">
                    <div class="title">感谢您的注册使用</div>
                    <p>已有账号？</p>
                    <button @click="switchStyle">登 录</button>
                </div>

                <!-- 注册 -->
                <div ref="registerSwitchPanel" class="switch-box" :class="{ 'switch-hidden': !switchHidden }">
                    <div class="title">你好！</div>
                    <p>新用户？</p>
                    <button @click="switchStyle">注册账号</button>
                </div>
            </div>

            <section ref="registerLoginPanel" class="register-login-panel"
                :class="{ 'register-login-panel-slide': !switchHidden }">
                <!-- 注册界面 -->
                <section ref="registerPanel" class="register" :class="{ 'switch-hidden-2': switchHidden }">
                    <div class="content">
                        <h2>创建属于你的账号</h2>
                        <input type="text" placeholder="请输入您的账号">
                        <input type="text" placeholder="请输入您的密码">
                        <input type="text" placeholder="请输入验证码">
                        <el-image class="catcha" alt="验证码"></el-image>
                        <button>注册</button>
                    </div>
                </section>

                <!-- 登录界面 -->
                <section ref="loginPanel" class="login" :class="{ 'switch-hidden-2': !switchHidden }">
                    <div class="content">
                        <h2>登 录</h2>
                        <input type="text" placeholder="请输入您的账号">
                        <input type="text" placeholder="请输入您的密码">
                        <input type="text" placeholder="请输入验证码">
                        <el-image class="catcha" alt="验证码"></el-image>
                        <span>忘记密码？</span>
                        <button @click="loginClickHandler">登 录</button>
                    </div>
                </section>
            </section>
        </div>
    </div>
</template>

<style scoped>
/* 样式初始化 */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

.body {
    height: 100vh;
    background: #ecf0f3;
    /*
        flex布局
        内容水平、垂直居中
    */
    display: flex;
    justify-content: center;
    align-items: center;
}

.container {
    width: 800px;
    height: 500px;
    /* 阴影效果 */
    box-shadow: 10px 10px 10px #d1d9e6,
        -10px -10px 10px #f9f9f9;
    border-radius: 12px;
    overflow: hidden;
    /* 因为它的子元素三个面板都要设置为绝对定位，所以这里要设置为相对定位 */
    position: relative;
}

/* 切换按钮所在的面板 */
.switch-panel {
    width: 300px;
    height: 500px;
    background: #ecf0f3;
    /* 阴影效果 */
    box-shadow: 10px 10px 10px #d1d9e6,
        -10px -10px 10px #f9f9f9;
    position: absolute;
    top: 0;
    left: 0;
    z-index: 20;
    display: flex;
    justify-content: center;
    align-items: center;
    transition: all .5s ease-in-out;
}

/* 切换面板滑动后的位置 */
.switch-panel-slide {
    left: 500px;
}

/* 注册面板 */
.register-login-panel {
    width: 500px;
    height: 500px;
    position: absolute;
    top: 0;
    right: 0;
    transition: all .5s ease-in-out;
}

.register-login-panel-slide {
    right: 300px;
}

.register,
.login {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    transition: all .5s ease-out;
}

.switch-box {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 30px;
    transition: all 0.5s ease-in-out;
}

.switch-box .title {
    font-size: 26px;
    font-weight: 700;
    line-height: 3;
    color: #000;
}

.switch-box p {
    font-size: 12px;
    letter-spacing: 0.25px;
    line-height: 1.6;
    text-align: center;
    color: #a0a5a8
}

/* 按钮统一样式 */
.switch-box button,
.login .content button,
.register .content button {
    /* 阴影效果 */
    box-shadow: 10px 10px 10px #d1d9e6,
        -10px -10px 10px #f9f9f9;
    width: 180px;
    height: 40px;
    border-radius: 25px;
    margin-top: 50px;
    font-weight: 700;
    font-size: 14px;
    color: #fff;
    border: none;
    outline: none;
    background: #4b70e2;
    user-select: none;
    /* 按钮点击后的过渡效果 */
    transition: all .3s ease-in-out;
    cursor: pointer;
}

/* 按钮点击时的效果 */
.switch-box button:active {
    transform: scale(0.8);
    background: #abbdf4
}

.switch-hidden {
    position: absolute;
    opacity: 0;
}

.switch-hidden-2 {
    position: absolute;
    opacity: 0;
    top: 0;
    z-index: -10;
}

/********         内容填充          ********/
.login .content,
.register .content {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.login .content h2,
.register .content h2 {
    font-size: 26px;
    font-weight: 700;
    line-height: 2.6;
    color: #000;
}

.login .content span,
.register .content span {
    font-size: 12px;
    color: #a0a5a8;
    margin-top: 12px;
    margin-bottom: 8px;
}

.login .content input,
.register .content input {
    width: 300px;
    height: 40px;
    margin: 8px 0;
    padding-left: 25px;
    font-size: 13px;
    letter-spacing: 0.15px;
    border: none;
    outline: none;
    background-color: #ecf0f3;
    transition: all .25s ease-in-out;
    border-radius: 8px;
    box-shadow: inset 2px 2px 4px #d1d9e6,
        inset -2px -2px 4px #f9f9f9;
}

/* 验证码 */
.el-image {
    margin: 8px 0;
    width: 120px;
    height: 50px;

}

.login .content button,
.register .content button {
    margin-top: 24px;
}
</style>