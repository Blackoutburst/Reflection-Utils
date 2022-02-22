package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class NMSAnimation {

    /**
     * Make an entity play an animation
     *
     * @param player the player that will receive the packet
     * @param npc the entity that will play the animation
     * @param animation the animation id
     */
    public static void animation(Player player, NMSEntities npc, int animation) {
        try {
            final Class<?> packetClass = NMS.getClass("PacketPlayOutAnimation");
            final Class<?> entityClass = NMS.getClass("Entity");

            final Constructor<?> packetConstructor = packetClass.getConstructor(entityClass, int.class);

            final Object packet = packetConstructor.newInstance(npc.entity, animation);

            NMS.sendPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}