package com.blackoutburst.nms;

import org.bukkit.World;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class NMSEntities {

    protected Object entity;

    /**
     * Define all the entity types
     */
    public enum EntityType {
        ARMOR_STAND("EntityArmorStand", NMS.getClass("World")),
        ARROW("EntityArrow", NMS.getClass("World"), double.class, double.class, double.class),
        BAT("EntityBat", NMS.getClass("World")),
        BLAZE("EntityBlaze", NMS.getClass("World")),
        BOAT("EntityBoat", NMS.getClass("World")),
        CAVE_SPIDER("EntityCaveSpider", NMS.getClass("World")),
        CHICKEN("EntityChicken", NMS.getClass("World")),
        COW("EntityCow", NMS.getClass("World")),
        CREEPER("EntityCreeper", NMS.getClass("World")),
        EGG("EntityEgg", NMS.getClass("World"), double.class, double.class, double.class),
        ENDER_CRYSTAL("EntityEnderCrystal", NMS.getClass("World")),
        ENDER_DRAGON("EnityEnderDragon", NMS.getClass("World")),
        ENDERMAN("EntityEnderman", NMS.getClass("World")),
        ENDERMITE("EntityEndermite", NMS.getClass("World")),
        ENDER_PEARL("EntityEnderPearl", NMS.getClass("World")),
        EXPERIENCE_ORB("EntityExperienceOrb", NMS.getClass("World"), double.class, double.class, double.class),
        FALLING_BLOCK("EntityFallingBlock", NMS.getClass("World"), double.class, double.class, double.class, NMS.getClass("IBlockData")),
        FIREWORK("EntityFireworks", NMS.getClass("World"), double.class, double.class, double.class, NMS.getClass("ItemStack")),
        GHAST("EntityGhast", NMS.getClass("World")),
        GIANT("EntityGiantZombie", NMS.getClass("World")),
        GUARDIAN("EntityGuardian", NMS.getClass("World")),
        HORSE("EntityHorse", NMS.getClass("World")),
        IRON_GOLEM("EntityIronGolem", NMS.getClass("World")),
        ITEM("EntityItem", NMS.getClass("World"), double.class, double.class, double.class),
        ITEM_FRAME("EntityItemFrame", NMS.getClass("World"), NMS.getClass("BlockPosition"), NMS.getClass("EnumDirection")),
        LARGE_FIREBALL("EntityLargeFireball", NMS.getClass("World")),
        LIGHTNING("EntityLightning", NMS.getClass("World"), double.class, double.class, double.class),
        MAGMA_CUBE("EntityMagmaCube", NMS.getClass("World")),
        MINECART_CHEST("EntityMinecartChest", NMS.getClass("World")),
        MINECART_COMMAND_BLOCK("EntityMinecartCommandBlock", NMS.getClass("World")),
        MINECART_FURNACE("EntityMinecartFurnace", NMS.getClass("World")),
        MINECART_HOPPER("EntityMinecartHopper", NMS.getClass("World")),
        MINECART_MOB_SPAWNER("EntityMinecartMobSpawner", NMS.getClass("World")),
        MINECART_TNT("EntityMinecartTNT", NMS.getClass("World")),
        MUSHROOM_COW("EntityMushroomCow", NMS.getClass("World")),
        OCELOT("EntityOcelot", NMS.getClass("World")),
        PAINTING("EntityPainting", NMS.getClass("World"), NMS.getClass("BlockPosition"), NMS.getClass("EnumDirection")),
        PIG("EntityPig", NMS.getClass("World")),
        PIG_ZOMBIE("EntityPigZombie", NMS.getClass("World")),
        PLAYER("EntityPlayer", NMS.getClass("MinecraftServer"), NMS.getClass("WorldServer"), NMS.getClass("GameProfile"), NMS.getClass("PlayerInteractManager")),
        POTION("EntityPotion", NMS.getClass("World"), double.class, double.class, double.class, NMS.getClass("ItemStack")),
        RABBIT("EntityRabbit", NMS.getClass("World")),
        SHEEP("EntitySheep", NMS.getClass("World")),
        SILVERFISH("EntitySilverfish", NMS.getClass("World")),
        SKELETON("EntitySkeleton", NMS.getClass("World")),
        SLIME("EntitySlime", NMS.getClass("World")),
        SMALL_FIREBALL("EntitySmallFireball", NMS.getClass("World")),
        SNOWBALL("EntitySnowball", NMS.getClass("World"), double.class, double.class, double.class),
        SNOWMAN("EntitySnowman", NMS.getClass("World")),
        SPIDER("EntitySpider", NMS.getClass("World")),
        SQUID("EntitySquid", NMS.getClass("World")),
        EXPERIENCE_BOTTLE("EntityExperienceBottle", NMS.getClass("World"), double.class, double.class, double.class),
        TNT("EntityTNTPrimed", NMS.getClass("World")),
        VILLAGER("EntityVillager", NMS.getClass("World")),
        WITCH("EntityWitch", NMS.getClass("World")),
        WITHER("EntityWither", NMS.getClass("World")),
        WITHER_SKULL("EntityWitherSkull", NMS.getClass("World")),
        WOLF("EntityWolf", NMS.getClass("World")),
        ZOMBIE("EntityZombie", NMS.getClass("World"));

        public final String className;
        public final Class<?>[] constructorArgs;

        EntityType(String className, Class<?>... constructorArgs) {
            this.className = className;
            this.constructorArgs = constructorArgs;
        }
    }

    /**
     * Allow you to get the entity ID
     *
     * @return the entity ID
     */
    public int getID() {
        try {
            final Method getId = entity.getClass().getMethod("getId");
            return (int) getId.invoke(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Allow you to get the DataWatcher
     *
     * @return the entity DataWatcher
     */
    public Object getDataWatcher() {
        try {
            final Method getDataWatcher = entity.getClass().getMethod("getDataWatcher");
            return getDataWatcher.invoke(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create a new entity instance
     *
     * @param world the world used to spawn the entity
     * @param type the type of entity that will be spawned
     */
    public NMSEntities(World world, EntityType type, Object... parameters) {
        try {
            final Class<?> entityClass = NMS.getClass(type.className);

            final Constructor<?> entityConstructor = entityClass.getConstructor(type.constructorArgs);

            parameters = new Object[] {NMSWorld.getWorld(world), parameters};
            entity = entityConstructor.newInstance(parameters);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}