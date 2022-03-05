package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class NMSSpawnEntity {

    /**
     * Allow you to spawn any kind of entity
     *
     * @param player the player that will receive the packet
     * @param entity the entity you wish to spawn
     */
    public static void send(Player player, NMSEntities entity, int data) {
        try {
            final Class<?> packetClass = NMS.getClass("PacketPlayOutSpawnEntity");
            final Class<?> entityClass = NMS.getClass("Entity");

            final Constructor<?> packetConstructor = packetClass.getConstructor(entityClass, int.class, int.class);

            final Object packet = packetConstructor.newInstance(entity.entity, entity.getNetworkID(), data);

            NMS.sendPacket(player, packet);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
