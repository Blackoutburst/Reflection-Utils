package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

public class NMSScoreboardTeam {

    public enum TagVisibility {
        ALWAYS("always"),
        NEVER("never"),
        HIDE_FOR_OTHER_TEAMS("hideForOtherTeams"),
        HIDE_FOR_OWN_TEAM("hideForOwnTeam");

        public final String name;

        TagVisibility(String name) {
            this.name = name;
        }
    }

    public static void send(Player player, String name, String displayName, String prefix, String suffix, TagVisibility tagVisibility, Collection<?> players) {
        try {
            final Class<?> chatComponentTextClass = NMS.getClass("ChatComponentText");
            final Class<?> packetClass = NMS.getClass("PacketPlayOutScoreboardTeam");

            final Constructor<?> teamConstructor = packetClass.getDeclaredConstructor();
            final Constructor<?> chatComponentTextConstructor  = chatComponentTextClass.getConstructor(String.class);

            final Object teamPacket = teamConstructor.newInstance();

            NMS.setField(teamPacket, "a", name);
            NMS.setField(teamPacket, "b", chatComponentTextConstructor.newInstance(displayName));
            NMS.setField(teamPacket, "c", chatComponentTextConstructor.newInstance(prefix));
            NMS.setField(teamPacket, "d", chatComponentTextConstructor.newInstance(suffix));
            NMS.setField(teamPacket, "e", tagVisibility.name);
            NMS.setField(teamPacket, "h", players);

            NMS.sendPacket(player, teamPacket);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
