<template>
  <div class="ai-insights">
    <div class="insights-header">
      <h3>AI智能洞察</h3>
      <div class="insights-controls">
        <select v-model="selectedPeriod" class="period-selector">
          <option value="day">今日</option>
          <option value="week">本周</option>
          <option value="month">本月</option>
        </select>
        <Button variant="primary" @click="refreshInsights">刷新洞察</Button>
      </div>
    </div>
    
    <div class="insights-grid">
      <Card class="insight-card">
        <div class="insight-icon">📈</div>
        <div class="insight-content">
          <h4>销售预测</h4>
          <p class="insight-value">¥1,245,000</p>
          <p class="insight-change positive">↑ 12.5% vs 上期</p>
        </div>
      </Card>
      
      <Card class="insight-card">
        <div class="insight-icon">👥</div>
        <div class="insight-content">
          <h4>客户流失风险</h4>
          <p class="insight-value">8位高风险客户</p>
          <p class="insight-change negative">⚠ 需要立即关注</p>
        </div>
      </Card>
      
      <Card class="insight-card">
        <div class="insight-icon">🎯</div>
        <div class="insight-content">
          <h4>营销建议</h4>
          <p class="insight-value">3个潜在机会</p>
          <p class="insight-change">基于客户行为分析</p>
        </div>
      </Card>
      
      <Card class="insight-card">
        <div class="insight-icon">⚙️</div>
        <div class="insight-content">
          <h4>服务优化</h4>
          <p class="insight-value">平均响应时间</p>
          <p class="insight-change positive">↓ 15% 改善</p>
        </div>
      </Card>
    </div>
    
    <div class="insights-chart">
      <Card header="关键指标趋势">
        <div class="chart-placeholder">
          <p>AI分析图表区域</p>
          <p>显示销售预测、客户满意度等关键指标的历史趋势</p>
        </div>
      </Card>
    </div>
    
    <div class="insights-recommendations">
      <Card header="个性化建议">
        <div class="recommendation-list">
          <div class="recommendation-item">
            <div class="recommendation-icon">🔔</div>
            <div class="recommendation-content">
              <h5>客户跟进提醒</h5>
              <p>ABC科技有限公司已有30天未联系，建议安排一次回访</p>
            </div>
          </div>
          
          <div class="recommendation-item">
            <div class="recommendation-icon">💰</div>
            <div class="recommendation-content">
              <h5>交叉销售机会</h5>
              <p>XYZ制造公司可能对您的新产品感兴趣</p>
            </div>
          </div>
          
          <div class="recommendation-item">
            <div class="recommendation-icon">📅</div>
            <div class="recommendation-content">
              <h5>合同续约预警</h5>
              <p>DEF集团的合同将在45天后到期，请提前准备续约谈判</p>
            </div>
          </div>
        </div>
      </Card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import Card from '../base/Card.vue'
import Button from '../base/Button.vue'

const selectedPeriod = ref('week')

const refreshInsights = () => {
  alert('刷新AI洞察功能待实现')
}
</script>

<style scoped>
.ai-insights {
  padding: 1rem;
}

.insights-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
}

.insights-header h3 {
  margin: 0;
}

.insights-controls {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.period-selector {
  padding: 0.5rem;
  border-radius: 0.375rem;
  border: 1px solid #ddd;
}

.insights-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 1rem;
  margin-bottom: 1.5rem;
}

.insight-card {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.insight-icon {
  font-size: 2rem;
}

.insight-content h4 {
  margin: 0 0 0.5rem 0;
}

.insight-value {
  font-size: 1.25rem;
  font-weight: bold;
  margin: 0 0 0.25rem 0;
}

.insight-change {
  margin: 0;
  font-size: 0.875rem;
}

.positive {
  color: #28a745;
}

.negative {
  color: #dc3545;
}

.insights-chart {
  margin-bottom: 1.5rem;
}

.chart-placeholder {
  text-align: center;
  padding: 2rem;
  color: #666;
}

.chart-placeholder p:first-child {
  font-size: 1.125rem;
  margin-bottom: 0.5rem;
}

.insights-recommendations .recommendation-list {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.recommendation-item {
  display: flex;
  align-items: flex-start;
  gap: 1rem;
  padding: 1rem;
  border-radius: 0.375rem;
  background-color: #f8f9fa;
}

.recommendation-icon {
  font-size: 1.5rem;
}

.recommendation-content h5 {
  margin: 0 0 0.5rem 0;
}

.recommendation-content p {
  margin: 0;
  color: #666;
}
</style>