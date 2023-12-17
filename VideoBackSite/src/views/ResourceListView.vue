<script lang="ts" setup>
import type { Group } from '@/config/Group';
import type { Video } from '@/config/Video';
import { ArrowLeft } from '@element-plus/icons-vue';
import { onMounted, ref } from 'vue';
import { Search, Edit, Delete } from '@element-plus/icons-vue';
import router from '@/router';
import { copyObject } from '@/config/tools';

//页头选择器和搜索参数
const sortSelect = ref<string>();
const sortGroups = ref<Group[]>();
const statusSelect = ref<string>();
const searchInput = ref<string>()
//分页参数
const dataTotleCount = ref<number>(0);
const dataCurrentPage = ref<number>(0);

//表格数据
const tableData = ref<Video[]>();
//参数字典
const parameterDictionary = ref<any>(
    {
        vid: { title: "视频ID号", type: "unchanged" },
        title: { title: "视频标题", type: "String" },
        cover: { title: "视频封面", type: "image" },
        sort: { title: "视频分类", type: "tree" },
        time: { title: "视频时长", type: "unchanged" },
        vipOnly: { title: "vip专享", type: "switch" },
        status: { title: "视频上线", type: "switch" },
        playTimes: { title: "播放量", type: "unchanged" },
        createTime: { title: "创建时间", type: "unchanged" },
        true: { title: "是", type: "const" },
        false: { title: "否", type: "const" },
    });
const emits = defineEmits(["goBack"])

//对话框参数
const checkDialogVisible = ref<boolean>(false);
const editDialogVisible = ref<boolean>(false);
const selectedData = ref<Video>();

onMounted(() => {
    //测试数据初始化
    updateDataSort();
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
    console.log("查看评论：" + row.title);
    selectedData.value = row;
    checkDialogVisible.value = true
}
//点击编辑
const editHandler = (row: any) => {
    console.log("编辑：" + row.title);
    selectedData.value = copyObject(row);
    // selectedData.value = row;
    editDialogVisible.value = true
}
//点击保存
const saveEditHandler = () => {
    // 保存修改
    ElMessageBox.confirm(
        '此操作将保存本次修改，是否确定？',
        '修改保存',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(() => {
            //进行保存操作，修改后数据为selectedData
            updateTableData();
            ElMessage({
                type: 'success',
                message: '保存成功',
            })
            editDialogVisible.value = false;
        })
        .catch(() => {
        })


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
            updateTableData();
            ElMessage({
                type: 'success',
                message: '删除成功',
            })
        })
        .catch(() => {
        })
}

//更新表格数据
//获取资源数据
const updateTableData = () => {
    //分页获取数据
    tableData.value = [{
        vid: "1",
        title: "资源1",
        cover: "",
        sort: "1-2",
        time: "16:00",
        vipOnly: true,
        status: true,
        playTimes: "1万",
        createTime: "2023-12-14 16:02:26",
    }]
    dataTotleCount.value = 1;
    dataCurrentPage.value = 1;
}
//获取资源分类
const updateDataSort = () => {
    //获取分类列表
    sortGroups.value = <Group[]>[
        {
            label: "一级分类1",
            options: [
                {
                    label: '1-1',
                    value: "1-1"
                },
                {
                    label: '1-2',
                    value: "1-2"
                },
                {
                    label: '1-3',
                    value: "1-3"
                },
            ]
        },
        {
            label: "一级分类2",
            options: [
                {
                    label: '2-1',
                    value: "2-1"
                },
                {
                    label: '2-2',
                    value: "2-2"
                },
                {
                    label: '2-3',
                    value: "2-3"
                },
            ]
        },
    ]
}



</script>

<template>
    <el-dialog v-model="checkDialogVisible" title="查看" width="50%" align-center>
        <el-descriptions :title="selectedData?.title" :column="3" border>
            <el-descriptions-item v-for="(prop, key) in selectedData" :label="parameterDictionary[key].title"
                label-align="center" align="center">
                {{ parameterDictionary[prop!.toString()] ? parameterDictionary[prop!.toString()].title : prop }}
            </el-descriptions-item>
        </el-descriptions>
        <template #footer>
            <span class="dialog-footer">
                <el-button type="primary" @click="checkDialogVisible = false">
                    确定
                </el-button>
            </span>
        </template>
    </el-dialog>
    <el-dialog v-model="editDialogVisible" title="修改" width="50%" align-center>
        <!-- <el-descriptions :title="selectedData?.title" :column="3" border>
            <el-descriptions-item v-for="(prop, key) in selectedData" :label="parameterDictionary[key]" label-align="right" align="center">
                {{ parameterDictionary[prop!.toString()]?parameterDictionary[prop!.toString()]:prop }}
            </el-descriptions-item>
        </el-descriptions> -->
        <el-form :model="selectedData" style="padding-right: 200px;">
            <template v-for="(props, key) in selectedData">
                <el-form-item :label="parameterDictionary[key].title" :prop="key" label-width="100px">
                    <span v-if="parameterDictionary[key].type == 'unchanged'">{{ props }}</span>
                    <el-input v-else-if="parameterDictionary[key].type == 'String'"
                        v-model="(selectedData![key] as string)"></el-input>
                    <el-image v-else-if="parameterDictionary[key].type == 'image'"
                        :src="(selectedData![key] as string)"></el-image>
                    <el-select v-else-if="parameterDictionary[key].type == 'tree'" v-model="selectedData![key]"
                        placeholder="分类选择" @change="sortChangeHandler">
                        <el-option-group v-for="group in sortGroups" :key="group.label" :label="group.label">
                            <el-option v-for="item in group.options" :key="item.value" :label="item.label"
                                :value="item.value" />
                        </el-option-group>
                    </el-select>
                    <template v-else-if="parameterDictionary[key].type == 'switch'">
                        <el-switch v-model="(selectedData![key] as boolean)" />
                    </template>
                </el-form-item>
            </template>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="editDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="saveEditHandler">
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
        <el-row>
            <el-table :data="tableData" stripe style="width: 100%">
                <el-table-column prop="vid" label="ID" width="80px" />
                <el-table-column prop="title" label="资源名" width="300" />
                <el-table-column prop="cover" label="封面" width="180" />
                <el-table-column />
                <el-table-column label="会员专享" width="80px">
                    <template #default="scope">
                        {{ parameterDictionary[scope.row.vipOnly.toString()].title }}
                    </template>
                </el-table-column>
                <el-table-column prop="time" label="时长" width="80px" />
                <el-table-column prop="sort" label="类型" width="80px" />
                <el-table-column label="上线" width="80px">
                    <template #default="scope">
                        {{ parameterDictionary[scope.row.status.toString()].title }}
                    </template>
                </el-table-column>
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
        <el-row class="pagination">
            <el-pagination background layout="prev, pager, next" :total="dataTotleCount" hide-on-single-page
                @current-change="updateTableData" v-model:current-page="dataCurrentPage" />
        </el-row>
    </el-container>
</template>

<style lang="scss" scoped>
.body {
    width: 100%;
    display: inline-block;
}

.search {
    width: 100%;
    margin-bottom: 20px;
}

.content {
    height: 100%;
}

.pagination {
    margin-top: 20px;
    align-items: center;
    justify-content: center;
}
</style>