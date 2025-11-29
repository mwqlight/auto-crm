<template>
  <div class="table-container">
    <table class="base-table" :class="{ 'table-striped': striped, 'table-hover': hover }">
      <thead>
        <tr>
          <th v-for="column in columns" :key="column.key">
            {{ column.title }}
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(row, rowIndex) in data" :key="rowIndex">
          <td v-for="column in columns" :key="column.key">
            <slot :name="column.key" :row="row" :value="row[column.key]">
              {{ row[column.key] }}
            </slot>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
interface Column {
  key: string
  title: string
}

interface Props {
  columns: Column[]
  data: Record<string, any>[]
  striped?: boolean
  hover?: boolean
}

withDefaults(defineProps<Props>(), {
  striped: false,
  hover: false
})
</script>

<style scoped>
.table-container {
  overflow-x: auto;
}

.base-table {
  width: 100%;
  margin-bottom: 1rem;
  color: #212529;
  vertical-align: top;
  border-color: #dee2e6;
}

.base-table thead th {
  vertical-align: bottom;
  border-bottom: 2px solid #dee2e6;
  padding: 0.5rem 0.5rem;
  text-align: left;
}

.base-table tbody td {
  padding: 0.5rem 0.5rem;
  border-bottom: 1px solid #dee2e6;
}

.base-table.table-striped tbody tr:nth-of-type(odd) {
  background-color: rgba(0, 0, 0, 0.05);
}

.base-table.table-hover tbody tr:hover {
  background-color: rgba(0, 0, 0, 0.075);
}
</style>