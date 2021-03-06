package com.netty.chapter_1_sync_block_io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 同步阻塞I/O的TimeServer
 */
public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        System.out.println("com.netty.chapter_1_sync_block_io.TimeServer.main.args1: "+args.length);
        if(args !=null && args.length > 0){
            try{
                System.out.println("com.netty.chapter_1_sync_block_io.TimeServer.main.args2: "+args);
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                //采用默认值
            }
        }
        ServerSocket server = null;
        try{
            server = new ServerSocket(port);
            System.out.println("com.netty.chapter_1_sync_block_io.The time server is start in port :"+port);
            Socket socket = null;
            while(true){
                System.out.println("com.netty.chapter_1_sync_block_io.The time server accept");
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        }finally {
            if(server != null){
                System.out.println("com.netty.chapter_1_sync_block_io.The time server close");
                server.close();
                server = null;
            }
        }
    }
}
