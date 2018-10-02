package com.goc.footballdatahandler;

/**
 * Created by kostya on 9/29/18.
 */


import com.goc.footballdatahandler.entity.Match;
import com.goc.footballdatahandler.entity.Matches;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@SpringBootApplication
public class FootballDataHandler {

    private static final Logger log = LoggerFactory.getLogger(FootballDataHandler.class);


    public static void main(String[] args) {

        SpringApplication.run(FootballDataHandler.class, args);
    }

/*    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Auth-Token", "f59ef304bfaa44f6bfe56e13ad7290a4");
            HttpEntity <String> httpEntity = new HttpEntity <String> (headers);
            //HttpEntity entity = new HttpEntity(headers);

            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromUriString("http://api.football-data.org/v2/competitions/PL/matches")
                    // Add query parameter
                    .queryParam("season", "2018");

            // Send request with GET method, and Headers.
            ResponseEntity<Matches> response = restTemplate.exchange(builder.toUriString(), //
                    HttpMethod.GET, httpEntity, Matches.class);

            //Matches matches = restTemplate.getForObject(builder.toUriString(), Matches.class);

            log.info(response.getBody().getMatches().get(0).toString());
        };
    }*/



}
