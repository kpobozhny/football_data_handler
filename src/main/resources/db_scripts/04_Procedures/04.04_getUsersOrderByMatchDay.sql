-- Get users positions by a particular matchday of a specified contest

DELIMITER $$

DROP PROCEDURE IF EXISTS getUsersOrderByMatchDay;
$$

CREATE PROCEDURE getUsersOrderByMatchDay(
  IN seasonCode     VARCHAR(9),
  IN contestCode VARCHAR(20),
  IN matchDay       TINYINT(4))
  BEGIN

    IF (matchDay <= 0)
    THEN
      SELECT
        'place',
        'userCode',
        'goalsScored',
        'goalsDiff',
        'points'
      LIMIT 0;
    ELSE

      SET @row_number = 0;

      SELECT
        CAST(@row_number := @row_number + 1 AS CHAR) AS place,
        userCode,
        won,
        drawn,
        lost,
        goalsScored,
        goalsDiff,
        points
      FROM (
             SELECT
               UserList.userCode,
               getUserWinsByMatchDay(UserList.userCode, seasonCode, contestCode, matchDay)         AS won,
               getUserDrawsByMatchDay(UserList.userCode, seasonCode, contestCode, matchDay)         AS drawn,
               getUserLosesByMatchDay(UserList.userCode, seasonCode, contestCode, matchDay)         AS lost,
               getUserPointsByMatchDay(UserList.userCode, seasonCode, contestCode, matchDay)         AS points,
               getUserGoalsTotalByMatchDay(UserList.userCode, seasonCode, contestCode, matchDay)     AS goalsScored,
               getUserGoalsDiffTotalByMatchDay(UserList.userCode, seasonCode, contestCode, matchDay) AS goalsDiff
             FROM (
                    SELECT DISTINCT
                      cu.userCode AS userCode
                    FROM ContestUsers cu
                    WHERE cu.contestCode = contestCode
                          AND cu.seasonCode = seasonCode) UserList
             ORDER BY points DESC,
               goalsDiff DESC,
               goalsScored DESC) UsersOrdered;
    END IF;
  END$$

DELIMITER ;
