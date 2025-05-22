import { createApp } from 'vue'; // Vue'yu import ediyoruz
import App from './App.vue'; // Ana uygulama bileşenini import ediyoruz
import router from './router'; // Yukarıda oluşturduğumuz router'ı import ediyoruz
import axios from 'axios';

// Axios için global ayarlar
axios.defaults.baseURL = 'http://localhost:7000';
axios.defaults.headers.common['Content-Type'] = 'application/json';
axios.defaults.headers.common['Accept'] = 'application/json';
axios.defaults.withCredentials = false; // Spring'de allowCredentials true ise burayı true yapın

// Vue uygulamasını başlatıyoruz ve router'ı kullanıyoruz
createApp(App)
  .use(router)  // Router'ı Vue uygulamasına dahil ediyoruz
  .mount('#app'); // Vue uygulamasını 'app' ID'li HTML elementine bağlıyoruz
