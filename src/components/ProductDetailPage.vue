<template>
  <div class="product-detail-page">
    <!-- Yükleme durumu -->
    <div v-if="loading" class="loading">
      Ürün detayları yükleniyor...
    </div>

    <!-- Hata durumu -->
    <div v-else-if="error" class="error-message">
      {{ error }}
    </div>

    <!-- Ürün detayları -->
    <div v-else-if="product" class="product-details">
      <div class="product-image">
        <img :src="product.imageUrl || 'https://via.placeholder.com/400'" :alt="product.name">
      </div>
      
      <div class="product-info">
        <h1>{{ product.name }}</h1>
        <p class="price">{{ product.price }}</p>
        <p class="location">
          <i class="fas fa-map-marker-alt"></i>
          {{ product.location }}
        </p>
        <div class="category">
          <strong>Kategori:</strong> {{ product.category }}
        </div>
        <div class="description">
          <h3>İlan Detayı</h3>
          <p>{{ product.description }}</p>
        </div>
      </div>
    </div>

    <div v-else class="not-found">
      Ürün bulunamadı.
    </div>

    <router-link to="/" class="back-button">
      Ana Sayfaya Dön
    </router-link>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'ProductDetailPage',
  props: {
    productId: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      product: null,
      loading: true,
      error: null
    };
  },
  async created() {
    try {
      const response = await axios.get(`/api/products/${this.productId}`);
      this.product = response.data;
    } catch (err) {
      this.error = 'Ürün detayları yüklenirken bir hata oluştu: ' + err.message;
      console.error('API Hatası:', err);
    } finally {
      this.loading = false;
    }
  }
};
</script>

<style scoped>
.product-detail-page {
  max-width: 1200px;
  margin: 40px auto;
  padding: 20px;
}

.loading, .error-message, .not-found {
  text-align: center;
  padding: 20px;
  margin: 20px 0;
  border-radius: 8px;
}

.loading {
  color: #666;
}

.error-message {
  background-color: #f8d7da;
  color: #721c24;
}

.not-found {
  background-color: #f8f9fa;
  color: #666;
}

.product-details {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 40px;
  background-color: white;
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

.product-image img {
  width: 100%;
  height: auto;
  border-radius: 8px;
  object-fit: cover;
}

.product-info h1 {
  margin: 0 0 20px 0;
  color: #333;
  font-size: 2em;
}

.price {
  font-size: 1.8em;
  color: #28a745;
  font-weight: bold;
  margin: 15px 0;
}

.location {
  color: #666;
  margin: 15px 0;
  display: flex;
  align-items: center;
  gap: 8px;
}

.category {
  margin: 15px 0;
  color: #666;
}

.description {
  margin-top: 30px;
}

.description h3 {
  color: #333;
  margin-bottom: 15px;
}

.description p {
  line-height: 1.6;
  color: #444;
}

.back-button {
  display: inline-block;
  margin-top: 30px;
  padding: 12px 24px;
  background-color: #050505;
  color: #ffc107;
  text-decoration: none;
  border-radius: 5px;
  transition: all 0.3s ease;
}

.back-button:hover {
  background-color: #f7e6a3;
  color: #050505;
}

@media (max-width: 768px) {
  .product-details {
    grid-template-columns: 1fr;
  }
}
</style> 