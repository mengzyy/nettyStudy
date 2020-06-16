package com.mzy.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: nettyStudy
 * @author: mengzy 18306299232@163.com
 * @create: 2020-06-13 21:22
 **/
public class fileChanelCopy2Demo {

    public static void main(String[] args) {

        File file = new File("d://1.txt");
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        //文件输入流
        try {
            fileInputStream = new FileInputStream(file);
            //文件输出流
            fileOutputStream = new FileOutputStream(new File("d://2.txt"));

            FileChannel channel = fileInputStream.getChannel();
            FileChannel channel2 = fileOutputStream.getChannel();
            channel2.transferFrom(channel,0,channel.size());


            fileInputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }




    }
}
