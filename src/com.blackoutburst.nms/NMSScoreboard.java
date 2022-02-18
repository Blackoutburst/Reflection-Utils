package com.blackoutburst.nms;

import java.lang.reflect.Constructor;

public class NMSScoreboard {

    public Object scoreboard;

    public NMSScoreboard() {
        try {
            final Class<?> packetClass = NMS.getClass("Scoreboard");

            final Constructor<?> packetConstructor = packetClass.getConstructor();

            scoreboard = packetConstructor.newInstance();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
