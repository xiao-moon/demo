package com.moon.netty.test.a_java_bio;

/**
 * @program: demo
 * @description: BIO
 * @author: 小月
 * @create: 2020-03-28 12:25
 **/

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * java BIO基本介绍：
 * 1.Java BIO 就是传统的java io 编程，其相关的类和接口在 java.io
 * 2.BIO(blocking I/O) ： 同步阻塞，服务器实现模式为一个连接一个线程，
 * 即客户端有连接请求时服务器端就需要启动一个线程进行处理，如果这个连接不做任何事情会造成不必要的线程开销，
 * 可以通过线程池机制改善(实现多个客户连接服务器)。
 * 3.BIO方式适用于连接数目比较小且固定的架构，这种方式对服务器资源要求比较高，并发局限于应用中。
 *
 * BIO编程简单流程:
 * 1.服务器端启动一个ServerSocket
 * 2.客户端启动Socket对服务器进行通信，默认情况下服务器端需要对每个客户 建立一个线程与之通讯
 * 3.客户端发出请求后, 先咨询服务器是否有线程响应，如果没有则会等待，或者被拒绝
 * 4.如果有响应，客户端线程会等待请求结束后，在继续执行
 *
 * 实例说明：
 * 1.使用BIO模型编写一个服务器端，监听6666端口，当有客户端连接时，就启动一个线程与之通讯。
 * 2.要求使用线程池机制改善，可以连接多个客户端.
 * 3.服务器端可以接收客户端发送的数据(telnet 方式即可)。
 *
 * BIO问题分析:
 * 1.每个请求都需要创建独立的线程，与对应的客户端进行数据 Read，业务处理，数据 Write 。
 * 2.当并发数较大时，需要创建大量线程来处理连接，系统资源占用较大。
 * 3.连接建立后，如果当前线程暂时没有数据可读，则线程就阻塞在 Read 操作上，造成线程资源浪费
 */
@Slf4j
public class BIOServer {


    /**
     * 1.启动main程序
     * 2.打开cmd，输入【需要先启用telnet】 telnet 127.0.0.1 6666
     * 3.进入发送命令窗口 输入ctrl+]
     * 4.使用send发送需要向服务器发送的命令：send hello100
     */
    public static void main(String[] args) throws IOException {
        //线程池机制

        //思路
        //1.创建一个线程池
        //2.如果有客户端连接，就创建一个线程池，与之通讯(单独写一个方法)

        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        //创建一个ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);
        log.info("服务器启动了");
        while (true) {
            //监听,等待客户端连接
            //注意，accept会阻塞
            log.info("等待连接...");
            final Socket socket = serverSocket.accept();
            log.info("连接到一个客户端");

            //创建一个线程池，与之通讯
            newCachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    //可以和客户端通讯
                    handler(socket);
                }
            });

        }

    }

    /**
     * 编写一个handler方法，可以和客户端通讯
     */
    public static void handler(Socket socket) {
        try {
            log.info("线程信息:id="+Thread.currentThread().getId()+";name="+Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            //通过socket获取输入流
            InputStream inputStream = socket.getInputStream();

            //循环读取客户端发送的数据
            while (true) {
                log.info("read...");
                int read = inputStream.read(bytes);
                if (read != -1) {
                    log.info("输出客户端发送的数据:" + new String(bytes, 0, read));
                } else {
                    break;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            log.info("关闭和client的连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
