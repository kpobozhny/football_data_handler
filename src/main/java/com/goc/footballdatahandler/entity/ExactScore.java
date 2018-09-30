package com.goc.footballdatahandler.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by kostya on 9/30/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExactScore {

    private Integer homeTeam;
    private Integer awayTeam;

    public ExactScore() {
    }

    public Integer getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Integer homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Integer getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Integer awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Override
    public String toString() {
        return "ExactScore{" +
                "homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                '}';
    }
}
