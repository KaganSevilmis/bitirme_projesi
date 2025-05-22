import { createApp } from 'vue'; // Vue'yu import ediyoruz
import App from './App.vue'; // Ana uygulama bileşenini import ediyoruz
import router from './router'; // Yukarıda oluşturduğumuz router'ı import ediyoruz

// Vue uygulamasını başlatıyoruz ve router'ı kullanıyoruz
createApp(App)
  .use(router)  // Router'ı Vue uygulamasına dahil ediyoruz
  .mount('#app'); // Vue uygulamasını 'app' ID'li HTML elementine bağlıyoruz
