package com.goc.footballdatahandler.dictionary;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kostya on 10/29/18.
 */
public class FootballDataAPI {

    public static final Map<String, String> tournamentMap;
    public static final Map<String, String> teamMap;

    static
    {
        tournamentMap = new HashMap<String, String>();
        tournamentMap.put("PL", "ENPL");

        //England Teams
        teamMap = new HashMap<String, String>();
        teamMap.put("63", "ENG001");
        teamMap.put("328", "ENG002");
        teamMap.put("1044", "ENG003");
        teamMap.put("67", "ENG004");
        teamMap.put("345", "ENG005");
        teamMap.put("69", "ENG006");
        teamMap.put("68", "ENG007");
        teamMap.put("397", "ENG008");
        teamMap.put("332", "ENG009");
        teamMap.put("73", "ENG010");
        teamMap.put("351", "ENG011");
        teamMap.put("563", "ENG012");
        teamMap.put("66", "ENG013");
        teamMap.put("343", "ENG014");
        teamMap.put("387", "ENG015");
        teamMap.put("394", "ENG016");
        teamMap.put("70", "ENG017");
        teamMap.put("57", "ENG018");
        teamMap.put("322", "ENG019");
        teamMap.put("74", "ENG020");
        teamMap.put("76", "ENG021");
        teamMap.put("349", "ENG022");
        teamMap.put("58", "ENG023");
        teamMap.put("1072", "ENG024");
        teamMap.put("71", "ENG025");
        teamMap.put("342", "ENG026");
        teamMap.put("62", "ENG027");
        teamMap.put("64", "ENG028");
        teamMap.put("346", "ENG029");
        teamMap.put("75", "ENG030");
        teamMap.put("341", "ENG031");
        teamMap.put("340", "ENG032");
        teamMap.put("1081", "ENG033");
        teamMap.put("402", "ENG034");
        teamMap.put("338", "ENG035");
        teamMap.put("335", "ENG036");
        teamMap.put("354", "ENG037");
        teamMap.put("61", "ENG038");
        teamMap.put("385", "ENG039");
        teamMap.put("65", "ENG040");
        teamMap.put("357", "ENG041");
        teamMap.put("59", "ENG042");

        //Wales Teams
        teamMap.put("715", "WLS001");
        teamMap.put("72", "WLS002");
    }
}
