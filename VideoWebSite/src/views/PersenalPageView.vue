<script lang="ts" setup>
import { useUserStore } from '@/stores/user';
import icon from '@/components/icons/icon.png';
import { storeToRefs } from 'pinia';
import { ref, onBeforeMount, computed } from 'vue';
import router from '@/router';
import LoginAndRegisterDialog from '@/components/LoginAndRegisterDialog.vue';
import type { PlayerRecord } from '@/config/PlayerRecord';
import type { OrderRecord } from '@/config/OrderRecord';
import type { TabPaneName } from 'element-plus';

//参数

//导航栏参数
//默认激活标签
const activeIndex = ref<string>("persenal");
const userStore = useUserStore();
const { expirationTime, remainingSum } = storeToRefs(userStore);

//会员信息显示
const accountType = computed(() => {
    return userStore.vip ? "VIP用户" : "普通用户";
});
const expirationTimeLabel = computed(() => {
    if (userStore.expirationTime) {
        return "到期时间：" + userStore.expirationTime;
    }
    return "";
});
//账户余额
const remainingSumLabel = remainingSum;
//播放记录
let playerRecordsTableHead = new Map<string,string>();
let playerRecordsTableHeadTracker = 0;
const playerRecordsTableHeadRef = computed(() => {
    var x = playerRecordsTableHeadTracker;
    return playerRecordsTableHead;
});
const playerRecords = ref<PlayerRecord[]>();
//购买记录
let buyRecordsTableHead = new Map();
let buyRecordsTableHeadTracker = 0;
const buyRecordsTableHeadRef = computed(() => {
    var x = buyRecordsTableHeadTracker;
    return buyRecordsTableHead;
});
const buyRecords = ref<OrderRecord[]>();

//事件回调函数
// onBeforeMount(()=>{
//     if(!isLogin.value){
//         router.push('/Login');
//     }
// });

const loginDialog = ref<InstanceType<typeof LoginAndRegisterDialog> | null>(null);
const tabChangeHandler = (tabPaneName: TabPaneName) => {
    console.log(tabPaneName)
    if (tabPaneName == "playerRecords") {
        getPlayerRecords();
        console.log(1)
    };
    if (tabPaneName == "buyRecords") {
        getBuyRecords();
    };
};
//点击登录按钮
const loginClick = () => {
    // userStore.isLogin = true;
    if (!userStore.isLogin) {
        // dialogVisible.value=true;
        if (loginDialog.value) {
            loginDialog.value.visible = true;
        }
    }
};
//点击续费/升级按钮
const buyVipClickHanlder = () => {
    userStore.vip = true;
    userStore.remainingSum -= 100;
    userStore.expirationTime = "2023-12-08 20:00:00"
}

//点击充值按钮
const buyRemainingSumClickHandler = () => {
    userStore.remainingSum += 100.5;
}

//获取播放记录
const getPlayerRecords = () => {
    //测试数据
    let testHeadMap = new Map();
    testHeadMap.set("videoName", "播放记录");
    testHeadMap.set("viewingDuration", "观看时长");
    testHeadMap.set("playTime", "观看时间");
    playerRecordsTableHead = testHeadMap;
    playerRecordsTableHeadTracker += 1;
    playerRecords.value = [
        {
            "videoName": "视频1",
            "vid": "cv1",
            "viewingDuration": "2h",
            "playTime": "2023-12-11 08:30:00",
        },
    ]
};
const getBuyRecords = () => {
    //测试数据
    let testHeadMap = new Map();
    testHeadMap.set("orderType", "交易类型");
    testHeadMap.set("goodsName", "商品名称");
    testHeadMap.set("createTime", "交易时间");
    testHeadMap.set("orderAmount", "交易金额");
    testHeadMap.set("orderStatus", "订单状态");
    buyRecordsTableHead = testHeadMap;
    buyRecordsTableHeadTracker += 1;
    buyRecords.value = [
        {
            "orderType": "消费",
            "goodsName": "商品1",
            "createTime": "2023-12-11 08:30:00",
            "orderAmount": "200",
            "orderStatus": "已完成",
            
        },
    ]
};


</script>

<template>
    <el-container>
        <login-and-register-dialog ref="loginDialog"></login-and-register-dialog>
        <el-header>
            <!-- 导航栏 -->
            <top-menu-bar :activeIndex="activeIndex" :webSiteIcon="icon"></top-menu-bar>
        </el-header>
        <el-main>
            <el-tabs tab-position="left" class="tabs" @tab-change="tabChangeHandler">
                <el-tab-pane label="我的账户" name="account">
                    <div class="persenal-info">
                        <el-image :src="userStore.headImage"></el-image>
                        <span>{{ userStore.userName }}</span>
                    </div>
                    <el-divider></el-divider>
                    <div style="margin-bottom: 20px;">
                        您当前的会员类型为：
                        <div class="vip-info">
                            <span class="account-type">{{ accountType }}</span>
                            <p class="expiration-time">{{ expirationTimeLabel }}</p>
                        </div>
                        <el-button type="primary" @click="buyVipClickHanlder">{{ userStore.vip ? "续费" : "升级" }}</el-button>
                    </div>
                    <div>
                        余额：
                        <div class="remaining-sum-info">
                            <span class="remaining-sum">￥{{ remainingSumLabel }}</span>
                            <el-button type="primary" @click="buyRemainingSumClickHandler">充值</el-button>
                        </div>
                    </div>
                </el-tab-pane>
                <el-tab-pane lazy label="播放记录" name="playerRecords">
                    <div class="player-records">
                        <span>播放记录</span>
                        <el-table :data="playerRecords">
                            <el-table-column v-for="[key,value] of playerRecordsTableHeadRef" :prop="key"
                                :label="value"></el-table-column>
                        </el-table>
                    </div>

                </el-tab-pane>
                <el-tab-pane label="交易查询" name="buyRecords">
                    <div class="buy-records">
                        <span>购买记录</span>
                        <el-table :data="buyRecords">
                            <el-table-column v-for="[key,value] of buyRecordsTableHeadRef" :prop="key"
                                :label="value"></el-table-column>
                        </el-table>
                    </div>
                </el-tab-pane>
            </el-tabs>
        </el-main>

    </el-container>
</template>

<style lang="scss" scoped>
.el-container{
    width: 100vw;
    height: 100vh;
}
.el-main {
    background-color: rgb(126, 192, 230);
    width: 100%;
    width: 100%;
}

.el-tabs {
    margin: 0 340px;
    padding: 25px;
    background-color: white;
    border-radius: 10px;
    height: 100%;
    box-sizing: border-box;
    --el-tabs-header-height: 60px;
}

.el-tabs .el-tab-pane {
    // text-align: center;
    padding: 30px 130px 0px;

}

.remaining-sum-info,
.vip-info {
    display: inline-block;
    vertical-align: center;

}

.remaining-sum-info .remaining-sum {
    display: inline-block;
    vertical-align: center;
    margin-right: 20px;
    width: 100px;
}

.remaining-sum-info .el-button {
    vertical-align: center;
}

.vip-info .account-type {
    color: red;
    font-size: x-large;
}

.vip-info .expiration-time {
    color: gray;
    font-size: small;
}

.persenal-info {
    margin-bottom: 30px;
}

.persenal-info .el-image {
    width: 100px;
    height: auto;
    border-radius: 50px;
}

.persenal-info span {
    font-size: x-large;
    margin-left: 20px;
}

.player-records span,
.buy-records span{
    display: inline-block;
    font-size: x-large;
    margin-bottom: 30px;
}
</style>