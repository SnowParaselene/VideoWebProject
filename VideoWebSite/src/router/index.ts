import { useUserStore } from '@/stores/user';
import HomePageViewVue from '@/views/HomePageView.vue'
import LoginPageViewVue from '@/views/LoginPageView.vue';
import PersenalPageViewVue from '@/views/PersenalPageView.vue';
import VideoPlayPageViewVue from '@/views/VideoPlayPageView.vue';
import { storeToRefs } from 'pinia';
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
      props: true,
      meta: {
        title: '视频播放页'
      }
    },
    {
      path: '/login',
      name: 'login',
      component: LoginPageViewVue,
      meta: {
        title: '登录',
        redirect: "",
      },
    },
    {
      path: '/persenal',
      name: 'persenal',
      component: PersenalPageViewVue,
      meta: {
        title: '个人中心'
      },
    }
  ]
})


router.beforeEach((to, from, next) => {
  const userStore = useUserStore();

  window.document.title = to.meta.title as string;

  if (to.path === '/persenal' && !userStore.isLogin) {
    to.meta.redirect = from.path;
    next({ path: '/login'});
  }
  if (to.path === '/login' && userStore.isLogin) {
    next({ path: "/"  });
  }

  next()
})

export default router
