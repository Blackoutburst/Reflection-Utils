package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class NMSEntityMove {

    public static void send(Player player, int entityID, byte x, byte y, byte z) {
        try {
            final Class<?> packetClass = NMS.getClass("PacketPlayOutEntity").getClasses()[1];

            final Constructor<?> packetConstructor = packetClass.getConstructor(int.class, byte.class, byte.class, byte.class, boolean.class);

            final Object packet = packetConstructor.newInstance(entityID, x, y, z, true);

            NMS.sendPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
