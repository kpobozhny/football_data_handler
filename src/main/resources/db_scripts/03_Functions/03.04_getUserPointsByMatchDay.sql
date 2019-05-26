DELIMITER $$
DROP FUNCTION IF EXISTS getUserPointsByMatchDay;
$$

CREATE FUNCTION getUserPointsByMatchDay(userCode   VARCHAR(10),
                                            seasonCode     VARCHAR(9),
                                            contestCode VARCHAR(20),
                                            matchDay       INT)
  RETURNS INT
DETERMINISTIC
  BEGIN
    DECLARE points INT DEFAULT 0;

    SELECT IFNULL(sum(tPoints), 0)
    INTO points
    FROM (
           SELECT CASE
                  WHEN getUserGoalsByBattleCode(b.hostUserCode, b.battleCode) > getUserGoalsByBattleCode(b.guestUserCode, b.battleCode) THEN 3
                  WHEN getUserGoalsByBattleCode(b.hostUserCode, b.battleCode) = getUserGoalsByBattleCode(b.guestUserCode, b.battleCode) THEN 1
                  ELSE 0
                  END AS tPoints
           FROM Battle b
           WHERE b.seasonCode = seasonCode
             AND b.contestCode = contestCode
             AND b.contestMatchDay <= matchDay
             AND b.battleStatusCode='ED'
             AND b.hostUserCode=userCode

           UNION ALL

           SELECT CASE
                  WHEN getUserGoalsByBattleCode(b.guestUserCode, b.battleCode) > getUserGoalsByBattleCode(b.hostUserCode, b.battleCode) THEN 3
                  WHEN getUserGoalsByBattleCode(b.guestUserCode, b.battleCode) = getUserGoalsByBattleCode(b.hostUserCode, b.battleCode) THEN 1
                  ELSE 0
                  END AS tPoints
           FROM Battle b
           WHERE b.seasonCode = seasonCode
             AND b.contestCode = contestCode
             AND b.contestMatchDay <= matchDay
			 AND b.battleStatusCode='ED'
             AND b.guestUserCode=userCode
         ) Points;

    RETURN points;
  END$$

DELIMITER ;
