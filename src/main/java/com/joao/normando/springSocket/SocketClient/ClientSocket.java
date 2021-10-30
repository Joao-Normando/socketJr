package com.joao.normando.springSocket.SocketClient;

import com.joao.normando.springSocket.Constants;
import com.joao.normando.springSocket.model.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientSocket implements Runnable {

    private Socket server = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private int ID_CLIENT;

    public ClientSocket(int id) {
        this.ID_CLIENT = id;
    }

    private void defineInputOutput() {
        try {
            in = new DataInputStream(server.getInputStream());
            out = new DataOutputStream(server.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error creating input/output stream. - CLIENT");
        }
    }

    public void clientReadRequest() {
        try {

            out.writeUTF("| 001 | 10032019 | 002 | 50186 | 003 | Raul Seixas | 004 | Rio de Janeiro | 005 | 0000015000 | 006 | SRF001 |");
        } catch (IOException ie) {

        }
    }
    public void sendRequest(int requestType){

        switch(requestType){
            case Constants.READ_FILE:
                clientReadRequest();
                break;
        }
    }

    public void run() {


        try {
            server = new Socket(Constants.IP, Constants.SOCKET_PORT);
            defineInputOutput();
        } catch (IOException ex) {
            System.out.println("Could not connect to server.");
        }
        sendRequest(Constants.READ_FILE);

        try {
            server.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public static void main(String[] args) {
        ExecutorService EXECUTOR = Executors.newCachedThreadPool();
        int i = 0;
            EXECUTOR.execute(new ClientSocket(i++));

        EXECUTOR.shutdown();
        System.out.println("Ending main class.");

    }

}