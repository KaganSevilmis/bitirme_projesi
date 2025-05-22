<template>
  <div class="category-page">
    <h1>Kategori: {{ categoryId }}</h1>
    <p>Burada {{ categoryId }} kategorisinin ürünlerini listeleyeceğiz.</p>
    <router-link to="/">Ana Sayfaya Geri Dön</router-link>
  </div>
</template>

<script>
export default {
  name: "CategoryPage",
  props: ["categoryId"],  // Kategori ID'sini prop olarak alıyoruz
};
</script>

<style scoped>
.category-page {
  padding: 20px;
}

h1 {
  font-size: 2rem;
  color: #333;
}

p {
  font-size: 1rem;
  color: #555;
}

router-link {
  font-size: 1rem;
  color: #007bff;
  text-decoration: none;
}
</style>
