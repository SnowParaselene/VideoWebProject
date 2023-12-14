import { createApp } from 'vue'
import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

import App from './App.vue'
import router from './router'
import * as Icons from '@element-plus/icons-vue'

const app = createApp(App)


const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)
app.use(pinia)

app.use(router)

Object.keys(Icons).forEach(key => {
    app.component(key, Icons[key as keyof typeof Icons])
  })


app.mount('#app')
