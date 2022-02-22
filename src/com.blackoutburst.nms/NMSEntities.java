package com.blackoutburst.nms;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class NMSEntities {

    protected Object entity;
    protected int ID;

    public enum EntityType {
        WITHER("EntityWither");

        public final String className;

        EntityType(String className) {
            this.className = className;
        }
    }

    public int getID() {
        return ID;
    }

    public NMSEntities(Player player, EntityType type) {
        try {
            final Class<?> entityClass = NMS.getClass(type.className);
            final Class<?> worldClass = NMS.getClass("World");

            final Constructor<?> dragonConstructor = entityClass.getConstructor(worldClass);

            final Method getId = entity.getClass().getMethod("getId");

            entity = dragonConstructor.newInstance(NMSWorld.getWorld(player));

            ID = (int) getId.invoke(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}