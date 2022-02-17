package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class NMSParticles {

    public enum ParticleType {
        EXPLOSION_NORMAL,
        EXPLOSION_LARGE,
        EXPLOSION_HUGE,
        FIREWORKS_SPARK,
        WATER_BUBBLE,
        WATER_SPLASH,
        WATER_WAKE,
        SUSPENDED,
        SUSPENDED_DEPTH,
        CRIT,
        CRIT_MAGIC,
        SMOKE_NORMAL,
        SMOKE_LARGE,
        SPELL,
        SPELL_INSTANT,
        SPELL_MOB,
        SPELL_MOB_AMBIENT,
        SPELL_WITCH,
        DRIP_WATER,
        DRIP_LAVA,
        VILLAGER_ANGRY,
        VILLAGER_HAPPY,
        TOWN_AURA,
        NOTE,
        PORTAL,
        ENCHANTMENT_TABLE,
        FLAME,
        LAVA,
        FOOTSTEP,
        CLOUD,
        REDSTONE,
        SNOWBALL,
        SNOW_SHOVEL,
        SLIME,
        HEART,
        BARRIER,
        ITEM_CRACK,
        BLOCK_CRACK,
        BLOCK_DUST,
        WATER_DROP,
        ITEM_TAKE,
        MOB_APPEARANCE
    }

    public static void send(Player player, ParticleType particle, float x, float y, float z, float r, float g, float b, int count) {
        try {
            final Class enumParticles = NMS.getClass("EnumParticle");
            final Class<?> packetClass = NMS.getClass("PacketPlayOutWorldParticles");

            final Enum particleType = Enum.valueOf(enumParticles, particle.name());

            final Constructor<?> packetConstructor = packetClass.getDeclaredConstructors()[0];
            final Object particlesPacket = packetConstructor.newInstance();

            NMS.setField(particlesPacket, "a", particleType);
            NMS.setField(particlesPacket, "b", true);
            NMS.setField(particlesPacket, "c", x);
            NMS.setField(particlesPacket, "d", y);
            NMS.setField(particlesPacket, "e", z);
            NMS.setField(particlesPacket, "f", r);
            NMS.setField(particlesPacket, "g", g);
            NMS.setField(particlesPacket, "h", b);
            NMS.setField(particlesPacket, "i", count);

            NMS.sendPacket(player, particlesPacket);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
