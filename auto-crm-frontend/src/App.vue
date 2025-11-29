<template>
  <div id="app">
    <Header />
    <div class="main-layout">
      <Sidebar />
      <MainContent />
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'
import Header from './components/layout/Header.vue'
import Sidebar from './components/layout/Sidebar.vue'
import MainContent from './components/layout/MainContent.vue'

// 检查是否有token，如果没有则重定向到登录页
const router = useRouter()

onMounted(() => {
  const token = localStorage.getItem('authToken')
  if (!token && router.currentRoute.value.path !== '/login') {
    router.push('/login')
  }
})
</script>

<style>
/* 全局样式已移至 src/assets/main.css */
</style>