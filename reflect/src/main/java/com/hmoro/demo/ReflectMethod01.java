package com.hmoro.demo;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

interface MethodMessage01{
    public void get();
}
class MethodStudent01 implements MethodMessage01{
    public void fun() {};
    public void print() {};
    public void get() {};
    private void myprivate(){};
}
public class ReflectMethod01 {
    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("com.hmoro.demo.MethodStudent01");
        // 获取本类和父类的public 方法
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("----------------");
        for (Method method : methods) {
            System.out.print(Modifier.toString(method.getModifiers()) + " ");// 获取方法修饰类型,method.getModifiers() 取得的是修饰类型对应的值，而不是我们熟知的字符串
            System.out.print(method.getReturnType().getSimpleName() + " "); // 获取返回值类型,不加getSimpleName()则为返回值类型的全类名
            System.out.print(method.getName() + "(");
            Class<?>[] parameterTypes = method.getParameterTypes(); // 取得全部参数
            for (int i = 0; i < parameterTypes.length; i++) {
                System.out.print(parameterTypes[i].getSimpleName() + " arg-" + i);
                if(i < parameterTypes.length - 1){
                    System.out.print(", ");
                }
            }
            System.out.print(") ");
            Class<?>[] exceptionTypes = method.getExceptionTypes();// 取得所有抛出的异常
            if(exceptionTypes.length > 0) {
                // 有异常抛出
                System.out.print("throws ");
                for (int i = 0; i < exceptionTypes.length; i++) {
                    System.out.print(exceptionTypes[i].getSimpleName());
                    if(i <exceptionTypes.length - 1){
                        System.out.print(", ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("---------------");
        // 可以获取本类private 方法
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
    }
}
