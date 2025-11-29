// 销售管理相关API接口
import axios from 'axios'

// 获取销售机会列表
export const getOpportunities = (params?: any) => {
  return axios.get('/api/v1/opportunities', { params })
}

// 获取销售机会详情
export const getOpportunityById = (id: number) => {
  return axios.get(`/api/v1/opportunities/${id}`)
}

// 创建销售机会
export const createOpportunity = (data: any) => {
  return axios.post('/api/v1/opportunities', data)
}

// 更新销售机会
export const updateOpportunity = (id: number, data: any) => {
  return axios.put(`/api/v1/opportunities/${id}`, data)
}

// 删除销售机会
export const deleteOpportunity = (id: number) => {
  return axios.delete(`/api/v1/opportunities/${id}`)
}