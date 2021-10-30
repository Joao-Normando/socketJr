package com.joao.normando.springSocket;

import org.apache.catalina.authenticator.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.joao.normando.springSocket.Constants.SOCKET_PORT;

public class Server {
    public static void main(String[] args) {

        ServerSocket server;
        Socket socket;
        ExecutorService EXECUTOR = Executors.newCachedThreadPool();


        try {
            server = new ServerSocket(SOCKET_PORT);

            while(true){
                System.out.println("Waiting connection");
                socket = server.accept();
                System.out.println("Connected: " + socket);
                EXECUTOR.execute(new Protocoll(socket));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
