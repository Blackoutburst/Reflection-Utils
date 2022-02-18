package com.blackoutburst.nms;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class NMSScoreboard {

    public Object scoreboard;
    public Object objective;

    private Class<?> packetClass;

    public NMSScoreboard() {
        try {
            packetClass = NMS.getClass("Scoreboard");

            final Constructor<?> packetConstructor = packetClass.getConstructor();

            scoreboard = packetConstructor.newInstance();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void registerObjective(String name, String objective) {
        try {
            final Class<?> criteriaClass = NMS.getClass("ScoreboardBaseCriteria");

            final Constructor<?> criteriaConstructor = criteriaClass.getConstructor(String.class);

            final Object criteria = criteriaConstructor.newInstance(objective);

            final Method method = packetClass.getMethod("registerObjective", String.class, criteriaClass);

            this.objective = method.invoke(null, name, criteria);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
