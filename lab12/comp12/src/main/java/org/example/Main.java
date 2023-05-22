package org.example;
import org.junit.Test;

import java.lang.reflect.Method;
public class Main {
        public static void main(String[] args) {
            String classPath = "org.example.TeoClass";
            try {

                Class<?> clazz = Class.forName(classPath);
                Package classPackage = clazz.getPackage();
                System.out.println("Package: " + classPackage.getName());

                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(Test.class) && method.getParameterCount() == 0) {
                        System.out.println("Method test name: " + method.getName());
                        method.invoke(null); 
                    }
                    else{
                        System.out.println("Other method: " + method.getName());
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
