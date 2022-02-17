package com.blackoutburst.nms;

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

    public static void send(String name, String displayName, String prefix, String suffix, TagVisibility tagVisibility, Collection<?> players) {

        try {
            final Class<?> chatComponentTextClass = NMS.getClass("ChatComponentText");
            final Class<?> packetClass = NMS.getClass("PacketPlayOutScoreboardTeam");

            final Constructor<?> teamConstructor = packetClass.getDeclaredConstructor();
            final Constructor<?> chatComponentTextConstructor  = chatComponentTextClass.getConstructor(String.class);

            final Object teamPacket = teamConstructor.newInstance();

            NMS.setField(teamPacket, "a", name); //teamName
            NMS.setField(teamPacket, "b", chatComponentTextConstructor.newInstance(displayName)); //displayName
            NMS.setField(teamPacket, "c", chatComponentTextConstructor.newInstance(prefix)); //prefix
            NMS.setField(teamPacket, "d", chatComponentTextConstructor.newInstance(suffix)); //suffix
            NMS.setField(teamPacket, "e", tagVisibility.name); //visibility
            NMS.setField(teamPacket, "h", players); //members
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
