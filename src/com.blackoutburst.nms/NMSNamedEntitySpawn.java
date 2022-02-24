package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class NMSNamedEntitySpawn {

    /**
     * Spawn a new entity
     *
     * @param player the player that will receive the packet
     * @param entity the entity that will be spawned
     */
    public static void send(Player player, NMSEntities entity) {
        try {
            final Class<?> packetClass = NMS.getClass("PacketPlayOutNamedEntitySpawn");
            final Class<?> entityHumanClass = NMS.getClass("EntityHuman");

            final Constructor<?> packetConstructor = packetClass.getConstructor(entityHumanClass);

            final Object packet = packetConstructor.newInstance(entity.entity);

            NMS.sendPacket(player, packet);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
