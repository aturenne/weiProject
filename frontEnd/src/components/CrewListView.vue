<!-- Use Case 6 -->
<template>
    <div class="crew-card" v-if="gameDetails">
        <h2>Crew Assignment: {{ gameDetails.opponent }}</h2>
        <h3>{{ gameDetails.gameDate }} at {{ gameDetails.venue }}</h3>

        <div v-if="loading" class="loading">Loading crew list...</div>
        <div v-else-if="error" class="error">{{ error }}</div>

        <table v-else class="crew-table">
            <thead>
                <tr>
                    <th>Position</th>
                    <th>Name</th>
                    <th>Report Time</th>
                    <th>Location</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="member in gameDetails.crew" :key="member.position">
                    <td>{{ formatPosition(member.position) }}</td>
                    <td>{{ member.fullName || 'Not assigned' }}</td>
                    <td>{{ member.reportTime || '--:--' }}</td>
                    <td>{{ member.reportLocation || 'TBD' }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import { getCrewList } from '@/api/schedule';

const props = defineProps({
    gameId: {
        type: String,
        required: true
    }
});

const gameDetails = ref(null);
const loading = ref(false);
const error = ref(null);

const formatPosition = (pos) => {
    return pos.replace(/_/g, ' ').toLowerCase()
        .replace(/\b\w/g, l => l.toUpperCase());
};

watch(() => props.gameId, async (newId) => {
    if (!newId) return;

    loading.value = true;
    try {
        const response = await getCrewList(newId);
        gameDetails.value = response.data;
    } catch (err) {
        error.value = err.message;
    } finally {
        loading.value = false;
    }
}, { immediate: true });
</script>

<style scoped>
.crew-card {
    background: white;
    border-radius: 8px;
    padding: 1.5rem;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    margin-top: 1rem;
}

.crew-table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 1rem;
}

.crew-table th {
    background: #4d1979;
    color: white;
    padding: 0.75rem;
    text-align: left;
}

.crew-table td {
    padding: 0.75rem;
    border-bottom: 1px solid #eee;
}

.loading,
.error {
    padding: 1rem;
    text-align: center;
}
</style>