<template>
    <div class="post-ad-page">
      <h2>Ücretsiz İlan Ver</h2>
      <form>
        <input type="text" placeholder="İlan Başlığı" />
        <textarea placeholder="İlan Açıklaması"></textarea>
        <button type="submit">İlan Ver</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    name: "PostAdPage",
  };
  </script>
  
  <style scoped>
  .post-ad-page {
    padding: 20px;
    font-family: Arial, sans-serif;
  }
  
  form {
    display: flex;
    flex-direction: column;
  }
  
  input, textarea {
    margin: 10px 0;
    padding: 10px;
    font-size: 16px;
    border-radius: 5px;
  }
  
  button {
    padding: 10px 20px;
    background-color: #050505;
    color: #ffc107;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #f7e6a3;
  }
  </style>
  