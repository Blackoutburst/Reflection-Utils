package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class NMSScoreboardScorePacket {

    public static void send(Player player, NMSScoreboardScore scoreboard) {
        try {
            final Class<?> packetClass = NMS.getClass("PacketPlayOutScoreboardScore");
            final Class<?> scoreClass = NMS.getClass("ScoreboardScore");

            final Constructor<?> packetConstructor = packetClass.getConstructor(scoreClass);

            final Object packet = packetConstructor.newInstance(scoreboard.scoreboardScore);

            NMS.sendPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
