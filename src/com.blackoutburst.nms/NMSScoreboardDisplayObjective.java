package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class NMSScoreboardDisplayObjective {

    /**
     * Display a new scoreboard objective for a player
     *
     * @param player the player that will receive the packet
     * @param scoreboard the scoreboard containing the objective
     */
    public static void send(Player player, NMSScoreboard scoreboard) {
        try {
            final Class<?> packetClass = NMS.getClass("PacketPlayOutScoreboardDisplayObjective");
            final Class<?> objectiveClass = NMS.getClass("ScoreboardObjective");

            final Constructor<?> packetConstructor = packetClass.getConstructor(int.class, objectiveClass);

            final Object packet = packetConstructor.newInstance(scoreboard.slot.ordinal(), scoreboard.objective);

            NMS.sendPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
