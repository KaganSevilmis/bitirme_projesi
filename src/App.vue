<template>
  <div id="app">
    <router-view></router-view> <!-- Burası çok önemli -->
    <SiteHeader />
    <div class="main-content">
      <CategoryList />
      <ProductList />
    </div>
  </div>
</template>

<script>
import SiteHeader from "./components/SiteHeader.vue";
import CategoryList from "./components/CategoryList.vue";
import ProductList from "./components/ProductList.vue";

export default {
  name: "App",
  components: {
    SiteHeader,
    CategoryList,
    ProductList,
  },
};
</script>

<style>
#app {
  font-family: Arial, sans-serif;
}

.main-content {
  display: flex;
}
</style>
