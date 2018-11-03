DELIMITER $$
DROP FUNCTION IF EXISTS getUserGoalsByBattleCode;
$$

CREATE FUNCTION getUserGoalsByBattleCode(userCode       VARCHAR(10),
												battleCode VARCHAR(30))
  RETURNS INT
DETERMINISTIC
  BEGIN
    DECLARE goals INT DEFAULT 0;

    SELECT IFNULL(sum(userGoals), 0)
    INTO goals
    FROM (
           SELECT IFNULL(f.fPoints, 0) AS userGoals
           FROM Forecast f
           WHERE f.userCode=userCode
             AND f.battleCode=battleCode
         ) Goals;

    RETURN goals;
  END$$

DELIMITER ;
