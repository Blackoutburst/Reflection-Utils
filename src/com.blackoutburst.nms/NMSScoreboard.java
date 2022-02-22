package com.blackoutburst.nms;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class NMSScoreboard {

    /**
     * Define all possible scoreboard slot
     */
    public enum DisplaySlot {
        PLAYER_LIST,
        SIDEBAR,
        BELOW_NAME
    }

    protected List<NMSScoreboardScore> lines;

    protected DisplaySlot slot;

    protected Object scoreboard;
    protected Object objective;

    private Class<?> scoreboardClass;
    private Class<?> objectiveClass;

    public List<NMSScoreboardScore> getLines() {
        return lines;
    }

    /**
     * Get the scoreboard slot
     *
     * @return the name of the display slot
     */
    public String getDisplaySlot() {
        try {
            final Method method = objectiveClass.getMethod("getSlotName", int.class);

            return (String) method.invoke(objective, slot.ordinal());
        } catch(Exception e) {
            e.printStackTrace();
        }
        return ("none");
    }

    /**
     * Get the scoreboard display name
     *
     * @return the scoreboard display name
     */
    public String getDisplayName() {
        try {
            final Method method = objectiveClass.getMethod("getDisplayName");

            return (String) method.invoke(objective);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return ("none");
    }

    /**
     * Create a new scoreboard
     */
    public NMSScoreboard() {
        try {
            scoreboardClass = NMS.getClass("Scoreboard");
            objectiveClass = NMS.getClass("ScoreboardObjective");

            final Constructor<?> scoreboardConstructor = scoreboardClass.getConstructor();

            scoreboard = scoreboardConstructor.newInstance();
            lines = new ArrayList<>();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Register a new objective for the scoreboard
     *
     * @param name the objective name
     * @param objective the objective type/criteria
     */
    public void registerObjective(String name, String objective) {
        try {
            final Class<?> criteriaClass = NMS.getClass("ScoreboardBaseCriteria");
            final Class<?> iCriteriaClass = NMS.getClass("IScoreboardCriteria");

            final Constructor<?> criteriaConstructor = criteriaClass.getConstructor(String.class);

            final Object criteria = criteriaConstructor.newInstance(objective);

            final Method method = scoreboardClass.getMethod("registerObjective", String.class, iCriteriaClass);

            this.objective = method.invoke(scoreboard, name, criteria);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the scoreboard display slot
     *
     * @param slot the display slot
     */
    public void setDisplaySlot(DisplaySlot slot) {
        try {
            final Method method = scoreboardClass.getMethod("setDisplaySlot", int.class, this.objectiveClass);

            method.invoke(scoreboard, slot.ordinal(), this.objective);
            this.slot = slot;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the scoreboard display name
     *
     * @param name the display name
     */
    public void setDisplayName(String name) {
        try {
            final Method method = objectiveClass.getMethod("setDisplayName", String.class);

            method.invoke(objective, name);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
