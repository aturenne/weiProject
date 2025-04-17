<!-- Use Case 18/20 -->

<template>
<div class="schedule-editor">
    <!-- UC-18: Create New Schedule -->
    <div v-if="mode === 'schedule'" class="create-schedule">
        <h2>Create New Schedule</h2>
        <form @submit.prevent="handleCreateSchedule">
        <div class="form-group">
            <label>Sport</label>
            <select v-model="scheduleForm.sport" required>
                <option value="Football">Football</option>
                <option value="Basketball">Basketball</option>
                <option value="Baseball">Baseball</option>
            </select>
        </div>
        
        <div class="form-group">
            <label>Season</label>
            <input 
                v-model="scheduleForm.season" 
                type="text" 
                placeholder="2024-2025" 
                required
            >
        </div>
        
        <button type="submit">Create Schedule</button>
        </form>
    </div>

    <!-- UC-20: Add Games to Schedule -->
    <div v-else class="add-game">
        <h2>Add Game to Schedule</h2>

        <form @submit.prevent="handleAddGame">
            <div class="form-group">
                <label>Opponent</label>
                <input v-model="gameForm.opponent" required>
            </div>
        
        <div class="form-group">
            <label>Venue</label>
            <input v-model="gameForm.venue" required>
        </div>
        
        <div class="form-group">
            <label>Date & Time</label>
            <input v-model="gameForm.gameDate" type="datetime-local" required>
        </div>
        
        <div class="form-group">
            <label>
                <input v-model="gameForm.isFinalized" type="checkbox">
                Finalized Schedule?
            </label>
        </div>
        
        <button type="submit">Add Game</button>
        </form>
    </div>
    <button @click="$emit('cancel')" class="cancel-btn">
        Back to Schedule
    </button>
</div>
</template>

<script setup>
import { ref } from 'vue';
import { createGameSchedule, addGamesToSchedule } from '@/api/scheduling';

const props = defineProps({
  mode: {
    type: String,
    required: true,
    validator: value => ['schedule', 'game'].includes(value)
  },
  selectedScheduleId: Number,
  default: null
});

const emit = defineEmits(['schedule-created', 'game-added', 'cancel']);

// UC-18 State
const scheduleForm = ref({
  sport: 'Football',
  season: ''
});

// UC-20 State
const gameForm = ref({
  opponent: '',
  venue: '',
  gameDate: '',
  isFinalized: false
});

// UC-18: Create Schedule
const handleCreateSchedule = async () => {
  try {
    if (!scheduleForm.value.sport || !scheduleForm.value.season) {
      alert('Please fill all required fields');
      return;
    }

    const response = await createGameSchedule({
      sport: scheduleForm.value.sport,
      season: scheduleForm.value.season
    });

    emit('schedule-created', response.data);
    scheduleForm.value = { sport: 'Football', season: '' };

  } catch (error) {
    console.error('Schedule creation failed:', error);
    alert('Failed to create schedule. Please try again.');
  }
};

// UC-20: Add Game
const handleAddGame = async () => {
  try {
    // Add validation
    if (!props.selectedScheduleId) {
      throw new Error('No schedule selected - please try again');
    }

    const response = await addGamesToSchedule(
      props.selectedScheduleId,
      gameForm.value
    ).catch(error => {
      console.error('API call failed:', error);
      throw error; // Re-throw to be caught by outer try/catch
    });

     if (!response?.data) {
      throw new Error('Invalid response from server');
    }

    console.log('Game added successfully:', response.data);
    emit('game-added', response.data);
    gameForm.value = { opponent: '', venue: '', gameDate: '', isFinalized: false };
  } catch (error) {
        console.error('Full error details:', {
      error,
      scheduleId: props.selectedScheduleId,
      formData: gameForm.value,
      errorStack: error.stack
    });
    alert(error.message);
  }
};
</script>

<style scoped>
.editor-container {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  max-width: 600px;
  margin: 0 auto;
}
.schedule-editor,
.add-game {
  display: grid;
  gap: 1.5rem;
}

.schedule-editor h2,
.add-game h2 {
  margin-top: 0;
  color: #4d1979; 
  font-size: 1.5rem;
  border-bottom: 2px solid #f0f0f0;
  padding-bottom: 0.5rem;
}

.form-group {
  display: grid;
  gap: 0.5rem;
}

.form-group label {
  font-weight: 600;
  color: #333;
}

.form-group input,
.form-group select {
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1rem;
}

.form-group input:focus,
.form-group select:focus {
  outline: none;
  border-color: #4d1979;
  box-shadow: 0 0 0 2px rgba(77, 25, 121, 0.2);
}

.form-actions {
  display: flex;
  gap: 1rem;
  margin-top: 1rem;
}

button[type="submit"] {
  background: #4d1979;
  color: white;
  border: none;
  padding: 0.75rem 1.5rem;
  border-radius: 4px;
  font-weight: 600;
  cursor: pointer;
  transition: background 0.2s;
}

button[type="submit"]:hover {
  background: #3a125c;
}

.cancel-btn {
  background: transparent;
  color: #666;
  border: 1px solid #ddd;
  padding: 0.75rem 1.5rem;
  border-radius: 4px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s;
  margin-top: 1rem;
}

.cancel-btn:hover {
  background: #f5f5f5;
  border-color: #ccc;
}

.add-game .form-group:last-child {
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.add-game .form-group:last-child label {
  font-weight: normal;
  margin-bottom: 0;
}
</style>