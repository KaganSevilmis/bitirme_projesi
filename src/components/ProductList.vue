<template>
    <div class="product-list">
      <h3>Ürünler</h3>
      <div class="product" v-for="product in products" :key="product.id">
        <img :src="product.image" alt="product" />
        <h4>{{ product.name }}</h4>
        <p>{{ product.price }} TL</p>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "ProductList",
    data() {
      return {
        products: [
          {
            id: 1,
            name: "Volkswagen Passat 2020",
            price: 750000,
            image: "https://via.placeholder.com/150",
          },
          {
            id: 2,
            name: "iPhone 13 Pro Max",
            price: 35000,
            image: "https://via.placeholder.com/150",
          },
          {
            id: 3,
            name: "Sony PlayStation 5",
            price: 18000,
            image: "https://via.placeholder.com/150",
          },
        ],
      };
    },
  };
  </script>
  
  <style>
  .product-list {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    padding: 15px;
  }
  
  .product {
    border: 1px solid #ddd;
    padding: 10px;
    width: calc(33% - 20px);
    text-align: center;
    background-color: #f8f8f8;
  }
  
  .product img {
    max-width: 100%;
    height: auto;
  }
  
  .product h4 {
    margin: 10px 0;
  }
  
  .product p {
    color: #ff5722;
    font-weight: bold;
  }
  </style>
  