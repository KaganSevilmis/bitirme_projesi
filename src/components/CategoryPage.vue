<template>
  <div class="category-page">
    <h1>{{ categoryId }} Kategorisi</h1>
    
    <!-- Yükleme durumu -->
    <div v-if="loading" class="loading">
      Ürünler yükleniyor...
    </div>

    <!-- Hata durumu -->
    <div v-else-if="error" class="error">
      {{ error }}
    </div>

    <!-- Ürünler yüklendiyse -->
    <div v-else>
      <div v-if="filteredProducts.length === 0" class="no-products">
        Bu kategoride henüz ürün bulunmamaktadır.
      </div>
      <div v-else class="products-grid">
        <div v-for="product in filteredProducts" :key="product.id" class="product-card">
          <img :src="product.imageUrl || 'https://via.placeholder.com/150'" :alt="product.name" class="product-image">
          <div class="product-info">
            <h3>{{ product.name }}</h3>
            <p class="description">{{ product.description }}</p>
            <p class="location">{{ product.location }}</p>
            <p class="price">{{ product.price }}</p>
            <button class="details-button">Detayları Gör</button>
          </div>
        </div>
      </div>
    </div>

    <router-link to="/" class="back-link">Ana Sayfaya Geri Dön</router-link>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "CategoryPage",
  props: ["categoryId"],
  
  data() {
    return {
      products: [],
      loading: true,
      error: null
    };
  },

  computed: {
    filteredProducts() {
      console.log('Mevcut categoryId:', this.categoryId);
      console.log('Tüm ürünler:', this.products);
      
      return this.products.filter(product => {
        console.log('Ürün kategorisi:', product.category);
        // Hem category hem de categoryName alanlarını kontrol edelim
        const productCategory = (product.category || product.categoryName || '').toString().toLowerCase();
        const searchCategory = this.categoryId.toString().toLowerCase();
        
        console.log('Karşılaştırma:', productCategory, '===', searchCategory);
        return productCategory === searchCategory;
      });
    }
  },

  async created() {
    try {
      const response = await axios.get('/api/products/all');
      console.log('API yanıtı:', response.data);
      this.products = response.data;
      this.loading = false;
    } catch (err) {
      this.error = "Veriler yüklenirken bir hata oluştu: " + err.message;
      this.loading = false;
      console.error("API Hatası:", err);
    }
  }
};
</script>

<style scoped>
.category-page {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.loading {
  text-align: center;
  padding: 20px;
  color: #666;
}

.error {
  color: #dc3545;
  padding: 20px;
  background-color: #f8d7da;
  border-radius: 4px;
  margin: 20px 0;
}

.no-products {
  text-align: center;
  padding: 40px;
  color: #666;
  background-color: #f8f9fa;
  border-radius: 4px;
  margin: 20px 0;
}

.products-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 20px;
  margin-top: 20px;
}

.product-card {
  border: 1px solid #ddd;
  border-radius: 8px;
  overflow: hidden;
  background-color: white;
  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
  transition: transform 0.3s ease;
}

.product-card:hover {
  transform: translateY(-5px);
}

.product-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
}

.product-info {
  padding: 15px;
}

.product-info h3 {
  margin: 0 0 10px 0;
  color: #333;
  font-size: 1.2em;
}

.description {
  color: #666;
  margin-bottom: 10px;
  font-size: 0.9em;
  line-height: 1.4;
}

.location {
  color: #666;
  font-size: 0.9em;
  margin: 5px 0;
}

.price {
  font-size: 1.2em;
  color: #28a745;
  font-weight: bold;
  margin: 10px 0;
}

.details-button {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.details-button:hover {
  background-color: #0056b3;
}

.back-link {
  display: inline-block;
  margin-top: 20px;
  color: #007bff;
  text-decoration: none;
}

.back-link:hover {
  text-decoration: underline;
}
</style>
