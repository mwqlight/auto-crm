<template>
  <div class="dashboard">
    <h2>仪表板</h2>
    <div class="dashboard-grid">
      <div class="card">
        <h3>客户总数</h3>
        <p>1,234</p>
      </div>
      <div class="card">
        <h3>本月销售额</h3>
        <p>¥567,890</p>
      </div>
      <div class="card">
        <h3>待处理工单</h3>
        <p>24</p>
      </div>
      <div class="card">
        <h3>营销活动</h3>
        <p>8</p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
// DashboardView - 仪表板页面
</script>

<style scoped>
.dashboard-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 1.5rem;
  margin-top: 1.5rem;
}

.card {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  padding: 1.5rem;
  text-align: center;
}

.card h3 {
  margin-top: 0;
  color: #666;
}

.card p {
  font-size: 2rem;
  font-weight: bold;
  margin: 0.5rem 0 0;
  color: #3498db;
}
</style>