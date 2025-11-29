<template>
  <aside class="app-sidebar">
    <nav class="sidebar-nav">
      <ul class="nav-list">
        <li class="nav-item">
          <router-link to="/dashboard" class="nav-link" active-class="active">
            <span class="nav-icon">📊</span>
            <span class="nav-text">控制台</span>
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/customers" class="nav-link" active-class="active">
            <span class="nav-icon">👥</span>
            <span class="nav-text">客户管理</span>
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/sales" class="nav-link" active-class="active">
            <span class="nav-icon">🤝</span>
            <span class="nav-text">销售管理</span>
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/marketing" class="nav-link" active-class="active">
            <span class="nav-icon">📣</span>
            <span class="nav-text">营销管理</span>
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/service" class="nav-link" active-class="active">
            <span class="nav-icon">⚙️</span>
            <span class="nav-text">服务管理</span>
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/ai" class="nav-link" active-class="active">
            <span class="nav-icon">🤖</span>
            <span class="nav-text">AI助手</span>
          </router-link>
        </li>
      </ul>
    </nav>
    
    <div class="sidebar-footer">
      <div class="user-info">
        <div class="user-avatar">👤</div>
        <div class="user-details">
          <div class="user-name">管理员</div>
          <div class="user-role">系统管理员</div>
        </div>
      </div>
    </div>
  </aside>
</template>

<script setup lang="ts">
// Sidebar组件不需要额外的逻辑
</script>

<style scoped>
.app-sidebar {
  width: 250px;
  background-color: #2c3e50;
  color: white;
  height: calc(100vh - 60px);
  position: fixed;
  top: 60px;
  left: 0;
  overflow-y: auto;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  z-index: 900;
}

.sidebar-nav {
  padding: 1rem 0;
}

.nav-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.nav-item {
  margin-bottom: 0.25rem;
}

.nav-link {
  display: flex;
  align-items: center;
  padding: 0.75rem 1.5rem;
  text-decoration: none;
  color: #ecf0f1;
  transition: all 0.2s ease;
  border-left: 3px solid transparent;
}

.nav-link:hover {
  background-color: #34495e;
  border-left-color: #3498db;
}

.nav-link.active {
  background-color: #34495e;
  border-left-color: #3498db;
  color: white;
  font-weight: 500;
}

.nav-icon {
  font-size: 1.25rem;
  margin-right: 1rem;
  width: 24px;
  text-align: center;
}

.sidebar-footer {
  padding: 1rem;
  border-top: 1px solid #34495e;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.user-avatar {
  font-size: 1.5rem;
}

.user-details {
  flex: 1;
}

.user-name {
  font-weight: 500;
  margin-bottom: 0.25rem;
}

.user-role {
  font-size: 0.875rem;
  color: #bdc3c7;
}
</style>