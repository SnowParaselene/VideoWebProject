<script lang="ts" setup>
import type { Video } from '@/config/Video';
import { ArrowLeft, Delete, Download, Plus, ZoomIn } from '@element-plus/icons-vue';
import { onMounted, ref } from 'vue';
import type { UploadProps, UploadFile } from 'element-plus'
import type { Group } from '@/config/Group';
import type { UploadFiles } from 'element-plus/lib/components/index.js';

const emits = defineEmits(["goBack"])
//组件
const coverUpload = ref();

const newResource = ref<Video>();

const title = ref<string>();
const subTitle = ref<string>();
const cover = ref<string>();
const coverList = ref<UploadFiles>();
const vipOnly = ref<string>();
const status = ref<boolean>();
const describe = ref<string>();
//资源分类
const sortSelect = ref<string>();
const sortGroups = ref<Group[]>();


//封面预览对话框参数
const coverDialogVisible = ref<boolean>(false);

//页头
//点击back按钮
const backClickHandler = () => {
    //返回首页
    // router.push("home");
    emits("goBack");
}

//封面上传
//封面预览
const handleRemove: UploadProps['onRemove'] = (uploadFile, uploadFiles) => {
    console.log(uploadFile, uploadFiles)
}

const handlePictureCardPreview: UploadProps['onPreview'] = (uploadFile) => {
    cover.value = uploadFile.url!
    coverDialogVisible.value = true
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

onMounted(() => {
    //重置数据
    // newResource.value = <Video>{
    //     cover: '/11',
    //     playTimes: '11',
    //     time: '11',
    //     title: '',
    //     vid: '',
    //     vipOnly: false,
    // }
    title.value = undefined;
    subTitle.value = undefined;
    cover.value = undefined;

    //获取分类
    updateDataSort();
})

</script>

<template>
    <!-- 资源新增界面 -->
    <el-page-header :icon="ArrowLeft" @back="backClickHandler">
        <template #content>
            <span class="text-large font-600 mr-3"> 资源新增 </span>
        </template>
    </el-page-header>
    <el-divider />
    <el-container direction="vertical">
        <el-row>
            <el-col :span="16" :offset="4">
                <el-form label-width="100px" >
                    <el-form-item label="视频标题">
                        <el-input v-model="title"></el-input>
                    </el-form-item>
                    <el-form-item label="视频副标题">
                        <el-input v-model="subTitle"></el-input>
                    </el-form-item>
                    <el-form-item label="视频封面">
                        <el-upload v-model:file-list="coverList" list-type="picture-card" :auto-upload="false" :limit="1"
                            :on-preview="handlePictureCardPreview" :on-remove="handleRemove">
                            <el-icon>
                                <Plus />
                            </el-icon>
                        </el-upload>
                        <el-dialog v-model="coverDialogVisible">
                            <img w-full :src="cover" alt="Preview Image" />
                        </el-dialog>
        
                    </el-form-item>
                    <el-form-item label="视频分类">
                        <el-select v-model="sortSelect" clearable placeholder="分类选择">
                            <el-option-group v-for="group in sortGroups" :key="group.label" :label="group.label">
                                <el-option v-for="item in group.options" :key="item.value" :label="item.label"
                                    :value="item.value" />
                            </el-option-group>
                        </el-select>
                    </el-form-item>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="vip专享">
                                <el-switch v-model="vipOnly"/>
                            </el-form-item></el-col>
                        <el-col :span="12">
                            <el-form-item label="视频上线">
                                <el-switch v-model="status"/>
                            </el-form-item>
                        </el-col>
                    </el-row>
        
        
                    <el-form-item label="视频简介">
                        <el-input type="textarea" v-model="describe" ></el-input>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="4" :offset="20" style="margin-top: 20px;">
                <el-button type="primary">添 加</el-button>
            </el-col>
        </el-row>
    </el-container>
</template>

<style lang="scss" scoped>
.el-container{
    justify-content: center;
}
</style>