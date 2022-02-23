package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class NMSEntityHeadRotation {

    /**
     * Rotate an entity head
     *
     * @param player the player that will receive the packet
     * @param entity the entity that will be rotated
     * @param rotation the head rotation
     */
    public static void send(Player player, NMSEntities entity, float rotation) {
        try {
            final Class<?> packetClass = NMS.getClass("PacketPlayOutEntityHeadRotation");
            final Class<?> entityClass = NMS.getClass("Entity");

            final Constructor<?> packetConstructor = packetClass.getConstructor(entityClass, byte.class);

            final Object packet = packetConstructor.newInstance(entity.entity, (byte) ((rotation * 256.0f) / 360.0f));

            NMS.sendPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
