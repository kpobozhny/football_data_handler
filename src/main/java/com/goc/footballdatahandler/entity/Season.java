package com.goc.footballdatahandler.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by kostya on 10/26/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Season {
    private Integer id;
    private Calendar startDate;
    private Calendar endDate;
    private Integer currentMatchday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public Integer getCurrentMatchday() {
        return currentMatchday;
    }

    public void setCurrentMatchday(Integer currentMatchday) {
        this.currentMatchday = currentMatchday;
    }

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", currentMatchday=" + currentMatchday +
                '}';
    }
}
