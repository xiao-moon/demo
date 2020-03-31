package com.moon.netty.test.b_java_nio;

/**
 * @program: demo
 * @description:
 * @author: 小月
 * @create: 2020-03-29 10:50
 **/

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 实例要求:本地文件写数据
 * 1.使用前面学习后的ByteBuffer(缓冲) 和 FileChannel(通道)， 将 "hello,小月!" 写入到file01.txt 中
 * 2.文件不存在就创建
 */
@Slf4j
public class B_NIOFileChannel01 {
    public static void main(String[] args) throws IOException {
        String str = "hello,小月!";

        //创建一个输出流，包装到channel里
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\file01.txt");

        //通过输出流获取对应的文件channel
        //这个fileChannel真实类型是FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建一个缓冲区ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //将str放入byteBuffer
        byteBuffer.put(str.getBytes());

        //对ByteBuffer进行flip
        byteBuffer.flip();

        //将ByteBuffer的数据写入到fileChannel
        fileChannel.write(byteBuffer);
        log.info("文件写入完毕...");
        //关闭流
        fileOutputStream.close();



    }
}
