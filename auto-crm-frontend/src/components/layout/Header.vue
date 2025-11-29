<template>
  <header class="app-header">
    <div class="header-left">
      <div class="logo">
        <h1>智能CRM系统</h1>
      </div>
      <nav class="main-nav">
        <router-link to="/dashboard" class="nav-item">控制台</router-link>
        <router-link to="/customers" class="nav-item">客户</router-link>
        <router-link to="/sales" class="nav-item">销售</router-link>
        <router-link to="/marketing" class="nav-item">营销</router-link>
        <router-link to="/service" class="nav-item">服务</router-link>
        <router-link to="/ai" class="nav-item">AI助手</router-link>
      </nav>
    </div>
    
    <div class="header-right">
      <div class="search-box">
        <input type="text" placeholder="全局搜索..." class="search-input" />
      </div>
      <div class="user-actions">
        <button class="action-button notification">
          <span>🔔</span>
          <span class="badge">3</span>
        </button>
        <button class="action-button user-profile">
          <span>👤</span>
          <span>管理员</span>
        </button>
      </div>
    </div>
  </header>
</template>

<script setup lang="ts">
// Header组件不需要额外的逻辑
</script>

<style scoped>
.app-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 1.5rem;
  height: 60px;
  background-color: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
}

.header-left {
  display: flex;
  align-items: center;
  gap: 2rem;
}

.logo h1 {
  margin: 0;
  font-size: 1.5rem;
  color: #3498db;
}

.main-nav {
  display: flex;
  gap: 1.5rem;
}

.nav-item {
  text-decoration: none;
  color: #333;
  font-weight: 500;
  padding: 0.5rem 1rem;
  border-radius: 0.375rem;
  transition: all 0.2s ease;
}

.nav-item:hover,
.nav-item.router-link-active {
  background-color: #3498db;
  color: white;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 1.5rem;
}

.search-box {
  position: relative;
}

.search-input {
  padding: 0.5rem 1rem 0.5rem 2rem;
  border: 1px solid #ddd;
  border-radius: 1.5rem;
  font-size: 0.875rem;
}

.search-input:focus {
  outline: none;
  border-color: #3498db;
  box-shadow: 0 0 0 2px rgba(52, 152, 219, 0.2);
}

.search-box::before {
  content: "🔍";
  position: absolute;
  left: 0.75rem;
  top: 50%;
  transform: translateY(-50%);
}

.user-actions {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.action-button {
  background: none;
  border: none;
  cursor: pointer;
  position: relative;
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.5rem;
  border-radius: 0.375rem;
  transition: background-color 0.2s ease;
}

.action-button:hover {
  background-color: #f5f5f5;
}

.badge {
  position: absolute;
  top: -5px;
  right: -5px;
  background-color: #e74c3c;
  color: white;
  font-size: 0.75rem;
  padding: 0.125rem 0.375rem;
  border-radius: 1rem;
}
</style>