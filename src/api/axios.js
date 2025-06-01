import axios from 'axios';

// Axios instance oluştur
const api = axios.create({
  baseURL: 'http://localhost:7000',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json'
  }
});

// Request interceptor
api.interceptors.request.use(
  (config) => {
    // İstek öncesi yapılacak işlemler
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Response interceptor
api.interceptors.response.use(
  (response) => {
    // Başarılı yanıt
    return response;
  },
  (error) => {
    // Hata yanıtı
    if (error.response) {
      // Sunucudan gelen hata yanıtı
      console.error('API Error Response:', error.response);
    } else if (error.request) {
      // İstek yapıldı ama yanıt alınamadı
      console.error('API Request Error:', error.request);
    } else {
      // İstek oluşturulurken hata oluştu
      console.error('API Error:', error.message);
    }
    return Promise.reject(error);
  }
);

export default api; 