package com.blackoutburst.nms;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class NMSScoreboardScore {

    protected Object scoreboardScore;

    protected String name;

    private Class<?> scoreClass;

    /**
     * Get the score name
     *
     * @return the score name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the scoreboard score, score
     *
     * @return the score
     */
    public int getScore() {
        try {
            final Method method = scoreClass.getMethod("getScore");

            return (int) method.invoke(scoreboardScore);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return (-1);
    }

    /**
     * Create a new scoreboard score
     *
     * @param scoreboard the scoreboard used to create the score
     * @param name the name of the score
     */
    public NMSScoreboardScore(NMSScoreboard scoreboard, String name) {
        try {
            scoreClass = NMS.getClass("ScoreboardScore");

            final Class<?> scoreboardClass = NMS.getClass("Scoreboard");
            final Class<?> scoreboardObjectiveClass = NMS.getClass("ScoreboardObjective");

            final Constructor<?> scoreboardConstructor = scoreClass.getConstructor(scoreboardClass, scoreboardObjectiveClass, String.class);

            scoreboardScore = scoreboardConstructor.newInstance(scoreboard.scoreboard, scoreboard.objective, name);
            this.name = name;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a new scoreboard score
     *
     * @param scoreboard the scoreboard used to create the score
     * @param name the name of the score
     * @param score the score of the score
     */
    public NMSScoreboardScore(NMSScoreboard scoreboard, String name, int score) {
        try {
            scoreClass = NMS.getClass("ScoreboardScore");

            final Class<?> scoreboardClass = NMS.getClass("Scoreboard");
            final Class<?> scoreboardObjectiveClass = NMS.getClass("ScoreboardObjective");

            final Constructor<?> scoreboardConstructor = scoreClass.getConstructor(scoreboardClass, scoreboardObjectiveClass, String.class);

            scoreboardScore = scoreboardConstructor.newInstance(scoreboard.scoreboard, scoreboard.objective, name);
            this.name = name;
            setScore(score);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the score of the scoreboard score
     *
     * @param value the new score
     */
    public void setScore(int value) {
        try {
            final Method method = scoreClass.getMethod("setScore", int.class);

            method.invoke(scoreboardScore, value);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
