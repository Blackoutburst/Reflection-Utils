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
        final Constructor<?> chatComponentText;

        final Class<?> typeChatComponentText = NMS.getClass("ChatComponentText");

        final Object targetDisantcePacket = NMS.getClass("PacketPlayOutChat").getDeclaredConstructor().newInstance();

        chatComponentText = typeChatComponentText.getConstructor(String.class);

        NMS.setField(targetDisantcePacket, "a", chatComponentText.newInstance(str));
        NMS.setField(targetDisantcePacket, "b", (byte) type.ordinal());
        NMS.sendPacket(player, targetDisantcePacket);
    }

}
