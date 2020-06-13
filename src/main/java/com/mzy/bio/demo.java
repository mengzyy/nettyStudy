package com.mzy.bio;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: nettyStudy
 * @author: mengzy 18306299232@163.com
 * @create: 2020-06-13 18:11
 **/
public class demo {


    private static void process(Socket accept) {
        InputStream inputStream;
        try {
            inputStream = accept.getInputStream();
            byte[] bytes = new byte[1024];
            int ind;
            while ((ind = inputStream.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, ind));
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }

    public static void main(String[] args) {

        //线程池
        ExecutorService executorService = new ThreadPoolExecutor(5, 10, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        ServerSocket serverSocket;

        {
            try {
                serverSocket = new ServerSocket(7777);

                while (true) {
                    System.out.println("等待连接中");
                    //等待获取连接
                    final Socket accept = serverSocket.accept();
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            process(accept);
                        }
                    });
                }


            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("服务器启动失败");
            }
        }


    }


}
