<template>
    <div class="profiles-list">
        <h2>Crew Members</h2>
        <div v-if="loading">Loading profiles...</div>
        <div v-else-if="error">Error loading profiles</div>
        <ul v-else class="profile-cards">
            <li v-for="profile in profiles" :key="profile.id" class="profile-card">
                <router-link :to="`/profile/${profile.id}`" class="profile-link">
                    <h3>{{ profile.firstName }} {{ profile.lastName }}</h3>
                    <p>{{ profile.role }}</p>
                </router-link>
            </li>
        </ul>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { getAllCrewMembers } from '@/api/crew';

const profiles = ref([]);
const loading = ref(true);
const error = ref(null);

onMounted(async () => {
    try {
        const response = await getAllCrewMembers();
        profiles.value = response.data;
    } catch (err) {
        error.value = err;
    } finally {
        loading.value = false;
    }
});
</script>

<style scoped>
.profiles-list {
    max-width: 800px;
    margin: 0 auto;
    padding: 2rem;
}

.profile-cards {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
    gap: 1rem;
    list-style: none;
    padding: 0;
}

.profile-card {
    background: white;
    border-radius: 8px;
    padding: 1.5rem;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.profile-link {
    text-decoration: none;
    color: #4d1979;
}

.profile-link h3 {
    margin: 0 0 0.5rem 0;
}
</style>