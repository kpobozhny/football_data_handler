package com.goc.footballdatahandler.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by kostya on 10/27/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Competition {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "code='" + code + '\'' +
                '}';
    }
}
