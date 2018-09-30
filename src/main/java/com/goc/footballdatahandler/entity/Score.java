package com.goc.footballdatahandler.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by kostya on 9/30/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Score {
    private String winner;
    private ExactScore fullTime;
    private ExactScore halfTime;

    public Score() {
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public ExactScore getFullTime() {
        return fullTime;
    }

    public void setFullTime(ExactScore fullTime) {
        this.fullTime = fullTime;
    }

    public ExactScore getHalfTime() {
        return halfTime;
    }

    public void setHalfTime(ExactScore halfTime) {
        this.halfTime = halfTime;
    }

    @Override
    public String toString() {
        return "Score{" +
                "winner='" + winner + '\'' +
                ", fullTime=" + fullTime +
                ", halfTime=" + halfTime +
                '}';
    }
}
