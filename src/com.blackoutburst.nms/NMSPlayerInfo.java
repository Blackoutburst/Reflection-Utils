package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class NMSPlayerInfo {

    /**
     * Define all possible player actions
     */
    public enum PlayerInfoAction {
        ADD_PLAYER,
        UPDATE_GAME_MODE,
        UPDATE_LATENCY,
        UPDATE_DISPLAY_NAME,
        REMOVE_PLAYER
    }

    /**
     * Send player action packet
     * @param player the player that will receive the packet
     * @param action the action performed
     * @param entity the entity performing the action
     */
    public static void send(Player player, PlayerInfoAction action, NMSEntities entity) {
        try {
            final Class<?> packetClass = NMS.getClass("PacketPlayOutPlayerInfo");
            final Class<?> playerClass = NMS.getClass("EntityPlayer");
            final Class enumClass = NMS.getClass("PacketPlayOutPlayerInfo.EnumPlayerInfoAction");

            final Enum actionType = Enum.valueOf(enumClass, action.toString());

            final Constructor<?> packetConstructor = packetClass.getDeclaredConstructor(enumClass, Iterable.class);

           final List<Object> p = new ArrayList<>();
            p.add(entity.entity);

            final Object packet = packetConstructor.newInstance(actionType, p);

            NMS.sendPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
