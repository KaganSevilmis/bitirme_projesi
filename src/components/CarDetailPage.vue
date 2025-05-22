<template>
    <div class="car-detail-page">
      <!-- Başlık -->
      <h1>{{ car.title }}</h1>
  
      <!-- Fiyat -->
      <div class="price">
        <strong>{{ car.price }} TL</strong>
      </div>
  
      <!-- Resimler -->
      <div class="car-images" v-if="car.images && car.images.length">
        <img :src="car.mainImage" alt="Ana Görsel" class="main-image" />
        <div class="thumbnail-images">
          <img
            v-for="(image, index) in car.images"
            :key="index"
            :src="image"
            alt="Görsel"
            class="thumbnail"
          />
        </div>
      </div>
  
      <!-- Teknik Özellikler -->
      <div class="technical-details">
        <h2>Teknik Özellikler</h2>
        <ul>
          <li><strong>Marka:</strong> {{ car.brand }}</li>
          <li><strong>Seri:</strong> {{ car.series }}</li>
          <li><strong>Model:</strong> {{ car.model }}</li>
          <li><strong>Yıl:</strong> {{ car.year }}</li>
          <li><strong>Yakıt:</strong> {{ car.fuel }}</li>
          <li><strong>Vites:</strong> {{ car.transmission }}</li>
          <li><strong>KM:</strong> {{ car.km }}</li>
          <li><strong>Motor Gücü:</strong> {{ car.enginePower }}</li>
          <li><strong>Motor Hacmi:</strong> {{ car.engineCapacity }}</li>
          <li><strong>Çekiş:</strong> {{ car.drive }}</li>
          <li><strong>Kapı:</strong> {{ car.doors }}</li>
          <li><strong>Renk:</strong> {{ car.color }}</li>
          <li><strong>Garanti:</strong> {{ car.warranty }}</li>
          <li><strong>Ağır Hasar Kayıtlı:</strong> {{ car.damageRecord }}</li>
          <li><strong>Plaka:</strong> {{ car.plate }}</li>
          <li><strong>Kimden:</strong> {{ car.owner }}</li>
          <li><strong>Takas:</strong> {{ car.trade }}</li>
        </ul>
      </div>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    name: "CarDetailPage",
    data() {
      return {
        car: {}, // Backend'den gelecek araç verileri burada saklanacak
        isLoading: true, // Yükleme durumu
      };
    },
    async created() {
      // URL'deki ID parametresini alıyoruz
      const carId = this.$route.params.id;
  
      try {
        // Backend'den verileri alıyoruz
        const response = await axios.get(`/api/cars/${carId}`);
        this.car = response.data;
        this.car.mainImage = this.car.images[0]; // İlk resmi ana görsel olarak ayarla
      } catch (error) {
        console.error("Araç detayları alınamadı:", error);
      } finally {
        this.isLoading = false;
      }
    },
  };
  </script>
  
  <style scoped>
  .car-detail-page {
    font-family: Arial, sans-serif;
    padding: 20px;
  }
  
  .price {
    font-size: 24px;
    font-weight: bold;
    color: #007bff;
    margin-bottom: 20px;
  }
  
  .car-images {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  
  .main-image {
    width: 100%;
    max-width: 600px;
    margin-bottom: 10px;
  }
  
  .thumbnail-images {
    display: flex;
    gap: 10px;
  }
  
  .thumbnail {
    width: 100px;
    height: 75px;
    object-fit: cover;
    cursor: pointer;
  }
  
  .technical-details {
    margin-top: 30px;
  }
  
  .technical-details h2 {
    margin-bottom: 15px;
  }
  
  .technical-details ul {
    list-style-type: none;
    padding: 0;
  }
  
  .technical-details li {
    margin-bottom: 10px;
    font-size: 16px;
  }
  </style>
  