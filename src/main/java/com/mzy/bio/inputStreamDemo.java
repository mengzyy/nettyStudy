package com.mzy.bio;

import java.io.*;

/**
 * @program: nettyStudy
 * @author: mengzy 18306299232@163.com
 * @create: 2020-06-17 10:33
 **/
public class inputStreamDemo {

    public static void copyByFileInputStream(String src, String out) {
        try {
            FileInputStream fileInputStream = new FileInputStream(src);
            FileOutputStream fileOutputStream = new FileOutputStream(out);
            byte[] bytes = new byte[1024];
            while (fileInputStream.read(bytes) != -1) {
                fileOutputStream.write(bytes);
            }
            System.out.println("coy完成");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("error: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void copyByBufferedInputStream(String src, String out) {
        try {
            FileInputStream fileInputStream = new FileInputStream(src);
            FileOutputStream fileOutputStream = new FileOutputStream(out);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            byte[] bytes = new byte[1024];
            while (bufferedInputStream.read(bytes) != -1) {
                bufferedOutputStream.write(bytes);
            }
            //记得刷新缓存内核态中的缓存数组
            bufferedOutputStream.flush();
            System.out.println("coy完成");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("error: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        inputStreamDemo.copyByBufferedInputStream("d://1.txt", "d://3.txt");


    }
}
