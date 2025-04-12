<template>
    <div class="availability-view">
        <ScheduleView v-model:selected-game-id="selectedGameId" />

        <div v-if="selectedGameId" class="details-section">
            <CrewListView :game-id="selectedGameId" />
            <AvailabilityForm :game-id="selectedGameId" />
        </div>
        <div v-else class="empty-state">
            Select a game to view crew and submit availability
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import ScheduleView from '@/components/ScheduleView.vue';
import CrewListView from '@/components/CrewListView.vue';
import AvailabilityForm from '@/components/AvailabilityForm.vue';

const selectedGameId = ref(null);
</script>

<style scoped>
.availability-view {
    display: grid;
    gap: 2rem;
}

.details-section {
    display: grid;
    gap: 1.5rem;
    background: #f9f9f9;
    padding: 1.5rem;
    border-radius: 8px;
}

.empty-state {
    padding: 2rem;
    text-align: center;
    color: #666;
    border: 1px dashed #ddd;
    border-radius: 8px;
}
</style>