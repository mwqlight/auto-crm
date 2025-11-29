// 示例数据API接口
import sampleData from '@/assets/sample-data.json'

// 获取示例数据
export const getSampleData = () => {
  return Promise.resolve(sampleData)
}

// 获取示例客户数据
export const getSampleCustomers = () => {
  return Promise.resolve(sampleData.customers)
}

// 获取示例销售机会数据
export const getSampleOpportunities = () => {
  return Promise.resolve(sampleData.opportunities)
}

// 获取示例服务工单数据
export const getSampleTickets = () => {
  return Promise.resolve(sampleData.tickets)
}

// 获取示例营销活动数据
export const getSampleCampaigns = () => {
  return Promise.resolve(sampleData.campaigns)
}

// 获取示例AI分析数据
export const getSampleAIAnalysis = () => {
  return Promise.resolve(sampleData.aiAnalysis)
}

// 获取示例仪表板统计数据
export const getSampleDashboardStats = () => {
  return Promise.resolve(sampleData.dashboardStats)
}