/**
 * Copyright (C), 2001-2031, www.bosssof.com.cn
 * FileName: Server.java
 * Author: ShaoWen
 * Date: 2024/5/15 23:33
 * Description:
 * Client客户端
 * History:
 * Date          Author   Version  Desc
 * 2024-01-01    bosssoft  1.0.0   initialize this file
 */
package com.bosssoft.client;

import com.bosssoft.basic.ability.SocketManager;
import com.bosssoft.exception.ExceptionHandler;
import com.bosssoft.utils.EncryptAndDecryptUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @className: Client
 * @description:
 * @author: ShaoWen
 * @date: 2024/5/15 11:06
 * @since 1.0
 **/
public class Client /*implements ClientInterface*/ {
//    private final SocketManager socketManager;
    private static final String HOST = "localhost";
    private static final int PORT = 12345;

//    public Client(SocketManager socketManager) {
//        this.socketManager = socketManager;
//    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        try (SocketManager socketManager = new SocketManager(HOST, PORT)) {

//            ClientInterface client = ClientFactory.createClient(HOST, PORT);
//            if (client != null) {
//                System.out.println("Connected to server");
//                client.sendFile("@all", "E:\\MyFile\\boss\\demo15\\bosssoft-programDemo\\src\\main\\java\\com\\bosssoft\\filesend\\file.xml");
//                //client.showFileContent("file.xml"); // Uncomment this line if needed
//                System.out.println("Connection closed");
//            } else {
//                System.out.println("Failed to connect to server");
//            }
            System.out.println("Connected to server");
            System.out.println("请输入命令");
            Scanner scanner=new Scanner(System.in);
            String newCommand=scanner.nextLine();
            String[] commands=newCommand.split(" ");
            if(commands[0].equals("show")){
                // 请求查看文件内容
                showFileContent(threadPool, socketManager, commands[1]);
            }else if(commands[0].equals("send")){
                // 发送文件
                sendFile(threadPool, socketManager, commands[1], commands[2]);

            }else{
                System.out.println("您的输入有误");
            }

            threadPool.shutdown();
            threadPool.awaitTermination(20, TimeUnit.SECONDS);
            System.out.println("Connection closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    private static void sendFile(ThreadPoolExecutor threadPool, SocketManager socketManager, String receiver, String filePath) {
//        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
//        threadPool.submit(() -> {
//            try {
//                //读取文件内容
//                StringBuffer content = new StringBuffer();
//                // 用带缓冲的流读取文件，默认缓冲区8K
//                try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
//                    String line;
//                    while ((line = br.readLine()) != null) {
//                        content.append(line);
//                    }
//                }
//                String encodedContent = EncryptAndDecryptUtils.encode(content.toString());
//
//                String tempPath = "encoded" + filePath;
//                Files.write(Paths.get(tempPath), encodedContent.getBytes());
//                String message = "send " + receiver + tempPath;
//                socketManager.send(message);
//                System.out.println("Sent file to server");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    @Override
//    private static void showFileContent(ThreadPoolExecutor threadPool, SocketManager socketManager, String filePath) {
//        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
//        threadPool.submit(() -> {
//            try {
//                socketManager.send("show " + filePath);
//                String response = socketManager.receive();
//                System.out.println("File content: " + response);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//}


    private static void sendFile(ThreadPoolExecutor threadPool, SocketManager socketManager, String receiver, String filePath) {
        threadPool.submit(() -> {
            try {
                //发送文件
                System.out.println("File transfer status: 开始接收文件...");

                String message = "send " + receiver+" " + filePath;
                socketManager.send(message);

                String response = socketManager.receive();
                System.out.println(response);
                System.out.println("Sent file to server");
            } catch (IOException e) {
                //调用异常处理类
                ExceptionHandler.handleException(e);

            }
        });
    }

    private static void showFileContent(ThreadPoolExecutor threadPool, SocketManager socketManager, String filePath) {
        threadPool.submit(() -> {
            try {
                socketManager.send("show " + filePath);
                String response = socketManager.receive();
                System.out.println(response);
            } catch (IOException e) {
                //调用异常处理类
                ExceptionHandler.handleException(e);
            }
        });
    }
}