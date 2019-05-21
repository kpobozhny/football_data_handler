-------------------
-- Football Data --
-------------------

-- create 'Country' table
CREATE TABLE `Country` (
  `countryId`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `countryCode` VARCHAR(3)   NOT NULL,
  `countryName` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`countryId`),
  UNIQUE KEY `countryCode` (`countryCode`)
)
  DEFAULT CHARSET = utf8;

-- create 'Season' table
CREATE TABLE `Season` (
  `seasonId`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `seasonCode` VARCHAR(9)   NOT NULL,
  `seasonName` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`seasonId`),
  UNIQUE KEY `seasonCode` (`seasonCode`)
)
  DEFAULT CHARSET = utf8;

-- create 'Tournament' table
CREATE TABLE `Tournament` (
  `tournamentId`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `tournamentCode` VARCHAR(20)  NOT NULL,
  `tournamentName` VARCHAR(255) NOT NULL,
  `countryCode`      VARCHAR(3)            DEFAULT NULL,
  PRIMARY KEY (`tournamentId`),
  UNIQUE KEY `tournamentCode` (`tournamentCode`),
  KEY `FK_countryCode` (`countryCode`),
  CONSTRAINT `FK_countryCode` FOREIGN KEY (`countryCode`) REFERENCES `Country` (`countryCode`)
)
  DEFAULT CHARSET = utf8;

-- create 'Team' table
CREATE TABLE `Team` (
  `teamId`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `teamCode` VARCHAR(6)   NOT NULL,
  `teamName` VARCHAR(255) NOT NULL,
  `teamCountryCode`      VARCHAR(3)            DEFAULT NULL,
  PRIMARY KEY (`teamId`),
  UNIQUE KEY `teamCode` (`teamCode`),
  UNIQUE KEY `teamName` (`teamName`),
  KEY `FK_teamCountryCode` (`teamCountryCode`),
  CONSTRAINT `FK_teamCountryCode` FOREIGN KEY (`teamCountryCode`) REFERENCES `Country` (`countryCode`)
)
  DEFAULT CHARSET = utf8;

-- create 'Result' table
CREATE TABLE `Result` (
  `resultId`     BIGINT(20) NOT NULL AUTO_INCREMENT,
  `resultCode`   VARCHAR(24)         DEFAULT NULL,
  `date`         DATETIME            DEFAULT NULL,
  `seasonCode`     VARCHAR(9) NOT NULL,
  `tournamentCode` VARCHAR(20) NOT NULL,
  `matchDay`     TINYINT(4) NOT NULL,
  `hostTeamCode`  VARCHAR(6) NOT NULL,
  `guestTeamCode`   VARCHAR(6) NOT NULL,
  `goalsByHost`  TINYINT(4)          DEFAULT NULL,
  `goalsByGuest` TINYINT(4)          DEFAULT NULL,
  PRIMARY KEY (`resultId`),
  UNIQUE KEY `resultCode` (`resultCode`),
  KEY `FK_guestTeamCode` (`guestTeamCode`),
  KEY `FK_hostTeamCode` (`hostTeamCode`),
  KEY `FK_seasonCode` (`seasonCode`),
  KEY `FK_tournamentCode` (`tournamentCode`),
  CONSTRAINT `FK_tournamentCode` FOREIGN KEY (`tournamentCode`) REFERENCES `Tournament` (`tournamentCode`),
  CONSTRAINT `FK_guestTeamCode` FOREIGN KEY (`guestTeamCode`) REFERENCES `Team` (`teamCode`),
  CONSTRAINT `FK_hostTeamCode` FOREIGN KEY (`hostTeamCode`) REFERENCES `Team` (`teamCode`),
  CONSTRAINT `FK_seasonCode` FOREIGN KEY (`seasonCode`) REFERENCES `Season` (`seasonCode`)
)
  DEFAULT CHARSET = utf8;

-------------------
-- Forecast Data --
-------------------

-- create 'User' table
CREATE TABLE `User` (
  `userId`    BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `userCode`  VARCHAR(10) NOT NULL, 
  `userEmail` VARCHAR(100) NOT NULL,
  `userPassword`  VARCHAR(255) NOT NULL,
  `userFirstName`  VARCHAR(255) NOT NULL,
  `userLastName`  VARCHAR(255) NOT NULL,
  `userCountryCode`    VARCHAR(3)    DEFAULT NULL,
  `active` BOOL NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userCode` (`userCode`),
  UNIQUE KEY `userEmail` (`userEmail`),  
  KEY `FK_userCountryCode` (`userCountryCode`),
  CONSTRAINT `FK_userCountryCode` FOREIGN KEY (`userCountryCode`) REFERENCES `Country` (`countryCode`)
)
  DEFAULT CHARSET = utf8;

-- create 'ContestSeason' table
CREATE TABLE `ContestSeason` (
  `id`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `seasonCode` VARCHAR(9)   NOT NULL,
  `seasonName` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `seasonCode` (`seasonCode`)
)
  DEFAULT CHARSET = utf8;

-- create 'ContestType' table
CREATE TABLE `ContestType` (
  `id`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `typeCode` VARCHAR(2)  NOT NULL,
  `typeDescription` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `typeCode` (`typeCode`)
)
  DEFAULT CHARSET = utf8;

-- create 'ContestStatus' table
CREATE TABLE `ContestStatus` (
  `id`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `statusCode` VARCHAR(2)  NOT NULL,
  `statusName` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `statusCode` (`statusCode`)
)
  DEFAULT CHARSET = utf8;

-- create 'Contest' table
CREATE TABLE `Contest` (
  `contestId`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `contestCode` VARCHAR(20)  NOT NULL,
  `contestName` VARCHAR(255) NOT NULL,
  `contestTypeCode` VARCHAR(2)  NOT NULL,
  `contestStatusCode` VARCHAR(2)  NOT NULL,
  PRIMARY KEY (`contestId`),
  UNIQUE KEY `contestCode` (`contestCode`),
  KEY `FK_contestTypeCode` (`contestTypeCode`),
  KEY `FK_contestStatusCode` (`contestStatusCode`),
  CONSTRAINT `FK_contestTypeCode` FOREIGN KEY (`contestTypeCode`) REFERENCES `ContestType` (`typeCode`),
  CONSTRAINT `FK_contestStatusCode` FOREIGN KEY (`contestStatusCode`) REFERENCES `ContestStatus` (`statusCode`)
)
  DEFAULT CHARSET = utf8;

-- create 'ContestUsers' table
CREATE TABLE `ContestUsers` (
  `Id`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `contestCode` VARCHAR(20)  NOT NULL,
  `seasonCode` VARCHAR(9)   NOT NULL,
  `userCode` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FTU_contestCode` (`contestCode`),
  KEY `FTU_seasonCode` (`seasonCode`),
  KEY `FTU_userCode` (`userCode`),
  CONSTRAINT `FTU_seasonCode` FOREIGN KEY (`seasonCode`) REFERENCES `ContestSeason` (`seasonCode`),
  CONSTRAINT `FTU_contestCode` FOREIGN KEY (`contestCode`) REFERENCES `Contest` (`contestCode`),
  CONSTRAINT `FTU_userCode` FOREIGN KEY (`userCode`) REFERENCES `User` (`userCode`)
)
  DEFAULT CHARSET = utf8;

-- create 'ForecastStatus' table
CREATE TABLE `ForecastStatus` (
  `statusId`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `statusCode` VARCHAR(2)  NOT NULL,
  `statusName` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`statusId`),
  UNIQUE KEY `statusCode` (`statusCode`)
)
  DEFAULT CHARSET = utf8;

-- create 'BattleStatus' table
CREATE TABLE `BattleStatus` (
  `statusId`   BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `statusCode` VARCHAR(2)  NOT NULL,
  `statusName` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`statusId`),
  UNIQUE KEY `statusCode` (`statusCode`)
)
  DEFAULT CHARSET = utf8;

-- create 'Battle' table
CREATE TABLE `Battle` (
  `battleId`     BIGINT(20) NOT NULL AUTO_INCREMENT,
  `battleCode`   VARCHAR(100) NOT NULL,
  `battleStatusCode` VARCHAR(2)  NOT NULL,
  `date`         DATETIME            DEFAULT NULL,
  `contestMatchDay`     TINYINT(4) NOT NULL,
  `seasonCode`     VARCHAR(9) NOT NULL,
  `contestCode` VARCHAR(20) NOT NULL,
  `hostUserCode`  VARCHAR(10) NOT NULL,
  `guestUserCode`   VARCHAR(10) NOT NULL,
  PRIMARY KEY (`battleId`),
  UNIQUE KEY `battleCode` (`battleCode`),
  UNIQUE KEY `battleRecord` (`contestMatchDay`, `seasonCode`, `contestCode`, `hostUserCode`, `guestUserCode`),
  KEY `FK_battleStatusCode` (`battleStatusCode`),
  KEY `FK_contestSeasonCode` (`seasonCode`),
  KEY `FK_contestCode` (`contestCode`),
  KEY `FK_hostUserCode` (`hostUserCode`),
  KEY `FK_guestUserCode` (`guestUserCode`),
  CONSTRAINT `FK_battleStatusCode` FOREIGN KEY (`battleStatusCode`) REFERENCES `BattleStatus` (`statusCode`),
  CONSTRAINT `FK_contestSeasonCode` FOREIGN KEY (`seasonCode`) REFERENCES `ContestSeason` (`seasonCode`),
  CONSTRAINT `FK_contestCode` FOREIGN KEY (`contestCode`) REFERENCES `Contest` (`contestCode`),
  CONSTRAINT `FK_hostUserCode` FOREIGN KEY (`hostUserCode`) REFERENCES `User` (`userCode`),
  CONSTRAINT `FK_guestUserCode` FOREIGN KEY (`guestUserCode`) REFERENCES `User` (`userCode`)
)
  DEFAULT CHARSET = utf8;

-- create 'Forecast' table
CREATE TABLE `Forecast` (
  `forecastId`     BIGINT(20) NOT NULL AUTO_INCREMENT,
  `forecastCode`   VARCHAR(40)       NOT NULL,
  `userCode`   VARCHAR(12)       NOT NULL,
  `forecastStatusCode` VARCHAR(2)  NOT NULL,
  `forecastModified`         DATETIME            DEFAULT NULL,
  `battleCode`   VARCHAR(100)         NOT NULL,
  `fResultCode`   VARCHAR(24)         NOT NULL,
  `fGoalsByHost` TINYINT(4)          DEFAULT NULL,
  `fGoalsByGuest`  TINYINT(4)          DEFAULT NULL,
  `fPoints`  TINYINT(2)          DEFAULT NULL,
  PRIMARY KEY (`forecastId`),
  UNIQUE KEY `forecastCode` (`forecastCode`),
  UNIQUE KEY `forecastRecord` (`userCode`, `battleCode`, `fResultCode`),
  KEY `FK_userCode` (`userCode`),
  KEY `FK_forecastStatusCode` (`forecastStatusCode`),
  KEY `FK_battleCode` (`battleCode`),
  KEY `FK_forecastResultCode` (`fResultCode`),
  CONSTRAINT `FK_userCode` FOREIGN KEY (`userCode`) REFERENCES `User` (`userCode`),
  CONSTRAINT `FK_forecastStatusCode` FOREIGN KEY (`forecastStatusCode`) REFERENCES `ForecastStatus` (`statusCode`),
  CONSTRAINT `FK_battleCode` FOREIGN KEY (`battleCode`) REFERENCES `Battle` (`battleCode`),
  CONSTRAINT `FK_forecastResultCode` FOREIGN KEY (`fResultCode`) REFERENCES `Result` (`resultCode`)
)
  DEFAULT CHARSET = utf8;

-- create 'ForecastBacklog' table
CREATE TABLE `ForecastBacklog` (
  `id`     BIGINT(20) NOT NULL AUTO_INCREMENT,
  `seasonCode`     VARCHAR(9) NOT NULL,
  `contestCode` VARCHAR(20) NOT NULL,
  `contestMatchDay`     TINYINT(4) NOT NULL,
  `resultCode`   VARCHAR(24)         DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `backlogItem` (`seasonCode`, `contestCode`, `resultCode`),
  KEY `FK_FB_seasonCode` (`seasonCode`),
  KEY `FK_FB_contestCode` (`contestCode`),
  KEY `FK_FB_resultCode` (`resultCode`),
  CONSTRAINT `FK_FB_seasonCode` FOREIGN KEY (`seasonCode`) REFERENCES `ContestSeason` (`seasonCode`),
  CONSTRAINT `FK_FB_contestCode` FOREIGN KEY (`contestCode`) REFERENCES `Contest` (`contestCode`),
  CONSTRAINT `FK_FB_resultCode` FOREIGN KEY (`resultCode`) REFERENCES `Result` (`resultCode`)
)
  DEFAULT CHARSET = utf8;

