DELIMITER $$

DROP PROCEDURE IF EXISTS setForecastPoints;
$$

CREATE PROCEDURE setForecastPoints(
  IN resultCode VARCHAR(24),
  IN goalsByHost  TINYINT(4),
  IN goalsByGuest  TINYINT(4)
)
  BEGIN
		update forecast f set f.fPoints = 0 where f.fResultCode=resultCode;
        update forecast f set f.fPoints = 1 where f.fResultCode=resultCode and f.fGoalsByHost-f.fGoalsByGuest<0 and goalsByHost-goalsByGuest<0;
		update forecast f set f.fPoints = 1 where f.fResultCode=resultCode and f.fGoalsByHost-f.fGoalsByGuest=0 and goalsByHost-goalsByGuest=0;
		update forecast f set f.fPoints = 1 where f.fResultCode=resultCode and f.fGoalsByHost-f.fGoalsByGuest>0 and goalsByHost-goalsByGuest>0;
        update forecast f set f.fPoints = 2 where f.fResultCode=resultCode and f.fGoalsByHost=goalsByHost and f.fGoalsByGuest=goalsByGuest;

  END$$

DELIMITER ;