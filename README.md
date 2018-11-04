## Readme


**/api/sync**

This service allows to update data in the 'gamesofchampions'-like database.
Data is taken from the resource: *api.football-data.org*

**Parameters:**
* *tournament*
    * allowed values:
         * ENPL (England, Premier League);
         * DEBL (Germany, Bundesliga1);
         * ITSA (Italy, Serie A);
         * ESLL (Spain, La Liga);
         * FRLG (France, Ligue 1).
    * if missed, 'ENPL' is used

* *season*
  * allowed values:
    * 2018 (Season 2018-2019);
    * 2017 (Season 2017-2018);
    * ...
  * if missed, a current season is used.

* *matchday*
  * allowed values: 1,2,3,...38 (depending of amount of matchdays of requested tournament).
  * if missed, all matchdays are included.

 #### Examples:
______________________________________________
**localhost:8080/api/sync**

Update data for:
* tournament: England, Premier League;
* season: 2018-2019;
* matchday: all (1-38).
_____________________________________________
**localhost:8080/api/sync&tournament=ITSA**

Update data for:
* tournament: Italy, Serie A;
* season: 2018-2019;
* matchday: all (1-38).
_____________________________________________
**localhost:8080/api/sync&tournament=ENPL?season=2016**

Update data for:
* tournament: England, Premier League;
* season: 2016-2017;
* matchday: all (1-38).
_____________________________________________
**localhost:8080/api/sync&tournament=ESLL?season=2018?matchday=15**

Update data for:
* tournament: Spain, La Liga;
* season: 2018-2019;
* matchday: 15.