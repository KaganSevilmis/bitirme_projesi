<template>
  <div class="product-detail-page">
    <!-- Yükleme durumu -->
    <div v-if="isLoading" class="loading">
      <div class="loading-spinner"></div>
      <p>Ürün detayları yükleniyor...</p>
    </div>

    <!-- Hata durumu -->
    <div v-else-if="error" class="error">
      <i class="fas fa-exclamation-circle"></i>
      {{ error }}
    </div>

    <!-- Ürün detayları -->
    <div v-else-if="product" class="product-container">
      <div class="product-header">
        <h1>{{ product.name }}</h1>
        <div class="product-meta">
          <span class="category">{{ product.category }}</span>
          <span class="location">
            <i class="fas fa-map-marker-alt"></i>
            {{ product.location }}
          </span>
          <span class="date">İlan Tarihi: {{ formatDate(product.createdAt) }}</span>
        </div>
      </div>

      <div class="product-content">
        <div class="product-images">
          <img 
            :src="product.imageUrl || 'https://via.placeholder.com/600x400'" 
            :alt="product.name"
            class="main-image"
          >
        </div>

        <div class="product-info">
          <div class="price-section">
            <h2>Fiyat</h2>
            <div class="price">{{ product.price }}</div>
          </div>

          <!-- VASITA kategorisi için detaylar -->
          <div v-if="product.category === 'VASITA'" class="details-section">
            <h2>Araç Bilgileri</h2>
            <ul class="details-list">
              <li><strong>Marka:</strong> {{ product.details?.brand || '-' }}</li>
              <li><strong>Model:</strong> {{ product.details?.model || '-' }}</li>
              <li><strong>Yıl:</strong> {{ product.details?.year || '-' }}</li>
              <li><strong>Yakıt:</strong> {{ product.details?.fuel || '-' }}</li>
              <li><strong>Vites:</strong> {{ product.details?.transmission || '-' }}</li>
              <li><strong>Kilometre:</strong> {{ product.details?.km ? `${product.details.km} km` : '-' }}</li>
              <li><strong>Motor Gücü:</strong> {{ product.details?.enginePower ? `${product.details.enginePower} HP` : '-' }}</li>
            </ul>
          </div>

          <!-- EMLAK kategorisi için detaylar -->
          <div v-if="product.category === 'EMLAK'" class="details-section">
            <h2>Emlak Bilgileri</h2>
            <ul class="details-list">
              <li><strong>Emlak Tipi:</strong> {{ product.details?.propertyType || '-' }}</li>
              <li><strong>Metrekare:</strong> {{ product.details?.squareMeters ? `${product.details.squareMeters} m²` : '-' }}</li>
              <li><strong>Oda Sayısı:</strong> {{ product.details?.rooms || '-' }}</li>
              <li><strong>Bulunduğu Kat:</strong> {{ product.details?.floor ? `${product.details.floor}. Kat` : '-' }}</li>
              <li><strong>Bina Yaşı:</strong> {{ product.details?.buildingAge ? `${product.details.buildingAge} yıl` : '-' }}</li>
              <li><strong>Isıtma:</strong> {{ product.details?.heating || '-' }}</li>
            </ul>
          </div>

          <!-- İKİNCİ EL VE SIFIR ALIŞVERİŞ kategorisi için detaylar -->
          <div v-if="product.category === 'IKINCI EL VE SIFIR ALISVERIS'" class="details-section">
            <h2>Ürün Bilgileri</h2>
            <ul class="details-list">
              <li><strong>Durumu:</strong> {{ product.details?.condition || '-' }}</li>
              <li><strong>Marka:</strong> {{ product.details?.brand || '-' }}</li>
              <li><strong>Garanti:</strong> {{ product.details?.warranty || '-' }}</li>
            </ul>
          </div>

          <div class="description-section">
            <h2>İlan Detayı</h2>
            <p>{{ product.description || 'Açıklama bulunmuyor.' }}</p>
          </div>

          <div class="contact-section">
            <button class="contact-button">
              <i class="fas fa-phone"></i>
              İlan Sahibini Ara
            </button>
            <button class="message-button">
              <i class="fas fa-envelope"></i>
              Mesaj Gönder
            </button>
          </div>
        </div>
      </div>

      <div class="additional-info">
        <h2>Önemli Bilgiler</h2>
        <ul>
          <li>İlan numarası: {{ product.id }}</li>
          <li>İlan tarihi: {{ formatDate(product.createdAt) }}</li>
          <li>Son güncelleme: {{ formatDate(product.updatedAt) }}</li>
        </ul>
      </div>
    </div>

    <!-- Ürün bulunamadı -->
    <div v-else class="not-found">
      <i class="fas fa-search"></i>
      <p>Ürün bulunamadı.</p>
    </div>

    <!-- Geri dön butonu -->
    <router-link to="/" class="back-button">
      <i class="fas fa-arrow-left"></i>
      Ana Sayfaya Dön
    </router-link>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ProductDetailPage",
  data() {
    return {
      product: null,
      isLoading: true,
      error: null
    };
  },
  methods: {
    formatDate(dateString) {
      if (!dateString) return '-';
      try {
        const date = new Date(dateString);
        return new Intl.DateTimeFormat('tr-TR', {
          day: '2-digit',
          month: '2-digit',
          year: 'numeric',
          hour: '2-digit',
          minute: '2-digit'
        }).format(date);
      } catch (error) {
        console.error('Tarih formatı hatası:', error);
        return '-';
      }
    }
  },
  async created() {
    try {
      const productId = this.$route.params.id;
      console.log('Route params:', this.$route.params);
      console.log('Product ID from route:', productId);

      if (!productId) {
        this.error = "Ürün ID'si bulunamadı.";
        this.isLoading = false;
        return;
      }

      // ID'yi sayıya çevir
      const numericId = parseInt(productId, 10);
      if (isNaN(numericId)) {
        this.error = "Geçersiz ürün ID'si.";
        this.isLoading = false;
        return;
      }

      const response = await axios.get(`http://localhost:7000/api/products/all`);
      console.log('API Response:', response.data);
      
      if (response.data) {
        // ID'ye göre ürünü filtrele
        const foundProduct = response.data.find(p => p.id === numericId);
        if (foundProduct) {
          // details alanı yoksa boş obje olarak ekle
          if (!foundProduct.details) {
            foundProduct.details = {};
          }
          this.product = foundProduct;
        } else {
          this.error = "Ürün bulunamadı.";
        }
      } else {
        this.error = "Ürün bulunamadı.";
      }
    } catch (error) {
      console.error("API Hatası:", error.response || error);
      this.error = error.response?.status === 404 
        ? "Ürün bulunamadı."
        : "Ürün detayları yüklenirken bir hata oluştu. Lütfen daha sonra tekrar deneyiniz.";
    } finally {
      this.isLoading = false;
    }
  }
};
</script>

<style scoped>
.product-detail-page {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  font-family: Arial, sans-serif;
}

.loading {
  text-align: center;
  padding: 40px;
}

.loading-spinner {
  border: 4px solid #f3f3f3;
  border-top: 4px solid #ffc107;
  border-radius: 50%;
  width: 40px;
  height: 40px;
  animation: spin 1s linear infinite;
  margin: 0 auto 20px;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

.error {
  background-color: #f8d7da;
  color: #721c24;
  padding: 20px;
  border-radius: 8px;
  text-align: center;
  margin: 20px 0;
}

.product-container {
  background-color: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
  padding: 30px;
}

.product-header {
  margin-bottom: 30px;
}

.product-header h1 {
  margin: 0;
  color: #333;
  font-size: 2em;
}

.product-meta {
  margin-top: 15px;
  display: flex;
  gap: 20px;
  color: #666;
  font-size: 0.9em;
}

.product-content {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 40px;
  margin-bottom: 30px;
}

.product-images {
  text-align: center;
}

.main-image {
  width: 100%;
  max-height: 400px;
  object-fit: cover;
  border-radius: 8px;
}

.product-info {
  display: flex;
  flex-direction: column;
  gap: 30px;
}

.price-section .price {
  font-size: 2em;
  color: #28a745;
  font-weight: bold;
}

.details-section {
  background-color: #f8f9fa;
  padding: 20px;
  border-radius: 8px;
}

.details-list {
  list-style: none;
  padding: 0;
  margin: 15px 0 0 0;
}

.details-list li {
  margin-bottom: 10px;
  color: #444;
}

.details-list strong {
  color: #333;
  margin-right: 8px;
}

.contact-section {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 15px;
  margin-top: 20px;
}

.contact-button, .message-button {
  padding: 12px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1em;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  transition: all 0.3s ease;
}

.contact-button {
  background-color: #28a745;
  color: white;
}

.message-button {
  background-color: #007bff;
  color: white;
}

.contact-button:hover, .message-button:hover {
  opacity: 0.9;
}

.additional-info {
  margin-top: 30px;
  padding-top: 30px;
  border-top: 1px solid #eee;
}

.additional-info ul {
  list-style: none;
  padding: 0;
  color: #666;
}

.additional-info li {
  margin-bottom: 10px;
}

.back-button {
  display: inline-flex;
  align-items: center;
  gap: 8px;
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

.not-found {
  text-align: center;
  padding: 40px;
  color: #666;
}

@media (max-width: 768px) {
  .product-content {
    grid-template-columns: 1fr;
  }

  .contact-section {
    grid-template-columns: 1fr;
  }

  .product-meta {
    flex-direction: column;
    gap: 10px;
  }
}
</style> 