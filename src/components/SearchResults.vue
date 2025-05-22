<template>
  <div class="search-results">
    <h2>Arama Sonuçları: "{{ searchQuery }}"</h2>

    <!-- Yükleme durumu -->
    <div v-if="loading" class="loading">
      Aranıyor...
    </div>

    <!-- Hata durumu -->
    <div v-else-if="error" class="error">
      {{ error }}
    </div>

    <!-- Sonuç bulunamadı -->
    <div v-else-if="filteredProducts.length === 0" class="no-results">
      Aramanızla eşleşen ürün bulunamadı.
    </div>

    <!-- Sonuçlar -->
    <div v-else class="results-grid">
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
</template>

<script>
import axios from 'axios';

export default {
  name: 'SearchResults',
  data() {
    return {
      searchQuery: '',
      allProducts: [],
      loading: true,
      error: null
    };
  },
  computed: {
    filteredProducts() {
      if (!this.searchQuery) return this.allProducts;

      const searchTerms = this.searchQuery.toLowerCase().split(' ');
      
      return this.allProducts.filter(product => {
        const searchableText = [
          product.name,
          product.description,
          product.location,
          product.price?.toString(),
          product.category,
          product.categoryName
        ]
          .filter(Boolean)
          .join(' ')
          .toLowerCase();

        // Tüm arama terimlerinin ürün bilgilerinde geçmesi gerekiyor
        return searchTerms.every(term => searchableText.includes(term));
      });
    }
  },
  watch: {
    '$route.query.q': {
      handler(newQuery) {
        this.searchQuery = newQuery || '';
      },
      immediate: true
    }
  },
  async created() {
    try {
      const response = await axios.get('/api/products/all');
      this.allProducts = response.data;
    } catch (err) {
      this.error = 'Ürünler yüklenirken bir hata oluştu: ' + err.message;
      console.error('Yükleme hatası:', err);
    } finally {
      this.loading = false;
    }
  }
};
</script>

<style scoped>
.search-results {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

h2 {
  color: #333;
  margin-bottom: 20px;
}

.loading {
  text-align: center;
  padding: 40px;
  color: #666;
}

.error {
  color: #dc3545;
  padding: 20px;
  background-color: #f8d7da;
  border-radius: 4px;
  margin: 20px 0;
}

.no-results {
  text-align: center;
  padding: 40px;
  color: #666;
  background-color: #f8f9fa;
  border-radius: 4px;
}

.results-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 20px;
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
</style> 