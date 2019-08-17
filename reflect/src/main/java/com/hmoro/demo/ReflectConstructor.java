package com.hmoro.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
class ConstructorStudent{
    private String name;
    private Integer age;
    public ConstructorStudent() {
        System.out.println("Student::无参构造器");
    }

    public ConstructorStudent(String name,Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class ReflectConstructor {
    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("com.hmoro.demo.ConstructorStudent");

        // 使用student的无参构造器实例化
        Object o = cls.newInstance();

        // 使用有参构造器
        Constructor<?> constructor = cls.getConstructor(String.class, Integer.class);
        Object o1 = constructor.newInstance("zhang", 20);

        System.out.println(o);
        System.out.println(o1);

    }
}
