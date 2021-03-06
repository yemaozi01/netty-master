package com.netty.chapter_1_pseudo_async_io;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 伪异步I/O的TimeServer
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port  = 8080;
        if(args !=null && args.length > 0){
            try{
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                //采用默认值
            }
        }
        ServerSocket server = null;
        try{
            server = new ServerSocket(port);
            System.out.println("com.netty.chapter_1_pseudo_async_io.TimeServer.main-->The time server is start int port : " + port);
            Socket socket = null;
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50,10000);//创建I/O任务线程池
            while(true){
                socket = server.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        } finally {
            if(server != null){
                System.out.println("com.netty.chapter_1_pseudo_async_io.TimeServer.main-->The time server close");
                server.close();
                server = null;
            }

        }
    }
}
