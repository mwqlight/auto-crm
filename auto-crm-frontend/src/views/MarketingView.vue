<template>
  <div class="marketing">
    <h2>营销管理</h2>
    <div class="marketing-toolbar">
      <button class="btn btn-primary">创建营销活动</button>
      <div class="search-box">
        <input type="text" placeholder="搜索活动..." />
      </div>
    </div>
    <div class="marketing-table">
      <table>
        <thead>
          <tr>
            <th>活动名称</th>
            <th>类型</th>
            <th>开始时间</th>
            <th>结束时间</th>
            <th>状态</th>
            <th>参与人数</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>春季促销活动</td>
            <td>邮件营销</td>
            <td>2023-03-01</td>
            <td>2023-03-31</td>
            <td>进行中</td>
            <td>1,234</td>
            <td>
              <button class="btn btn-sm">详情</button>
              <button class="btn btn-sm btn-danger">停止</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup lang="ts">
// MarketingView - 营销管理页面
</script>

<style scoped>
.marketing-toolbar {
  display: flex;
  justify-content: space-between;
  margin: 1.5rem 0;
}

.search-box input {
  padding: 0.5rem;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.marketing-table {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  overflow: hidden;
}

.marketing-table table {
  width: 100%;
  border-collapse: collapse;
}

.marketing-table th,
.marketing-table td {
  padding: 1rem;
  text-align: left;
  border-bottom: 1px solid #eee;
}

.marketing-table th {
  background-color: #f8f9fa;
  font-weight: 600;
}
</style>