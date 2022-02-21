package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class NMSEntities {

    protected Object entity;

    public enum EntityType {
        WITHER("EntityWither");
        
        public String className;

        EntityType(String className) {
            this.className = className;
        }
    }

    public NMSEntities(Player player, EntityType type) {
        try {
            final Class<?> entityClass = NMS.getClass(type.className);
            final Class<?> worldClass = NMS.getClass("World");

            final Constructor<?> dragonConstructor = entityClass.getConstructor(worldClass);

            entity = dragonConstructor.newInstance(NMSWorld.getWorld(player));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
