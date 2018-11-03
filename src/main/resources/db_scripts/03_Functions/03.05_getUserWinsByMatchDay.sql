DELIMITER $$
DROP FUNCTION IF EXISTS getUserWinsByMatchDay;
$$

CREATE FUNCTION getUserWinsByMatchDay(userCode   VARCHAR(10),
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
           SELECT IF(getUserGoalsByBattleCode(b.hostUserCode, b.battleCode) > getUserGoalsByBattleCode(b.guestUserCode, b.battleCode), 1, 0) AS tPoints
           FROM Battle b
           WHERE b.seasonCode = seasonCode
             AND b.contestCode = contestCode
             AND b.contestMatchDay <= matchDay
             AND b.battleStatusCode='ED'
             AND b.hostUserCode=userCode

           UNION ALL

           SELECT IF(getUserGoalsByBattleCode(b.guestUserCode, b.battleCode) > getUserGoalsByBattleCode(b.hostUserCode, b.battleCode), 1, 0) AS tPoints
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
