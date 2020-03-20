package com.moon.netty.chat.core;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 步骤二:初始化器
 */
public class WSServerInitialzer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // websocket 基于http协议，所以要有http编解码器
        pipeline.addLast(new HttpServerCodec());
        // 对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        // 对httpMessage进行聚合，聚合成FullHttpRequest或FullHttpResponse
        // 几乎在netty中的编程，都会使用到此hanler
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));


        // ====================== 以上是用于支持http协议    ======================


        // ====================== 增加心跳支持 start    ======================
        // 针对客户端，如果在1分钟时没有向服务端发送读写心跳(ALL)，则主动断开
        // 需要前端定时的向后端发送心跳包
        // 如果是读空闲或者写空闲，不处理
        // 激活心跳检测,8--读空闲秒数,10--写空闲秒数,12--读写空闲秒数
        pipeline.addLast(new IdleStateHandler(8, 10, 12));
        // 自定义的空闲状态检测
        pipeline.addLast(new HeartBeatHandler());
        // ====================== 增加心跳支持 end    ======================


        // ====================== 以下是支持httpWebsocket ======================

        /**
         * websocket 服务器处理的协议，用于指定给客户端连接访问的路由 : /ws
         * 本handler会帮你处理一些繁重的复杂的事
         * 会帮你处理握手动作： handshaking（close, ping, pong） ping + pong = 心跳
         * 对于websocket来讲，都是以frames进行传输的，不同的数据类型对应的frames也不同
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // 解决拆包粘包问题，但是需要注意的是websocket一帮帮我们封装了拆包粘包问题，所以不需要自己写了
		// 编码器
		/**
		 * 参数的含义:
		 * byteOrder：ByteOrder定义了写入buffer时字节的顺序
		 * lengthFieldLength：前置长度字段的长度。 仅允许1,2,3,4和8
		 * lengthAdjustment：要添加到长度字段的值的补偿值
		 * lengthIncludesLengthFieldLength：为true时，length字段的值=length字段的长度+Content的长度。为false时，length字段的值=Content的长度。
		 */
		pipeline.addLast(new LengthFieldPrepender(2));

        // 解码器
        /**
		 * 参数的含义:
         * 1、maxFrameLength - 发送的数据帧最大长度
		 *
		 * 2、lengthFieldOffset - 定义长度域位于发送的字节数组中的下标。
		 *
		 * 3、lengthFieldLength - 用于描述定义的长度域的长度。
		 *
		 * 4、lengthAdjustment - 要添加到长度字段值的补偿值，
		 * 满足公式: 发送的字节数组bytes.length - lengthFieldLength = bytes[lengthFieldOffset,lengthFieldOffset+lengthFieldLength]
		 * + lengthFieldOffset + lengthAdjustment
		 * lengthAdjustment = 0 = 数据包长度(14) - lengthFieldOffset - lengthFieldLength - 长度域的值(12)
		 *
		 * 5、initialBytesToStrip - 接收到的发送数据包，去除前initialBytesToStrip位
		 *
		 * 6、failFast - true: 读取到长度域超过maxFrameLength，就抛出一个 TooLongFrameException。
		 * false: 只有真正读取完长度域的值表示的字节之后，才会抛出 TooLongFrameException，默认情况下设置为true，建议不要修改，否则可能会造成内存溢出
		 *
		 * 7、ByteOrder - 数据存储采用大端模式或小端模式
		 *
		 * 发送数据包长度 = 长度域的值 + lengthFieldOffset + lengthFieldLength + lengthAdjustment
         */
        pipeline.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 2, 0, 2));

        // 自定义的handler
        pipeline.addLast(new ChatHandler());
    }

}
