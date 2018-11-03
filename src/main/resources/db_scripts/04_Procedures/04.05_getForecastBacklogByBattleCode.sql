-- Get list of football matches, which are appointed to a specified battle 

DELIMITER $$

DROP PROCEDURE IF EXISTS getForecastBacklogByBattleCode;
$$

CREATE PROCEDURE getForecastBacklogByBattleCode(
  IN battleCode VARCHAR(24))
  BEGIN

 DECLARE contestSeasonCode VARCHAR(9);
 DECLARE contestCode VARCHAR(20);
 DECLARE contestMatchDay TINYINT(4);

 SELECT b.seasonCode INTO contestSeasonCode FROM Battle b WHERE b.battleCode=battleCode;
 SELECT b.contestCode INTO contestCode FROM Battle b WHERE b.battleCode=battleCode;
 SELECT b.contestMatchDay INTO contestMatchDay FROM Battle b WHERE b.battleCode=battleCode;


		SELECT 
				  t.tournamentName,
				  r.date,
				  ht.teamName AS hostTeam,
				  gt.teamName AS guestTeam,
                  r.resultCode
		FROM ForecastBacklog fb
			JOIN Result r ON fb.resultCode=r.resultCode
			JOIN Tournament t ON r.tournamentCode=t.tournamentCode
			JOIN Team ht ON r.hostTeamCode=ht.teamCode
			JOIN Team gt ON r.guestTeamCode=gt.teamCode
				WHERE fb.seasonCode = contestSeasonCode
				AND fb.contestCode = contestCode
				AND fb.contestMatchDay = contestMatchDay
                AND fb.resultCode NOT IN (SELECT DISTINCT fResultCode 
											FROM Forecast f
                                            WHERE f.battleCode=battleCode)
		ORDER BY r.date;
 
  END$$

DELIMITER ;
