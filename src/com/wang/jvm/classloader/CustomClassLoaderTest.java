package com.wang.jvm.classloader;

import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoaderTest {

    private static class MyClassLoader extends ClassLoader{
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            String fileName = name.substring(name.lastIndexOf(".") + 1 ) + ".class";
            try {
                InputStream is = ClassLoader.getSystemResourceAsStream(fileName);
                if (is == null) {
                    return super.loadClass(name);
                }
                byte[] b = new byte[is.available()];
                is.read(b);
                return defineClass(name, b, 0, b.length);
            } catch (IOException ex){
                throw new ClassNotFoundException();
            }
        }
    }

    public static void main(String[] args){
        MyClassLoader myClassLoader = new MyClassLoader();
        try {
            Object obj = myClassLoader.loadClass("com.wang.jvm.classloader.CustomClassLoaderTest");
            System.out.println(obj instanceof CustomClassLoaderTest);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
