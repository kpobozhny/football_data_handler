package com.goc.footballdatahandler;

/**
 * Created by kostya on 9/29/18.
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class FootballDataHandler {

    private static final Logger log = LoggerFactory.getLogger(FootballDataHandler.class);


    public static void main(String[] args) {

        SpringApplication.run(FootballDataHandler.class, args);
    }

}
