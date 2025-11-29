// 服务管理相关API接口
import axios from 'axios'

// 获取服务工单列表
export const getTickets = (params?: any) => {
  return axios.get('/api/v1/tickets', { params })
}

// 获取服务工单详情
export const getTicketById = (id: number) => {
  return axios.get(`/api/v1/tickets/${id}`)
}

// 创建服务工单
export const createTicket = (data: any) => {
  return axios.post('/api/v1/tickets', data)
}

// 更新服务工单
export const updateTicket = (id: number, data: any) => {
  return axios.put(`/api/v1/tickets/${id}`, data)
}

// 删除服务工单
export const deleteTicket = (id: number) => {
  return axios.delete(`/api/v1/tickets/${id}`)
}