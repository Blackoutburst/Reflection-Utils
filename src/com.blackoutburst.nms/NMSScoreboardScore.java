package com.blackoutburst.nms;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class NMSScoreboardScore {

    public Object scoreboardScore;

    public String name;
    public int score;

    private Class<?> scoreClass;

    public NMSScoreboardScore(NMSScoreboard scoreboard, String name) {
        try {
            scoreClass = NMS.getClass("ScoreboardScore");

            final Class<?> scoreboardClass = NMS.getClass("Scoreboard");
            final Class<?> scoreboardObjectiveClass = NMS.getClass("ScoreboardObjective");

            final Constructor<?> scoreboardConstructor = scoreClass.getConstructor(scoreboardClass, scoreboardObjectiveClass, String.class);

            scoreboardScore = scoreboardConstructor.newInstance(scoreboard, scoreboard.objective, name);
            this.name = name;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
