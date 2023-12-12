import HomePageViewVue from '@/views/HomePageView.vue'
import LoginPageViewVue from '@/views/LoginPageView.vue'
import { createRouter, createWebHistory } from 'vue-router'
import { useAdminStore } from '@/stores/admin'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomePageViewVue
    },
    {
      path: '/login',
      name: 'login',
      component: LoginPageViewVue
    }
  ]
})

router.beforeEach((to, from, next) => {
  const store = useAdminStore();
  
  if (to.name === 'login') {
    if (store.isLogin) {
      next({ name: 'home' });
    }
    next();
  } else if (!store.isLogin) {
    next({ name: 'login' });
  }
  next();


})

export default router
