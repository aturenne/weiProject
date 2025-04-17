<template>
<div class="scheduling-view">
    <!-- Header and View Toggle -->
    <div class="header">
        <h1>Scheduling Dashboard</h1>
        <div class="action-buttons">
            <button @click="openForm('schedule')">
                Create New Schedule
            </button>
            <button 
                @click="openForm('game')" 
                :disabled="!selectedScheduleId"
                class="add-game-btn"
            >
                + Add Game (Schedule #{{ selectedScheduleId }})
            </button>
        </div>
    </div>

    <!-- Dynamic Form Container (UC-18/20)-->
    <ScheduleEditor 
        v-if="activeForm"
        :mode="activeForm"
        :selected-schedule-id="selectedScheduleId"
        @schedule-created="handleScheduleCreated"
        @game-added="handleGameAdded"
        @cancel="activeForm = null"
    />

    <!-- Crew Assignment (UC-23) -->
    <div v-else>
        <div class="schedule-selector">
            <select v-model="selectedScheduleId">
            <option v-for="s in schedules" :value="s.id" :key="s.id">
                {{ s.sport }} - {{ s.season }} (ID: {{ s.id }})
            </option>
            </select>
        </div>

        <CrewAssigner 
            v-if="selectedGame"
            :game="selectedGame"
            :crew-members="crewMembers"
            @assignments-saved="handleAssignmentsSaved"
        />

        <div class="game-list">
            <div v-if="filteredGames.length === 0" class="no-games">
            No games scheduled yet. 
            </div>
            <div 
            v-for="game in filteredGames" 
            :key="game.gameId"
            @click="selectedGame = game"
            :class="{ active: selectedGame?.gameId === game.gameId }"
            >
            {{ game.opponent }} @ {{ game.venue }} ({{ game.gameDate }})
            </div>
        </div>
    </div>
</div>
</template>

<script setup>
import { ref, computed, watch, onMounted } from 'vue';
import { 
  createGameSchedule, 
  addGamesToSchedule, 
  assignCrewToGame,
  getAllSchedules,
  getGamesBySchedule
} from '@/api/scheduling';
import { getAllCrewMembers } from '@/api/admin';
import ScheduleEditor from '@/components/ScheduleEditor.vue';
import CrewAssigner from '@/components/CrewAssigner.vue';

//State
//const showCrewEditor = ref(false);
const showAddGameForm = ref(false);
const schedules = ref([]);
const selectedScheduleId = ref(null);
const games = ref([]);
const selectedGame = ref(null);
const crewMembers = ref([]);
const activeForm = ref(null); //'schedule' or 'game' or null

const openForm = (mode) => {
    if (mode === 'game' && !selectedScheduleId.value) {
    alert('Please select a schedule first');
    return;
  }
  //console.log('Opening form for:', mode, 'with schedule:', selectedScheduleId.value);
  activeForm.value = mode;
};

// watch(selectedScheduleId, (newId) => {
//   console.log('Selected Schedule ID:', newId);
//   console.log('Matching Schedule:', schedules.value.find(s => s.id === newId));
// });

//Computed
const filteredGames = computed(() => 
  games.value.filter(g => g.scheduleId == selectedScheduleId.value)
);

//Methods
const refreshSchedules = async () => {
  const res = await getAllSchedules();
  schedules.value = res.data;
  if (schedules.value.length && !selectedScheduleId.value) {
    selectedScheduleId.value = schedules.value[0].id; // Set first schedule as default
    console.log('Default schedule set to:', selectedScheduleId.value);
  }
};

const refreshGames = async () => {
  if (!selectedScheduleId.value) return;
  const res = await getGamesBySchedule(selectedScheduleId.value);
  games.value = res.data;
};

const refreshCrew = async () => {
  const res = await getAllCrewMembers();
  crewMembers.value = res.data;
};

const handleScheduleCreated = (newSchedule) => {

    activeForm.value = null;
    selectedScheduleId.value = newSchedule.id;
    selectedGame.value = null;
    games.value = [];
};

const handleAssignmentsSaved = async (success) => {
  if (success) {
    await refreshGames();
    alert('Assignments saved!');
  } else {
    alert('Failed to save assignments');
  }
};

const handleGameAdded = (newGame) => {
    activeForm.value = null;
    refreshGames();
};

watch(selectedGame, (newGame) => {
  if (newGame) refreshCrew();
});

watch(selectedScheduleId, (newId) => {
  //When schedule changes:
  //Clear selected game
  selectedGame.value = null;
  //load games for new schedule
  if (newId) refreshGames();
});

//lifecycle
onMounted(async () => {
  await refreshSchedules();
  await refreshCrew();
  if (selectedScheduleId.value) {
    await refreshGames();
  }
});
</script>

<style scoped>
.scheduling-view {
  padding: 2rem;
}
.header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 2rem;
}
.game-list {
  margin-top: 1rem;
  border: 1px solid #eee;
}
.game-list div {
  padding: 0.5rem;
  cursor: pointer;
}
.game-list div:hover {
  background: #f5f5f5;
}
.game-list div.active {
  background: #e6f7ff;
}
.no-games {
  padding: 1rem;
  text-align: center;
  color: #666;
}
.action-buttons {
  display: flex;
  gap: 1rem;
  align-items: center;
}

.add-game-btn {
  padding: 0.5rem 1rem;
  background: #4CAF50; /* Green */
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background 0.2s;
}

.add-game-btn:disabled {
  background: #cccccc;
  cursor: not-allowed;
}

.add-game-btn:not(:disabled):hover {
  background: #3e8e41;
}

.action-buttons > button:first-child {
  background: #4d1979; /* TCU purple */
  color: white;
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.action-buttons > button:first-child:hover {
  background: #3a125c;
}
</style>