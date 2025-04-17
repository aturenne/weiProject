const gameSchedulesDB = [
  {
    id: 1,
    sport: "Football",
    season: "2023-2024"
  }
];

const gamesDB = [
  {
    gameId: 1,
    scheduleId: 1,
    gameDate: "2023-09-10",
    venue: "Amon G. Carter Stadium",
    opponent: "Tarleton State",
    isFinalized: true
  }
];

const crewSchedulesDB = [
  {
    crewedUserId: 1,
    userId: 1,
    gameId: 1,
    position: "PRODUCER",
    fullName: "Mike Martin",
    reportTime: "08:00",
    reportLocation: "CONTROL ROOM"
  }
];

export const getAllSchedules = async () => {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve({
        flag: true,
        code: 200,
        message: "Find Success",
        data: gameSchedulesDB //Return mock schedules
      });
    }, 500);
  });
};

export const getGamesBySchedule = async (scheduleId) => {
  return new Promise(resolve => {
    setTimeout(() => {
      const scheduleGames = gamesDB.filter(g => g.scheduleId == scheduleId);
      resolve({
        flag: true,
        code: 200,
        message: "Find Success",
        data: scheduleGames
      });
    }, 500);
  });
};

export const createGameSchedule = async (scheduleData) => { //UC-18
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      if (!scheduleData.sport || !scheduleData.season) {
        reject({
          flag: false,
          code: 400,
          message: "Provided arguments are invalid, see data for details.",
          data: {
            sport: !scheduleData.sport ? "Sport is required" : undefined,
            season: !scheduleData.season ? "Season is required" : undefined
          }
        });
        return;
      }

      // Success case (matches 200 response)
      const newSchedule = {
        id: gameSchedulesDB.length + 1,
        sport: scheduleData.sport,
        season: scheduleData.season
      };
      gameSchedulesDB.push(newSchedule);
      
      resolve({
        flag: true,
        code: 200,
        message: "Add Success",
        data: newSchedule
      });
    }, 500);
  });
};

export const addGamesToSchedule = async (scheduleId, gameData) => { //UC-20
  //console.log('API called with:', { scheduleId, gameData }); // Debug

    return new Promise((resolve, reject) => {
    setTimeout(() => {
        try {
            //Validate schedule exists
            const scheduleExists = gameSchedulesDB.some(s => s.id == scheduleId);
            if (!scheduleExists) {
                reject({
                flag: false,
                code: 404,
                message: `Could not find schedule with id ${scheduleId}`,
                data: null
                });
            return;
            }

            // Validate input
            const errors = {};
            if (!gameData.gameDate) errors.gameDate = "Game date is required";
            if (!gameData.venue) errors.venue = "Venue is required";
            if (!gameData.opponent) errors.opponent = "Opponent is required";
            if (gameData.isFinalized === undefined) errors.isFinalized = "Is finalized is required";

            if (Object.keys(errors).length > 0) {
                reject({
                flag: false,
                code: 400,
                message: "Provided arguments are invalid, see data for details.",
                data: errors
                });
                return;
            }

            // Success case 
            const newGame = {
                gameId: gamesDB.length + 1,
                scheduleId: Number(scheduleId),
                gameDate: gameData.gameDate,
                venue: gameData.venue,
                opponent: gameData.opponent,
                isFinalized: gameData.isFinalized
            };
            gamesDB.push(newGame);

            resolve({
                flag: true,
                code: 200,
                message: "Add Success",
                data: newGame
            });
        } catch (error) {
        console.error('API error:', error);
        reject(error);
      }
    }, 500);
  });
};

export const assignCrewToGame = async (gameId, assignments) => { //UC-23
    console.log('[API] Assigning crew:', { gameId, assignments });
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      // Validate game exists 
      const gameExists = gamesDB.some(g => g.gameId == gameId);
      if (!gameExists) {
        reject({
          flag: false,
          code: 404,
          message: `Could not find game with id ${gameId}`,
          data: null
        });
        return;
      }

      // Validate assignments
      const errors = {};
      assignments.forEach((assign, i) => {
        if (!assign.userId) errors[`assignments[${i}].userId`] = "UserId is required";
        if (!assign.position) errors[`assignments[${i}].position`] = "Position is required";
      });

      if (Object.keys(errors).length > 0) {
        reject({
          flag: false,
          code: 400,
          message: "Provided arguments are invalid, see data for details.",
          data: errors
        });
        return;
      }

      

      // Success case
      const savedAssignments = assignments.map(assign => ({
        crewedUserId: crewSchedulesDB.length + 1,
        userId: assign.userId,
        gameId: Number(gameId),
        position: assign.position,
        fullName: "Mock Name", // Would come from crew data
        reportTime: "",
        reportLocation: "CONTROL ROOM"
      }));
      crewSchedulesDB.push(...savedAssignments);

      resolve({
        flag: true,
        code: 200,
        message: "Add Success",
        data: savedAssignments
      });
    }, 500);
  });
};

export { getAllCrewMembers } from '@/api/admin';