package com.goc.footballdatahandler.controller;

import com.goc.footballdatahandler.entity.Match;
import com.goc.footballdatahandler.entity.Matches;
import com.goc.footballdatahandler.entity.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.goc.footballdatahandler.dictionary.FootballDataAPI.teamMap;
import static com.goc.footballdatahandler.dictionary.FootballDataAPI.tournamentMap;


/**
 * Created by kostya on 9/29/18.
 */
@RestController
public class SyncController {


    private static final Logger log = LoggerFactory.getLogger(SyncController.class);


    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("api/sync")
    public Response sync(@RequestParam(value = "season", defaultValue = "2018") String season,
                         @RequestParam(value = "tournament", defaultValue = "PL") String tournament,
                         @RequestParam(value = "matchday", defaultValue = "") String matchday) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", "f59ef304bfaa44f6bfe56e13ad7290a4");
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString("http://api.football-data.org/v2/competitions/" + tournament + "/matches")
                // Add query parameter
                .queryParam("season", season)
                .queryParam("matchday", matchday);

        // Send request with GET method, and Headers.
        ResponseEntity<Matches> response = restTemplate.exchange(builder.toUriString(), //
                HttpMethod.GET, httpEntity, Matches.class);

        List<Match> matches = response.getBody().getMatches();
        String competitionCode = response.getBody().getCompetition().getCode();

        List<Object[]> batchInsert = new ArrayList<Object[]>();
        List<Object[]> batchUpdate = new ArrayList<Object[]>();

        for (Match match : matches) {
            String resultCode = competitionCode +
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

            Object[] updateValues = new Object[]{

                    new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(match.getUtcDate().getTime()),
                    match.getScore().getFullTime().getHomeTeam(),
                    match.getScore().getFullTime().getAwayTeam(),
                    resultCode
            };

/*            for (Object item : insertValues) {
                System.out.println(item);
            }*/

            batchInsert.add(insertValues);
            batchUpdate.add(updateValues);
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

        } catch (Exception e) {
            System.out.println("Failed to insert");

            try {

                jdbcTemplate.batchUpdate("UPDATE Result SET date=?, goalsByHost=?, goalsByGuest=? WHERE resultCode=?", batchUpdate);

            } catch (Exception ee) {
                System.out.println("Failed to update");
            }
        }


        return new Response("200 OK",
                response.getBody().toString());
    }

}
