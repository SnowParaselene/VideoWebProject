<script lang="ts" setup>
import { useUserStore } from '@/stores/user';
import icon from '@/components/icons/icon.png';
import { storeToRefs } from 'pinia';
import { ref, onBeforeMount, computed } from 'vue';
import router from '@/router';
import LoginAndRegisterDialog from '@/components/LoginAndRegisterDialog.vue';

//参数

//导航栏参数
//默认激活标签
const activeIndex = ref<string>("persenal");
const userStore = useUserStore();
const {expirationTime,remainingSum} = storeToRefs(userStore);

const accountType = computed(() => {
    return userStore.vip ? "VIP用户" : "普通用户";
});
const expirationTimeLabel = computed(() => {
    if (userStore.expirationTime) {
        return "到期时间：" + userStore.expirationTime;
    }
    return "";
});
const remainingSumLabel = remainingSum;

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
//点击续费/升级按钮
const buyVipClickHanlder = ()=>{
    userStore.vip = true;
    userStore.remainingSum -= 100;
    userStore.expirationTime = "2023-12-08 20:00:00"
}

//点击充值按钮
const buyRemainingSumClickHandler = ()=>{
    userStore.remainingSum += 100.5;
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
            <el-tabs tab-position="left" class="tabs">
                <el-tab-pane label="我的账户">
                    <div>
                        您当前的会员类型为：
                        <div class="vipInfo">
                            <span class="accountType">{{ accountType }}</span>
                            <p class="expirationTime">{{ expirationTimeLabel }}</p>
                        </div>
                        <el-button type="primary" @click="buyVipClickHanlder">{{ userStore.vip ? "续费" : "升级" }}</el-button>
                    </div>
                    <div>
                        余额：
                        <div  class="remainingSumInfo">
                            <span class="remainingSum">￥{{ remainingSumLabel }}</span>
                        </div>
                        <el-button type="primary" @click="buyRemainingSumClickHandler">充值</el-button>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="播放记录">Config</el-tab-pane>
                <el-tab-pane label="交易查询">Role</el-tab-pane>
            </el-tabs>
        </el-main>

    </el-container>
</template>

<style lang="scss" scoped>
.el-main{
    background-color: rgb(126, 192, 230);
    width: 100%;
    width: 100%;
}

.el-tabs{
    margin: 0 340px;
    padding: 25px;
    background-color: white;
    border-radius: 10px;
    height: 100%;
    box-sizing: border-box;
    --el-tabs-header-height:60px;
}

.el-tabs .el-tab-pane{
    text-align: center;
    padding: 30px 80px 0 0;

}

.remainingSumInfo,
.vipInfo {
    display: inline-block;
    vertical-align: top;
    
}

.vipInfo .accountType {
    color: red;
    font-size: x-large;
}

.vipInfo .expirationTime {
    color: gray;
    font-size: small;
}

</style>