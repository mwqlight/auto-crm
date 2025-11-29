<template>
  <div class="customer-360-view">
    <Card header="客户概览">
      <div class="customer-header">
        <div class="customer-info">
          <h3>{{ customer.name }}</h3>
          <p>{{ customer.industry }} | {{ customer.size }}人</p>
        </div>
        <div class="customer-actions">
          <Button variant="primary" size="sm">编辑</Button>
          <Button variant="danger" size="sm">删除</Button>
        </div>
      </div>
      
      <div class="customer-details">
        <div class="detail-item">
          <span class="label">联系人:</span>
          <span>{{ customer.contact }}</span>
        </div>
        <div class="detail-item">
          <span class="label">电话:</span>
          <span>{{ customer.phone }}</span>
        </div>
        <div class="detail-item">
          <span class="label">邮箱:</span>
          <span>{{ customer.email }}</span>
        </div>
        <div class="detail-item">
          <span class="label">地址:</span>
          <span>{{ customer.address }}</span>
        </div>
      </div>
    </Card>
    
    <div class="customer-sections">
      <Card header="销售机会">
        <Table 
          :columns="opportunityColumns" 
          :data="opportunities" 
          striped 
          hover
        >
          <template #stage="{ value }">
            <span :class="`stage-${value.toLowerCase()}`">{{ value }}</span>
          </template>
          <template #amount="{ value }">
            ¥{{ value.toLocaleString() }}
          </template>
        </Table>
      </Card>
      
      <Card header="服务工单">
        <Table 
          :columns="ticketColumns" 
          :data="tickets" 
          striped 
          hover
        >
          <template #priority="{ value }">
            <span :class="`priority-${value.toLowerCase()}`">{{ value }}</span>
          </template>
          <template #status="{ value }">
            <span :class="`status-${value.toLowerCase()}`">{{ value }}</span>
          </template>
        </Table>
      </Card>
      
      <Card header="营销互动">
        <Table 
          :columns="campaignColumns" 
          :data="campaigns" 
          striped 
          hover
        >
          <template #status="{ value }">
            <span :class="`status-${value.toLowerCase()}`">{{ value }}</span>
          </template>
        </Table>
      </Card>
      
      <Card header="AI分析洞察">
        <div class="ai-insights">
          <div class="insight-item" v-for="insight in aiInsights" :key="insight.id">
            <h4>{{ insight.title }}</h4>
            <p>{{ insight.description }}</p>
            <div class="insight-score">
              置信度: {{ insight.confidence }}%
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
import Table from '../base/Table.vue'

// 模拟客户数据
const customer = ref({
  name: 'ABC科技有限公司',
  industry: '信息技术',
  size: '500-1000',
  contact: '张三',
  phone: '13800138000',
  email: 'zhangsan@abc.com',
  address: '北京市海淀区中关村大街1号'
})

// 销售机会表格列定义
const opportunityColumns = [
  { key: 'name', title: '机会名称' },
  { key: 'amount', title: '金额' },
  { key: 'stage', title: '阶段' },
  { key: 'probability', title: '成功率' }
]

// 销售机会数据
const opportunities = ref([
  { name: '企业级软件采购', amount: 150000, stage: '谈判中', probability: '70%' },
  { name: '云服务续费', amount: 80000, stage: '已成交', probability: '100%' }
])

// 服务工单表格列定义
const ticketColumns = [
  { key: 'id', title: '工单号' },
  { key: 'subject', title: '主题' },
  { key: 'priority', title: '优先级' },
  { key: 'status', title: '状态' }
]

// 服务工单数据
const tickets = ref([
  { id: 'TCK-202303001', subject: '软件安装问题', priority: '高', status: '处理中' },
  { id: 'TCK-202302015', subject: '功能咨询', priority: '中', status: '已解决' }
])

// 营销活动表格列定义
const campaignColumns = [
  { key: 'name', title: '活动名称' },
  { key: 'type', title: '类型' },
  { key: 'status', title: '状态' },
  { key: 'participants', title: '参与人数' }
]

// 营销活动数据
const campaigns = ref([
  { name: '春季促销活动', type: '邮件营销', status: '进行中', participants: 1234 },
  { name: '产品发布会', type: '线下活动', status: '已完成', participants: 856 }
])

// AI洞察数据
const aiInsights = ref([
  { 
    id: 1, 
    title: '客户流失风险评估', 
    description: '该客户在未来30天内有25%的可能性流失', 
    confidence: 85 
  },
  { 
    id: 2, 
    title: '交叉销售机会', 
    description: '推荐该客户尝试我们的云存储服务，匹配度90%', 
    confidence: 90 
  }
])
</script>

<style scoped>
.customer-360-view {
  padding: 1rem;
}

.customer-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 1rem;
}

.customer-info h3 {
  margin: 0 0 0.5rem 0;
}

.customer-info p {
  margin: 0;
  color: #666;
}

.customer-actions {
  display: flex;
  gap: 0.5rem;
}

.customer-details {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 1rem;
}

.detail-item {
  display: flex;
  flex-direction: column;
}

.label {
  font-weight: bold;
  margin-bottom: 0.25rem;
}

.customer-sections {
  margin-top: 1.5rem;
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 1.5rem;
}

.ai-insights {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.insight-item {
  padding: 1rem;
  border-radius: 0.375rem;
  background-color: #f8f9fa;
}

.insight-item h4 {
  margin: 0 0 0.5rem 0;
  color: #333;
}

.insight-item p {
  margin: 0 0 0.5rem 0;
  color: #666;
}

.insight-score {
  font-size: 0.875rem;
  color: #0d6efd;
  font-weight: 500;
}

.stage-谈判中 {
  color: #0d6efd;
  font-weight: 500;
}

.stage-已成交 {
  color: #198754;
  font-weight: 500;
}

.priority-高 {
  color: #dc3545;
  font-weight: 500;
}

.priority-中 {
  color: #fd7e14;
  font-weight: 500;
}

.status-处理中 {
  color: #0d6efd;
  font-weight: 500;
}

.status-已解决 {
  color: #198754;
  font-weight: 500;
}

.status-进行中 {
  color: #0d6efd;
  font-weight: 500;
}

.status-已完成 {
  color: #198754;
  font-weight: 500;
}
</style>