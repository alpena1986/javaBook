package com.wang.network.io.bio;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClassicBIOClient {

    public static void main(String[] args){

        try {
            Socket socket = new Socket("127.0.0.1", 8880);
            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
            osw.write("tttttt");
            osw.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
