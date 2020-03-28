package com.moon.im.chat.core;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 处理二进制消息
 * @Author：xiaojiaxin
 * @Date：2020-02-28 18:31
 */
public class BinaryHandler extends SimpleChannelInboundHandler<BinaryWebSocketFrame> {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BinaryWebSocketFrame msg) throws Exception {
        logger.info("服务器接收到二进制消息.");
        ByteBuf content = msg.content();
        content.markReaderIndex();
        int flag = content.readInt();
        logger.info("标识位",flag);
        content.readerIndex();
        ByteBuf byteBuf = Unpooled.directBuffer(msg.content().capacity());
        byteBuf.writeBytes(msg.content());
        ctx.writeAndFlush(new BinaryWebSocketFrame(byteBuf));
    }
}
