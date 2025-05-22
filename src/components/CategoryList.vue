<template>
    <div class="category-list">
      <ul>
        <li v-for="category in categories" :key="category.name" class="category-item">
          <router-link :to="{ name: 'CategoryPage', params: { categoryId: category.name } }">
            {{ category.name }}
          </router-link>
        </li>
      </ul>
    </div>
</template>

<script>
export default {
  name: "CategoryList",
  data() {
    return {
      categories: [
        { name: "VASITA" },
        { name: "EMLAK" },
        { name: "IKINCI EL VE SIFIR ALISVERIS" }
      ],
    };
  },
};
</script>

<style scoped>
.category-list {
  padding: 15px;
  background-color: #f8f9fa;
  font-family: Arial, sans-serif;
}

.category-item {
  margin-bottom: 15px;
  font-size: 16px;
}

.category-item a {
  text-decoration: none;
  color: #333;
}

.category-item a:hover {
  color: #007bff;
}
</style>
