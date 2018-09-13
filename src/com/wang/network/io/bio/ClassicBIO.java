package com.wang.network.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示经典的BIO的使用方法
 */
public class ClassicBIO {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(5);//线程池
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket();
            SocketAddress address = new InetSocketAddress("127.0.0.1", 8880);
            serverSocket.bind(address);

            // 这个写法是写一个可中断的线程的唯一解
            while (!Thread.currentThread().isInterrupted()) {

                // 主线程死循环等待新连接到来
                Socket socket = serverSocket.accept();

                log("new Conn:" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());

                // 为新的连接创建新的线程
                // 也就是说每个连接到来，分配新的线程
                // 这个线程就永远地监视这个连接
                // 同一个连接发生的IO，不会再创建新的线程。
                executor.submit(new ConnectIOnHandler(socket));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 事件处理器，本身就是个线程
     */
    public static class ConnectIOnHandler extends Thread {

        private Socket socket;

        public ConnectIOnHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            while (!Thread.currentThread().isInterrupted() && !socket.isClosed()) {
                //死循环处理读写事件
                //读取数据
                InputStream is = null;
                try {
                    is = socket.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    //处理数据
                    String info = null;
                    while((info=br.readLine()) != null){
                        log(info);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void log(String content){
        System.out.println("[" + Thread.currentThread().getName() + "]" + "：" + content);
    }

}
