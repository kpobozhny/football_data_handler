package com.goc.footballdatahandler.entity;

/**
 * Created by kostya on 10/3/18.
 */
public class Response {
    private final String status;
    private final String content;

    public Response(String status, String content) {
        this.status = status;
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }


}
