// Mock database
const mockCrewDB = [
  {
    id: '1',
    firstName: 'Test',
    lastName: 'User',
    email: 'test@tcu.edu',
    phoneNumber: '817-555-1234',
    role: 'PRODUCER',
    positions: ['DIRECTOR']
  }
];

//Named exports
export const createCrewMember = async (data) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      const newMember = {
        id: String(mockCrewDB.length + 1),
        ...data
      };
      mockCrewDB.push(newMember);
      resolve({ data: newMember });
    }, 500);
  });
};

export const getCrewMember = async (userId) => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const member = mockCrewDB.find(m => m.id === userId);
      if (member) {
        resolve({ data: member });
      } else {
        reject(new Error('Crew member not found'));
      }
    }, 500);
  });
};