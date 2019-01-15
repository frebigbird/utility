package frebigbird.tcpip.udp.multicast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastSender {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.printf("Usage: %s [join-group-ip] [port]\n", MulticastSender.class.getSimpleName());
            System.exit(1);
        }

        String joinGroupIp = args[0];
        int destPort = Integer.parseInt(args[1]);

        MulticastSocket ms = new MulticastSocket();
        ms.setTimeToLive(16);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        boolean shouldRun = true;
        while (shouldRun) {
            System.out.print("Message = ");
            String msg = in.readLine();
            InetAddress ia = InetAddress.getByName(joinGroupIp);

            DatagramPacket data = new DatagramPacket(msg.getBytes(), msg.getBytes().length, ia, destPort);
            ms.send(data);
            System.out.println("Write message to client.");
        }
        ms.close();
    }
}
