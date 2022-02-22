package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class NMSEntityTeleport {

    public static void send(Player player, NMSEntities entity, double x, double y, double z) {
        try {
            final Class<?> packetClass = NMS.getClass("PacketPlayOutEntityTeleport");
            final Class<?> entityClass = NMS.getClass("Entity");

            final Constructor<?> packetConstructor = packetClass.getConstructor(entityClass);

            final Method setPosition = entity.entity.getClass().getMethod("setPosition", double.class, double.class, double.class);
            setPosition.invoke(entity.entity, x, y, z);

            final Object packet = packetConstructor.newInstance(entity.entity);

            NMS.sendPacket(player, packet);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
