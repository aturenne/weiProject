<!-- Use Case 3 -->

<template>
    <div class="profile-view">
        <div v-if="loading" class="loading">Loading profile...</div>

        <div v-else-if="error" class="error-message">
            <p>Error loading profile.</p>
            <button @click="fetchProfile" class="retry-button">Retry</button>
        </div>

        <div v-else-if="profile" class="profile-card">
            <!-- Profile Header -->
            <div class="profile-header">
                <h2 class="profile-name">
                    {{ profile.firstName }} {{ profile.lastName }}
                </h2>
                <span class="profile-role">{{ profile.role }}</span>
            </div>

            <!-- Contact Info -->
            <div class="section">
                <h3 class="section-title">Contact</h3>
                <div class="detail-row">
                    <span class="detail-label">Email:</span>
                    <span class="detail-value">{{ profile.email }}</span>
                </div>
                <div class="detail-row">
                    <span class="detail-label">Phone:</span>
                    <span class="detail-value">{{ profile.phoneNumber }}</span>
                </div>
            </div>

            <!-- Positions -->
            <div class="section" v-if="profile.positions?.length">
                <h3 class="section-title">Qualified Positions</h3>
                <div class="positions-grid">
                    <span v-for="position in profile.positions" :key="position" class="position-tag">
                        {{ formatPosition(position) }}
                    </span>
                </div>
            </div>

            <!-- Back/Create Link -->
            <router-link to="/profile/create" class="action-link">
                Create New Profile
            </router-link>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { createCrewMember, getCrewMember } from '@/api/crew';

// Reactive state
const profile = ref(null);
const loading = ref(true);
const error = ref(null);
const route = useRoute();

// Helper functions
const formatPosition = (pos) => {
    return pos
        .replace('_', ' ')
        .toLowerCase()
        .replace(/\b\w/g, l => l.toUpperCase());
};

// Data fetching
const fetchProfile = async () => {
    try {
        loading.value = true;
        error.value = null;
        const response = await getCrewMember(route.params.id);
        profile.value = response.data;
    } catch (err) {
        error.value = err;
        console.error('Profile load error:', err);
    } finally {
        loading.value = false;
    }
};

onMounted(fetchProfile);

// Expose to template
defineExpose({
    formatPosition
});
</script>

<style scoped>
/* Layout */
.profile-view {
    max-width: 800px;
    margin: 0 auto;
    padding: 2rem;
}

.profile-card {
    background: white;
    border-radius: 8px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
    padding: 2rem;
}

/* Header */
.profile-header {
    margin-bottom: 2rem;
    padding-bottom: 1rem;
    border-bottom: 1px solid #eee;
}

.profile-name {
    color: #4d1979;
    /* TCU purple */
    margin: 0 0 0.5rem 0;
    font-size: 1.8rem;
}

.profile-role {
    display: inline-block;
    background: #f0e6ff;
    color: #4d1979;
    padding: 0.25rem 0.75rem;
    border-radius: 4px;
    font-size: 0.9rem;
    font-weight: 500;
}

/* Sections */
.section {
    margin-bottom: 2rem;
}

.section-title {
    color: #555;
    font-size: 1.1rem;
    margin-bottom: 1rem;
    padding-bottom: 0.5rem;
    border-bottom: 1px solid #f0f0f0;
}

/* Detail Rows */
.detail-row {
    display: grid;
    grid-template-columns: 120px 1fr;
    margin-bottom: 0.75rem;
}

.detail-label {
    font-weight: 600;
    color: #666;
}

.detail-value {
    color: #333;
}

/* Positions Grid */
.positions-grid {
    display: flex;
    flex-wrap: wrap;
    gap: 0.5rem;
}

.position-tag {
    background: #f5f5f5;
    color: #333;
    padding: 0.35rem 0.75rem;
    border-radius: 4px;
    font-size: 0.85rem;
}

/* Action Link */
.action-link {
    display: inline-block;
    margin-top: 1.5rem;
    color: #4d1979;
    text-decoration: none;
    font-weight: 500;
    transition: all 0.2s;
}

.action-link:hover {
    text-decoration: underline;
    color: #3a125c;
}

/* States */
.loading {
    text-align: center;
    color: #666;
    padding: 2rem;
}

.error-message {
    text-align: center;
    color: #d32f2f;
    padding: 2rem;
}

.retry-button {
    margin-top: 1rem;
    background: #4d1979;
    color: white;
    border: none;
    padding: 0.5rem 1rem;
    border-radius: 4px;
    cursor: pointer;
}
</style>