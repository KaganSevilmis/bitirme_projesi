<template>
  <div class="post-ad-page">
    <h2>Ücretsiz İlan Ver</h2>

    <!-- Hata mesajı -->
    <div v-if="error" class="error-message">
      {{ error }}
    </div>

    <!-- Başarı mesajı -->
    <div v-if="successMessage" class="success-message">
      {{ successMessage }}
    </div>

    <form @submit.prevent="handleSubmit">
      <div class="form-group">
        <label for="name">İlan Başlığı</label>
        <input 
          type="text" 
          id="name"
          v-model="formData.name"
          placeholder="Örn: Audi A4"
          required 
        />
      </div>

      <div class="form-group">
        <label for="description">İlan Açıklaması</label>
        <textarea 
          id="description"
          v-model="formData.description"
          placeholder="Ürün detaylarını buraya yazın..."
          required
        ></textarea>
      </div>

      <div class="form-group">
        <label for="price">Fiyat</label>
        <input 
          type="text" 
          id="price"
          v-model="formData.price"
          placeholder="Örn: 1.200.000 TL"
          required 
        />
      </div>

      <div class="form-group">
        <label for="imageUrl">Görsel URL</label>
        <input 
          type="url" 
          id="imageUrl"
          v-model="formData.imageUrl"
          placeholder="Ürün görseli için URL girin"
          required 
        />
      </div>

      <div class="form-group">
        <label for="location">Konum</label>
        <input 
          type="text" 
          id="location"
          v-model="formData.location"
          placeholder="Örn: İstanbul"
          required 
        />
      </div>

      <div class="form-group">
        <label for="category">Kategori</label>
        <select 
          id="category"
          v-model="formData.category"
          required
        >
          <option value="">Kategori Seçin</option>
          <option value="VASITA">VASITA</option>
          <option value="EMLAK">EMLAK</option>
          <option value="IKINCI EL VE SIFIR ALISVERIS">IKINCI EL VE SIFIR ALISVERIS</option>
        </select>
      </div>

      <button type="submit" :disabled="loading">
        {{ loading ? 'İlan Ekleniyor...' : 'İlan Ver' }}
      </button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "PostAdPage",
  data() {
    return {
      formData: {
        name: '',
        description: '',
        price: '',
        imageUrl: '',
        location: '',
        category: ''
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
        await axios.post('http://localhost:7000/api/products/new', this.formData);
        
        this.successMessage = 'İlanınız başarıyla eklendi! Ana sayfaya yönlendiriliyorsunuz...';
        
        // Form verilerini temizle
        this.formData = {
          name: '',
          description: '',
          price: '',
          imageUrl: '',
          location: '',
          category: ''
        };

        // 2 saniye sonra ana sayfaya yönlendir
        setTimeout(() => {
          this.$router.push('/');
        }, 2000);

      } catch (err) {
        this.error = err.response?.data?.message || 'İlan eklenirken bir hata oluştu.';
        console.error('İlan ekleme hatası:', err);
      } finally {
        this.loading = false;
      }
    }
  }
};
</script>

<style scoped>
.post-ad-page {
  max-width: 600px;
  margin: 40px auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

h2 {
  text-align: center;
  color: #333;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 8px;
  color: #333;
  font-weight: bold;
}

input, textarea, select {
  width: 100%;
  padding: 12px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 5px;
  transition: border-color 0.3s ease;
}

textarea {
  min-height: 120px;
  resize: vertical;
}

input:focus, textarea:focus, select:focus {
  border-color: #007bff;
  outline: none;
}

button {
  width: 100%;
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
</style>
  