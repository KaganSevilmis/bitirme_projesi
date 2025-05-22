<template>
  <header class="site-header">
    <!-- Logo -->
    <div class="logo">
      <router-link to="/">sarrafından.com</router-link>
    </div>

    <!-- Arama Çubuğu -->
    <input
      type="text"
      class="search-bar"
      placeholder="Aramak istediğiniz ürünü yazın..."
    />

    <!-- Kullanıcı İşlemleri -->
    <div class="user-actions">
      <template v-if="!isAuthenticated">
        <router-link to="/login">
          <button>Giriş Yap</button>
        </router-link>
        <router-link to="/register">
          <button>Üye Ol</button>
        </router-link>
      </template>
      <template v-else>
        <router-link to="/post-ad">
          <button>Ücretsiz İlan Ver</button>
        </router-link>
        <button @click="handleLogout" class="logout-button">Çıkış Yap</button>
      </template>
    </div>
  </header>
</template>

<script>
import { auth } from '../store/auth';

export default {
  name: "SiteHeader",
  data() {
    return {
      isAuthenticated: false
    };
  },
  created() {
    // Sayfa yüklendiğinde auth durumunu kontrol et
    this.isAuthenticated = auth.checkAuth();
    
    // Auth durumu değişikliklerini dinle
    setInterval(() => {
      this.isAuthenticated = auth.state.isAuthenticated;
    }, 1000);
  },
  methods: {
    handleLogout() {
      auth.logout();
      this.$router.push('/login');
    }
  }
};
</script>

<style scoped>
.site-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px;
  background-color: #ffc107;
  color: rgb(12, 12, 12);
  font-family: Arial, sans-serif;
}

.logo {
  font-size: 24px;
  font-weight: bold;
}

/* Alt çizgiyi tüm router-link'ler için kaldırıyoruz */
.router-link {
  text-decoration: none;
}

.logo a {
  text-decoration: none;
  color: inherit;
}

.search-bar {
  width: 40%;
  padding: 10px;
  border-radius: 5px;
  border: none;
}

.user-actions {
  display: flex;
  gap: 10px;
}

.user-actions button {
  padding: 10px 20px;
  background-color: #050505;
  color: #ffc107;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.user-actions button:hover {
  background-color: #f7e6a3;
  color: #050505;
}

.logout-button {
  background-color: #dc3545 !important;
}

.logout-button:hover {
  background-color: #c82333 !important;
  color: #ffc107 !important;
}
</style>
