<template>
    <div class="login-page">
      <h2>Giriş Yap</h2>
      <form>
        <input type="text" placeholder="Kullanıcı Adı" />
        <input type="password" placeholder="Şifre" />
        <button type="submit">Giriş Yap</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    name: "LoginPage",
  };
  </script>
  
  <style scoped>
  .login-page {
    padding: 20px;
    font-family: Arial, sans-serif;
  }
  
  form {
    display: flex;
    flex-direction: column;
  }
  
  input {
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
  