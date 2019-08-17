package com.hmoro.demo;

import java.lang.reflect.Field;

interface FieldMessage01{
    public static final String INFO = "Hello World!";
}
class FieldPerson01{
    private String name;
    private Integer age;
}
class FieldStudent01 extends FieldPerson01 implements  FieldMessage01{
    private String school;
    private Double price;
}
public class ReflectField01 {
    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("com.hmoro.demo.FieldStudent01");
        {
            // 取得继承而来的属性
            Field[] fields = cls.getFields();
            for (Field field : fields) {
                System.out.println(field);
            }
        }
        {
            // 取得本类定义的属性
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                System.out.println(declaredField);
            }
        }
        {
            // 取得父类的属性
            Class<?> superclass = cls.getSuperclass();
            System.out.println(superclass);
            Field[] declaredFields = superclass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                System.out.println(declaredField);
            }
        }
    }
}
