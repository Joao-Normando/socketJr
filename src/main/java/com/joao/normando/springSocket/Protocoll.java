package com.joao.normando.springSocket;

import com.google.gson.Gson;
import com.joao.normando.springSocket.model.DataClient;
import okhttp3.*;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Protocoll implements Runnable{

    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;
    private boolean end = false;

    public Protocoll(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        defineInputOutput();

            end = waitForRequest();

        System.out.println("--------CONNECTION CLOSED.--------\n\n");
    }

    public void defineInputOutput(){
        try {
            System.out.println("Creating input/output.");
            in = new DataInputStream (socket.getInputStream());
            System.out.println("In reference: " + in);
            out = new DataOutputStream (socket.getOutputStream());

        } catch (IOException ex) {
            System.out.println("Error creating input/output stream. - SERVER");
        }
    }

    private boolean waitForRequest() {
        try {
            int requestType = 0;

            String leitor = in.readUTF();
            System.out.println(leitor);
            DataClient data = separador(leitor);


            Gson gson= new Gson();
            String requestData = gson.toJson(data);
            System.out.println(requestData);
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, requestData);
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

    private static final String DATA = "001";
    private static final String ID = "002";
    private static final String NAME = "003";
    private static final String STATE = "004";
    private static final String BRAVERY = "005";
    private static final String LOW = "006";

    public static DataClient separador(String message) {
        String[] splittedMessage = message.split("\\|");

        Map<String, String> mappedMessage = new HashMap<>();

        for(int i = 1; i < splittedMessage.length; i+=2){
            mappedMessage.put(splittedMessage[i].trim(), splittedMessage[i+1]);
        }

        DataClient data = new DataClient();
        data.setData(mappedMessage.get(DATA));
        data.setId(mappedMessage.get(ID));
        data.setName(mappedMessage.get(NAME));
        data.setState(mappedMessage.get(STATE));
        data.setBravery(mappedMessage.get(BRAVERY));
        data.setLow(mappedMessage.get(LOW));

        return data;
    }

}