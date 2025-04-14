<template>
    <div class="admin-view">
        <InviteForm @invited="refreshCrew" />
        <CrewTable :crew-members="crewMembers" @view-details="viewMemberDetails" @delete="openDeleteModal" />
        <DeleteModal v-if="memberToDelete" :member="memberToDelete" @confirm="handleDelete"
            @cancel="memberToDelete = null" />

        <div class="detail-container">
            <router-view v-slot="{ Component }">
                <component :is="Component" />
            </router-view>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { getAllCrewMembers, getCrewMember, deleteCrewMember } from '@/api/admin';
import InviteForm from '@/components/InviteForm.vue';
import CrewTable from '@/components/CrewTable.vue';
import DeleteModal from '@/components/DeleteModal.vue';

const router = useRouter();
const crewMembers = ref([]);
const memberToDelete = ref(null);

const refreshCrew = async () => {
    try {
        const response = await getAllCrewMembers();
        crewMembers.value = response.data;
    } catch (error) {
        console.error("Failed to load crew:", error);
    }
};

const viewMemberDetails = async (userId) => {
    //Push to route AND ensure data is loaded
    router.push(`/admin/crew/${userId}`);
};

const openDeleteModal = (member) => {  //receive full member object
    console.log('Member being deleted:', JSON.parse(JSON.stringify(member)));
    memberToDelete.value = member; 
};

//just deal with success/fail notification
const handleDelete = (deletedUserId) => {
    crewMembers.value = crewMembers.value.filter(m => m.userId !== deletedUserId);
    memberToDelete.value = null;

    if (router.currentRoute.value.params.userId == deletedUserId) {
        router.push('/admin');
    }
};

onMounted(refreshCrew);
</script>

<style scoped>
.admin-view {
    max-width: 1200px;
    margin: 0 auto;
    padding: 2rem;
}

.detail-container {
    margin-top: 2rem;
    border-top: 1px solid #eee;
    padding-top: 2rem;
}
</style>