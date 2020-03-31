package com.moon.netty.test.b_java_nio;

/**
 * @program: demo
 * @description: 拷贝文件transferFrom 方法
 * @author: 小月
 * @create: 2020-03-29 12:35
 **/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * 要求:
 * 1.使用 FileChannel(通道) 和 方法  transferFrom ，完成文件的拷贝
 * 2.拷贝一张图片
 */
public class E_NIOFileChannel04 {
    public static void main(String[] args) throws Exception {
        //创建相关流
        FileInputStream fileInputStream = new FileInputStream("D:\\a.png");
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\a2.png");

        //获取各个流对应的filechannel
        FileChannel sourceCh = fileInputStream.getChannel();
        FileChannel destCh = fileOutputStream.getChannel();

        //使用transferForm完成拷贝
        destCh.transferFrom(sourceCh, 0, sourceCh.size());
        //关闭相关通道和流
        sourceCh.close();
        destCh.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

}
