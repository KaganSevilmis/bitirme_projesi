<template>
    <div class="login-page">
      <h2>Giriş Yap</h2>

      <!-- Hata mesajı -->
      <div v-if="error" class="error-message">
        {{ error }}
      </div>

      <!-- Başarı mesajı -->
      <div v-if="successMessage" class="success-message">
        {{ successMessage }}
      </div>

      <form @submit.prevent="handleSubmit">
        <input 
          type="text" 
          v-model="formData.username"
          placeholder="Kullanıcı Adı"
          required 
        />
        <input 
          type="password" 
          v-model="formData.password"
          placeholder="Şifre"
          required 
        />
        <button type="submit" :disabled="loading">
          {{ loading ? 'Giriş Yapılıyor...' : 'Giriş Yap' }}
        </button>
      </form>

      <div class="register-link">
        Hesabınız yok mu? <router-link to="/register">Üye Olun</router-link>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';

  export default {
    name: "LoginPage",
    data() {
      return {
        formData: {
          username: '',
          password: ''
        },
        loading: false,
        error: null,
        successMessage: null
      };
    },
    methods: {
      async handleSubmit() {
        this.loading = true;
        this.error = null;
        this.successMessage = null;

        try {
          await axios.post('/api/users/login', this.formData);
          this.successMessage = 'Giriş başarılı! Yönlendiriliyorsunuz...';
          
          // Form verilerini temizle
          this.formData = {
            username: '',
            password: ''
          };

          // 2 saniye sonra ana sayfaya yönlendir
          setTimeout(() => {
            this.$router.push('/');
          }, 2000);

        } catch (err) {
          this.error = err.response?.data?.message || 'Giriş yapılırken bir hata oluştu.';
          console.error('Giriş hatası:', err);
        } finally {
          this.loading = false;
        }
      }
    }
  };
  </script>
  
  <style scoped>
  .login-page {
    max-width: 400px;
    margin: 40px auto;
    padding: 20px;
    font-family: Arial, sans-serif;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  }
  
  h2 {
    text-align: center;
    color: #333;
    margin-bottom: 20px;
  }
  
  form {
    display: flex;
    flex-direction: column;
  }
  
  input {
    margin: 10px 0;
    padding: 12px;
    font-size: 16px;
    border: 1px solid #ddd;
    border-radius: 5px;
    transition: border-color 0.3s ease;
  }
  
  input:focus {
    border-color: #007bff;
    outline: none;
  }
  
  button {
    margin-top: 20px;
    padding: 12px 20px;
    background-color: #050505;
    color: #ffc107;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    transition: all 0.3s ease;
  }
  
  button:hover:not(:disabled) {
    background-color: #f7e6a3;
    color: #050505;
  }
  
  button:disabled {
    background-color: #cccccc;
    cursor: not-allowed;
  }
  
  .error-message {
    background-color: #f8d7da;
    color: #721c24;
    padding: 10px;
    border-radius: 4px;
    margin-bottom: 15px;
    text-align: center;
  }
  
  .success-message {
    background-color: #d4edda;
    color: #155724;
    padding: 10px;
    border-radius: 4px;
    margin-bottom: 15px;
    text-align: center;
  }
  
  .register-link {
    text-align: center;
    margin-top: 20px;
    color: #666;
  }
  
  .register-link a {
    color: #007bff;
    text-decoration: none;
    font-weight: bold;
  }
  
  .register-link a:hover {
    text-decoration: underline;
  }
  </style>
  