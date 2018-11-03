-- Get a list of football matches of a specified battle

DELIMITER $$

DROP PROCEDURE IF EXISTS getForecastListByBattleCode;
$$

CREATE PROCEDURE getForecastListByBattleCode(
  IN battleCode VARCHAR(24)
)
  BEGIN
  
	SELECT 	Main.tournamentName,
			Main.date,
			Main.hostTeam,
			Main.guestTeam,
			Main.hostForecast_GoalsByHost,
			Main.hostForecast_GoalsByGuest,
		    f1.fGoalsByHost AS guestForecast_GoalsByHost,
		    f1.fGoalsByGuest AS guestForecast_GoalsByGuest,            
			Main.result_GoalsByHost,
			Main.result_GoalsByGuest,
			Main.hostPoints,
            f1.fPoints AS guestPoints, 
            Main.tournamentCode,
			Main.hostTeamCode,
			Main.guestTeamCode,
			Main.resultCode,
			Main.forecastStatusCode,
            Main.battleCode,
            Main.hostUserCode,
            Main.guestUserCode
			FROM (
    
			SELECT
			  r.tournamentCode,
			  r.hostTeamCode AS hostTeamCode,
			  r.guestTeamCode AS guestTeamCode,
			  t.tournamentName,
			  r.date,
			  ht.teamName AS hostTeam,
			  gt.teamName AS guestTeam,
			  f.fGoalsByHost AS hostForecast_GoalsByHost,
			  f.fGoalsByGuest AS hostForecast_GoalsByGuest,
			  r.goalsByHost AS result_GoalsByHost,
			  r.goalsByGuest AS result_GoalsByGuest,
			  f.fPoints AS hostPoints,
			  f.forecastStatusCode,
              f.fResultCode AS resultCode,
              f.battleCode AS battleCode,
              b.hostUserCode AS hostUserCode,
              b.guestUserCode AS guestUserCode
			FROM Result r
				JOIN Tournament t ON r.tournamentCode=t.tournamentCode
				JOIN Team ht ON r.hostTeamCode=ht.teamCode
				JOIN Team gt ON r.guestTeamCode=gt.teamCode
				 JOIN Forecast f ON f.fResultCode=r.resultCode AND f.battleCode=battleCode
				 JOIN Battle b ON f.battleCode=b.battleCode  
			 WHERE b.hostUserCode=f.userCode) Main
             
       JOIN Forecast f1 ON f1.fResultCode=Main.resultCode 
						AND f1.battleCode=Main.battleCode 	
                        AND f1.userCode=Main.guestUserCode
	;
  END$$

DELIMITER ;


