package com.goc.footballdatahandler.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by kostya on 9/30/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {
    private Integer id;
    private String name;

    public Team() {
    }

    public Team(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
