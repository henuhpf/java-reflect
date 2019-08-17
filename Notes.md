### 反射的基本操作

#### 操作构造方法

- 使用Class.forName("").newInstance();实例化对象，相当于调用类的无参构造器，如果只有有参构造器，则使用这个方法实例化会抛出异常，此时只能取得类之中的构造方法，传递所需要的参数后参考一执行

```java
package com.hmoro.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class Student{
    private String name;
    private Integer age;
    public Student() {
        System.out.println("Student::无参构造器");
    }

    public Student(String name,Integer age) {
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
public class ReflectDemo {
    public static void main(String[] args) throws Exception {
        Class<?> cls = Class.forName("com.hmoro.demo.Student");
        
        // 使用student的无参构造器实例化
        Object o = cls.newInstance();

        // 使用有参构造器
        Constructor<?> constructor = cls.getConstructor(String.class, Integer.class);
        Object o1 = constructor.newInstance("zhang", 20);
        
        System.out.println(o);
        System.out.println(o1);

    }
}

```

```java
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
```



#### 类中方法

- 取得父类继承过来的方法 : 

  - ```java
    public Method[] getMethods()
    ```

  - ```java
    public Method getMethod(String name, Class<?>... parameterTypes)
    ```

- 取得本类定义的方法

  - ```java
    public Method[] getDeclaredMethods()
    ```

  - ```java
    public Method getDeclaredMethod(String name, Class<?>... parameterTypes)
    ```
- 获取类中的方法实例

```java
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

```

- 反射调用类中的方法

```java
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

```

#### 调用类中的属性

- 取得所有继承而来的属性

  - ```java
    public Field[] getFields()
    ```

  - ```java
    public Field getField(String name)
    ```

- 取得本类定义的属性

  - ```java
    public Field[] getDeclaredFields()
    ```

  - ```java
    public Field getDeclaredField(String name)
    ```

- 获取属性实例

```java
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

```

- 属性调用的方法

  - ```java
    //设置属性内容
    public void set(Object obj, Object value)
    ```

  - ```java
    // 获取属性内容
    public Object get(Object obj)
    ```

- 直接操作属性实例

```java
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


```
