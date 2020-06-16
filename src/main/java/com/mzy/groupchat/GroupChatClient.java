package com.mzy.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @program: nettyStudy
 * @author: mengzy 18306299232@163.com
 * @create: 2020-06-14 21:43
 **/
public class GroupChatClient {

    private final String HOST = "127.0.0.1";
    private final int PORT = 8083;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    public GroupChatClient()  {
        try {
            this.selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel=socketChannel.open(new InetSocketAddress(HOST, PORT));
            socketChannel.configureBlocking(false);
            socketChannel.register(this.selector, SelectionKey.OP_READ);
            username = socketChannel.getLocalAddress().toString().substring(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(username + "is ok ...");
    }

    public void sendInfo(String info) throws IOException {
        info = username + "说：" + info;
        ByteBuffer byteBuffer = ByteBuffer.wrap(info.getBytes());
        socketChannel.write(byteBuffer);
    }

    //读取服务器端信息
    public void readInfo() throws IOException {
        while (true) {
            int select = selector.select(2000);
            if (select > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    if (next.isReadable()) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        SocketChannel channel = (SocketChannel) next.channel();
                        int read = channel.read(byteBuffer);
                        String s = new String(byteBuffer.array());
                        System.out.println("获取服务器信息:" + s);
                    }
                    iterator.remove();
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {

        final GroupChatClient groupChatClient = new GroupChatClient();

        new Thread() {
            public void run() {
                try {
                    groupChatClient.readInfo();
                    Thread.sleep(1000);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            groupChatClient.sendInfo(s);
        }


    }

}
