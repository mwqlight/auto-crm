<template>
  <div class="sales">
    <h2>销售管理</h2>
    <div class="sales-toolbar">
      <button class="btn btn-primary">添加销售机会</button>
      <div class="sales-funnel">
        <h3>3D销售漏斗</h3>
        <!-- 3D销售漏斗可视化组件 -->
      </div>
    </div>
    <div class="sales-table">
      <table>
        <thead>
          <tr>
            <th>机会名称</th>
            <th>客户</th>
            <th>预计金额</th>
            <th>阶段</th>
            <th>负责人</th>
            <th>创建时间</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>企业级软件采购</td>
            <td>ABC公司</td>
            <td>¥150,000</td>
            <td>谈判中</td>
            <td>李四</td>
            <td>2023-02-20</td>
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
// SalesView - 销售管理页面
</script>

<style scoped>
.sales-toolbar {
  display: flex;
  justify-content: space-between;
  margin: 1.5rem 0;
}

.sales-funnel {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  padding: 1rem;
  min-height: 200px;
}

.sales-table {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  overflow: hidden;
  margin-top: 1.5rem;
}

.sales-table table {
  width: 100%;
  border-collapse: collapse;
}

.sales-table th,
.sales-table td {
  padding: 1rem;
  text-align: left;
  border-bottom: 1px solid #eee;
}

.sales-table th {
  background-color: #f8f9fa;
  font-weight: 600;
}
</style>