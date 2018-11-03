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
        tournamentMap.put("BL1", "DEBL");
        tournamentMap.put("SA", "ITSA");
        tournamentMap.put("PD", "ESLL");
        tournamentMap.put("FL1", "FRLG");

        teamMap = new HashMap<String, String>();

        //France Teams (Season 2018-19)
        teamMap.put("511", "FRA026");
        teamMap.put("514", "FRA037");
        teamMap.put("516", "FRA005");
        teamMap.put("518", "FRA030");
        teamMap.put("521", "FRA007");
        teamMap.put("522", "FRA013");
        teamMap.put("523", "FRA024");
        teamMap.put("524", "FRA017");
        teamMap.put("526", "FRA002");
        teamMap.put("527", "FRA039");
        teamMap.put("528", "FRA028");
        teamMap.put("529", "FRA033");
        teamMap.put("530", "FRA034");
        teamMap.put("532", "FRA003");
        teamMap.put("538", "FRA022");
        teamMap.put("543", "FRA020");
        teamMap.put("547", "FRA029");
        teamMap.put("548", "FRA012");
        teamMap.put("556", "FRA015");
        teamMap.put("576", "FRA032");

        //Spain Teams (Season 2018-19)
        teamMap.put("77", "ESP037");
        teamMap.put("78", "ESP039");
        teamMap.put("80", "ESP024");
        teamMap.put("81", "ESP027");
        teamMap.put("82", "ESP007");
        teamMap.put("86", "ESP018");
        teamMap.put("87", "ESP028");
        teamMap.put("88", "ESP004");
        teamMap.put("90", "ESP020");
        teamMap.put("92", "ESP040");
        teamMap.put("94", "ESP041");
        teamMap.put("95", "ESP042");
        teamMap.put("250", "ESP015");
        teamMap.put("263", "ESP023");
        teamMap.put("278", "ESP032");
        teamMap.put("298", "ESP016");
        teamMap.put("299", "ESP003");
        teamMap.put("558", "ESP022");
        teamMap.put("559", "ESP033");
        teamMap.put("745", "ESP008");


        //Germany Teams (Season 2018-19)
        teamMap.put("2", "DEU175");
        teamMap.put("3", "DEU031");
        teamMap.put("4", "DEU228");
        teamMap.put("5", "DEU137");
        teamMap.put("6", "DEU094");
        teamMap.put("8", "DEU058");
        teamMap.put("9", "DEU047");
        teamMap.put("10", "DEU219");
        teamMap.put("11", "DEU072");
        teamMap.put("12", "DEU109");
        teamMap.put("14", "DEU198");
        teamMap.put("15", "DEU067");
        teamMap.put("16", "DEU224");
        teamMap.put("17", "DEU063");
        teamMap.put("18", "DEU165");
        teamMap.put("19", "DEU091");
        teamMap.put("24", "DEU092");
        teamMap.put("721", "DEU160");

        //Italy Teams (Season 2018-19)
        teamMap.put("98", "ITA016");
        teamMap.put("99", "ITA046");
        teamMap.put("100", "ITA055");
        teamMap.put("102", "ITA021");
        teamMap.put("103", "ITA024");
        teamMap.put("104", "ITA059");
        teamMap.put("106", "ITA085");
        teamMap.put("107", "ITA007");
        teamMap.put("108", "ITA079");
        teamMap.put("109", "ITA027");
        teamMap.put("110", "ITA017");
        teamMap.put("112", "ITA023");
        teamMap.put("113", "ITA062");
        teamMap.put("115", "ITA092");
        teamMap.put("445", "ITA043");
        teamMap.put("470", "ITA031");
        teamMap.put("471", "ITA090");
        teamMap.put("584", "ITA093");
        teamMap.put("586", "ITA096");
        teamMap.put("1107", "ITA037");

        //England Teams
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

    public static String getTournamentByValue(String value) {
        for (String o : tournamentMap.keySet()) {
            if (tournamentMap.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }
}
