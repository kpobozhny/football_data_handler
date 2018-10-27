package com.goc.footballdatahandler.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kostya on 9/29/18.
 */

//Matching api.football-data.org teams' codes to the gamesofchampions database teams' codes
public enum TeamDictionary {

    //England
    ENG001(63),
    ENG002(328),
    ENG003(1044),
    ENG004(67),
    ENG005(345),
    ENG006(69),
    ENG007(68),
    ENG008(397),
    ENG009(332),
    ENG010(73),
    ENG011(351),
    ENG012(563),
    ENG013(66),
    ENG014(343),
    ENG015(387),
    ENG016(394),
    ENG017(70),
    ENG018(57),
    ENG019(322),
    ENG020(74),
    ENG021(76),
    ENG022(349),
    ENG023(58),
    ENG024(1072),
    ENG025(71),
    ENG026(342),
    ENG027(62),
    ENG028(64),
    ENG029(346),
    ENG030(75),
    ENG031(341),
    ENG032(340),
    ENG033(1081),
    ENG034(402),
    ENG035(338),
    ENG036(355),
    ENG037(354),
    ENG038(61),
    ENG039(385),
    ENG040(65),
    ENG041(357),
    ENG042(59),

    //Wales
    WLS001(715),
    WLS002(72);

    private Integer footballDataId;
    TeamDictionary(Integer id){
        this.footballDataId=id;
    };

    private static final Map<Integer, TeamDictionary> LOOKUP_MAP;

    static {
        LOOKUP_MAP = new HashMap<Integer, TeamDictionary>();
        for (TeamDictionary teamCode : TeamDictionary.values()) {
            LOOKUP_MAP.put(teamCode.getFootballDataId(), teamCode);
        }
    }

    private TeamDictionary(int footballDataId) {
        this.footballDataId = footballDataId;
    }

    public Integer getFootballDataId() {
        return footballDataId;
    }

    public static TeamDictionary retrieveByFootballDataId(int n) {
        return LOOKUP_MAP.get(n);
    }

    @Override
    public String toString() {
        return "TeamDictionary{" +
                "footballDataId=" + footballDataId +
                '}';
    }
}
