<!-- Use Case 16 -->
<template>
    <div class="crew-table">
        <h2>Crew Members</h2>
        <table>
            <thead>
                <tr>
                    <th @click="sortByName">
                        <div class="header-content">
                            Name
                            <SortIcon :direction="sortDirection" />
                        </div>
                    </th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="member in sortedMembers" :key="member.userId" @click="$emit('view-details', member.userId)"
                    class="clickable-row">
                    <td>{{ member.fullName }}</td>
                    <td>{{ member.email }}</td>
                    <td>{{ formatPhone(member.phoneNumber) }}</td>
                    <td>
                        <button @click.stop="$emit('delete', member)" class="delete-btn"> 
                            Delete
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import SortIcon from '@/components/ui/SortIcon.vue';

const props = defineProps({
    crewMembers: {
        type: Array,
        default: () => []
    }
});

const sortDirection = ref('asc'); // 'asc' or 'desc'

const sortedMembers = computed(() => {
    return [...props.crewMembers].sort((a, b) => {
        const modifier = sortDirection.value === 'asc' ? 1 : -1;
        return a.fullName.localeCompare(b.fullName) * modifier;
    });
});

const sortByName = () => {
    sortDirection.value = sortDirection.value === 'asc' ? 'desc' : 'asc';
};

const formatPhone = (phone) => {
    return phone.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
};

defineEmits(['view-details', 'delete']);
</script>

<style scoped>
.crew-table {
    background: white;
    padding: 1.5rem;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

table {
    width: 100%;
    border-collapse: collapse;
}

th,
td {
    padding: 0.75rem;
    text-align: left;
    border-bottom: 1px solid #eee;
}

th {
    background: #4d1979;
    color: white;
    cursor: pointer;
    user-select: none;
}

.header-content {
    display: inline-flex;
    align-items: center;
    gap: 0.5rem;
}

.clickable-row:hover {
    background-color: #f9f5ff;
    cursor: pointer;
}

.delete-btn {
    background: #d32f2f;
    color: white;
    border: none;
    padding: 0.5rem 1rem;
    border-radius: 4px;
    cursor: pointer;
}

.delete-btn:hover {
    background: #b71c1c;
}
</style>