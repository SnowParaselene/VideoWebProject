import HomePageViewVue from '@/views/HomePageView.vue'
import VideoPlayPageViewVue from '@/views/VideoPlayPageView.vue';
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomePageViewVue,
      meta: {
        title: '首页'
      }
    },
    {
      path: '/player/:vid',
      name: 'player',
      component: VideoPlayPageViewVue,
      props: { vid: String },
      meta: {
        title: '视频播放页'
      }
    }
  ]
})


router.beforeEach((to, from, next) => {
  window.document.title = to.meta.title as string;
  next()
})

export default router
