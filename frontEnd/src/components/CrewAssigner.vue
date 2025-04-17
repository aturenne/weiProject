<!-- Use Case 23 -->

<template>
  <div class="crew-assigner">
    <h2>Crew Assignments for {{ game.opponent }}</h2>
    <p>{{ game.gameDate }} @ {{ game.venue }}</p>
    
    <div class="position-grid">
      <div v-for="position in availablePositions" :key="position" class="position-card">
        <h3>{{ position }}</h3>
        
        <select v-model="assignments[position]">
          <option value="">-- Select Crew --</option>
          <option 
            v-for="member in availableCrew" 
            :value="member.userId"
            :key="member.userId"
            :disabled="isAssignedElsewhere(member.userId, position)"
          >
            {{ member.fullName }}
            <span v-if="isAssignedElsewhere(member.userId, position)">
              (Already assigned)
            </span>
          </option>
        </select>
      </div>
    </div>
    
    <button @click="saveAssignments" :disabled="isSaving">
      {{ isSaving ? 'Saving...' : 'Save Assignments' }}
    </button>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { assignCrewToGame } from '@/api/scheduling';
import { getAllCrewMembers } from '@/api/admin';

const props = defineProps({
  game: {
    type: Object,
    required: true
  },
  crewMembers: {  // Make sure this prop is properly defined
    type: Array,
    required: true,
    default: () => []
  }
});

const emit = defineEmits(['assignments-saved']);

// Local state
const assignments = ref({});
const isSaving = ref(false);

// Available positions
const availablePositions = [
  'PRODUCER',
  'DIRECTOR',
  'CAMERA', 
  'AUDIO',
  'TECHNICAL_DIRECTOR'
];

// Initialize assignments when game changes
onMounted(() => {
  if (props.game.crewAssignments) {
    props.game.crewAssignments.forEach(assign => {
      assignments.value[assign.position] = assign.userId;
    });
  }
});

// Filter available crew (replace with your actual logic if needed)
const availableCrew = computed(() => props.crewMembers);

// Check if member is assigned elsewhere
const isAssignedElsewhere = (userId, currentPosition) => {
  return Object.entries(assignments.value)
    .some(([pos, id]) => pos !== currentPosition && id === userId);
};

// Save assignments
const saveAssignments = async () => {
  isSaving.value = true;
  try {
    const assignmentList = Object.entries(assignments.value)
      .filter(([_, userId]) => userId)
      .map(([position, userId]) => ({
        userId: Number(userId),
        position,
        gameId: Number(props.game.gameId)
      }));

    console.log('Submitting:', assignmentList);

    const response = await assignCrewToGame(
      Number(props.game.gameId),
      assignmentList
    );

     console.log('API Response:', response);
    
    if (response?.flag !== true) {
      throw new Error(response?.message || 'Failed to save assignments');
    }

    emit('assignments-saved', true);
  } catch (error) {
    console.error('Full error details:', {
      error,
      assignments: assignments.value,
      errorStack: error.stack
    });
    emit('assignments-saved', false); // Pass success=false
  } finally {
    isSaving.value = false;
  }
};
</script>


<style scoped>
.crew-assigner {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  margin-bottom: 2rem;
}
.position-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 1rem;
  margin: 1rem 0;
}
.position-card {
  border: 1px solid #eee;
  padding: 1rem;
  border-radius: 4px;
}
.position-card h3 {
  margin-top: 0;
  color: #4d1979;
}
select {
  width: 100%;
  padding: 0.5rem;
}
button:disabled {
  opacity: 0.7;
  cursor: not-allowed;
}
</style>