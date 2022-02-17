package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class NMSParticles {

    public enum ParticleType {
        EXPLOSION_NORMAL("explode"),
        EXPLOSION_LARGE("largeexplode"),
        EXPLOSION_HUGE("hugeexplosion"),
        FIREWORKS_SPARK("fireworksSpark"),
        WATER_BUBBLE("bubble"),
        WATER_SPLASH("splash"),
        WATER_WAKE("wake"),
        SUSPENDED("suspended"),
        SUSPENDED_DEPTH("depthsuspend"),
        CRIT("crit"),
        CRIT_MAGIC("magicCrit"),
        SMOKE_NORMAL("smoke"),
        SMOKE_LARGE("largesmoke"),
        SPELL("spell"),
        SPELL_INSTANT("instantSpell"),
        SPELL_MOB("mobSpell"),
        SPELL_MOB_AMBIENT("mobSpellAmbient"),
        SPELL_WITCH("witchMagic"),
        DRIP_WATER("dripWater"),
        DRIP_LAVA("dripLava"),
        VILLAGER_ANGRY("angryVillager"),
        VILLAGER_HAPPY("happyVillager"),
        TOWN_AURA("townaura"),
        NOTE("note"),
        PORTAL("portal"),
        ENCHANTMENT_TABLE("enchantmenttable"),
        FLAME("flame"),
        LAVA("lava"),
        FOOTSTEP("footstep"),
        CLOUD("cloud"),
        REDSTONE("reddust"),
        SNOWBALL("snowballpoof"),
        SNOW_SHOVEL("snowshovel"),
        SLIME("slime"),
        HEART("heart"),
        BARRIER("barrier"),
        ITEM_CRACK("iconcrack_"),
        BLOCK_CRACK("blockcrack_"),
        BLOCK_DUST("blockdust_"),
        WATER_DROP("droplet"),
        ITEM_TAKE("take"),
        MOB_APPEARANCE("mobappearance");

        public final String name;

        ParticleType(String name) {
            this.name = name;
        }
    }

    public static void send(Player player, ParticleType particle, float x, float y, float z, float r, float g, float b, int count) {
        try {
            final Class<?> packetClass = NMS.getClass("PacketPlayOutWorldParticles");

            final Constructor<?> packetConstructor = packetClass.getDeclaredConstructor();

            final Object particlesPacket = packetConstructor.newInstance();

            NMS.setField(particlesPacket, "a", particle.name);
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
