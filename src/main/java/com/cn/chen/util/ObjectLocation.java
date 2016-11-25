package com.cn.chen.util;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class ObjectLocation {

    private final static int apple = 10;
    private int orange = 10;
    private Integer banana = 10;
    private String color = "abcde";
    private String country = "china";
    private final static int xiaomi = 10;

    public static void main(String[] args) throws Exception {
        Unsafe unsafe = getUnsafeInstance();

        Field appleField = ObjectLocation.class.getDeclaredField("apple");
        System.out.println("Location of Apple: " + unsafe.staticFieldOffset(appleField));


        Field orangeField = ObjectLocation.class.getDeclaredField("orange");
        System.out.println("Location of Orange: " + unsafe.objectFieldOffset(orangeField));

        Field bananaField = ObjectLocation.class.getDeclaredField("banana");
        System.out.println("Location of banana: " + unsafe.objectFieldOffset(bananaField));

        Field colorField = ObjectLocation.class.getDeclaredField("color");
        System.out.println("Location of color: " + unsafe.objectFieldOffset(colorField));

        Field chinaField = ObjectLocation.class.getDeclaredField("country");
        System.out.println("Location of china: " + unsafe.objectFieldOffset(chinaField));

        Field xiaomiField = ObjectLocation.class.getDeclaredField("xiaomi");
        System.out.println("Location of xiaomi: " + unsafe.staticFieldOffset(xiaomiField));
    }

    private static Unsafe getUnsafeInstance() throws SecurityException, NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }
}