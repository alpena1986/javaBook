package com.wang.network.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class NIOServer implements Runnable {

    private int port;
    private ServerSocketChannel serverSocketChannel;
    private ServerSocket serverSocket;
    private SocketAddress socketAddress;
    private Selector selector;
    private ByteBuffer receiveByteBuffer;
    private ByteBuffer sendByteBuffer;
    private Set<SelectionKey> keySet;
    private Iterator<SelectionKey> keyIterator;


    public NIOServer(int port, int bufferSize) throws IOException {
        this.port = port;
        this.serverSocketChannel = ServerSocketChannel.open();
        this.serverSocketChannel.configureBlocking(false);
        this.serverSocket = this.serverSocketChannel.socket();
        this.selector = Selector.open();
        this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        this.receiveByteBuffer = ByteBuffer.allocate(bufferSize);
        this.sendByteBuffer = ByteBuffer.allocate(bufferSize);
        this.socketAddress = new InetSocketAddress(this.port);
        this.serverSocket.bind(this.socketAddress);
        System.out.println("The server is now listening to port: " + this.port);
    }

    @Override
    public void run() {
        while (true) {
            try {
                selector.select();
                keySet = selector.selectedKeys();
                //Loop the selection key
                keyIterator = keySet.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    //Business Process
                    handleKey(key);
                    keyIterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleKey(SelectionKey key) throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel;
        SocketChannel socketChannel;
        int count;
        String informationFromClient;
        String informationToClient;

        if (key.isAcceptable()) {
            serverSocketChannel = (ServerSocketChannel) key.channel();
            socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        }

        if (key.isReadable()) {
            socketChannel = (SocketChannel) key.channel();
            count = socketChannel.read(receiveByteBuffer);
            if (count > 0) {
                informationFromClient = new String(receiveByteBuffer.array(), 0, count);
                System.out.println("The server receives information from client: " + informationFromClient);
                socketChannel.register(selector, SelectionKey.OP_WRITE);
            }
            receiveByteBuffer.clear();
        }

        if (key.isWritable()) {
            socketChannel = (SocketChannel) key.channel();
            informationToClient = "The information from server: now the time is" + new Date();
            sendByteBuffer.put(informationToClient.getBytes());
            sendByteBuffer.flip();
            socketChannel.write(sendByteBuffer);
            sendByteBuffer.clear();
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("The server sends information to client.");

            Thread.sleep(3 * 1000);
        }
    }
}