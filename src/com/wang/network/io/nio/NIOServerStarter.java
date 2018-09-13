package com.wang.network.io.nio;

public class NIOServerStarter {


    public static void main(String[] args){
        try {
            NIOServer server = new NIOServer(8880, 200);
            server.run();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
