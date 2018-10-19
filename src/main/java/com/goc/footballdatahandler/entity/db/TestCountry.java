package com.goc.footballdatahandler.entity.db;

public class TestCountry {

    private String countryCode;

    private String countryName;

    public TestCountry(String countryCode, String countryName) {
        this.countryCode = countryCode;
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "TestCountry{" +
                "countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
