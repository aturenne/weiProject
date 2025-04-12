<!-- Use Case 5 -->
<template>
    <div class="schedule-card">
        <h2>Game Schedule</h2>
        <ul v-if="loading" class="loading">Loading...</ul>
        <ul v-else-if="error" class="error">{{ error }}</ul>
        <ul v-else class="game-list">
            <li v-for="game in games" :key="game.id" @click="selectGame(game.id)"
                :class="{ active: selectedGameId === game.id }">
                <div class="game-meta">
                    <span class="date">{{ game.gameDate }}</span>
                    <span class="time">{{ game.gameTime }}</span>
                    <span class="venue">{{ game.venue }}</span>
                </div>
                <h3 class="opponent">{{ game.opponent }}</h3>
                <div class="positions">
                    <span v-for="pos in game.requiredPositions" :key="pos" class="position-tag">
                        {{ pos.replace('_', ' ') }}
                    </span>
                </div>
            </li>
        </ul>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { getSchedule } from '@/api/schedule';

const props = defineProps({
    selectedGameId: String
});

const emit = defineEmits(['update:selectedGameId']);

const games = ref([]);
const loading = ref(true);
const error = ref(null);

const selectGame = (gameId) => {
    emit('update:selectedGameId', gameId);
};

onMounted(async () => {
    try {
        const response = await getSchedule();
        games.value = response.data;
    } catch (err) {
        error.value = err.message;
    } finally {
        loading.value = false;
    }
});
</script>

<style scoped>
.schedule-card {
    background: white;
    border-radius: 8px;
    padding: 1.5rem;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.game-list {
    list-style: none;
    padding: 0;
    display: grid;
    gap: 1rem;
}

.game-list li {
    padding: 1rem;
    border: 1px solid #ddd;
    border-radius: 4px;
    cursor: pointer;
}

.game-list li.active {
    border-color: #4d1979;
    background: #f0e6ff;
}

.game-meta {
    margin-bottom: 0.5rem;
    color: #666;
}

.game-meta span {
    margin-right: 1rem;
}

.opponent {
    margin: 0 0 0.5rem 0;
    color: #333;
}

.positions {
    display: flex;
    flex-wrap: wrap;
    gap: 0.5rem;
}

.position-tag {
    background: #f5f5f5;
    padding: 0.25rem 0.5rem;
    border-radius: 4px;
    font-size: 0.8rem;
}

.loading,
.error {
    padding: 1rem;
    text-align: center;
}
</style>