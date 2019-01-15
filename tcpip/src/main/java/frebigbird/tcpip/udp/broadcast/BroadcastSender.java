package frebigbird.tcpip.udp.broadcast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BroadcastSender extends Thread {
	private static InetAddress destIp;
	private static int destPort;

	public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.printf("Usage: %s [ip] [port]\n",  BroadcastSender.class.getSimpleName());
            System.exit(1);
		}

		destIp = InetAddress.getByName(args[0]);
		destPort = Integer.parseInt(args[1]);

		DatagramSocket ds = new DatagramSocket();
		ds.setBroadcast(true);

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		boolean shouldRun = true;
		while (shouldRun) {
			System.out.print("Message = ");
			String msg = in.readLine();

			// for문을 돌리지 않고도 그룹 전체에 보내는 것과 동일한 효과
			DatagramPacket data = new DatagramPacket(msg.getBytes(), msg.getBytes().length, destIp, destPort);
			ds.send(data);
		}
		ds.close();
	}
}