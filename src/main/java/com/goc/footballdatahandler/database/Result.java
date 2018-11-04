package com.goc.footballdatahandler.database;

import com.goc.footballdatahandler.entity.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static com.goc.footballdatahandler.dictionary.FootballDataAPI.teamMap;
import static com.goc.footballdatahandler.dictionary.FootballDataAPI.tournamentMap;

/**
 * Created by kostya on 10/30/18.
 */
@Controller
public class Result {

    private static final Logger log = LoggerFactory.getLogger(Result.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean updateByFootballDataApi(String competitionCode, List<Match> matches){

        List<Object[]> batchInsert = new ArrayList<Object[]>();
        List<Object[]> batchUpdate = new ArrayList<Object[]>();

        for (Match match : matches) {
            String resultCode = tournamentMap.get(competitionCode) +
                    match.getSeason().getStartDate().get(Calendar.YEAR) +
                    match.getMatchday() +
                    teamMap.get(match.getHomeTeam().getId().toString()) +
                    teamMap.get(match.getAwayTeam().getId().toString());

            Object[] insertValues = new Object[]{
                    resultCode,
                    new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(match.getUtcDate().getTime()),
                    "S" + match.getSeason().getStartDate().get(Calendar.YEAR) + (match.getSeason().getStartDate().get(Calendar.YEAR) + 1),
                    tournamentMap.get(competitionCode),
                    match.getMatchday(),
                    teamMap.get(match.getHomeTeam().getId().toString()),
                    teamMap.get(match.getAwayTeam().getId().toString()),
                    match.getScore().getFullTime().getHomeTeam(),
                    match.getScore().getFullTime().getAwayTeam()
            };
            batchInsert.add(insertValues);

            if(match.getStatus().equals("FINISHED")) {

                Object[] updateValues = new Object[]{
                        new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(match.getUtcDate().getTime()),
                        match.getScore().getFullTime().getHomeTeam(),
                        match.getScore().getFullTime().getAwayTeam(),
                        resultCode
                };
                batchUpdate.add(updateValues);
            }
         }

        try {
            jdbcTemplate.batchUpdate("INSERT INTO Result(" +
                    "resultCode, " +
                    "date, " +
                    "seasonCode, " +
                    "tournamentCode, " +
                    "matchDay, " +
                    "hostTeamCode, " +
                    "guestTeamCode, " +
                    "goalsByHost, " +
                    "goalsByGuest) VALUES (?,?,?,?,?,?,?,?,?)", batchInsert);

            log.info("Results have been inserted successfully.");
            return true;

        } catch (Exception e) {
            try {
                jdbcTemplate.batchUpdate("UPDATE Result SET date=?, goalsByHost=?, goalsByGuest=? WHERE resultCode=?", batchUpdate);
                log.info("Results have been updated successfully.");
                return true;
            } catch (Exception ee) {
                log.info("Failed to update results");
                return false;
            }
        }
    }
}