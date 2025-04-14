<!-- Use Case 15 -->
<template>
    <div class="modal-overlay" @click.self="$emit('cancel')">
        <div class="modal-content">
            <h2>Confirm Deletion</h2>
            <p>Are you sure you want to delete {{ firstName }} {{ lastName }}?</p>
            <p class="email">{{ member.email }}</p>

            <div class="button-group">
                <button @click="$emit('cancel')" class="cancel-btn">
                    Cancel
                </button>
                <button @click="confirmDelete" class="confirm-btn" :disabled="isDeleting">
                    {{ isDeleting ? 'Deleting...' : 'Delete' }}
                </button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { deleteCrewMember } from '@/api/admin';

const props = defineProps({
    member: {
        type: Object,
        required: true
    }
});

const firstName = computed(() =>
    props.member.firstName || props.member.fullName?.split(' ')[0] || ''
);
const lastName = computed(() =>
    props.member.lastName || props.member.fullName?.split(' ')[1] || ''
);

const emit = defineEmits(['confirm', 'cancel']);
const isDeleting = ref(false);

const confirmDelete = async () => {
    console.log('Member props:', JSON.parse(JSON.stringify(props.member)));
    isDeleting.value = true;
    try {
        await deleteCrewMember(props.member.userId);
        emit('confirm', props.member.userId);
    } catch (error) {
        alert(`Deletion failed: ${error.message}`);
    } finally {
        isDeleting.value = false;
    }
};
</script>

<style scoped>
.modal-overlay {
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
}

.email {
    color: #666;
    margin-bottom: 1.5rem;
}

.button-group {
    display: flex;
    justify-content: flex-end;
    gap: 1rem;
}

.cancel-btn {
    background: #f5f5f5;
    border: 1px solid #ddd;
    padding: 0.75rem 1.5rem;
}

.confirm-btn {
    background: #d32f2f;
    color: white;
    padding: 0.75rem 1.5rem;
}
</style>