package com.mzy.groupchat;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: nettyStudy
 * @author: mengzy 18306299232@163.com
 * @create: 2020-06-14 20:51
 **/
public class GroupChatService {
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 8083;

    //初始化
    public GroupChatService() {
        try {
            //得到选择器
            this.selector = Selector.open();
            //得到服务端的channel
            this.listenChannel = ServerSocketChannel.open();
            //bind
            this.listenChannel.socket().bind(new InetSocketAddress(PORT));
            //设置非阻塞
            this.listenChannel.configureBlocking(false);
            //将服务端的channel注册到selector
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //监听
    public void listen() throws IOException {
        while (true) {
            //监听两秒
            int select = 0;
            try {
                select = this.selector.select(2000);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (select > 0) {
                Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    if (next.isAcceptable()) {
                        //如果是连接的请求
                        try {
                            SocketChannel accept = this.listenChannel.accept();
                            accept.configureBlocking(false);
                            accept.register(this.selector, SelectionKey.OP_READ);
                            //提示
                            System.out.println("ip为" + accept.getRemoteAddress() + "的用户" + "上线");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    //如果是发送信息的请求
                    if (next.isReadable()) {
                        //取出通道
                        SocketChannel channel = (SocketChannel) next.channel();
                        //buff数组,假设限制发送大小为1024
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        //读通道
                        try {
                            int read = channel.read(byteBuffer);
                            //如果读到有效的信息
                            if (read > 0) {
                                //msg
                                String msg = new String(byteBuffer.array());
                                System.out.println("服务器接收到信息 from" + channel.getRemoteAddress() + ":" + msg);
                                //实现msg的转发
                                forwardingMsg(msg, channel);
                                System.out.println("fuwuqi转发成功");
                            }

                        } catch (IOException e) {
                            System.out.println("客户端" + channel.getRemoteAddress() + "离线");
                            next.cancel();
                            channel.close();
                            e.printStackTrace();
                        }
                    }
                    iterator.remove();
                }


            }

        }
    }

    private void forwardingMsg(String msg, SocketChannel sc) throws IOException {
        //!!!!转发时，是转发全部的key
        Set<SelectionKey> selectionKeys = this.selector.keys();
        for (SelectionKey selectionKey : selectionKeys) {
            Channel channel = selectionKey.channel();
            //排除自己
            if (channel instanceof SocketChannel &&sc!=channel) {
                System.out.println("转发中");
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                //输入进通道
                SocketChannel socketChannel = (SocketChannel) channel;
                try {
                    socketChannel.write(byteBuffer);
                } catch (IOException e) {
                    System.out.println("客户端退出" + socketChannel.getRemoteAddress() + "聊天室");
                    //关闭文件描述符，关闭通道
//                    next.cancel();
                    socketChannel.close();
                    e.printStackTrace();
                }

            }

        }

    }

    public static void main(String[] args) throws IOException {


        GroupChatService groupChatService = new GroupChatService();

        groupChatService.listen();
    }
}
