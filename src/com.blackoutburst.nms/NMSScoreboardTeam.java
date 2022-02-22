package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

public class NMSScoreboardTeam {

    /**
     * Define all the possible team option
     */
    public enum TeamOption {
        CREATE,
        DELETE,
        EDIT,
        ADD_PLAYER,
        REMOVE_PLAYER
    }

    /**
     * Define the tag visibility
     */
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

    /**
     * Create a new team for a player
     *
     * @param player the player that will receive the packet
     * @param option the team option
     * @param name the name of the team
     * @param displayName the display name of the team
     * @param prefix the team prefix
     * @param suffix the team suffix
     * @param tagVisibility the team tag visibility
     * @param players the team members
     */
    public static void send(Player player, TeamOption option, String name, String displayName, String prefix, String suffix, TagVisibility tagVisibility, Collection<?> players) {
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
            NMS.setField(teamPacket, "i", option.ordinal());

            NMS.sendPacket(player, teamPacket);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
