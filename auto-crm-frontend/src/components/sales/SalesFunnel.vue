<template>
  <div class="sales-funnel">
    <div class="funnel-header">
      <h3>3D销售漏斗</h3>
      <div class="funnel-controls">
        <select v-model="selectedPeriod">
          <option value="month">本月</option>
          <option value="quarter">本季度</option>
          <option value="year">本年</option>
        </select>
      </div>
    </div>
    
    <div class="funnel-chart">
      <div class="funnel-stage" v-for="(stage, index) in funnelStages" :key="stage.name">
        <div 
          class="funnel-bar" 
          :style="{ 
            width: `${stage.percentage}%`,
            backgroundColor: stage.color,
            height: `${100 - index * 15}px`
          }"
        >
          <div class="stage-label">
            <div class="stage-name">{{ stage.name }}</div>
            <div class="stage-value">¥{{ stage.value.toLocaleString() }}</div>
            <div class="stage-count">{{ stage.count }}个机会</div>
          </div>
        </div>
      </div>
    </div>
    
    <div class="funnel-stats">
      <div class="stat-card">
        <div class="stat-value">¥1,250,000</div>
        <div class="stat-label">预期收入</div>
      </div>
      <div class="stat-card">
        <div class="stat-value">42%</div>
        <div class="stat-label">转化率</div>
      </div>
      <div class="stat-card">
        <div class="stat-value">24</div>
        <div class="stat-label">销售机会</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'

const selectedPeriod = ref('month')

// 销售漏斗阶段数据
const funnelStages = ref([
  { name: '初步接触', value: 1250000, count: 24, percentage: 100, color: '#3498db' },
  { name: '需求分析', value: 875000, count: 18, percentage: 70, color: '#2ecc71' },
  { name: '方案报价', value: 500000, count: 12, percentage: 40, color: '#f1c40f' },
  { name: '商务谈判', value: 250000, count: 6, percentage: 20, color: '#e67e22' },
  { name: '成交', value: 125000, count: 3, percentage: 10, color: '#e74c3c' }
])
</script>

<style scoped>
.sales-funnel {
  padding: 1rem;
}

.funnel-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
}

.funnel-header h3 {
  margin: 0;
}

.funnel-controls select {
  padding: 0.5rem;
  border-radius: 0.375rem;
  border: 1px solid #ddd;
}

.funnel-chart {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
  margin-bottom: 2rem;
}

.funnel-stage {
  width: 100%;
  display: flex;
  justify-content: center;
}

.funnel-bar {
  position: relative;
  border-radius: 0.5rem;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.3s ease;
}

.stage-label {
  text-align: center;
  color: white;
  text-shadow: 1px 1px 2px rgba(0,0,0,0.5);
}

.stage-name {
  font-weight: bold;
  font-size: 1rem;
}

.stage-value {
  font-size: 0.875rem;
  margin: 0.25rem 0;
}

.stage-count {
  font-size: 0.75rem;
}

.funnel-stats {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
  gap: 1rem;
}

.stat-card {
  background-color: #f8f9fa;
  border-radius: 0.375rem;
  padding: 1rem;
  text-align: center;
}

.stat-value {
  font-size: 1.5rem;
  font-weight: bold;
  color: #3498db;
  margin-bottom: 0.5rem;
}

.stat-label {
  color: #666;
  font-size: 0.875rem;
}
</style>