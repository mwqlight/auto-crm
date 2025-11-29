<template>
  <div class="service">
    <h2>服务管理</h2>
    <div class="service-toolbar">
      <button class="btn btn-primary">创建服务工单</button>
      <div class="search-box">
        <input type="text" placeholder="搜索工单..." />
      </div>
    </div>
    <div class="service-table">
      <table>
        <thead>
          <tr>
            <th>工单编号</th>
            <th>客户</th>
            <th>主题</th>
            <th>优先级</th>
            <th>状态</th>
            <th>负责人</th>
            <th>创建时间</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>TCK-202303001</td>
            <td>ABC公司</td>
            <td>软件安装问题</td>
            <td>高</td>
            <td>处理中</td>
            <td>王五</td>
            <td>2023-03-15</td>
            <td>
              <button class="btn btn-sm">处理</button>
              <button class="btn btn-sm btn-danger">关闭</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup lang="ts">
// ServiceView - 服务管理页面
</script>

<style scoped>
.service-toolbar {
  display: flex;
  justify-content: space-between;
  margin: 1.5rem 0;
}

.search-box input {
  padding: 0.5rem;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.service-table {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  overflow: hidden;
}

.service-table table {
  width: 100%;
  border-collapse: collapse;
}

.service-table th,
.service-table td {
  padding: 1rem;
  text-align: left;
  border-bottom: 1px solid #eee;
}

.service-table th {
  background-color: #f8f9fa;
  font-weight: 600;
}
</style>