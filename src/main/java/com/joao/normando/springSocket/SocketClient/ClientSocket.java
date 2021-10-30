package com.joao.normando.springSocket.SocketClient;

import com.joao.normando.springSocket.Constants;
import com.joao.normando.springSocket.model.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientSocket implements Runnable {

    private Socket server = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private boolean endOfRequests = false;
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
            //sending request to read a file
            out.writeUTF("123");
            /*
            //sending file name
            out.writeUTF(Constants.CLIENT + this.ID_CLIENTE + ".txt");
            //waiting for server response - File exists
            String response = in.readUTF();

            if (response.equalsIgnoreCase("File not found.")) {
                out.writeUTF("" + Constants.END_OF_REQUEST);
            } else {
                while (true) {
                    System.out.println("====Reading file====\n\n");
                    String line = in.readUTF();
                    if (line.equals("***")) {
                        break;
                    } else
                        System.out.println(line);
                }
            }*/
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