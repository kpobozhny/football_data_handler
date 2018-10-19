package com.goc.footballdatahandler.entity.db;

public class TestTeam {

    private String teamCode;

    private String teamName;

    private String teamCountryCode;

    public TestTeam(String teamCode, String teamName, String teamCountryCode) {
        this.teamCode = teamCode;
        this.teamName = teamName;
        this.teamCountryCode = teamCountryCode;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamCountryCode() {
        return teamCountryCode;
    }

    public void setTeamCountryCode(String teamCountryCode) {
        this.teamCountryCode = teamCountryCode;
    }

    @Override
    public String toString() {
        return "TestTeam{" +
                "teamCode='" + teamCode + '\'' +
                ", teamName='" + teamName + '\'' +
                ", teamCountryCode='" + teamCountryCode + '\'' +
                '}';
    }
}
