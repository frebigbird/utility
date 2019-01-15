package frebigbird.tcpip.udp.broadcast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class BroadcastAddress {
	public static void main(String[] args) throws IOException {
		List<InetAddress> list = listAllBroadcastAddresses();
		for (InetAddress address: list) {
			System.out.println("broadcast address : " + address.getHostAddress());
			return;
		}
	}

	private static List<InetAddress> listAllBroadcastAddresses() throws SocketException {
		List<InetAddress> broadcastList = new ArrayList<>();
		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		while (interfaces.hasMoreElements()) {
			NetworkInterface networkInterface = interfaces.nextElement();
	 
			if (networkInterface.isLoopback() || !networkInterface.isUp()) {
				continue;
			}
	 
			networkInterface.getInterfaceAddresses().stream() 
			  .map(a -> a.getBroadcast())
			  .filter(Objects::nonNull)
			  .forEach(broadcastList::add);
		}

		return broadcastList;
	}
}