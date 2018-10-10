package com.goc.footballdatahandler.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Result {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer resultId;

    private String resultCode;

    private Date date;

    private String seasonCode;

    private String tournamentCode;

    private Integer matchDay;

    private String hostTeamCode;

    private String guestTeamCode;

    private Integer goalsByHost;

    private Integer goalsByGuest;

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSeasonCode() {
        return seasonCode;
    }

    public void setSeasonCode(String seasonCode) {
        this.seasonCode = seasonCode;
    }

    public String getTournamentCode() {
        return tournamentCode;
    }

    public void setTournamentCode(String tournamentCode) {
        this.tournamentCode = tournamentCode;
    }

    public Integer getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(Integer matchDay) {
        this.matchDay = matchDay;
    }

    public String getHostTeamCode() {
        return hostTeamCode;
    }

    public void setHostTeamCode(String hostTeamCode) {
        this.hostTeamCode = hostTeamCode;
    }

    public String getGuestTeamCode() {
        return guestTeamCode;
    }

    public void setGuestTeamCode(String guestTeamCode) {
        this.guestTeamCode = guestTeamCode;
    }

    public Integer getGoalsByHost() {
        return goalsByHost;
    }

    public void setGoalsByHost(Integer goalsByHost) {
        this.goalsByHost = goalsByHost;
    }

    public Integer getGoalsByGuest() {
        return goalsByGuest;
    }

    public void setGoalsByGuest(Integer goalsByGuest) {
        this.goalsByGuest = goalsByGuest;
    }
}
