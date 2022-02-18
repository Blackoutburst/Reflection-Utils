package com.blackoutburst.nms;

import org.bukkit.scoreboard.DisplaySlot;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class NMSScoreboard {

    public enum DisplaySlot {
        BELOW_NAME,
        PLAYER_LIST,
        SIDEBAR;
    }

    public Object scoreboard;
    public Object objective;

    private Class<?> scoreboardClass;

    public NMSScoreboard() {
        try {
            scoreboardClass = NMS.getClass("Scoreboard");

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
            final Class<?> objectiveClass = NMS.getClass("ScoreboardObjective");

            final Method method = scoreboardClass.getMethod("setDisplaySlot", int.class, objectiveClass);

            method.invoke(null, slot.ordinal(), this.objective);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
