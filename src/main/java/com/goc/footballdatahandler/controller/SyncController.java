package com.goc.footballdatahandler.controller;

import com.goc.footballdatahandler.entity.Match;
import com.goc.footballdatahandler.entity.Matches;
import com.goc.footballdatahandler.entity.Response;
import com.goc.footballdatahandler.enums.TeamDictionary;
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


/**
 * Created by kostya on 9/29/18.
 */
@RestController
public class SyncController {

    private static final Map<String, String> tournamentDictionary;
    static
    {
        tournamentDictionary = new HashMap<String, String>();
        tournamentDictionary.put("PL", "ENG_PREM_LEAGUE");
    }

    private static final Logger log = LoggerFactory.getLogger(SyncController.class);


    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("api/sync")
    public Response sync(@RequestParam(value="season", defaultValue="2018") String season,
                         @RequestParam(value="tournament", defaultValue="PL") String tournament,
                         @RequestParam(value="matchday", defaultValue="") String matchday) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", "f59ef304bfaa44f6bfe56e13ad7290a4");
        HttpEntity<String> httpEntity = new HttpEntity <String> (headers);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString("http://api.football-data.org/v2/competitions/"+tournament+"/matches")
                // Add query parameter
                .queryParam("season", season)
                .queryParam("matchday", matchday);

        // Send request with GET method, and Headers.
        ResponseEntity<Matches> response = restTemplate.exchange(builder.toUriString(), //
                HttpMethod.GET, httpEntity, Matches.class);

        List<Match> matches = response.getBody().getMatches();
        String competitionCode = response.getBody().getCompetition().getCode();

        List<Object[]> batch = new ArrayList<Object[]>();
        for (Match match : matches) {
            String resultCode = competitionCode +
                    match.getSeason().getStartDate().get(Calendar.YEAR) +
                    match.getMatchday() +
                    TeamDictionary.retrieveByFootballDataId(match.getHomeTeam().getId()).name()+
                    TeamDictionary.retrieveByFootballDataId(match.getAwayTeam().getId()).name();



            Object[] values = new Object[] {
                    resultCode,
                    new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(match.getUtcDate().getTime()),
                    "S"+match.getSeason().getStartDate().get(Calendar.YEAR)+(match.getSeason().getStartDate().get(Calendar.YEAR)+1),
                    tournamentDictionary.get(competitionCode),
                    match.getMatchday(),
                    TeamDictionary.retrieveByFootballDataId(match.getHomeTeam().getId()).name(),
                    TeamDictionary.retrieveByFootballDataId(match.getAwayTeam().getId()).name(),
                    match.getScore().getFullTime().getHomeTeam(),
                    match.getScore().getFullTime().getAwayTeam()
            };

            for (Object item: values) {
                System.out.println(item);
            }

            batch.add(values);
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
                    "goalsByGuest) VALUES (?,?,?,?,?,?,?,?,?)", batch);

        }catch (Exception e){
            System.out.println("Failed to insert");
        }
        //log.info(response.getBody().getMatches().get(0).toString());

        //jdbcTemplate.update("INSERT INTO TestTeam(teamCode, teamName, teamCountryCode) VALUES (?,?,?)", new Object[]{"ENG004", "Arsenal", "ENG"} );


        return new Response("200 OK",
                response.getBody().toString());
    }

}
