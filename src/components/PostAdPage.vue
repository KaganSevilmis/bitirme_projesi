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
        <label for="category">Kategori</label>
        <select 
          id="category"
          v-model="formData.category"
          required
          @change="handleCategoryChange"
        >
          <option value="">Kategori Seçin</option>
          <option value="VASITA">VASITA</option>
          <option value="EMLAK">EMLAK</option>
          <option value="IKINCI EL VE SIFIR ALISVERIS">IKINCI EL VE SIFIR ALISVERIS</option>
        </select>
      </div>

      <!-- VASITA kategorisi için özel alanlar -->
      <div v-if="formData.category === 'VASITA'" class="category-specific-fields">
        <div class="form-group">
          <label for="brand">Marka</label>
          <input type="text" id="brand" v-model="formData.details.brand" required placeholder="Örn: Audi" />
        </div>
        <div class="form-group">
          <label for="model">Model</label>
          <input type="text" id="model" v-model="formData.details.model" required placeholder="Örn: A4" />
        </div>
        <div class="form-group">
          <label for="year">Yıl</label>
          <input type="number" id="year" v-model="formData.details.year" required placeholder="Örn: 2020" />
        </div>
        <div class="form-group">
          <label for="fuel">Yakıt</label>
          <select id="fuel" v-model="formData.details.fuel" required>
            <option value="">Seçiniz</option>
            <option value="Benzin">Benzin</option>
            <option value="Dizel">Dizel</option>
            <option value="LPG">LPG</option>
            <option value="Elektrik">Elektrik</option>
            <option value="Hibrit">Hibrit</option>
          </select>
        </div>
        <div class="form-group">
          <label for="transmission">Vites</label>
          <select id="transmission" v-model="formData.details.transmission" required>
            <option value="">Seçiniz</option>
            <option value="Manuel">Manuel</option>
            <option value="Otomatik">Otomatik</option>
            <option value="Yarı Otomatik">Yarı Otomatik</option>
          </select>
        </div>
        <div class="form-group">
          <label for="km">Kilometre</label>
          <input type="number" id="km" v-model="formData.details.km" required placeholder="Örn: 50000" />
        </div>
        <div class="form-group">
          <label for="enginePower">Motor Gücü (HP)</label>
          <input type="number" id="enginePower" v-model="formData.details.enginePower" required placeholder="Örn: 150" />
        </div>
      </div>

      <!-- EMLAK kategorisi için özel alanlar -->
      <div v-if="formData.category === 'EMLAK'" class="category-specific-fields">
        <div class="form-group">
          <label for="propertyType">Emlak Tipi</label>
          <select id="propertyType" v-model="formData.details.propertyType" required>
            <option value="">Seçiniz</option>
            <option value="Daire">Daire</option>
            <option value="Villa">Villa</option>
            <option value="Müstakil Ev">Müstakil Ev</option>
            <option value="Arsa">Arsa</option>
            <option value="İş Yeri">İş Yeri</option>
          </select>
        </div>
        <div class="form-group">
          <label for="squareMeters">Metrekare</label>
          <input type="number" id="squareMeters" v-model="formData.details.squareMeters" required placeholder="Örn: 120" />
        </div>
        <div class="form-group">
          <label for="rooms">Oda Sayısı</label>
          <select id="rooms" v-model="formData.details.rooms" required>
            <option value="">Seçiniz</option>
            <option value="1+0">1+0</option>
            <option value="1+1">1+1</option>
            <option value="2+1">2+1</option>
            <option value="3+1">3+1</option>
            <option value="4+1">4+1</option>
            <option value="5+">5+</option>
          </select>
        </div>
        <div class="form-group">
          <label for="floor">Bulunduğu Kat</label>
          <input type="number" id="floor" v-model="formData.details.floor" required placeholder="Örn: 3" />
        </div>
        <div class="form-group">
          <label for="buildingAge">Bina Yaşı</label>
          <input type="number" id="buildingAge" v-model="formData.details.buildingAge" required placeholder="Örn: 5" />
        </div>
        <div class="form-group">
          <label for="heating">Isıtma</label>
          <select id="heating" v-model="formData.details.heating" required>
            <option value="">Seçiniz</option>
            <option value="Doğalgaz">Doğalgaz</option>
            <option value="Merkezi">Merkezi</option>
            <option value="Soba">Soba</option>
            <option value="Klima">Klima</option>
          </select>
        </div>
      </div>

      <!-- İKİNCİ EL VE SIFIR ALIŞVERİŞ kategorisi için özel alanlar -->
      <div v-if="formData.category === 'IKINCI EL VE SIFIR ALISVERIS'" class="category-specific-fields">
        <div class="form-group">
          <label for="condition">Durumu</label>
          <select id="condition" v-model="formData.details.condition" required>
            <option value="">Seçiniz</option>
            <option value="Sıfır">Sıfır</option>
            <option value="İkinci El">İkinci El</option>
          </select>
        </div>
        <div class="form-group">
          <label for="brand">Marka</label>
          <input type="text" id="brand" v-model="formData.details.brand" required placeholder="Ürün markası" />
        </div>
        <div class="form-group">
          <label for="warranty">Garanti Durumu</label>
          <select id="warranty" v-model="formData.details.warranty" required>
            <option value="">Seçiniz</option>
            <option value="Var">Var</option>
            <option value="Yok">Yok</option>
          </select>
        </div>
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
        category: '',
        details: {}
      },
      loading: false,
      error: null,
      successMessage: null
    };
  },
  methods: {
    handleCategoryChange() {
      // Kategori değiştiğinde details objesini sıfırla
      this.formData.details = {};
    },

    generateDetailedDescription() {
      let detailedDescription = '';
      
      if (this.formData.category === 'VASITA') {
        detailedDescription = `ARAÇ DETAYLARI\n`;
        detailedDescription += `Marka: ${this.formData.details.brand}\n`;
        detailedDescription += `Model: ${this.formData.details.model}\n`;
        detailedDescription += `Yıl: ${this.formData.details.year}\n`;
        detailedDescription += `Yakıt: ${this.formData.details.fuel}\n`;
        detailedDescription += `Vites: ${this.formData.details.transmission}\n`;
        detailedDescription += `Kilometre: ${this.formData.details.km} km\n`;
        detailedDescription += `Motor Gücü: ${this.formData.details.enginePower} HP`;
      } 
      else if (this.formData.category === 'EMLAK') {
        detailedDescription = `EMLAK DETAYLARI\n`;
        detailedDescription += `Emlak Tipi: ${this.formData.details.propertyType}\n`;
        detailedDescription += `Metrekare: ${this.formData.details.squareMeters} m²\n`;
        detailedDescription += `Oda Sayısı: ${this.formData.details.rooms}\n`;
        detailedDescription += `Bulunduğu Kat: ${this.formData.details.floor}\n`;
        detailedDescription += `Bina Yaşı: ${this.formData.details.buildingAge}\n`;
        detailedDescription += `Isıtma: ${this.formData.details.heating}`;
      }
      else if (this.formData.category === 'IKINCI EL VE SIFIR ALISVERIS') {
        detailedDescription = `ÜRÜN DETAYLARI\n`;
        detailedDescription += `Durumu: ${this.formData.details.condition}\n`;
        detailedDescription += `Marka: ${this.formData.details.brand}\n`;
        detailedDescription += `Garanti Durumu: ${this.formData.details.warranty}`;
      }

      return detailedDescription;
    },

    async handleSubmit() {
      this.loading = true;
      this.error = null;
      this.successMessage = null;

      try {
        // Detaylı açıklamayı oluştur
        const detailedDescription = this.generateDetailedDescription();
        
        // Form verilerini hazırla
        const postData = {
          ...this.formData,
          description: detailedDescription
        };

        await axios.post('http://localhost:7000/api/products/new', postData);
        
        this.successMessage = 'İlanınız başarıyla eklendi! Ana sayfaya yönlendiriliyorsunuz...';
        
        // Form verilerini temizle
        this.formData = {
          name: '',
          description: '',
          price: '',
          imageUrl: '',
          location: '',
          category: '',
          details: {}
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
  max-width: 800px;
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

.category-specific-fields {
  background-color: #f8f9fa;
  padding: 20px;
  border-radius: 8px;
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
  