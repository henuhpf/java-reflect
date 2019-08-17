package com.hmoro.demo;

import java.lang.reflect.Constructor;

public class ReflectStringDemo {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> cls = Class.forName("java.lang.String");
        Constructor<?>[] constructors = cls.getConstructors();// 取得类的全部构造方法
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
    }
}
