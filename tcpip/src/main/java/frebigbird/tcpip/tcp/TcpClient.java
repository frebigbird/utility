package frebigbird.tcpip.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {
    public static void main(String[] args) throws UnknownHostException, IOException {
        if (args.length != 2) {
            System.out.printf("Usage: %s [ip] [port]\n", TcpClient.class.getSimpleName());
            System.exit(1);
        }

        String destIp = args[0];
        int destPort = Integer.parseInt(args[1]);

        System.out.println("Attemping to connect to (" + destIp + ") via TCP port " + destPort);

        // 소켓을 생성하여 연결을 요청한다.
        Socket socket = new Socket(destIp, destPort);

        // 소켓의 입력스트림을 얻는다.
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        // 소켓으로 부터 받은 데이터를 출력한다.
        System.out.println("message from server : " + dis.readUTF());

        // 스트림과 소켓을 닫는다.        
        dis.close();
        socket.close();
        System.out.println("Disconnected !!!");
    }
}