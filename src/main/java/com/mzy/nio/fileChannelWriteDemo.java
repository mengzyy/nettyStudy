package com.mzy.nio;

import sun.nio.ch.FileChannelImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: nettyStudy
 * @author: mengzy 18306299232@163.com
 * @create: 2020-06-13 20:21
 **/
public class fileChannelWriteDemo {


    public static void main(String[] args) {


        try {
            //得到文件输出流
            FileOutputStream fileOutputStream = new FileOutputStream("d://1.txt");
            //data
            String data = "demo test mzy";
            //ByteBuffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            //放入数据
            byteBuffer.put(data.getBytes());
            //反转使之可以读
            byteBuffer.flip();
            //将输出流包装为FileChannel
            FileChannel channel = fileOutputStream.getChannel();
            //将数据写入channel
            channel.write(byteBuffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
