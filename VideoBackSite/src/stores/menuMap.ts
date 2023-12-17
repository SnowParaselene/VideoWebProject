import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import type { Group } from '@/config/Group'

export const useMenuMapStore = defineStore('menuMap', () => {
    const menuMap = ref<Group[]>([
        {
            label: '首页',
            icon: 'House',
            value: '/'
        },
        {
            label: '资源管理',
            icon: 'Files',
            value: '/resource',
            options: [
                {
                    label: '资源列表',
                    icon: '',
                    value: '/resource-list',
                },
                {
                    label: '资源添加',
                    icon: '',
                    value: '/resource-add',
                },
            ]
        },
        {
            label: '分类管理',
            icon: 'Folder',
            value: '/sort',
        },
        {
            label: '用户管理',
            icon: 'User',
            value: '/user'
        },
        {
            label: '评论管理',
            icon: 'Comment',
            value: '/comment',
        },
        {
            label: '权限管理',
            icon: 'Setting',
            value: '/authority',
        },


    ]);
    return { menuMap }
})
