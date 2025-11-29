# API文档

本文档详细描述StellarCRM系统的RESTful API接口。

## 目录
1. [认证API](#认证api)
2. [客户管理API](#客户管理api)
3. [销售机会API](#销售机会api)
4. [服务工单API](#服务工单api)
5. [营销活动API](#营销活动api)
6. [AI分析API](#ai分析api)
7. [错误码说明](#错误码说明)

## 认证API

### 用户登录
```http
POST /api/v1/auth/login
```

**请求参数**
```json
{
  "username": "string",
  "password": "string"
}
```

**响应**
```json
{
  "code": 200,
  "message": "登录成功",
  "data": {
    "token": "string",
    "username": "string",
    "userId": 0
  },
  "timestamp": 1650000000000
}
```

### 用户登出
```http
POST /api/v1/auth/logout
```

**请求头**
```http
Authorization: Bearer <token>
```

**响应**
```json
{
  "code": 200,
  "message": "登出成功",
  "data": null,
  "timestamp": 1650000000000
}
```

## 客户管理API

### 获取客户列表
```http
GET /api/v1/customers
```

**查询参数**
| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| page | integer | 否 | 页码，默认1 |
| size | integer | 否 | 每页大小，默认10 |
| name | string | 否 | 客户名称搜索 |
| industry | string | 否 | 行业筛选 |

**响应**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "items": [
      {
        "id": 1,
        "name": "客户名称",
        "industry": "行业",
        "phone": "电话",
        "email": "邮箱",
        "address": "地址",
        "createdAt": "2023-01-01T00:00:00",
        "updatedAt": "2023-01-01T00:00:00"
      }
    ],
    "total": 100
  },
  "timestamp": 1650000000000
}
```

### 获取客户详情
```http
GET /api/v1/customers/{id}
```

**路径参数**
| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| id | integer | 是 | 客户ID |

**响应**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": 1,
    "name": "客户名称",
    "industry": "行业",
    "phone": "电话",
    "email": "邮箱",
    "address": "地址",
    "contacts": [
      {
        "id": 1,
        "name": "联系人姓名",
        "position": "职位",
        "phone": "电话",
        "email": "邮箱"
      }
    ],
    "interactions": [
      {
        "id": 1,
        "type": "电话",
        "content": "沟通内容",
        "createdAt": "2023-01-01T00:00:00"
      }
    ],
    "createdAt": "2023-01-01T00:00:00",
    "updatedAt": "2023-01-01T00:00:00"
  },
  "timestamp": 1650000000000
}
```

### 创建客户
```http
POST /api/v1/customers
```

**请求参数**
```json
{
  "name": "客户名称",
  "industry": "行业",
  "phone": "电话",
  "email": "邮箱",
  "address": "地址"
}
```

**响应**
```json
{
  "code": 200,
  "message": "创建成功",
  "data": {
    "id": 1,
    "name": "客户名称",
    "industry": "行业",
    "phone": "电话",
    "email": "邮箱",
    "address": "地址",
    "createdAt": "2023-01-01T00:00:00",
    "updatedAt": "2023-01-01T00:00:00"
  },
  "timestamp": 1650000000000
}
```

### 更新客户
```http
PUT /api/v1/customers/{id}
```

**路径参数**
| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| id | integer | 是 | 客户ID |

**请求参数**
```json
{
  "name": "客户名称",
  "industry": "行业",
  "phone": "电话",
  "email": "邮箱",
  "address": "地址"
}
```

**响应**
```json
{
  "code": 200,
  "message": "更新成功",
  "data": {
    "id": 1,
    "name": "客户名称",
    "industry": "行业",
    "phone": "电话",
    "email": "邮箱",
    "address": "地址",
    "createdAt": "2023-01-01T00:00:00",
    "updatedAt": "2023-01-01T00:00:00"
  },
  "timestamp": 1650000000000
}
```

### 删除客户
```http
DELETE /api/v1/customers/{id}
```

**路径参数**
| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| id | integer | 是 | 客户ID |

**响应**
```json
{
  "code": 200,
  "message": "删除成功",
  "data": null,
  "timestamp": 1650000000000
}
```

## 销售机会API

### 获取销售机会列表
```http
GET /api/v1/opportunities
```

**查询参数**
| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| page | integer | 否 | 页码，默认1 |
| size | integer | 否 | 每页大小，默认10 |
| customerId | integer | 否 | 客户ID筛选 |
| status | string | 否 | 状态筛选(PROSPECTING, QUALIFICATION, PROPOSAL, NEGOTIATION, CLOSED_WON, CLOSED_LOST) |

**响应**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "items": [
      {
        "id": 1,
        "customerId": 1,
        "customerName": "客户名称",
        "title": "机会标题",
        "value": 100000,
        "status": "PROSPECTING",
        "probability": 20,
        "expectedCloseDate": "2023-12-31",
        "createdAt": "2023-01-01T00:00:00",
        "updatedAt": "2023-01-01T00:00:00"
      }
    ],
    "total": 50
  },
  "timestamp": 1650000000000
}
```

### 获取销售机会详情
```http
GET /api/v1/opportunities/{id}
```

**路径参数**
| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| id | integer | 是 | 机会ID |

**响应**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": 1,
    "customerId": 1,
    "customerName": "客户名称",
    "title": "机会标题",
    "description": "机会描述",
    "value": 100000,
    "status": "PROSPECTING",
    "probability": 20,
    "expectedCloseDate": "2023-12-31",
    "activities": [
      {
        "id": 1,
        "type": "电话",
        "subject": "活动主题",
        "scheduledAt": "2023-01-01T00:00:00",
        "completed": false
      }
    ],
    "createdAt": "2023-01-01T00:00:00",
    "updatedAt": "2023-01-01T00:00:00"
  },
  "timestamp": 1650000000000
}
```

### 创建销售机会
```http
POST /api/v1/opportunities
```

**请求参数**
```json
{
  "customerId": 1,
  "title": "机会标题",
  "description": "机会描述",
  "value": 100000,
  "status": "PROSPECTING",
  "probability": 20,
  "expectedCloseDate": "2023-12-31"
}
```

**响应**
```json
{
  "code": 200,
  "message": "创建成功",
  "data": {
    "id": 1,
    "customerId": 1,
    "customerName": "客户名称",
    "title": "机会标题",
    "description": "机会描述",
    "value": 100000,
    "status": "PROSPECTING",
    "probability": 20,
    "expectedCloseDate": "2023-12-31",
    "createdAt": "2023-01-01T00:00:00",
    "updatedAt": "2023-01-01T00:00:00"
  },
  "timestamp": 1650000000000
}
```

### 更新销售机会
```http
PUT /api/v1/opportunities/{id}
```

**路径参数**
| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| id | integer | 是 | 机会ID |

**请求参数**
```json
{
  "customerId": 1,
  "title": "机会标题",
  "description": "机会描述",
  "value": 100000,
  "status": "QUALIFICATION",
  "probability": 40,
  "expectedCloseDate": "2023-12-31"
}
```

**响应**
```json
{
  "code": 200,
  "message": "更新成功",
  "data": {
    "id": 1,
    "customerId": 1,
    "customerName": "客户名称",
    "title": "机会标题",
    "description": "机会描述",
    "value": 100000,
    "status": "QUALIFICATION",
    "probability": 40,
    "expectedCloseDate": "2023-12-31",
    "createdAt": "2023-01-01T00:00:00",
    "updatedAt": "2023-01-01T00:00:00"
  },
  "timestamp": 1650000000000
}
```

### 删除销售机会
```http
DELETE /api/v1/opportunities/{id}
```

**路径参数**
| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| id | integer | 是 | 机会ID |

**响应**
```json
{
  "code": 200,
  "message": "删除成功",
  "data": null,
  "timestamp": 1650000000000
}
```

## 服务工单API

### 获取服务工单列表
```http
GET /api/v1/tickets
```

**查询参数**
| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| page | integer | 否 | 页码，默认1 |
| size | integer | 否 | 每页大小，默认10 |
| customerId | integer | 否 | 客户ID筛选 |
| status | string | 否 | 状态筛选(OPEN, IN_PROGRESS, RESOLVED, CLOSED) |

**响应**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "items": [
      {
        "id": 1,
        "customerId": 1,
        "customerName": "客户名称",
        "title": "工单标题",
        "description": "工单描述",
        "priority": "HIGH",
        "status": "OPEN",
        "assignedTo": "处理人员",
        "createdAt": "2023-01-01T00:00:00",
        "updatedAt": "2023-01-01T00:00:00"
      }
    ],
    "total": 30
  },
  "timestamp": 1650000000000
}
```

### 获取服务工单详情
```http
GET /api/v1/tickets/{id}
```

**路径参数**
| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| id | integer | 是 | 工单ID |

**响应**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": 1,
    "customerId": 1,
    "customerName": "客户名称",
    "title": "工单标题",
    "description": "工单描述",
    "priority": "HIGH",
    "status": "OPEN",
    "assignedTo": "处理人员",
    "comments": [
      {
        "id": 1,
        "author": "评论人",
        "content": "评论内容",
        "createdAt": "2023-01-01T00:00:00"
      }
    ],
    "createdAt": "2023-01-01T00:00:00",
    "updatedAt": "2023-01-01T00:00:00"
  },
  "timestamp": 1650000000000
}
```

### 创建服务工单
```http
POST /api/v1/tickets
```

**请求参数**
```json
{
  "customerId": 1,
  "title": "工单标题",
  "description": "工单描述",
  "priority": "HIGH"
}
```

**响应**
```json
{
  "code": 200,
  "message": "创建成功",
  "data": {
    "id": 1,
    "customerId": 1,
    "customerName": "客户名称",
    "title": "工单标题",
    "description": "工单描述",
    "priority": "HIGH",
    "status": "OPEN",
    "assignedTo": null,
    "createdAt": "2023-01-01T00:00:00",
    "updatedAt": "2023-01-01T00:00:00"
  },
  "timestamp": 1650000000000
}
```

### 更新服务工单
```http
PUT /api/v1/tickets/{id}
```

**路径参数**
| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| id | integer | 是 | 工单ID |

**请求参数**
```json
{
  "customerId": 1,
  "title": "工单标题",
  "description": "工单描述",
  "priority": "MEDIUM",
  "status": "IN_PROGRESS",
  "assignedTo": "处理人员"
}
```

**响应**
```json
{
  "code": 200,
  "message": "更新成功",
  "data": {
    "id": 1,
    "customerId": 1,
    "customerName": "客户名称",
    "title": "工单标题",
    "description": "工单描述",
    "priority": "MEDIUM",
    "status": "IN_PROGRESS",
    "assignedTo": "处理人员",
    "createdAt": "2023-01-01T00:00:00",
    "updatedAt": "2023-01-01T00:00:00"
  },
  "timestamp": 1650000000000
}
```

### 删除服务工单
```http
DELETE /api/v1/tickets/{id}
```

**路径参数**
| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| id | integer | 是 | 工单ID |

**响应**
```json
{
  "code": 200,
  "message": "删除成功",
  "data": null,
  "timestamp": 1650000000000
}
```

## 营销活动API

### 获取营销活动列表
```http
GET /api/v1/campaigns
```

**查询参数**
| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| page | integer | 否 | 页码，默认1 |
| size | integer | 否 | 每页大小，默认10 |
| status | string | 否 | 状态筛选(PLANNING, ACTIVE, COMPLETED, CANCELLED) |

**响应**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "items": [
      {
        "id": 1,
        "name": "活动名称",
        "description": "活动描述",
        "startDate": "2023-01-01",
        "endDate": "2023-12-31",
        "budget": 10000,
        "status": "ACTIVE",
        "metrics": {
          "leadsGenerated": 100,
          "conversionRate": 5.5,
          "roi": 2.3
        },
        "createdAt": "2023-01-01T00:00:00",
        "updatedAt": "2023-01-01T00:00:00"
      }
    ],
    "total": 10
  },
  "timestamp": 1650000000000
}
```

### 获取营销活动详情
```http
GET /api/v1/campaigns/{id}
```

**路径参数**
| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| id | integer | 是 | 活动ID |

**响应**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "id": 1,
    "name": "活动名称",
    "description": "活动描述",
    "startDate": "2023-01-01",
    "endDate": "2023-12-31",
    "budget": 10000,
    "status": "ACTIVE",
    "targetCustomers": [
      {
        "id": 1,
        "name": "目标客户"
      }
    ],
    "metrics": {
      "leadsGenerated": 100,
      "conversionRate": 5.5,
      "roi": 2.3
    },
    "createdAt": "2023-01-01T00:00:00",
    "updatedAt": "2023-01-01T00:00:00"
  },
  "timestamp": 1650000000000
}
```

### 创建营销活动
```http
POST /api/v1/campaigns
```

**请求参数**
```json
{
  "name": "活动名称",
  "description": "活动描述",
  "startDate": "2023-01-01",
  "endDate": "2023-12-31",
  "budget": 10000,
  "status": "PLANNING"
}
```

**响应**
```json
{
  "code": 200,
  "message": "创建成功",
  "data": {
    "id": 1,
    "name": "活动名称",
    "description": "活动描述",
    "startDate": "2023-01-01",
    "endDate": "2023-12-31",
    "budget": 10000,
    "status": "PLANNING",
    "metrics": {
      "leadsGenerated": 0,
      "conversionRate": 0,
      "roi": 0
    },
    "createdAt": "2023-01-01T00:00:00",
    "updatedAt": "2023-01-01T00:00:00"
  },
  "timestamp": 1650000000000
}
```

### 更新营销活动
```http
PUT /api/v1/campaigns/{id}
```

**路径参数**
| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| id | integer | 是 | 活动ID |

**请求参数**
```json
{
  "name": "活动名称",
  "description": "活动描述",
  "startDate": "2023-01-01",
  "endDate": "2023-12-31",
  "budget": 15000,
  "status": "ACTIVE"
}
```

**响应**
```json
{
  "code": 200,
  "message": "更新成功",
  "data": {
    "id": 1,
    "name": "活动名称",
    "description": "活动描述",
    "startDate": "2023-01-01",
    "endDate": "2023-12-31",
    "budget": 15000,
    "status": "ACTIVE",
    "metrics": {
      "leadsGenerated": 0,
      "conversionRate": 0,
      "roi": 0
    },
    "createdAt": "2023-01-01T00:00:00",
    "updatedAt": "2023-01-01T00:00:00"
  },
  "timestamp": 1650000000000
}
```

### 删除营销活动
```http
DELETE /api/v1/campaigns/{id}
```

**路径参数**
| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| id | integer | 是 | 活动ID |

**响应**
```json
{
  "code": 200,
  "message": "删除成功",
  "data": null,
  "timestamp": 1650000000000
}
```

## AI分析API

### 获取AI分析结果
```http
GET /api/v1/ai-analysis/results
```

**查询参数**
| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| page | integer | 否 | 页码，默认1 |
| size | integer | 否 | 每页大小，默认10 |
| type | string | 否 | 分析类型(SALES_FORECAST, CUSTOMER_RISK, CHURN_PREDICTION, CROSS_SELL) |

**响应**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "items": [
      {
        "id": 1,
        "type": "SALES_FORECAST",
        "referenceId": 1,
        "result": {
          "predictedValue": 150000,
          "confidence": 0.85,
          "factors": [
            "市场趋势",
            "季节性因素",
            "历史数据"
          ]
        },
        "createdAt": "2023-01-01T00:00:00"
      }
    ],
    "total": 25
  },
  "timestamp": 1650000000000
}
```

### 触发AI分析
```http
POST /api/v1/ai-analysis/trigger
```

**请求参数**
```json
{
  "type": "SALES_FORECAST",
  "referenceId": 1
}
```

**响应**
```json
{
  "code": 200,
  "message": "分析任务已提交",
  "data": {
    "taskId": "uuid-string"
  },
  "timestamp": 1650000000000
}
```

### 获取AI分析状态
```http
GET /api/v1/ai-analysis/status/{taskId}
```

**路径参数**
| 参数名 | 类型 | 必填 | 描述 |
|--------|------|------|------|
| taskId | string | 是 | 任务ID |

**响应**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "taskId": "uuid-string",
    "status": "COMPLETED",
    "result": {
      "predictedValue": 150000,
      "confidence": 0.85,
      "factors": [
        "市场趋势",
        "季节性因素",
        "历史数据"
      ]
    },
    "createdAt": "2023-01-01T00:00:00",
    "finishedAt": "2023-01-01T00:05:00"
  },
  "timestamp": 1650000000000
}
```

## 错误码说明

### 通用错误码
| 错误码 | 说明 | HTTP状态码 |
|--------|------|------------|
| 200 | 操作成功 | 200 |
| 400 | 请求参数错误 | 400 |
| 401 | 未认证 | 401 |
| 403 | 权限不足 | 403 |
| 404 | 资源不存在 | 404 |
| 500 | 服务器内部错误 | 500 |

### 业务错误码
| 错误码 | 说明 |
|--------|------|
| 10001 | 用户不存在 |
| 10002 | 密码错误 |
| 10003 | 客户不存在 |
| 10004 | 销售机会不存在 |
| 10005 | 服务工单不存在 |
| 10006 | 营销活动不存在 |
| 10007 | AI分析任务不存在 |
| 10008 | 数据验证失败 |
| 10009 | 数据库操作失败 |
| 10010 | 外部服务调用失败 |

通过以上API接口，您可以完整地管理和操作StellarCRM系统的各项功能。