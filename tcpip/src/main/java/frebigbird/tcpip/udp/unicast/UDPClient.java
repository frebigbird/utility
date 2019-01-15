package frebigbird.tcpip.udp.unicast;

import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String args[]) throws Exception {
        if (args.length != 2) {
            System.out.printf("Usage: %s [ip] [port]\n", UDPClient.class.getSimpleName());
            System.exit(1);
        }

        try {
            String destIp = args[0];
            int destPort = Integer.parseInt(args[1]);

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress serverAddress = InetAddress.getByName(destIp);
            System.out.println("Attemping to connect to (" + serverAddress.getHostAddress() + ") via UDP port " + destPort);

            boolean shouldRun = true;
            while (shouldRun) {
                System.out.print("Enter Message: ");
                String sentence = inFromUser.readLine();
                byte[] sendData = sentence.getBytes();
    
                System.out.println("Sending data to " + sendData.length + " bytes to server.");
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, destPort);
    
                clientSocket.send(sendPacket);
    
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
    
                System.out.println("Waiting for return packet");
                clientSocket.setSoTimeout(10000);
    
                clientSocket.receive(receivePacket);
                String modifiedSentence = new String(receivePacket.getData(), 0, receivePacket.getLength());

                InetAddress returnIPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                System.out.println("From servdfer at: " + returnIPAddress.getHostAddress() + ":" + port);
                System.out.println("Message: " + modifiedSentence);
            }
            clientSocket.close();
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}