<template>
  <main class="main-content">
    <div class="content-wrapper">
      <router-view />
    </div>
  </main>
</template>

<script setup lang="ts">
// MainContent组件不需要额外的逻辑
</script>

<style scoped>
.main-content {
  margin-left: 250px;
  margin-top: 60px;
  padding: 1.5rem;
  min-height: calc(100vh - 60px);
}

.content-wrapper {
  max-width: 1400px;
  margin: 0 auto;
}
</style>