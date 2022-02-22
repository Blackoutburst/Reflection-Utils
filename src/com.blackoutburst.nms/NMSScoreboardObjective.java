package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class NMSScoreboardObjective {

    /**
     * The objective actions
     */
    public enum ObjectiveOption {
        CREATE,
        DELETE,
        EDIT
    }

    /**
     * Perform an action on a scoreboard objective
     *
     * @param player the player that will receive the packet
     * @param scoreboard the scoreboard containing the objective
     * @param option the action performed
     */
    public static void send(Player player, NMSScoreboard scoreboard, ObjectiveOption option) {
        try {
            final Class<?> packetClass = NMS.getClass("PacketPlayOutScoreboardObjective");
            final Class<?> objectiveClass = NMS.getClass("ScoreboardObjective");

            final Constructor<?> packetConstructor = packetClass.getConstructor(objectiveClass, int.class);

            final Object packet = packetConstructor.newInstance(scoreboard.objective, option.ordinal());

            NMS.sendPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
