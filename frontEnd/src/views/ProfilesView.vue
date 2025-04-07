<template>
    <div class="profiles-view">
        <ProfilesList />
    </div>
</template>

<script setup>
import ProfilesList from '@/components/ProfilesList.vue';
</script>

<style scoped>
.profiles-view {
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
}
</style>