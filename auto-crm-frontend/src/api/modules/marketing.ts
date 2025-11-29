// 营销管理相关API接口
import axios from 'axios'

// 获取营销活动列表
export const getCampaigns = (params?: any) => {
  return axios.get('/api/v1/campaigns', { params })
}

// 获取营销活动详情
export const getCampaignById = (id: number) => {
  return axios.get(`/api/v1/campaigns/${id}`)
}

// 创建营销活动
export const createCampaign = (data: any) => {
  return axios.post('/api/v1/campaigns', data)
}

// 更新营销活动
export const updateCampaign = (id: number, data: any) => {
  return axios.put(`/api/v1/campaigns/${id}`, data)
}

// 删除营销活动
export const deleteCampaign = (id: number) => {
  return axios.delete(`/api/v1/campaigns/${id}`)
}