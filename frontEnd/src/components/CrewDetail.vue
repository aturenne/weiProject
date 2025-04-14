<template>
    <div class="crew-detail-modal" @click.self="close">
        <div class="modal-content">
            <button class="close-btn" @click="close">×</button>

            <template v-if="loading">Loading...</template>
            <template v-else-if="error">{{ error }}</template>
            <template v-else>
                <h2>{{ member.firstName }} {{ member.lastName }}</h2>
                <div class="detail-grid">
                    <div class="detail-item">
                        <label>Email:</label>
                        <span>{{ member.email }}</span>
                    </div>
                    <div class="detail-item">
                        <label>Phone:</label>
                        <span>{{ formatPhone(member.phoneNumber) }}</span>
                    </div>
                    <div class="detail-item">
                        <label>Role:</label>
                        <span>{{ member.role }}</span>
                    </div>
                    <div class="detail-item">
                        <label>Positions:</label>
                        <div class="positions">
                            <span v-for="pos in member.positions" :key="pos" class="position-tag">
                                {{ pos.replace('_', ' ') }}
                            </span>
                        </div>
                    </div>
                </div>
            </template>
        </div>
    </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { getCrewMember } from '@/api/admin';

const route = useRoute();
const router = useRouter();
const member = ref(null);
const loading = ref(false);
const error = ref(null);

const formatPhone = (phone) => {
    return phone?.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3') || 'N/A';
};

//Fetch crew member data
const loadMemberData = async (userId) => {
    try {
        loading.value = true;
        const response = await getCrewMember(Number(userId));
        member.value = response.data;
    } catch (err) {
        error.value = err.message || 'Failed to load crew member';
    } finally {
        loading.value = false;
    }
};

//Close detail view and return to admin dashboard
const close = () => {
    router.push('/admin');
};

//load data when route changes
watch(
    () => route.params.userId,
    (newUserId) => {
        if (newUserId) loadMemberData(newUserId);
    },
    { immediate: true }
);
</script>

<style scoped>
.crew-detail-modal {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;
}

.modal-content {
    background: white;
    padding: 2rem;
    border-radius: 8px;
    max-width: 500px;
    width: 90%;
    position: relative;
}

.close-btn {
    position: absolute;
    top: 0.5rem;
    right: 0.5rem;
    font-size: 1.5rem;
    background: none;
    border: none;
    cursor: pointer;
}

.detail-grid {
    display: grid;
    grid-template-columns: max-content 1fr;
    gap: 1rem;
    margin-top: 1.5rem;
}

.detail-item label {
    font-weight: bold;
    color: #4d1979;
}

.positions {
    display: flex;
    flex-wrap: wrap;
    gap: 0.5rem;
}

.position-tag {
    background: #f0e6ff;
    color: #4d1979;
    padding: 0.25rem 0.5rem;
    border-radius: 4px;
    font-size: 0.85rem;
}
</style>