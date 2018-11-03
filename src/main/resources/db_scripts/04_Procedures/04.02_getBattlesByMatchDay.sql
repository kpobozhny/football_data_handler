-- Receiving a list of battles on a particular matchday of contest

DELIMITER $$

DROP PROCEDURE IF EXISTS getBattlesByMatchDay;
$$

CREATE PROCEDURE getBattlesByMatchDay(
  IN seasonCode VARCHAR(9),
  IN contestCode VARCHAR(20),
  IN matchDay TINYINT(4)

)
  BEGIN
    SELECT
      b.battleCode AS battleCode,
      b.hostUserCode AS hostUser,
      b.guestUserCode AS guestUser,
      getUserGoalsByBattleCode(b.hostUserCode, b.battleCode) AS hostGoals,
      getUserGoalsByBattleCode(b.guestUserCode, b.battleCode) AS guestGoals

    FROM Battle b

    WHERE b.seasonCode=seasonCode
      AND b.contestCode=contestCode
      AND b.contestMatchDay=matchDay;
  END$$

DELIMITER ;


