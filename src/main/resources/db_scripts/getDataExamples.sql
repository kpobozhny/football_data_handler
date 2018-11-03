SET @userCode='usr0000001';
SET @seasonCode='Season001';
SET @contestCode='KSTT_LEAGUE';
SET @contestMatchDay=1;
SET @battleCode='battle0000004';

-- Get a list of contests, in which the user is involved
CALL getContestsByUser(@userCode);

-- Get a list of battles on a particular matchday of contest
CALL getBattlesByMatchDay(@seasonCode, @contestCode, @contestMatchDay);

-- Get a list of football matches of a specified battle
CALL getForecastListByBattleCode(@battleCode);

-- Get users positions by a particular matchday of a specified contest
CALL getUsersOrderByMatchDay(@seasonCode, @contestCode, @contestMatchDay);

-- Get list of football matches, which are appointed to a specified battle (forecast backlog)
CALL getForecastBacklogByBattleCode(@battleCode);