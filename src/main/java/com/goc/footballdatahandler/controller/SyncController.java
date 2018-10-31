package com.goc.footballdatahandler.controller;

import com.goc.footballdatahandler.database.Result;
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

    @Autowired
    Result result;

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
        ResponseEntity<Matches> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, Matches.class);

        List<Match> matches = response.getBody().getMatches();
        String competitionCode = response.getBody().getCompetition().getCode();

        boolean success = result.updateByFootballDataApi(competitionCode, matches);

        if (success) {
            return new Response("200 OK", response.getBody().toString());
        } else return new Response("500", "Failed to update data!");

    }

}
