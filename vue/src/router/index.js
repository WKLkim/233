import { createRouter, createWebHistory } from 'vue-router'
// import Home from '../views/Home.vue'
import Login from "../views/Login";
//import Showperson from "../views/Showperson";

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import(/* webpackChunkName: "home" */ '../views/Home.vue')
  },
  {
    path: '/test',
    name: 'Test',
    component: () => import(/* webpackChunkName: "home" */ '../views/Test.vue')
  },
  {
    path: '/showperson',
    name: 'Showperson',
    component: () => import(/* webpackChunkName: "home" */ '../views/Showperson.vue')
  },
  // {
  //   path: '/login',
  //   name: 'Login',
  //   component: () => import(/* webpackChunkName: "home" */ '../views/Login.vue')
  // },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router
