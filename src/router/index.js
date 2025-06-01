import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../components/HomePage.vue';
import LoginPage from '../components/LoginPage.vue';
import RegisterPage from '../components/RegisterPage.vue';
import PostAdPage from '../components/PostAdPage.vue';
import CategoryPage from '../components/CategoryPage.vue';  // CategoryPage bileşenini içe aktarın
import SearchResults from '../components/SearchResults.vue';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomePage,
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginPage,
  },
  {
    path: '/register',
    name: 'Register',
    component: RegisterPage,
  },
  {
    path: '/post-ad',
    name: 'PostAd',
    component: PostAdPage,
  },
  {
    path: '/category/:categoryId',  // Dinamik kategori sayfası
    name: 'CategoryPage',
    component: CategoryPage,
    props: true,  // URL parametrelerini prop olarak ilet
  },
  {
    path: '/search',
    name: 'SearchResults',
    component: SearchResults,
  },
  {
    path: '/product/:id',
    name: 'ProductDetail',
    component: () => import('../components/CarDetailPage.vue'),
    props: true
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
