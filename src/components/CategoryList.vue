<template>
    <div class="category-list">
      <ul>
        <li v-for="category in categories" :key="category.name" class="category-item">
          <div class="category-header">
            <img :src="category.icon" alt="icon" class="category-icon" />
            <router-link :to="{ name: 'CategoryPage', params: { categoryId: category.name } }">
              <span>{{ category.name }}</span>
            </router-link>
            <span class="item-count">({{ category.count }})</span>
          </div>
          <ul v-if="category.subcategories.length > 0" class="subcategory-list">
            <li v-for="sub in category.subcategories" :key="sub.name">
              <router-link :to="{ name: 'CategoryPage', params: { categoryId: sub.name } }">
                {{ sub.name }}
              </router-link>
              <span class="item-count">({{ sub.count }})</span>
            </li>
          </ul>
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
          {
            name: "Emlak",
            icon: "https://via.placeholder.com/24",
            count: 4566521,
            subcategories: [
              { name: "Konut", count: 722058 },
              { name: "İş Yeri", count: 132569 },
              { name: "Arsa", count: 279641 },
              { name: "Konut Projeleri", count: 1319 },
              { name: "Bina", count: 9510 },
            ],
          },
          {
            name: "Vasıta",
            icon: "https://via.placeholder.com/24",
            count: 838224,
            subcategories: [
              { name: "Otomobil", count: 432445 },
              { name: "Arazi, SUV & Pickup", count: 90934 },
              { name: "Motosiklet", count: 125843 },
              { name: "Minivan & Panelvan", count: 195236 },
              { name: "Ticari Araçlar", count: 45695 },
              { name: "Elektrikli Araçlar", count: 5463 },
              { name: "Deniz Araçları", count: 7247 },
            ],
          },
          {
            name: "İkinci El ve Sıfır Alışveriş",
            icon: "https://via.placeholder.com/24",
            count: 4236458,
            subcategories: [
              { name: "Bilgisayar", count: 432445 },
              { name: "Cep Telefonu", count: 90934 },
              { name: "Fotoğraf & Kamera", count: 125843 },
              { name: "Ev Elektroniği", count: 195236 },
              { name: "Saat", count: 45695 },
              { name: "Hobi & Oyuncak", count: 5463 },
              { name: "Spor", count: 7247 },
            ],
          },
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
  }
  
  .category-header {
    display: flex;
    align-items: center;
    font-weight: bold;
    font-size: 16px;
    color: #333;
  }
  
  .category-icon {
    width: 24px;
    height: 24px;
    margin-right: 10px;
  }
  
  .item-count {
    margin-left: auto;
    color: #777;
  }
  
  .subcategory-list {
    padding-left: 25px;
    margin-top: 5px;
  }
  
  .subcategory-list li {
    margin-bottom: 5px;
    font-size: 14px;
    color: #555;
  }

  .category-header a,
  .subcategory-list li a {
  text-decoration: none;
}
  
  .subcategory-list .item-count {
    margin-left: 5px;
    color: #999;
  }
  </style>
  