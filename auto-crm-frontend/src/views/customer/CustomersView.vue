<template>
  <div class="customers-view">
    <div class="view-header">
      <h1>客户管理</h1>
      <div class="header-actions">
        <Button variant="primary" @click="createCustomer">添加客户</Button>
        <Button variant="secondary" @click="importCustomers">导入客户</Button>
      </div>
    </div>
    
    <div class="view-filters">
      <div class="filter-group">
        <input type="text" placeholder="搜索客户名称、联系人、邮箱..." class="search-input" />
        <select class="filter-select">
          <option>所有行业</option>
          <option>科技</option>
          <option>制造</option>
          <option>金融</option>
        </select>
        <select class="filter-select">
          <option>所有地区</option>
          <option>北京</option>
          <option>上海</option>
          <option>深圳</option>
        </select>
      </div>
      
      <div class="filter-tags">
        <span class="tag">VIP客户</span>
        <span class="tag">即将过期</span>
        <span class="tag active">高价值</span>
      </div>
    </div>
    
    <div class="customers-table">
      <Table 
        :columns="customerColumns" 
        :data="customerData"
        striped
        hover
      >
        <template #actions="{ row }">
          <Button variant="secondary" size="small" @click="viewCustomer(row)">查看</Button>
          <Button variant="outline" size="small" @click="editCustomer(row)">编辑</Button>
        </template>
      </Table>
    </div>
    
    <div class="pagination">
      <div class="pagination-info">
        显示第 1 到 10 项，共 1248 项
      </div>
      <div class="pagination-controls">
        <Button variant="outline" size="small" disabled>上一页</Button>
        <Button variant="outline" size="small" class="active">1</Button>
        <Button variant="outline" size="small">2</Button>
        <Button variant="outline" size="small">3</Button>
        <Button variant="outline" size="small">下一页</Button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import Table from '../../components/base/Table.vue'
import Button from '../../components/base/Button.vue'
import Customer360View from '../../components/customer/Customer360View.vue'

// 定义表格列
const customerColumns = [
  { key: 'name', title: '客户名称' },
  { key: 'industry', title: '行业' },
  { key: 'contact', title: '联系人' },
  { key: 'email', title: '邮箱' },
  { key: 'phone', title: '电话' },
  { key: 'value', title: '客户价值' },
  { key: 'status', title: '状态' },
  { key: 'actions', title: '操作' }
]

// 模拟客户数据
const customerData = ref([
  {
    id: 1,
    name: 'ABC科技有限公司',
    industry: '科技',
    contact: '张三',
    email: 'zhangsan@abc.com',
    phone: '010-12345678',
    value: '高价值',
    status: '活跃'
  },
  {
    id: 2,
    name: 'XYZ制造集团',
    industry: '制造',
    contact: '李四',
    email: 'lisi@xyz.com',
    phone: '021-87654321',
    value: '中等',
    status: '潜在'
  },
  {
    id: 3,
    name: 'DEF金融服务公司',
    industry: '金融',
    contact: '王五',
    email: 'wangwu@def.com',
    phone: '0755-11111111',
    value: '高价值',
    status: '活跃'
  }
])

const createCustomer = () => {
  alert('添加客户功能待实现')
}

const importCustomers = () => {
  alert('导入客户功能待实现')
}

const viewCustomer = (customer: any) => {
  alert(`查看客户: ${customer.name}`)
}

const editCustomer = (customer: any) => {
  alert(`编辑客户: ${customer.name}`)
}
</script>

<style scoped>
.customers-view {
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

.view-filters {
  margin-bottom: 1.5rem;
}

.filter-group {
  display: flex;
  gap: 1rem;
  margin-bottom: 1rem;
  flex-wrap: wrap;
}

.search-input,
.filter-select {
  padding: 0.5rem;
  border: 1px solid #ddd;
  border-radius: 0.375rem;
}

.filter-tags {
  display: flex;
  gap: 0.5rem;
}

.tag {
  padding: 0.25rem 0.75rem;
  background-color: #e9ecef;
  border-radius: 1rem;
  font-size: 0.875rem;
  cursor: pointer;
}

.tag.active {
  background-color: #0d6efd;
  color: white;
}

.customers-table {
  margin-bottom: 1.5rem;
}

.pagination {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.pagination-info {
  color: #666;
}

.pagination-controls {
  display: flex;
  gap: 0.25rem;
}

.pagination-controls .active {
  background-color: #0d6efd;
  color: white;
  border-color: #0d6efd;
}
</style>