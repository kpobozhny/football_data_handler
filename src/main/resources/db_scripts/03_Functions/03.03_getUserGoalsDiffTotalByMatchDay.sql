DELIMITER $$
DROP FUNCTION IF EXISTS getUserGoalsDiffTotalByMatchDay;
$$

CREATE FUNCTION getUserGoalsDiffTotalByMatchDay(userCode       VARCHAR(10),
                                            seasonCode     VARCHAR(9),
                                            contestCode VARCHAR(20),
                                            matchDay       INT)
  RETURNS INT
DETERMINISTIC
  
    BEGIN
    DECLARE goals INT DEFAULT 0;
    DECLARE opponentsGoals INT DEFAULT 0;

    SELECT IFNULL(sum(teamGoals), 0)
    INTO goals
    FROM (
           SELECT IFNULL(f.fPoints, 0) AS teamGoals
           FROM Forecast f
				JOIN Battle b ON f.battleCode=b.battleCode
           WHERE b.seasonCode = seasonCode
             AND b.contestCode = contestCode
             AND b.contestMatchDay <= matchDay
             AND f.userCode=userCode
             AND f.forecastStatusCode IN ('D', 'C')
         ) Goals;
         
        SELECT IFNULL(sum(opTeamGoals), 0)
    INTO opponentsGoals
    FROM (
           SELECT IFNULL(f.fPoints, 0) AS opTeamGoals
           FROM Forecast f
				JOIN Battle b ON f.battleCode=b.battleCode
           WHERE b.seasonCode = seasonCode
             AND b.contestCode = contestCode
             AND b.contestMatchDay <= matchDay
             AND (b.hostUserCode=userCode OR b.guestUserCode=userCode)
			 AND f.userCode!=userCode
             AND f.forecastStatusCode IN ('D', 'C')
         ) opGoals;     

    RETURN goals-opponentsGoals;
  END$$

DELIMITER ;
