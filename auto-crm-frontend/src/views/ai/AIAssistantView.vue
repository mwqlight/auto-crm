<template>
  <div class="ai-assistant-view">
    <div class="view-header">
      <h1>AI智能助手</h1>
      <div class="header-actions">
        <Button variant="primary" @click="runAnalysis">运行全面分析</Button>
        <Button variant="secondary" @click="viewHistory">分析历史</Button>
      </div>
    </div>
    
    <div class="ai-dashboard">
      <AIInsights />
    </div>
    
    <div class="analysis-section">
      <div class="section-header">
        <h2>智能分析</h2>
        <div class="section-actions">
          <select class="filter-select" v-model="analysisType">
            <option value="sales">销售预测</option>
            <option value="customers">客户风险</option>
            <option value="marketing">营销建议</option>
            <option value="service">服务优化</option>
          </select>
          <Button variant="outline" @click="runSpecificAnalysis">运行分析</Button>
        </div>
      </div>
      
      <div class="analysis-results">
        <Card>
          <template #header>
            <h3>分析结果</h3>
          </template>
          <div class="result-content">
            <div class="result-placeholder">
              <p>AI分析结果将在此显示</p>
              <p>选择分析类型并点击"运行分析"开始</p>
            </div>
          </div>
        </Card>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import Card from '../../components/base/Card.vue'
import Button from '../../components/base/Button.vue'
import AIInsights from '../../components/ai/AIInsights.vue'

const analysisType = ref('sales')

const runAnalysis = () => {
  alert('运行全面分析功能待实现')
}

const viewHistory = () => {
  alert('查看分析历史功能待实现')
}

const runSpecificAnalysis = () => {
  alert(`运行${analysisType.value}分析功能待实现`)
}
</script>

<style scoped>
.ai-assistant-view {
  padding: 1rem;
}

.view-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
}

.view-header h1 {
  margin: 0;
}

.header-actions {
  display: flex;
  gap: 0.5rem;
}

.ai-dashboard {
  margin-bottom: 2rem;
}

.analysis-section {
  background-color: white;
  border-radius: 0.5rem;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 1.5rem;
}

.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
}

.section-header h2 {
  margin: 0;
}

.section-actions {
  display: flex;
  gap: 1rem;
  align-items: center;
}

.filter-select {
  padding: 0.5rem;
  border: 1px solid #ddd;
  border-radius: 0.375rem;
}

.result-content {
  min-height: 300px;
}

.result-placeholder {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 300px;
  color: #666;
}

.result-placeholder p:first-child {
  font-size: 1.125rem;
  margin-bottom: 0.5rem;
}
</style>