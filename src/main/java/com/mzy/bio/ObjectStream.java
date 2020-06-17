package com.mzy.bio;

import java.beans.Transient;
import java.io.*;

/**
 * @program: nettyStudy
 * @author: mengzy 18306299232@163.com
 * @create: 2020-06-17 11:46
 **/

class student implements Serializable {

    private  transient  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private int age;
}

public class ObjectStream {

    public static void objectStreamTest() {
        try {

            student student = new student("mzy", 2);
            //写入
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("d://student"));
            objectOutputStream.writeObject(student);

            //读取
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("d://student"));
            student object = (student) objectInputStream.readObject();
            System.out.println(object.getAge() + object.getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {

        objectStreamTest();
    }
}
