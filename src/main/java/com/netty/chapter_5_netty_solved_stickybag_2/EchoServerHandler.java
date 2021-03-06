package com.netty.chapter_5_netty_solved_stickybag_2;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        System.out.println("Receive client :["+msg+"]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)throws Exception{
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}