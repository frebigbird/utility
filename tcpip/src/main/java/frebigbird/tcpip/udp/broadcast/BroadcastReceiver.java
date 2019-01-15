package frebigbird.tcpip.udp.broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BroadcastReceiver {
	public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.printf("Usage: %s [bind-ip] [listen-port]\n",  BroadcastReceiver.class.getSimpleName());
            System.exit(1);
		}

		String bindIp = args[0];
		int listenPort = Integer.parseInt(args[1]);

		DatagramSocket ds = new DatagramSocket(listenPort, InetAddress.getByName(bindIp));
		byte[] by = new byte[65508];
		boolean shouldRun = true;

		while (shouldRun) {
			System.out.println("Waiting for receiving from " + bindIp + ":" + listenPort);
			DatagramPacket data = new DatagramPacket(by, by.length);
			ds.receive(data);
			InetAddress ia = data.getAddress(); // 주소를 얻어냄.
			String str = new String(data.getData()).trim(); // Data를 얻어냄.
			System.out.println(ia.getHostName() + " ===> " + str);
		}
		ds.close();
	}
}
