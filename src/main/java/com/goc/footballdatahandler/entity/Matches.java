package com.goc.footballdatahandler.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by kostya on 9/30/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Matches {
    private Competition competition;
    private List<Match> matches;

    public Matches() {
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "Matches{" +
                "competition=" + competition +
                ", matches=" + matches +
                '}';
    }
}
