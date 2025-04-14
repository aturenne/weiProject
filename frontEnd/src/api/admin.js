const mockCrewDB = [
  {
    userId: 1,
    fullName: "John Doe",
    email: "john.doe@example.com",
    phoneNumber: "1234567890"
  },
  {
    userId: 2,
    fullName: "Jane Smith",
    email: "jane.smith@example.com",
    phoneNumber: "1112223333"
  },
  {
    userId: 3,
    fullName: "Bob Marley",
    email: "bob.marley@example.com",
    phoneNumber: "1231231234"
  }
];

export const getAllCrewMembers = async () => {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve({
        flag: true,
        code: 200,
        message: "Find Success",
        data: mockCrewDB
      });
    }, 500);
  });
};

export const getCrewMember = async (userId) => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const member = mockCrewDB.find(m => m.userId === userId);
      if (member) {
        resolve({       //returns crew member details
          flag: true,
          code: 200,
          message: "Find Success",
          data: {
            ...member,
            //Additional detail fields
            firstName: member.fullName.split(' ')[0],
            lastName: member.fullName.split(' ')[1],
            role: "CREW_MEMBER",
            positions: ["DIRECTOR", "PRODUCER"]
          }
        });
      } else {
        reject(new Error('Crew member not found'));
      }
    }, 500);
  });
};

export const inviteCrewMember = async (emails) => {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve({ data: emails }); //UC-14: Returns list of invited emails
    }, 500);
  });
};

export const deleteCrewMember = async (userId) => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
        console.log('Current mockDB:', mockCrewDB); // Debug log
        const index = mockCrewDB.findIndex(m => m.userId === userId);
        console.log('Found index:', index); // Debug log

        if (index >= 0) {
        const [deletedMember] = mockCrewDB.splice(index, 1);
        console.log('Deleted member:', deletedMember); // Debug log
        resolve({ data: { userId } });
      } else {
        console.error('Member not found in mockDB. Looking for:', userId);
        console.error('Available IDs:', mockCrewDB.map(m => m.userId));
        reject(new Error('Crew member not found'));
      }
    }, 500);
  });
};