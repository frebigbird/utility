package frebigbird.tcpip.udp.unicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String args[]) throws IOException {
        if (args.length != 2) {
            System.out.printf("Usage: %s [bind-ip] [listen-port]\n",  UDPServer.class.getName());
            System.exit(1);
        }

        String bindIp = args[0];
        int listenPort = Integer.parseInt(args[1]);
        DatagramSocket serverSocket = null;

        InetAddress bindAddr = InetAddress.getByName(bindIp);
        serverSocket = new DatagramSocket(listenPort, bindAddr);
        boolean shouldRun = true;

        while (shouldRun) {
            System.out.println("Waiting for datagram packet from " + listenPort + " port");
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
            InetAddress ipAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            System.out.println("From: " + ipAddress.getHostName() + ":" + port);
            System.out.println("Message: " + sentence);

            String capitalizedSentence = sentence.toUpperCase();
            byte[] sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
            serverSocket.send(sendPacket);
        }
        serverSocket.close();
    }
}
