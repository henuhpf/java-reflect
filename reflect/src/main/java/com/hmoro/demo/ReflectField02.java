package com.hmoro.demo;

import java.lang.reflect.Field;

class FieldStudent02 {
    private String school;
    public String name;
}
public class ReflectField02 {
    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("com.hmoro.demo.FieldStudent02");
        Object instance = cls.newInstance();
        Field nameField = cls.getDeclaredField("name");
        nameField.set(instance,"henu");
        System.out.println(nameField.get(instance));

        Field schoolField = cls.getDeclaredField("school");
        schoolField.setAccessible(true);// 取消对private类型的保护(封装)|| 设置访问权限,如果是public类型，则不需要设置权限

        schoolField.set(instance, "河南大学");
        System.out.println(schoolField.get(instance));
    }
}
