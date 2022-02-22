package com.blackoutburst.nms;

import org.bukkit.World;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class NMSEntities {

    protected Object entity;
    protected int ID;

    /**
     * Define all the entity types
     */
    public enum EntityType {
        WITHER("EntityWither");

        public final String className;

        EntityType(String className) {
            this.className = className;
        }
    }

    /**
     * Allow you to get the entity ID
     *
     * @return the entity ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Create a new entity instance
     *
     * @param world the world used to spawn the entity
     * @param type the type of entity that will be spawned
     */
    public NMSEntities(World world, EntityType type) {
        try {
            final Class<?> entityClass = NMS.getClass(type.className);
            final Class<?> worldClass = NMS.getClass("World");

            final Constructor<?> dragonConstructor = entityClass.getConstructor(worldClass);

            final Method getId = entity.getClass().getMethod("getId");

            entity = dragonConstructor.newInstance(NMSWorld.getWorld(world));

            ID = (int) getId.invoke(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}