-- Get a list of contests, in which the user is involved

DELIMITER $$

DROP PROCEDURE IF EXISTS getContestsByUser;
$$

CREATE PROCEDURE getContestsByUser(
  IN userCode VARCHAR(10)

)
  BEGIN
    SELECT
      ft.contestCode AS contestCode,
      ft.contestName AS contestName,
      ts.statusCode AS contestStatusCode,
      ts.statusName AS contestStatusName

    FROM Contest ft
      JOIN ContestStatus ts ON ft.contestStatusCode = ts.statusCode
      JOIN ContestUsers tu ON ft.contestCode = tu.contestCode
    WHERE tu.userCode = userCode;
  END$$

DELIMITER ;


