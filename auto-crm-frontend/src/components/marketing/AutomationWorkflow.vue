<template>
  <div class="automation-workflow">
    <div class="workflow-header">
      <h3>营销自动化工作流</h3>
      <Button variant="primary" @click="createWorkflow">创建新工作流</Button>
    </div>
    
    <div class="workflow-canvas">
      <div class="workflow-node start-node">
        <div class="node-content">
          <div class="node-icon">▶</div>
          <div class="node-label">开始</div>
        </div>
      </div>
      
      <div class="workflow-connector"></div>
      
      <div class="workflow-node condition-node">
        <div class="node-content">
          <div class="node-icon">?</div>
          <div class="node-label">客户条件</div>
        </div>
      </div>
      
      <div class="workflow-connector"></div>
      
      <div class="workflow-node action-node">
        <div class="node-content">
          <div class="node-icon">✉</div>
          <div class="node-label">发送邮件</div>
        </div>
      </div>
      
      <div class="workflow-connector"></div>
      
      <div class="workflow-node action-node">
        <div class="node-content">
          <div class="node-icon">📊</div>
          <div class="node-label">跟踪行为</div>
        </div>
      </div>
      
      <div class="workflow-connector"></div>
      
      <div class="workflow-node end-node">
        <div class="node-content">
          <div class="node-icon">⏹</div>
          <div class="node-label">结束</div>
        </div>
      </div>
    </div>
    
    <div class="workflow-stats">
      <Card header="工作流统计">
        <div class="stats-grid">
          <div class="stat-item">
            <div class="stat-value">12</div>
            <div class="stat-label">活跃工作流</div>
          </div>
          <div class="stat-item">
            <div class="stat-value">1,248</div>
            <div class="stat-label">触发次数</div>
          </div>
          <div class="stat-item">
            <div class="stat-value">24.5%</div>
            <div class="stat-label">平均转化率</div>
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

const createWorkflow = () => {
  alert('创建新工作流功能待实现')
}
</script>

<style scoped>
.automation-workflow {
  padding: 1rem;
}

.workflow-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1.5rem;
}

.workflow-header h3 {
  margin: 0;
}

.workflow-canvas {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 2rem;
  padding: 2rem;
  background-color: #f8f9fa;
  border-radius: 0.5rem;
  min-height: 200px;
}

.workflow-node {
  width: 120px;
  height: 80px;
  border-radius: 0.5rem;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  z-index: 1;
}

.start-node {
  background-color: #28a745;
  color: white;
}

.condition-node {
  background-color: #17a2b8;
  color: white;
}

.action-node {
  background-color: #007bff;
  color: white;
}

.end-node {
  background-color: #6c757d;
  color: white;
}

.node-content {
  text-align: center;
}

.node-icon {
  font-size: 1.5rem;
  margin-bottom: 0.5rem;
}

.node-label {
  font-size: 0.875rem;
}

.workflow-connector {
  flex: 1;
  height: 2px;
  background-color: #6c757d;
  margin: 0 1rem;
  position: relative;
}

.workflow-connector::after {
  content: "▶";
  position: absolute;
  right: -10px;
  top: 50%;
  transform: translateY(-50%);
  color: #6c757d;
}

.stats-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
  gap: 1rem;
}

.stat-item {
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