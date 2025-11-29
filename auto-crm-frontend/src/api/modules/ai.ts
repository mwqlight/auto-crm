// AI分析相关API接口
import axios from 'axios'

// 执行AI分析
export const analyze = (data: any) => {
  return axios.post('/api/v1/ai/analyze', data)
}

// 执行高级AI分析
export const advancedAnalyze = (data: any) => {
  return axios.post('/api/v1/ai/advanced-analyze', data)
}

// 获取详细分析结果
export const getDetailedAnalysis = (analysisId: number) => {
  return axios.get(`/api/v1/ai/detailed/${analysisId}`)
}

// 批量AI分析
export const batchAnalyze = (data: any) => {
  return axios.post('/api/v1/ai/batch-analyze', data)
}

// 根据风险等级获取分析结果
export const getAnalysisByRiskLevel = (riskLevel: string) => {
  return axios.get(`/api/v1/ai/risk-level/${riskLevel}`)
}