<!--Use Case 7 -->
<template>
    <div class="availability-form" v-if="gameId">
        <h2>Submit Availability</h2>
        <form @submit.prevent="handleSubmit">
            <div class="form-group">
                <label class="checkbox-label">
                    <input type="checkbox" v-model="form.isAvailable" required class="checkbox-input">
                    <span class="checkmark"></span>
                    I am available for this game
                </label>
            </div>

            <div class="form-group">
                <label>Comment (Optional)</label>
                <textarea v-model="form.comment" placeholder="E.g. 'Only available for first half'"
                    maxlength="500"></textarea>
            </div>

            <button type="submit" :disabled="isSubmitting">
                {{ isSubmitting ? 'Submitting...' : 'Submit Availability' }}
            </button>

            <div v-if="submitSuccess" class="success-message">
                ✔ Availability submitted successfully!
            </div>
        </form>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { submitAvailability } from '@/api/schedule';

const props = defineProps({
    gameId: {
        type: String,
        required: true
    }
});

const form = ref({
    isAvailable: false,
    comment: ''
});

const isSubmitting = ref(false);
const submitSuccess = ref(false);

const handleSubmit = async () => {
    isSubmitting.value = true;
    try {
        //matches API endpoint structure
        await submitAvailability({
            userId: 'current-user-id', //replace with real auth later
            gameId: props.gameId,
            available: form.value.isAvailable,
            comment: form.value.comment
        });

        submitSuccess.value = true;
        setTimeout(() => submitSuccess.value = false, 3000);

        // Reset form (keep gameId)
        form.value.isAvailable = false;
        form.value.comment = '';

    } catch (error) {
        alert(`Submission failed: ${error.message}`);
    } finally {
        isSubmitting.value = false;
    }
};
</script>

<style scoped>
.availability-form {
    background: white;
    border-radius: 8px;
    padding: 1.5rem;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    margin-top: 1.5rem;
}

.form-group {
    margin-bottom: 1.5rem;
}

/* Custom checkbox styling */
.checkbox-label {
    display: flex;
    align-items: center;
    cursor: pointer;
    user-select: none;
}

.checkbox-input {
    position: absolute;
    opacity: 0;
    cursor: pointer;
}

.checkmark {
    display: inline-block;
    width: 18px;
    height: 18px;
    background-color: #fff;
    border: 2px solid #4d1979;
    border-radius: 3px;
    margin-right: 10px;
    position: relative;
}

.checkbox-input:checked~.checkmark {
    background-color: #4d1979;
}

.checkmark:after {
    content: "";
    position: absolute;
    display: none;
    left: 5px;
    top: 1px;
    width: 4px;
    height: 10px;
    border: solid white;
    border-width: 0 2px 2px 0;
    transform: rotate(45deg);
}

.checkbox-input:checked~.checkmark:after {
    display: block;
}

textarea {
    width: 100%;
    padding: 0.75rem;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-family: inherit;
}

button {
    background: #4d1979;
    color: white;
    border: none;
    padding: 0.75rem 1.5rem;
    border-radius: 4px;
    cursor: pointer;
    font-size: 1rem;
}

.success-message {
    color: #2e7d32;
    margin-top: 1rem;
    font-weight: 500;
}
</style>