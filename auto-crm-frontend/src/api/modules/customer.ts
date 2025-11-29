// 客户管理相关API接口
import axios from 'axios'

// 获取客户列表
export const getCustomers = (params?: any) => {
  return axios.get('/api/v1/customers', { params })
}

// 获取客户详情
export const getCustomerById = (id: number) => {
  return axios.get(`/api/v1/customers/${id}`)
}

// 创建客户
export const createCustomer = (data: any) => {
  return axios.post('/api/v1/customers', data)
}

// 更新客户
export const updateCustomer = (id: number, data: any) => {
  return axios.put(`/api/v1/customers/${id}`, data)
}

// 删除客户
export const deleteCustomer = (id: number) => {
  return axios.delete(`/api/v1/customers/${id}`)
}