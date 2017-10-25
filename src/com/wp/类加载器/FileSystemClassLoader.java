package com.wp.类加载器;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by 王萍 on 2017/10/23 0023.
 */
public class FileSystemClassLoader extends ClassLoader {

    private String classLoaderName;
    private String path;

    public FileSystemClassLoader(ClassLoader parent, String classLoaderName, String path) {
        super(parent);
        this.classLoaderName = classLoaderName;
        this.path = path;
    }

    public FileSystemClassLoader(String classLoaderName, String path) {//这里没有指定父加载器，默认为AppClassLoader（SystemClassLoader）
        this.classLoaderName = classLoaderName;
        this.path = path;
    }

    @Override
    public String toString() {
        return this.classLoaderName;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = readClassFile(name);
        return this.defineClass(name, bytes, 0, bytes.length);
    }

//    private byte[] readClassFile(String name) {
//        FileInputStream fileInputStream = null;
//        byte[] returnBytes = null;
//
//        name = name.replaceAll("\\.", "/");
//        String filePath = this.path + name + ".class";
//        try {
//            fileInputStream = new FileInputStream(filePath);
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            int tmp = 0;
//            while ((tmp = fileInputStream.read()) != -1) {
//                outputStream.write(tmp);
//            }
//            returnBytes = outputStream.toByteArray();
//            outputStream.close();
//            fileInputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return returnBytes;
//    }

    private byte[] readClassFile(String name) {
        FileInputStream fileInputStream = null;
        byte[] returnBytes = null;

        name = name.replaceAll("\\.", "/");
        String filePath = this.path + name + ".class";
        try {
            fileInputStream = new FileInputStream(filePath);
            returnBytes = new byte[fileInputStream.available()];
            fileInputStream.read(returnBytes);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnBytes;
    }
}
