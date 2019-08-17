package com.hmoro.demo;

import java.lang.reflect.Method;

class MethodStudent02 {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
public class ReflectMethod02 {
    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("com.hmoro.demo.MethodStudent02");
        Object instance = cls.newInstance();
        Method setNameMethod = cls.getMethod("setName", String.class);
        Method getNameMethod = cls.getMethod("getName");
        setNameMethod.invoke(instance, "Li");
        System.out.println(getNameMethod.invoke(instance));
    }
}
