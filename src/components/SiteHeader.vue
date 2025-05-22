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
      <router-link to="/login">
        <button>Giriş Yap</button>
      </router-link>
      <router-link to="/register">
        <button>Üye Ol</button>
      </router-link>
      <router-link to="/post-ad">
        <button>Ücretsiz İlan Ver</button>
      </router-link>
    </div>
  </header>
</template>

<script>
export default {
  name: "SiteHeader",
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
}

.logo a {
text-decoration: none;
}

.user-actions button:hover {
  background-color: #f7e6a3;
}
</style>
