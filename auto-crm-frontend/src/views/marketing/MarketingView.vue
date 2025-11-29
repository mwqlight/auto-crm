<template>
  <div class="marketing-view">
    <div class="view-header">
      <h1>营销管理</h1>
      <div class="header-actions">
        <Button variant="primary" @click="createCampaign">创建营销活动</Button>
        <Button variant="secondary" @click="viewAnalytics">营销分析</Button>
      </div>
    </div>
    
    <div class="marketing-dashboard">
      <AutomationWorkflow />
    </div>
    
    <div class="campaigns-section">
      <div class="section-header">
        <h2>营销活动</h2>
        <div class="section-actions">
          <input type="text" placeholder="搜索营销活动..." class="search-input" />
          <select class="filter-select">
            <option>所有状态</option>
            <option>进行中</option>
            <option>已结束</option>
            <option>已暂停</option>
          </select>
        </div>
      </div>
      
      <div class="campaigns-table">
        <Table 
          :columns="campaignColumns" 
          :data="campaignData"
          striped
          hover
        >
          <template #actions="{ row }">
            <Button variant="secondary" size="small" @click="viewCampaign(row)">查看</Button>
            <Button variant="outline" size="small" @click="editCampaign(row)">编辑</Button>
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
import AutomationWorkflow from '../../components/marketing/AutomationWorkflow.vue'

// 定义营销活动表格列
const campaignColumns = [
  { key: 'name', title: '活动名称' },
  { key: 'type', title: '类型' },
  { key: 'startDate', title: '开始日期' },
  { key: 'endDate', title: '结束日期' },
  { key: 'budget', title: '预算' },
  { key: 'status', title: '状态' },
  { key: 'reach', title: '触达人数' },
  { key: 'conversion', title: '转化率' },
  { key: 'actions', title: '操作' }
]

// 模拟营销活动数据
const campaignData = ref([
  {
    id: 1,
    name: '春季产品推广',
    type: '邮件营销',
    startDate: '2023-03-01',
    endDate: '2023-03-31',
    budget: '¥50,000',
    status: '进行中',
    reach: '12,450',
    conversion: '3.2%'
  },
  {
    id: 2,
    name: '新产品发布会',
    type: '线上活动',
    startDate: '2023-02-15',
    endDate: '2023-02-28',
    budget: '¥100,000',
    status: '已结束',
    reach: '8,760',
    conversion: '5.1%'
  },
  {
    id: 3,
    name: '客户回馈计划',
    type: '短信营销',
    startDate: '2023-03-10',
    endDate: '2023-04-10',
    budget: '¥30,000',
    status: '进行中',
    reach: '9,870',
    conversion: '2.8%'
  }
])

const createCampaign = () => {
  alert('创建营销活动功能待实现')
}

const viewAnalytics = () => {
  alert('查看营销分析功能待实现')
}

const viewCampaign = (campaign: any) => {
  alert(`查看营销活动: ${campaign.name}`)
}

const editCampaign = (campaign: any) => {
  alert(`编辑营销活动: ${campaign.name}`)
}
</script>

<style scoped>
.marketing-view {
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

.marketing-dashboard {
  margin-bottom: 2rem;
}

.campaigns-section {
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