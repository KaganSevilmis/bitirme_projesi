<template>
    <div>
      <h1>Ana Sayfa</h1>
      <p>Hoş geldiniz! Kategorilerden birini seçin:</p>
  
      <!-- Örnek Kategoriler -->
      <div class="categories">
        <router-link to="/category/1">Elektronik</router-link>
        <router-link to="/category/2">Mobilya</router-link>
        <router-link to="/category/3">Kırtasiye</router-link>
      </div>
  
      <!-- Örnek Ürünler -->
      <div class="products">
        <router-link to="/product/1">iPhone 13 Pro Max</router-link>
        <router-link to="/product/2">Volkswagen Passat 2020</router-link>
        <router-link to="/product/3">Sony PlayStation 5</router-link>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'HomePage',
  };
  </script>
  
  <style scoped>
 
  </style>
  