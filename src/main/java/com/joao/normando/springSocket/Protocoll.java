package com.joao.normando.springSocket;

import okhttp3.*;
import org.apache.catalina.authenticator.Constants;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.joao.normando.springSocket.Constants.*;

public class Protocoll implements Runnable{

    private DataInputStream in;
    private DataOutputStream out;
    private Socket s;
    private boolean end = false;
    private String path = "src\\server\\";

    public Protocoll(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {

        defineInputOutput();

            end = waitForRequest();

        System.out.println("+++++++++++++++++CONNECTION CLOSED.++++++++++++++++\n\n");
    }

    public void defineInputOutput(){
        try {
            System.out.println("Creating input/output.");
            in = new DataInputStream (s.getInputStream());
            System.out.println("In reference: " + in);
            out = new DataOutputStream (s.getOutputStream());

        } catch (IOException ex) {
            System.out.println("Error creating input/output stream. - SERVER");
        }
    }

    private boolean waitForRequest() {
        try {
            int requestType = 0;

            String leitor = in.readUTF();
            System.out.println(leitor);
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\r\n    \"data\": null,\r\n    \"name\": \"joao\",\r\n    \"state\": null,\r\n    \"bravery\": null,\r\n    \"low\": null\r\n}");
            Request request = new Request.Builder()
                    .url("http://localhost:8080/client/save")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println("Salvou !");


        } catch (IOException ex) {
            Logger.getLogger(Protocoll.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}