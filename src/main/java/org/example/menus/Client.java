package org.example.menus;


import com.google.gson.Gson;
import org.example.DTO.Staff;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Client
{
    public static void main(String[] args)
    {
        Client client = new Client();
        client.start();
    }

    public void start()
    {
        Scanner in = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 8080);  // connect to server socket
            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort() );

            System.out.println("Client message: The Client is running and has connected to the server");

            System.out.println("\nPlease enter a command:  \n \"DisplayById\"- to get staff by Id \n \"DisplayAll\"- to get all staff \n \"AddStaff\"- to add staff \n \"DeleteStaffById\"- to delete staff by Id\n \"Summary\"- to view Total salary for each staff, Average and Standard Deviation for staff salary\n \"Quit\"- to quit application\n>");
            String command = in.nextLine();

            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers

            socketWriter.println(command);

            Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply

            try{
                boolean contd = true;
                while(contd) {
                    if (command.startsWith("DisplayAll")) {
                        Gson gsonParser = new Gson();
                        String input = socketReader.nextLine();
                        Staff[] staffArr = gsonParser.fromJson(input, Staff[].class);
                        System.out.println("Client message: Response from server -> Staff array : ");
                        for (Staff s : staffArr)
                            System.out.println(s);

                    } else if (command.startsWith("DisplayById")) {
                        //System.out.println("we are in");
                        Gson gsonParser = new Gson();

                        String input = socketReader.nextLine();

                        try {
                            Staff outputClass = gsonParser.fromJson(input, Staff.class);
                            String output = outputClass.toString();
                            System.out.println("Client message: Response from server: ");
                            System.out.println(output);
                        } catch (IllegalStateException | NullPointerException e) {
                            System.out.println("Staff doesnt exist - Sorry!");
                        }
                    } else if (command.startsWith("DeleteById")) {
                        String output = socketReader.nextLine();
                        System.out.println("Client message: Response from server: " + output);
                    } else if (command.startsWith("AddStaff")) {
                        String input = socketReader.nextLine();
                        System.out.println("Client message: Response from server: " + input);
                    } else if (command.startsWith("Quit")) {
                        String input = socketReader.nextLine();
                        System.out.println("Client message: Response from server: " + input);
                        break;
                    } else if (command.startsWith("Summary")) {
                        String input = socketReader.nextLine();
                        ObjectMapper mapper = new ObjectMapper();
                        HashMap<String, Double> map = mapper.readValue(input, HashMap.class);
                        System.out.println("Client message: Response from server -> Summary Data : ");
                        String key;
                        Double value;
                        for (Map.Entry<String, Double> entry : map.entrySet()) {
                            key = entry.getKey();
                            value = entry.getValue();
                            System.out.printf("%10s: %2.3f\n", key, value);
                        }
                    } else {
                        String input = socketReader.nextLine();
                        System.out.println("Client message: Response from server: \"" + input + "\"");
                    }

                    System.out.println("\nPlease enter a command:  \n \"DisplayById\"- to get staff by Id \n \"DisplayAll\"- to get all staff \n \"AddStaff\"- to add staff \n \"DeleteStaffById\"- to delete staff by Id\n \"Summary\"- to view Total salary for each staff, Average and Standard Deviation for staff salary\n \"Quit\"- to quit application\n>");
                    command = in.nextLine();
                    socketWriter.println(command); //println very important - otherwise takes in all characters before \n

                }
            }catch(Exception e){
                socketWriter.println("Invalid Input");
            }

            socketWriter.close();
            socketReader.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
        }
    }
}