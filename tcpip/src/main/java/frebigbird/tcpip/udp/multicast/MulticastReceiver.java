package frebigbird.tcpip.udp.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver {
    public static void main(String[] args) throws IOException
    {
        if (args.length != 2) {
            System.out.printf("Usage: %s [join-group-ip] [listen-port]\n",  MulticastReceiver.class.getSimpleName());
            System.exit(1);
        }

        String joinGroupIp = args[0];
        int listenPort = Integer.parseInt(args[1]);

        MulticastSocket ms = new MulticastSocket(listenPort);

        // 230.0.0.1
        // 224.0.0.1
        ms.joinGroup(InetAddress.getByName(joinGroupIp));
        boolean shouldRun = true;

        while (shouldRun)
        {
            byte[] by = new byte[65508];
            DatagramPacket data = new DatagramPacket(by, by.length);
            ms.receive(data);

            InetAddress ia = data.getAddress();
            String str = new String(data.getData()).trim();
            System.out.println(ia.getHostName() + " ==> " + str + " from " + ia.getHostAddress() + ":" + data.getPort());
        }
        ms.close();
    }
}
