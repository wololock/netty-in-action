package com.github.wololock.netty.echo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@Sharable
public final class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
    System.out.println("Client received: " + byteBuf.toString(CharsetUtil.UTF_8));
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
    ctx.close();
  }
}
