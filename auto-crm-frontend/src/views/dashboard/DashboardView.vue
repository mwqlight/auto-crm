<template>
  <div class="dashboard">
    <div class="dashboard-header">
      <h1>控制台</h1>
      <div class="date-range">
        <span>2023年3月1日 - 2023年3月31日</span>
        <Button variant="secondary" size="small">自定义日期</Button>
      </div>
    </div>
    
    <div class="dashboard-stats">
      <div class="stat-card">
        <div class="stat-icon revenue">💰</div>
        <div class="stat-info">
          <h3>¥1,245,678</h3>
          <p>总营收</p>
          <span class="trend positive">↑ 12.5%</span>
        </div>
      </div>
      
      <div class="stat-card">
        <div class="stat-icon customers">👥</div>
        <div class="stat-info">
          <h3>1,248</h3>
          <p>新增客户</p>
          <span class="trend positive">↑ 8.3%</span>
        </div>
      </div>
      
      <div class="stat-card">
        <div class="stat-icon deals">🤝</div>
        <div class="stat-info">
          <h3>86</h3>
          <p>成交订单</p>
          <span class="trend negative">↓ 2.4%</span>
        </div>
      </div>
      
      <div class="stat-card">
        <div class="stat-icon conversion">📊</div>
        <div class="stat-info">
          <h3>24.5%</h3>
          <p>转化率</p>
          <span class="trend positive">↑ 3.1%</span>
        </div>
      </div>
    </div>
    
    <div class="dashboard-charts">
      <Card class="chart-container">
        <template #header>
          <h3>销售趋势</h3>
          <div class="chart-controls">
            <button class="control-btn active">月度</button>
            <button class="control-btn">季度</button>
            <button class="control-btn">年度</button>
          </div>
        </template>
        <div class="chart-placeholder">
          <p>销售趋势图表区域</p>
        </div>
      </Card>
      
      <Card class="chart-container">
        <template #header>
          <h3>客户分布</h3>
        </template>
        <div class="chart-placeholder">
          <p>客户分布图表区域</p>
        </div>
      </Card>
    </div>
    
    <div class="dashboard-recent">
      <Card class="recent-activities">
        <template #header>
          <h3>最近活动</h3>
        </template>
        <div class="activity-list">
          <div class="activity-item">
            <div class="activity-icon">📝</div>
            <div class="activity-content">
              <p><strong>张三</strong> 创建了新的销售机会</p>
              <p class="activity-time">10分钟前</p>
            </div>
          </div>
          
          <div class="activity-item">
            <div class="activity-icon">📞</div>
            <div class="activity-content">
              <p><strong>李四</strong> 完成了客户电话回访</p>
              <p class="activity-time">1小时前</p>
            </div>
          </div>
          
          <div class="activity-item">
            <div class="activity-icon">📧</div>
            <div class="activity-content">
              <p><strong>王五</strong> 发送了营销邮件</p>
              <p class="activity-time">2小时前</p>
            </div>
          </div>
          
          <div class="activity-item">
            <div class="activity-icon">✅</div>
            <div class="activity-content">
              <p><strong>赵六</strong> 解决了服务工单</p>
              <p class="activity-time">3小时前</p>
            </div>
          </div>
        </div>
      </Card>
    </div>
  </div>
</template>

<script setup lang="ts">
import Card from '../../components/base/Card.vue'
import Button from '../../components/base/Button.vue'
</script>

<style scoped>
.dashboard {
  padding: 1rem;
}

.dashboard-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
}

.dashboard-header h1 {
  margin: 0;
}

.date-range {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.dashboard-stats {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
  gap: 1rem;
  margin-bottom: 1.5rem;
}

.stat-card {
  display: flex;
  align-items: center;
  padding: 1.5rem;
  background-color: white;
  border-radius: 0.5rem;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.stat-icon {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1.5rem;
  margin-right: 1rem;
}

.stat-icon.revenue {
  background-color: #fff3cd;
  color: #856404;
}

.stat-icon.customers {
  background-color: #cce5ff;
  color: #004085;
}

.stat-icon.deals {
  background-color: #d4edda;
  color: #155724;
}

.stat-icon.conversion {
  background-color: #f8d7da;
  color: #721c24;
}

.stat-info h3 {
  margin: 0 0 0.25rem 0;
  font-size: 1.5rem;
}

.stat-info p {
  margin: 0 0 0.5rem 0;
  color: #666;
}

.trend {
  font-size: 0.875rem;
  font-weight: 500;
}

.trend.positive {
  color: #28a745;
}

.trend.negative {
  color: #dc3545;
}

.dashboard-charts {
  display: grid;
  grid-template-columns: 2fr 1fr;
  gap: 1rem;
  margin-bottom: 1.5rem;
}

.chart-container {
  height: 100%;
}

.chart-container :deep(.card-header) {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.chart-controls {
  display: flex;
  gap: 0.5rem;
}

.control-btn {
  padding: 0.25rem 0.75rem;
  border: 1px solid #ddd;
  background: white;
  border-radius: 0.25rem;
  cursor: pointer;
}

.control-btn.active {
  background-color: #0d6efd;
  color: white;
  border-color: #0d6efd;
}

.chart-placeholder {
  height: 300px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f8f9fa;
  border-radius: 0.375rem;
  color: #666;
}

.dashboard-recent .activity-list {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.activity-item {
  display: flex;
  align-items: center;
  gap: 1rem;
  padding: 1rem;
  border-radius: 0.375rem;
  background-color: #f8f9fa;
}

.activity-icon {
  font-size: 1.5rem;
}

.activity-content p {
  margin: 0 0 0.25rem 0;
}

.activity-content .activity-time {
  font-size: 0.875rem;
  color: #666;
}

@media (max-width: 768px) {
  .dashboard-charts {
    grid-template-columns: 1fr;
  }
  
  .dashboard-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 1rem;
  }
}
</style>