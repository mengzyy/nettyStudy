package com.mzy.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: nettyStudy
 * @author: mengzy 18306299232@163.com
 * @create: 2020-06-13 20:43
 **/

/*
文件的输入流
 */
public class fileChannelReadDemo {


    public static void main(String[] args) {


        File file = new File("d://1.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            FileChannel channel = fileInputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

            channel.read(byteBuffer);
            System.out.println(new String(byteBuffer.array()));
            fileInputStream.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
