<template>
  <div class="service-view">
    <div class="view-header">
      <h1>服务管理</h1>
      <div class="header-actions">
        <Button variant="primary" @click="createTicket">创建工单</Button>
        <Button variant="secondary" @click="viewReports">服务报表</Button>
      </div>
    </div>
    
    <div class="service-dashboard">
      <div class="dashboard-stats">
        <Card class="stat-card">
          <div class="stat-content">
            <h3>24</h3>
            <p>待处理工单</p>
          </div>
        </Card>
        
        <Card class="stat-card">
          <div class="stat-content">
            <h3>48</h3>
            <p>今日处理</p>
          </div>
        </Card>
        
        <Card class="stat-card">
          <div class="stat-content">
            <h3>92%</h3>
            <p>解决率</p>
          </div>
        </Card>
        
        <Card class="stat-card">
          <div class="stat-content">
            <h3>2.4h</h3>
            <p>平均响应时间</p>
          </div>
        </Card>
      </div>
    </div>
    
    <div class="tickets-section">
      <div class="section-header">
        <h2>服务工单</h2>
        <div class="section-actions">
          <input type="text" placeholder="搜索工单..." class="search-input" />
          <select class="filter-select">
            <option>所有优先级</option>
            <option>高</option>
            <option>中</option>
            <option>低</option>
          </select>
          <select class="filter-select">
            <option>所有状态</option>
            <option>待处理</option>
            <option>处理中</option>
            <option>已解决</option>
            <option>已关闭</option>
          </select>
        </div>
      </div>
      
      <div class="tickets-table">
        <Table 
          :columns="ticketColumns" 
          :data="ticketData"
          striped
          hover
        >
          <template #priority="{ row }">
            <span :class="`priority-badge priority-${row.priority.toLowerCase()}`">
              {{ row.priority }}
            </span>
          </template>
          
          <template #status="{ row }">
            <span :class="`status-badge status-${row.status.toLowerCase()}`">
              {{ row.status }}
            </span>
          </template>
          
          <template #actions="{ row }">
            <Button variant="secondary" size="small" @click="viewTicket(row)">查看</Button>
            <Button variant="outline" size="small" @click="editTicket(row)">处理</Button>
          </template>
        </Table>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import Table from '../../components/base/Table.vue'
import Button from '../../components/base/Button.vue'
import Card from '../../components/base/Card.vue'

// 定义工单表格列
const ticketColumns = [
  { key: 'id', title: '工单号' },
  { key: 'subject', title: '主题' },
  { key: 'customer', title: '客户' },
  { key: 'priority', title: '优先级' },
  { key: 'status', title: '状态' },
  { key: 'assignee', title: '负责人' },
  { key: 'createdDate', title: '创建时间' },
  { key: 'actions', title: '操作' }
]

// 模拟工单数据
const ticketData = ref([
  {
    id: 'TCK-202303001',
    subject: '软件安装问题',
    customer: 'ABC科技有限公司',
    priority: '高',
    status: '处理中',
    assignee: '王五',
    createdDate: '2023-03-15'
  },
  {
    id: 'TCK-202303002',
    subject: '功能咨询',
    customer: 'XYZ制造集团',
    priority: '中',
    status: '待处理',
    assignee: '张三',
    createdDate: '2023-03-15'
  },
  {
    id: 'TCK-202303003',
    subject: '账单疑问',
    customer: 'DEF金融服务公司',
    priority: '低',
    status: '已解决',
    assignee: '李四',
    createdDate: '2023-03-14'
  }
])

const createTicket = () => {
  alert('创建工单功能待实现')
}

const viewReports = () => {
  alert('查看服务报表功能待实现')
}

const viewTicket = (ticket: any) => {
  alert(`查看工单: ${ticket.id}`)
}

const editTicket = (ticket: any) => {
  alert(`处理工单: ${ticket.id}`)
}
</script>

<style scoped>
.service-view {
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

.service-dashboard {
  margin-bottom: 2rem;
}

.dashboard-stats {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 1rem;
}

.stat-card {
  text-align: center;
}

.stat-card h3 {
  margin: 0 0 0.5rem 0;
  font-size: 1.5rem;
  color: #3498db;
}

.stat-card p {
  margin: 0;
  color: #666;
}

.tickets-section {
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
}

.search-input,
.filter-select {
  padding: 0.5rem;
  border: 1px solid #ddd;
  border-radius: 0.375rem;
}

.priority-badge {
  padding: 0.25rem 0.5rem;
  border-radius: 0.25rem;
  font-size: 0.875rem;
  font-weight: 500;
}

.priority-高 {
  background-color: #f8d7da;
  color: #721c24;
}

.priority-中 {
  background-color: #fff3cd;
  color: #856404;
}

.priority-低 {
  background-color: #d4edda;
  color: #155724;
}

.status-badge {
  padding: 0.25rem 0.5rem;
  border-radius: 0.25rem;
  font-size: 0.875rem;
  font-weight: 500;
}

.status-待处理 {
  background-color: #e2e3e5;
  color: #41464b;
}

.status-处理中 {
  background-color: #cce5ff;
  color: #004085;
}

.status-已解决 {
  background-color: #d4edda;
  color: #155724;
}

.status-已关闭 {
  background-color: #f8d7da;
  color: #721c24;
}
</style>