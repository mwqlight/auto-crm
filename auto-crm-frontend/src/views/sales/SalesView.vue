<template>
  <div class="sales-view">
    <div class="view-header">
      <h1>销售管理</h1>
      <div class="header-actions">
        <Button variant="primary" @click="createOpportunity">创建销售机会</Button>
        <Button variant="secondary" @click="viewReports">销售报表</Button>
      </div>
    </div>
    
    <div class="sales-dashboard">
      <SalesFunnel />
    </div>
    
    <div class="opportunities-section">
      <div class="section-header">
        <h2>销售机会</h2>
        <div class="section-actions">
          <input type="text" placeholder="搜索销售机会..." class="search-input" />
          <select class="filter-select">
            <option>所有阶段</option>
            <option>初步接触</option>
            <option>需求分析</option>
            <option>方案演示</option>
            <option>商务谈判</option>
            <option>已成交</option>
          </select>
        </div>
      </div>
      
      <div class="opportunities-table">
        <Table 
          :columns="opportunityColumns" 
          :data="opportunityData"
          striped
          hover
        >
          <template #actions="{ row }">
            <Button variant="secondary" size="small" @click="viewOpportunity(row)">查看</Button>
            <Button variant="outline" size="small" @click="editOpportunity(row)">编辑</Button>
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
import SalesFunnel from '../../components/sales/SalesFunnel.vue'

// 定义销售机会表格列
const opportunityColumns = [
  { key: 'name', title: '机会名称' },
  { key: 'customer', title: '客户' },
  { key: 'value', title: '预计金额' },
  { key: 'stage', title: '阶段' },
  { key: 'probability', title: '成交概率' },
  { key: 'closeDate', title: '预计成交日期' },
  { key: 'owner', title: '负责人' },
  { key: 'actions', title: '操作' }
]

// 模拟销售机会数据
const opportunityData = ref([
  {
    id: 1,
    name: '企业版软件采购',
    customer: 'ABC科技有限公司',
    value: '¥500,000',
    stage: '商务谈判',
    probability: '80%',
    closeDate: '2023-04-15',
    owner: '张三'
  },
  {
    id: 2,
    name: '云服务年度合同',
    customer: 'XYZ制造集团',
    value: '¥1,200,000',
    stage: '方案演示',
    probability: '60%',
    closeDate: '2023-05-20',
    owner: '李四'
  },
  {
    id: 3,
    name: '定制开发项目',
    customer: 'DEF金融服务公司',
    value: '¥800,000',
    stage: '需求分析',
    probability: '40%',
    closeDate: '2023-06-30',
    owner: '王五'
  }
])

const createOpportunity = () => {
  alert('创建销售机会功能待实现')
}

const viewReports = () => {
  alert('查看销售报表功能待实现')
}

const viewOpportunity = (opportunity: any) => {
  alert(`查看销售机会: ${opportunity.name}`)
}

const editOpportunity = (opportunity: any) => {
  alert(`编辑销售机会: ${opportunity.name}`)
}
</script>

<style scoped>
.sales-view {
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

.sales-dashboard {
  margin-bottom: 2rem;
}

.opportunities-section {
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
</style>