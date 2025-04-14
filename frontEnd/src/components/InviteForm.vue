<!-- Use Case 14 -->
<template>
    <div class="invite-form">
        <h2>Invite Crew Members</h2>
        <form @submit.prevent="handleSubmit">
            <div class="form-group">
                <label>Email Addresses (comma-separated)</label>
                <textarea v-model="emailInput" placeholder="john@tcu.edu, jane@tcu.edu" required rows="3"></textarea>
            </div>
            <button type="submit" :disabled="isSubmitting">
                {{ isSubmitting ? 'Sending...' : 'Send Invites' }}
            </button>
            <div v-if="successMessage" class="success-message">
                {{ successMessage }}
            </div>
            <div v-if="error" class="error-message">
                {{ error }}
            </div>
        </form>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { inviteCrewMember } from '@/api/admin';

const emailInput = ref('');
const isSubmitting = ref(false);
const successMessage = ref('');
const error = ref('');

const handleSubmit = async () => {
    isSubmitting.value = true;
    error.value = '';

    try {
        const emails = emailInput.value
            .split(',')
            .map(email => email.trim())
            .filter(email => email.length > 0);

        const response = await inviteCrewMember(emails);
        successMessage.value = `Invites sent to: ${response.data.join(', ')}`;
        emailInput.value = '';

        setTimeout(() => successMessage.value = '', 3000);
    } catch (err) {
        error.value = err.message || 'Failed to send invites';
    } finally {
        isSubmitting.value = false;
    }
};
</script>

<style scoped>
.invite-form {
    background: white;
    padding: 1.5rem;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    margin-bottom: 2rem;
}

textarea {
    width: 100%;
    padding: 0.75rem;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-family: inherit;
}

.success-message {
    color: #2e7d32;
    margin-top: 1rem;
}

.error-message {
    color: #d32f2f;
    margin-top: 1rem;
}
</style>