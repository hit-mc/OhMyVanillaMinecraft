package com.keuin.ohmyvanillamc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author 落叶飞翔的蜗牛 Keuin
 * @Date 2018/3/10
 * @Description 常用反射函数
 */
public final class ReflectionUtils {

    /**
     * 获取私有成员变量的值
     */
    public static Object getPrivateField(Object instance, String filedName) throws NoSuchFieldException, IllegalAccessException {
        Field field = instance.getClass().getDeclaredField(filedName);
        field.setAccessible(true);
        return field.get(instance);
    }

    /**
     * 设置私有成员的值
     */
    public static void setPrivateField(Object instance, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = instance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(instance, value);
    }

    /**
     * 访问私有方法
     */
    public static Object invokePrivateMethod(Object instance, String methodName, Class[] classes, Object... args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = instance.getClass().getDeclaredMethod(methodName, classes);
        method.setAccessible(true);
        return method.invoke(instance, args);
    }

    /**
     * 获取所有成员变量的名字
     *
     * @param o 要获取成员变量的对象实例
     * @return 所有成员变量名字数组
     */
    public static String[] getFieldName(Object o) {
        return Arrays.stream(o.getClass().getDeclaredFields()).map(Field::getName).toArray(String[]::new);
    }
}