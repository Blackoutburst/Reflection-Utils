package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class NMSEntityEquipment {

    public static void send(Player player, int entityID, int itemID, int slot) {
        try {
            final Class<?> packetClass = NMS.getClass("PacketPlayOutEntityEquipment");
            final Class<?> itemClass = NMS.getClass("Item");
            final Class<?> itemStackClass = NMS.getClass("ItemStack");

            final Constructor<?> packetConstructor = packetClass.getConstructor(int.class, int.class, itemStackClass);
            final Constructor<?> itemStackConstructor = itemStackClass.getConstructor(itemClass);

            final Object itemStack = itemStackConstructor.newInstance(itemClass.getMethod("getById", int.class).invoke(null, itemID));
            final Object packet = packetConstructor.newInstance(entityID, slot, itemStack);

            NMS.sendPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
