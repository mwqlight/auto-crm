<template>
  <div class="customers">
    <h2>客户管理</h2>
    <div class="customers-toolbar">
      <button class="btn btn-primary">添加客户</button>
      <div class="search-box">
        <input type="text" placeholder="搜索客户..." />
      </div>
    </div>
    <div class="customers-table">
      <table>
        <thead>
          <tr>
            <th>客户名称</th>
            <th>联系人</th>
            <th>电话</th>
            <th>邮箱</th>
            <th>创建时间</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>ABC公司</td>
            <td>张三</td>
            <td>13800138000</td>
            <td>zhangsan@abc.com</td>
            <td>2023-01-15</td>
            <td>
              <button class="btn btn-sm">编辑</button>
              <button class="btn btn-sm btn-danger">删除</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup lang="ts">
// CustomersView - 客户管理页面
</script>

<style scoped>
.customers-toolbar {
  display: flex;
  justify-content: space-between;
  margin: 1.5rem 0;
}

.search-box input {
  padding: 0.5rem;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.customers-table {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  overflow: hidden;
}

.customers-table table {
  width: 100%;
  border-collapse: collapse;
}

.customers-table th,
.customers-table td {
  padding: 1rem;
  text-align: left;
  border-bottom: 1px solid #eee;
}

.customers-table th {
  background-color: #f8f9fa;
  font-weight: 600;
}

.btn {
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.btn-primary {
  background-color: #3498db;
  color: white;
}

.btn-sm {
  padding: 0.25rem 0.5rem;
  font-size: 0.875rem;
}

.btn-danger {
  background-color: #e74c3c;
  color: white;
}
</style>