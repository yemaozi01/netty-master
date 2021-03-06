package com.netty.chapter_2_aio;

/**
 * AIO 时间服务器服务端 TimeServer
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try{
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                //采用默认值
            }
        }

        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer,"AIO-AsyncTimeServerHandler-001").start();
    }
}
