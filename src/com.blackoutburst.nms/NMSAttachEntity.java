package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class NMSAttachEntity {

    public static void send(Player player, NMSEntities rider, NMSEntities mount) {
        try {
            Class<?> packetClass = NMS.getClass("PacketPlayOutAttachEntity");
            Class<?> entityClass = NMS.getClass("Entity");

            Constructor<?> packetConstructor = packetClass.getConstructor(int.class, entityClass, entityClass);

            Object packet = packetConstructor.newInstance(0, rider.entity, mount.entity);

            NMS.sendPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}