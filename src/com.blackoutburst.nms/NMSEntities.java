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
        ARMOR_STAND("EntityArmorStand"),
        ARROW("EntityArrow"),
        BAT("EntityBat"),
        BLAZE("EntityBlaze"),
        BOAT("EntityBoat"),
        CAVE_SPIDER("EntityCaveSpider"),
        CHICKEN("EntityChicken"),
        COW("EntityCow"),
        CREEPER("EntityCreeper"),
        EGG("EntityEgg"),
        ENDER_CRYSTAL("EntityEnderCrystal"),
        ENDER_DRAGON("EnityEnderDragon"),
        ENDERMAN("EntityEnderman"),
        ENDERMITE("EntityEndermite"),
        ENDER_PEARL("EntityEnderPearl"),
        EXPERIENCE_ORB("EntityExperienceOrb"),
        FALLING_BLOCK("EntityFallingBlock"),
        FIREWORK("EntityFireworks"),
        GHAST("EntityGhast"),
        GIANT("EntityGiantZombie"),
        GUARDIAN("EntityGuardian"),
        HORSE("EntityHorse"),
        IRON_GOLEM("EntityIronGolem"),
        ITEM("EntityItem"),
        ITEM_FRAME("EntityItemFrame"),
        LARGE_FIREBALL("EntityLargeFireball"),
        LIGHTNING("EntityLightning"),
        MAGMA_CUBE("EntityMagmaCube"),
        MINECART_CHEST("EntityMinecartChest"),
        MINECART_COMMAND_BLOCK("EntityMinecartCommandBlock"),
        MINECART_CONTAINER("EntityMinecartContainer"),
        MINECART_FURNACE("EntityMinecartFurnace"),
        MINECART_HOPPER("EntityMinecartHopper"),
        MINECART_MOB_SPAWNER("EntityMinecartMobSpawner"),
        MINECART_TNT("EntityMinecartTNT"),
        MUSHROOM_COW("EntityMushroomCow"),
        OCELOT("EntityOcelot"),
        PAINTING("EntityPainting"),
        PIG("EntityPig"),
        PIG_ZOMBIE("EntityPigZombie"),
        PLAYER("EntityPlayer"),
        POTION("EntityPotion"),
        RABBIT("EntityRabbit"),
        SHEEP("EntitySheep"),
        SILVERFISH("EntitySilverfish"),
        SKELETON("EntitySkeleton"),
        SLIME("EntitySlime"),
        SMALL_FIREBALL("EntitySmallFireball"),
        SNOWBALL("EntitySnowball"),
        SNOWMAN("EntitySnowman"),
        SPIDER("EntitySpider"),
        SQUID("EntitySquid"),
        EXPERIENCE_BOTTLE("EntityExperienceBottle"),
        TNT("EntityTNTPrimed"),
        VILLAGER("EntityVillager"),
        WITCH("EntityWitch"),
        WITHER("EntityWither"),
        WITHER_SKULL("EntityWitherSkull"),
        WOLF("EntityWolf"),
        ZOMBIE("EntityZombie");

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