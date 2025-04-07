<!-- Use Case 1 -->

<template>
    <div class="profile-form">
        <h2 class="header">Create Crew Profile</h2>
        <form @submit.prevent="handleSubmit" class="form">
            <!-- First Name -->
            <div class="form-group">
                <label for="firstName">First Name*</label>
                <input id="firstName" v-model="form.firstName" type="text" required placeholder="John">
            </div>

            <!-- Last Name -->
            <div class="form-group">
                <label for="lastName">Last Name*</label>
                <input id="lastName" v-model="form.lastName" type="text" required placeholder="Doe">
            </div>

            <!-- Email -->
            <div class="form-group">
                <label for="email">Email*</label>
                <input id="email" v-model="form.email" type="email" required placeholder="john.doe@tcu.edu">
            </div>

            <!-- Phone -->
            <div class="form-group">
                <label for="phone">Phone* (Format: 123-456-7890)</label>
                <input id="phone" v-model="form.phoneNumber" pattern="\d{3}-\d{3}-\d{4}" required
                    placeholder="123-456-7890">
                <small v-if="form.phoneNumber && !isValidPhone">Format: 123-456-7890</small>
            </div>

            <!-- Role -->
            <div class="form-group">
                <label for="role">Role*</label>
                <select id="role" v-model="form.role" required>
                    <option value="" disabled>Select a role</option>
                    <option value="PRODUCER">Producer</option>
                    <option value="DIRECTOR">Director</option>
                    <option value="CAMERA_OPERATOR">Camera Operator</option>
                </select>
            </div>

            <!-- Positions -->
            <div class="form-group">
                <fieldset>
                    <legend>Qualified Positions*</legend>
                    <div v-for="position in positionOptions" :key="position">
                        <input type="checkbox" :id="position" v-model="form.positions" :value="position">
                        <label :for="position">{{ position.replace('_', ' ') }}</label>
                    </div>
                </fieldset>
            </div>

            <!-- Submit -->
            <button type="submit" :disabled="isSubmitting">
                {{ isSubmitting ? 'Creating...' : 'Create Profile' }}
            </button>

            <!-- Success Message -->
            <div v-if="isSuccess">
                <p>Profile created successfully!</p>
                <router-link :to="`/profile/${newProfileId}`">View Profile</router-link>
            </div>
        </form>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';
import { createCrewMember, getCrewMember } from '@/api/crew';

const router = useRouter();
const isSubmitting = ref(false);
const isSuccess = ref(false);
const newProfileId = ref(null);

const form = ref({
    firstName: '',
    lastName: '',
    email: '',
    phoneNumber: '',
    role: '',
    positions: []
});

const positionOptions = [
    'DIRECTOR',
    'PRODUCER',
    'CAMERA_OPERATOR',
    'AUDIO_ENGINEER',
    'TECHNICAL_DIRECTOR'
];

const isValidPhone = computed(() =>
    /^\d{3}-\d{3}-\d{4}$/.test(form.value.phoneNumber)
);

const handleSubmit = async () => {
    if (!isValidPhone.value) return;

    isSubmitting.value = true;
    try {
        const response = await createCrewMember(form.value);
        newProfileId.value = response.data.id; // Assuming API returns ID
        isSuccess.value = true;

        // Reset form after delay
        setTimeout(() => {
            form.value = {
                firstName: '',
                lastName: '',
                email: '',
                phoneNumber: '',
                role: '',
                positions: []
            };
            isSuccess.value = false;
            isSubmitting.value = false;
        }, 3000);
    } catch (error) {
        alert(`Error: ${error.response?.data?.message || error.message}`);
        isSubmitting.value = false;
    }
};
</script>

<style>
.profile-form {
    max-width: 600px;
    margin: 2rem auto;
    padding: 2rem;
    background: white;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.header {
    color: #4d1979;
    /* TCU purple */
    text-align: center;
    margin-bottom: 1.5rem;
}

.form {
    display: grid;
    gap: 1.25rem;
}

.form-group label {
    display: block;
    margin-bottom: 0.5rem;
    font-weight: 600;
    color: #333;
}

.form-group input,
.form-group select {
    width: 100%;
    padding: 0.75rem;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 1rem;
}

.form-group input:focus {
    outline: 2px solid #4d1979;
}

button[type="submit"] {
    background: #4d1979;
    color: white;
    padding: 0.75rem 1.5rem;
    border: none;
    border-radius: 4px;
    font-size: 1rem;
    cursor: pointer;
    transition: background 0.2s;
}

button[type="submit"]:hover {
    background: #3a125c;
}

.positions-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
    gap: 0.75rem;
}
</style>