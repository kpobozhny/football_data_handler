DELIMITER $$

DROP TRIGGER IF EXISTS updateForecastOnResultUpdate;
$$

CREATE TRIGGER updateForecastOnResultUpdate AFTER UPDATE ON result
FOR EACH ROW
BEGIN
		CALL setForecastPoints(NEW.resultCode, NEW.goalsByHost, NEW.goalsByGuest);
END;$$
DELIMITER ;