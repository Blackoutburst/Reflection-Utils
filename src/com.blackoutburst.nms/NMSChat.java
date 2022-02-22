package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class NMSChat {

    public enum MessageType {
        CHAT,
        SYSTEM,
        ACTION_BAR;
    }

    public static void send(Player player, String str, MessageType type) throws Exception {
        try {
            final Class<?> chatComponentClass = NMS.getClass("ChatComponentText");

            final Constructor<?> chatComponentText = chatComponentClass.getConstructor(String.class);

            final Object chatPacket = NMS.getClass("PacketPlayOutChat").getDeclaredConstructor().newInstance();

            NMS.setField(chatPacket, "a", chatComponentText.newInstance(str));
            NMS.setField(chatPacket, "b", (byte) type.ordinal());
            NMS.sendPacket(player, chatPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
