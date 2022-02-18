package com.blackoutburst.nms;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class NMSScoreboard {

    public enum DisplaySlot {
        BELOW_NAME,
        PLAYER_LIST,
        SIDEBAR
    }

    public DisplaySlot slot;
    public String name;

    public Object scoreboard;
    public Object objective;

    private Class<?> scoreboardClass;
    private Class<?> objectiveClass;

    public NMSScoreboard() {
        try {
            scoreboardClass = NMS.getClass("Scoreboard");
            objectiveClass = NMS.getClass("ScoreboardObjective");

            final Constructor<?> scoreboardConstructor = scoreboardClass.getConstructor();

            scoreboard = scoreboardConstructor.newInstance();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void registerObjective(String name, String objective) {
        try {
            final Class<?> criteriaClass = NMS.getClass("ScoreboardBaseCriteria");

            final Constructor<?> criteriaConstructor = criteriaClass.getConstructor(String.class);

            final Object criteria = criteriaConstructor.newInstance(objective);

            final Method method = scoreboardClass.getMethod("registerObjective", String.class, criteriaClass);

            this.objective = method.invoke(null, name, criteria);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setDisplaySlot(DisplaySlot slot) {
        try {
            final Method method = scoreboardClass.getMethod("setDisplaySlot", int.class, this.objectiveClass);

            method.invoke(null, slot.ordinal(), this.objective);

            this.slot = slot;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setDisplayName(String name) {
        try {
            final Method method = objectiveClass.getMethod("setDisplayName", String.class);

            method.invoke(null, name);

            this.name = name;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
