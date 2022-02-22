package com.blackoutburst.nms;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.io.File;
import java.lang.reflect.Constructor;

public class NMSWorld {

    /**
     * Get the world data manager
     *
     * @param world the world used to retrieve the data manager
     *
     * @return the world data manager
     */
    private static Object getDataManager(World world) {
        try {
            final String worldName = world.getName();

            final Class<?> iDataManagerClass = NMS.getClass("ServerNBTManager");

            final Constructor<?> iDataManagerConstruction = iDataManagerClass.getConstructor(File.class, String.class, boolean.class);

            return iDataManagerConstruction.newInstance(new File(worldName), worldName, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the world data
     *
     * @param worldDataClass the world data class
     * @return the world data
     */
    private static Object getWorldData(Class<?> worldDataClass) {
        try {
            final Class<?> nbtClass = NMS.getClass("NBTTagCompound");

            final Constructor<?> worldDataConstructor = worldDataClass.getConstructor(nbtClass);
            final Constructor<?> nbtConstructor = nbtClass.getConstructor();

            return worldDataConstructor.newInstance(nbtConstructor.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the world method profiler
     *
     * @param methodProfilerClass the method profiler class
     * @return the method profiler
     */
    private static Object getMethodProfiler(Class<?> methodProfilerClass) {
        try {
            final Constructor<?> methodProfilerConstructor = methodProfilerClass.getConstructor();

            return methodProfilerConstructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the world instance
     *
     * @param world the world used to create a NMS copies of it
     * @return the NMS world
     */
    public static Object getWorld(World world) {
        try {
            final Class<?> serverClass = NMS.getClass("MinecraftServer");
            final Class<?> iDataManagerClass = NMS.getClass("IDataManager");
            final Class<?> worldDataClass = NMS.getClass("WorldData");
            final Class<?> methodProfilerClass = NMS.getClass("MethodProfiler");
            final Class<?> worldClass = NMS.getClass("WorldServer");

            final Constructor<?> worldConstructor = worldClass.getConstructor(serverClass, iDataManagerClass, worldDataClass, int.class, methodProfilerClass, World.Environment.class, ChunkGenerator.class);

            final Object iDataManager = getDataManager(world);
            final Object worldData = getWorldData(worldDataClass);
            final Object methodProfiler = getMethodProfiler(methodProfilerClass);
            final Object server = serverClass.getMethod("getServer").invoke(null);

            return worldConstructor.newInstance(server, iDataManager, worldData, 0, methodProfiler, World.Environment.NORMAL,world.getGenerator());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
