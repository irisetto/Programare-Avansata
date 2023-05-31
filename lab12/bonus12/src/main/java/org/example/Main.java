package org.example;
import org.junit.Test;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;

    public static void main(String[] args) {
            String inputPath = "D:\\Faculty\\II-2\\Programare avansata\\Programare-Avansata\\lab12\\homework12\\src\\main\\java\\org\\example";
            compileJavaFiles(inputPath);
            analyzeClasses(inputPath);
            printStatistics();
        }
    private static void compileJavaFiles(String inputPath) {
        File file = new File(inputPath);

        if (file.isDirectory()) {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            compiler.run(null, null, null, getFileList(file));
        }
    }
    private static String[] getFileList(File directory) {
        List<String> fileList = new ArrayList<>();

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
              if (file.isFile() && file.getName().endsWith(".java")) {
                    fileList.add(file.getAbsolutePath());
                }
            }
        }

        return fileList.toArray(new String[0]);
    }
    private static void analyzeClasses(String inputPath) {
        File file = new File(inputPath);

        if (file.isDirectory()) {
            analyzeClassesInDirectory(file);
        } 
    }

    private static void analyzeClassesInDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    analyzeClassesInDirectory(file);
                } else if (file.isFile() && file.getName().endsWith(".class")) {
                    String className = getClassName(file.getAbsolutePath(), directory.getAbsolutePath());
                    try {
                        Class<?> clazz = Class.forName(className);
                              analyzeTestClass(clazz);

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static String getClassName(String filePath, String basePath) {
        String className = filePath.substring(basePath.length() + 1);
        className = className.replace(File.separator, ".");
        className = className.substring(0, className.length() - 6); // Remove ".class" extension
        className = "org.example." + className;
        //System.out.println(className);
        return className;
    }

    private static void analyzeTestClass(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class) && Modifier.isPublic(method.getModifiers())) {
                totalTests++;
                System.out.println("Running test: " + clazz.getName() + "." + method.getName());
                try {
                    if (Modifier.isStatic(method.getModifiers())) {
                        invokeStaticTestMethod(clazz, method);
                    } else {
                        Object instance = clazz.getDeclaredConstructor().newInstance();
                        invokeTestMethod(instance, method);
                    }
                    passedTests++;
                } catch (Exception e) {
                    failedTests++;
                    e.printStackTrace();
                }
            }
        }
    }
    private static void invokeTestMethod(Object instance, Method method) throws Exception {
        if (method.getParameterCount() == 0) {
            method.invoke(instance); // Non-static method with no arguments
        } else {
            // Generate mock values for method arguments
            Object[] args = generateMockArguments(method.getParameterTypes());

            method.invoke(instance, args); // Non-static method with arguments
        }
    }

    private static Object[] generateMockArguments(Class<?>[] parameterTypes) {
        Object[] args = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            if (parameterType == int.class) {
                args[i] = 0; // Mock integer value
            } else if (parameterType == String.class) {
                args[i] = "mock"; // Mock string value
            } else {
                args[i] = null; // For other types, set to null
            }
        }
        return args;
    }

    private static void invokeStaticTestMethod(Class<?> clazz, Method method) throws Exception {
        if (method.getParameterCount() == 0) {
            method.invoke(null); // Static method with no arguments
        } else {
            // Generate mock values for method arguments
            Object[] args = generateMockArguments(method.getParameterTypes());

            method.invoke(null, args); // Static method with arguments
        }
    }
    private static void printStatistics() {
        System.out.println("\n--- Test Statistics ---");
        System.out.println("Total tests: " + totalTests);
        System.out.println("Passed tests: " + passedTests);
        System.out.println("Failed tests: " + failedTests);
    }
}
