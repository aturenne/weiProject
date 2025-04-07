import { ref } from 'vue';

// Mock database
const mockCrewDB = ref([  // Make it reactive with ref()
  {
    id: '1',
    firstName: 'Test',
    lastName: 'User',
    email: 'test@tcu.edu',
    phoneNumber: '817-555-1234',
    role: 'PRODUCER',
    positions: ['DIRECTOR']
  }
]);

export const createCrewMember = async (data) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      const newMember = {
        id: String(mockCrewDB.value.length + 1),
        ...data
      };
      mockCrewDB.value.push(newMember);
      resolve({ data: newMember });
    }, 500);
  });
};

export const getAllCrewMembers = async () => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve({ data: mockCrewDB.value });
    }, 500);
  });
};

export const getCrewMember = async (userId) => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      //Access .value to get the array
      const member = mockCrewDB.value.find(m => m.id === userId);
      if (member) {
        resolve({ data: member });
      } else {
        reject(new Error('Crew member not found'));
      }
    }, 500);
  });
};