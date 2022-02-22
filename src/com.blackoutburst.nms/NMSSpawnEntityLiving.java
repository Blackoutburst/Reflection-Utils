package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class NMSSpawnEntityLiving {

    public static void send(Player player, NMSEntities entity) {
        try {
            final Class<?> packetClass = NMS.getClass("PacketPlayOutSpawnEntityLiving");
            final Class<?> entityLivingClass = NMS.getClass("EntityLiving");

            final Constructor<?> packetConstructor = packetClass.getConstructor(entityLivingClass);

            final Object packet = packetConstructor.newInstance(entity.entity);

            NMS.sendPacket(player, packet);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
