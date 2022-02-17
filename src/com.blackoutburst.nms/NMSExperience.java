package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class NMSExperience {

    public static void send(Player player, float percent) {
        try {
            final Class<?> packetClass = NMS.getClass("PacketPlayOutExperience");

            final Constructor<?> packetConstructor = packetClass.getConstructor(float.class, int.class, int.class);

            final Object packet = packetConstructor.newInstance(percent, 0, 0);

            NMS.sendPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
