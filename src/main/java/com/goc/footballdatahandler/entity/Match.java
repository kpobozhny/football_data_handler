package com.goc.footballdatahandler.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Calendar;

/**
 * Created by kostya on 9/30/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match {
    private Integer id;
    private Season season;
    private Calendar utcDate;
    private Integer matchday;
    private String status;
    private Team homeTeam;
    private Team awayTeam;
    private Score score;

    public Match() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Season getSeason() { return season; }

    public void setSeason(Season season) { this.season = season; }

    public Calendar getUtcDate() {
        return utcDate;
    }

    public void setUtcDate(Calendar utcDate) {
        this.utcDate = utcDate;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public Integer getMatchday() {
        return matchday;
    }

    public void setMatchday(Integer matchday) {
        this.matchday = matchday;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", season=" + season +
                ", utcDate=" + utcDate +
                ", matchday=" + matchday +
                ", status='" + status + '\'' +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                ", score=" + score +
                '}';
    }
}
