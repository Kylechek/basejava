package com.javaops.webapp;

import com.javaops.webapp.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume("fullName1");
//        Field field = r.getClass().getDeclaredFields()[0];
        Method method = r.getClass().getDeclaredMethod("toString");
        method.setAccessible(true);
        method.invoke(r);
//        field.setAccessible(true);
//        System.out.println(field.getName());
//        field.get(r);
        System.out.println(r);

    }
}
