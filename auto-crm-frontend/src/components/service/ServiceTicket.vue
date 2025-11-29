<template>
  <div class="service-ticket">
    <Card :header="`工单 #${ticket.id}`">
      <div class="ticket-header">
        <div class="ticket-summary">
          <h4>{{ ticket.subject }}</h4>
          <p class="ticket-description">{{ ticket.description }}</p>
        </div>
        <div class="ticket-meta">
          <div class="meta-item">
            <span class="label">优先级:</span>
            <span :class="`priority-${ticket.priority.toLowerCase()}`">{{ ticket.priority }}</span>
          </div>
          <div class="meta-item">
            <span class="label">状态:</span>
            <span :class="`status-${ticket.status.toLowerCase()}`">{{ ticket.status }}</span>
          </div>
          <div class="meta-item">
            <span class="label">负责人:</span>
            <span>{{ ticket.assignee }}</span>
          </div>
        </div>
      </div>
      
      <div class="ticket-details">
        <div class="detail-section">
          <h5>客户信息</h5>
          <div class="customer-info">
            <p><strong>{{ ticket.customer.name }}</strong></p>
            <p>{{ ticket.customer.contact }}</p>
            <p>{{ ticket.customer.email }}</p>
          </div>
        </div>
        
        <div class="detail-section">
          <h5>处理记录</h5>
          <div class="activity-feed">
            <div class="activity-item" v-for="activity in ticket.activities" :key="activity.id">
              <div class="activity-header">
                <span class="activity-author">{{ activity.author }}</span>
                <span class="activity-time">{{ activity.time }}</span>
              </div>
              <div class="activity-content">
                {{ activity.content }}
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <div class="ticket-actions" slot="footer">
        <Button variant="primary" @click="updateStatus">更新状态</Button>
        <Button variant="secondary" @click="addComment">添加评论</Button>
        <Button variant="success" @click="resolveTicket">解决工单</Button>
      </div>
    </Card>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import Card from '../base/Card.vue'
import Button from '../base/Button.vue'

// 模拟工单数据
const ticket = ref({
  id: 'TCK-202303001',
  subject: '软件安装问题',
  description: '客户在安装我们的企业版软件时遇到兼容性问题，需要技术支持协助解决。',
  priority: '高',
  status: '处理中',
  assignee: '王五',
  customer: {
    name: 'ABC科技有限公司',
    contact: '张三',
    email: 'zhangsan@abc.com'
  },
  activities: [
    {
      id: 1,
      author: '王五',
      time: '2023-03-15 14:30',
      content: '已联系客户确认问题详情，正在准备解决方案。'
    },
    {
      id: 2,
      author: '张三',
      time: '2023-03-15 10:15',
      content: '客户报告软件在Windows Server 2019上无法正常启动。'
    }
  ]
})

const updateStatus = () => {
  alert('更新状态功能待实现')
}

const addComment = () => {
  alert('添加评论功能待实现')
}

const resolveTicket = () => {
  alert('解决工单功能待实现')
}
</script>

<style scoped>
.service-ticket {
  padding: 1rem;
}

.ticket-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 1.5rem;
}

.ticket-summary h4 {
  margin: 0 0 0.5rem 0;
}

.ticket-description {
  color: #666;
  margin: 0;
}

.ticket-meta {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.meta-item {
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.label {
  font-weight: bold;
}

.priority-高 {
  color: #dc3545;
  font-weight: 500;
}

.priority-中 {
  color: #fd7e14;
  font-weight: 500;
}

.priority-低 {
  color: #28a745;
  font-weight: 500;
}

.status-处理中 {
  color: #0d6efd;
  font-weight: 500;
}

.status-已解决 {
  color: #198754;
  font-weight: 500;
}

.status-待处理 {
  color: #6c757d;
  font-weight: 500;
}

.ticket-details {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 1.5rem;
}

.detail-section h5 {
  margin: 0 0 1rem 0;
  color: #333;
}

.customer-info {
  background-color: #f8f9fa;
  padding: 1rem;
  border-radius: 0.375rem;
}

.activity-feed {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.activity-item {
  padding: 1rem;
  border-left: 3px solid #0d6efd;
  background-color: #f8f9fa;
  border-radius: 0 0.375rem 0.375rem 0;
}

.activity-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 0.5rem;
}

.activity-author {
  font-weight: 500;
}

.activity-time {
  color: #666;
  font-size: 0.875rem;
}

.ticket-actions {
  display: flex;
  gap: 0.5rem;
  justify-content: flex-end;
}
</style>