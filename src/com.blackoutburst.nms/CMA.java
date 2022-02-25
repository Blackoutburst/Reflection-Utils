package com.blackoutburst.nms;

public class CMA {

    /**
     * Get a mojang authlib class
     *
     * @param name the class name
     * @return the class
     */
    public static Class<?> getClass(String name) {
        try {
            return Class.forName("com.mojang.authlib." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
