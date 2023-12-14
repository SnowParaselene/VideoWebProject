<script lang="ts" setup>
import type { Group } from '@/config/Group';
import type {Video} from '@/config/Video';
import { ArrowLeft } from '@element-plus/icons-vue';
import { onMounted, ref } from 'vue';
import { Search, Edit, Delete } from '@element-plus/icons-vue';
import router from '@/router';

//页头选择器和搜索参数
const sortSelect = ref<string>();
const sortGroups = ref<Group[]>();
const statusSelect = ref<string>();
const searchInput = ref<string>()

//表格数据
const tableData = ref<Video[]>();
const emits = defineEmits(["goBack"])

//对话框参数
const dialogVisible = ref<boolean>(false);
const selectedData =ref<Video>();

onMounted(() => {
    //测试数据初始化
    sortGroups.value = <Group[]>[
        {
            label: "一级分类1",
            options: [
                {
                    label: '1-1',
                    value: "1-1v"
                },
                {
                    label: '1-2',
                    value: "1-2v"
                },
                {
                    label: '1-3',
                    value: "1-3v"
                },
            ]
        },
        {
            label: "一级分类2",
            options: [
                {
                    label: '2-1',
                    value: "2-1v"
                },
                {
                    label: '2-2',
                    value: "2-2v"
                },
                {
                    label: '2-3',
                    value: "2-3v"
                },
            ]
        },
    ]
    updateTableData();
})

//页头
//点击back按钮
const backClickHandler = () => {
    //返回首页
    // router.push("home");
    emits("goBack");
}

//搜索操作
//修改分类选择器
const sortChangeHandler = () => {
    console.log("分类变化，查看：" + sortSelect.value);
}
//修改状态选择器
const statusChangeHandler = () => {
    console.log("状态变化，查看：" + statusSelect.value);
}
//点击搜索按钮
const searchClickHandler = () => {
    console.log("搜索：" + searchInput.value);
}

//表格操作
//点击查看
const checkHandler = (row: any) => {
    console.log("查看评论：" + row.name);
    dialogVisible.value = true
}
//点击编辑
const editHandler = (row: any) => {
    console.log("编辑：" + row.name);
}
//点击删除
const deleteHandler = (row: any) => {
    console.log("删除：" + row.name);
    ElMessageBox.confirm(
        '此操作将删除该资源，是否确定？',
        '资源删除',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(() => {
            //进行删除操作
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
        })
        .catch(() => {
        })
}
//更新表格数据
const updateTableData = () => {
    //获取数据
    tableData.value = [{
        vid: "1",
        title: "资源1",
        cover: "",
        sort: "类型1",
        time:"16:00",
        vipOnly:false,
        status: "上线",
        playTimes: "1万",
        createTime: "2023-12-14 16:02:26",
    }]
}

</script>

<template>
    <el-dialog v-model="dialogVisible" title="查看" width="50%" align-center>
        <el-descriptions title="Customized style list" :column="3" border>
            <!-- <el-descriptions-item v-for="props in selectedData" :label="props" label-align="right" align="center">{{ selectedData. }}</el-descriptions-item> -->
            
            <el-descriptions-item label="Username" label-align="right" align="center" label-class-name="my-label"
                class-name="my-content" width="150px">kooriookami</el-descriptions-item>
            <el-descriptions-item label="Telephone" label-align="right" align="center">18100000000</el-descriptions-item>
            <el-descriptions-item label="Place" label-align="right" align="center">Suzhou</el-descriptions-item>
            <el-descriptions-item label="Remarks" label-align="right" align="center">
                <el-tag size="small">School</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="Address" label-align="right" align="center">No.1188, Wuzhong Avenue, Wuzhong
                District, Suzhou, Jiangsu
                Province</el-descriptions-item>
        </el-descriptions>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="dialogVisible = false">
                    保存
                </el-button>
            </span>
        </template>
    </el-dialog>
    <el-page-header :icon="ArrowLeft" @back="backClickHandler">
        <template #content>
            <span class="text-large font-600 mr-3"> 资源管理 </span>
        </template>
    </el-page-header>
    <el-divider />
    <el-container class="body">
        <el-row class="search" :gutter="20">
            <el-col :span="4">
                <el-select v-model="sortSelect" clearable placeholder="分类选择" @change="sortChangeHandler">
                    <el-option-group v-for="group in sortGroups" :key="group.label" :label="group.label">
                        <el-option v-for="item in group.options" :key="item.value" :label="item.label"
                            :value="item.value" />
                    </el-option-group>
                </el-select>
            </el-col>
            <el-col :span="4">
                <el-select v-model="statusSelect" clearable placeholder="状态选择" @change="statusChangeHandler">
                    <el-option key="online" label="上线" value="online" />
                    <el-option key="offline" label="下线" value="offline" />
                </el-select>
            </el-col>
            <el-col :span="10"></el-col>
            <el-col :span="6">
                <el-input v-model="searchInput" placeholder="请输入关键字">
                    <template #append>
                        <el-button :icon="Search" @click="searchClickHandler" />
                    </template>
                </el-input>
            </el-col>
        </el-row>
        <el-row class="content">
            <el-table :data="tableData" stripe style="width: 100%">
                <el-table-column prop="vid" label="ID" width="80px" />
                <el-table-column prop="title" label="资源名" width="300" />
                <el-table-column prop="cover" label="封面" width="180" />
                <el-table-column />
                <el-table-column prop="vipOnly" label="会员专享" width="80px" />
                <el-table-column prop="time" label="时长" width="80px" />
                <el-table-column prop="sort" label="类型" width="80px" />
                <el-table-column prop="status" label="状态" width="80px" />
                <el-table-column prop="playTimes" label="播放量" width="80px" />
                <el-table-column prop="createTime" label="上传时间" width="180px" />
                <el-table-column label="操作" width="150px">
                    <template #default="scope">
                        <el-button type="success" :icon="Search" circle @click="checkHandler(scope.row)" />
                        <el-button type="primary" :icon="Edit" circle @click="editHandler(scope.row)" />
                        <el-button type="danger" :icon="Delete" circle @click="deleteHandler(scope.row)" />
                    </template>
                </el-table-column>
            </el-table>
        </el-row>
    </el-container>
</template>

<style lang="scss" scoped>
.body {
    width: 100%;
    height: 100%;
    display: inline-block;
}

.search {
    width: 100%;
}

.content {
    height: 100%;
}
</style>