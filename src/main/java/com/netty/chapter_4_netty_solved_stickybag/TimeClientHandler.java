package com.netty.chapter_4_netty_solved_stickybag;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeClientHandler extends ChannelHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(TimeClientHandler.class);

    private int counter;

    private byte[] req;

    public TimeClientHandler(){
        req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        ByteBuf message = null;
        for(int i=0;i<100;i++){
            message= Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg)throws Exception{
        String body  = (String)msg;
        System.out.println("Now is :"+body+" ; the counter is :"+ ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        //释放资源
        logger.warn("Unexcepted exception from downstream : ",cause.getMessage());
        ctx.close();
    }
}
