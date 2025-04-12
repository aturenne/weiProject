// Mock database
const mockScheduleDB = [
  {
    id: '1',  
    gameDate: '2023-11-15',
    gameTime: '19:00',
    venue: 'Amon G. Carter Stadium',
    opponent: 'Baylor Bears',
    requiredPositions: ['PRODUCER', 'DIRECTOR', 'CAMERA_OPERATOR'],
  }
];

//UC-5: Get Game Schedule
export const getSchedule = async () => {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve({ data: mockScheduleDB }); //Directly return the array
    }, 500);
  });
};

//UC-6: Get Crew List
export const getCrewList = async (gameId) => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      const game = mockScheduleDB.find(g => g.id === gameId);
      if (game) {
        resolve({ 
          data: {
            ...game, //Spread all game properties
            crew: [
              {
                position: "PRODUCER",
                fullName: "Mike Martin",
                reportTime: "08:00",
                reportLocation: "CONTROL ROOM" 
              },
              {
                position: "CAMERA_OPERATOR",
                fullName: "Jane Doe",
                reportTime: "07:30",
                reportLocation: "FIELD A" 
              }
            ]
          }
        });
      } else {
        reject(new Error('Game not found'));
      }
    }, 500);
  });
};

// UC-7: Submit Availability
export const submitAvailability = async (data) => {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve({
        data: {
          ...data,
          status: 'CONFIRMED',
          timestamp: new Date().toISOString()
        }
      });
    }, 500);
  });
};