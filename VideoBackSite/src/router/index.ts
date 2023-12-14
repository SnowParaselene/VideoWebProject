import HomePageViewVue from '@/views/HomePageView.vue'
import LoginPageViewVue from '@/views/LoginPageView.vue'
import { createRouter, createWebHistory } from 'vue-router'
import { useAdminStore } from '@/stores/admin'
import FrameworkVue from '@/components/Framework.vue'
import ResourceListViewVue from '@/views/ResourceListView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/home',
      redirect:{path:'/'}
    },
    {
      path: '/framework',
      name: 'framework',
      component: FrameworkVue,
      children: [
        {
          path: '/',
          name: 'home',
          component: HomePageViewVue
        },
        {
          path: '/resource-list',
          component: ResourceListViewVue
        }
      ]
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
