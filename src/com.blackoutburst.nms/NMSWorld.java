package com.blackoutburst.nms;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;

import java.io.File;
import java.lang.reflect.Constructor;

public class NMSWorld {

    private static Object getDataManager(Player player) {
        try {
            final String worldName = player.getWorld().getName();

            final Class<?> iDataManagerClass = NMS.getClass("ServerNBTManager");

            final Constructor<?> iDataManagerConstruction = iDataManagerClass.getConstructor(File.class, String.class, boolean.class);

            return iDataManagerConstruction.newInstance(new File(worldName), worldName, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

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

    private static Object getMethodProfiler(Class<?> methodProfilerClass) {
        try {
            final Constructor<?> methodProfilerConstructor = methodProfilerClass.getConstructor();

            return methodProfilerConstructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getWorld(Player player) {
        try {
            final Class<?> serverClass = NMS.getClass("MinecraftServer");
            final Class<?> iDataManagerClass = NMS.getClass("IDataManager");
            final Class<?> worldDataClass = NMS.getClass("WorldData");
            final Class<?> methodProfilerClass = NMS.getClass("MethodProfiler");
            final Class<?> worldClass = NMS.getClass("WorldServer");

            final Constructor<?> worldConstructor = worldClass.getConstructor(serverClass, iDataManagerClass, worldDataClass, int.class, methodProfilerClass, World.Environment.class, ChunkGenerator.class);

            final Object iDataManager = getDataManager(player);
            final Object worldData = getWorldData(worldDataClass);
            final Object methodProfiler = getMethodProfiler(methodProfilerClass);
            final Object server = serverClass.getMethod("getServer").invoke(null);

            return worldConstructor.newInstance(server, iDataManager, worldData, 0, methodProfiler, World.Environment.NORMAL, player.getWorld().getGenerator());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
