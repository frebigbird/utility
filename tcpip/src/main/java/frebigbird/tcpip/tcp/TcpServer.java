package frebigbird.tcpip.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.printf("Usage: %s [bind-ip] [listen-port]\n",  TcpServer.class.getSimpleName());
            System.exit(1);
        }

        String bindIp = args[0];
        int listenPort = Integer.parseInt(args[1]);

        // 서버소켓을 생성하고 5000번 포트와 결합(bind) 시킨다.
        InetAddress bindAddr = InetAddress.getByName(bindIp);
        ServerSocket serverSocket = new ServerSocket(listenPort, 0, bindAddr);

        boolean shouldRun = true;
        while (shouldRun) {
            try {
                // 서버소켓은 클라이언트의 연결요청이 올 때까지 실행을 멈추고 계속 기다린다.
                // 클라이언트의 연결요청이 오면 클라이언트 소켓과 통신할 새로운 소켓을 생성한다.
                System.out.println(getTime() + "Waiting for connecting from " + bindIp + ":" + listenPort);

                Socket socket = serverSocket.accept();
                System.out.println(getTime() + "Accepted from " + socket.getInetAddress().getHostAddress() +":" + socket.getPort());

                // 소켓의 출력스트림을 얻는다.
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                // 원격 소켓(remote socket)에 데이터를 보낸다.
                dos.writeUTF("This is message from server.");
                System.out.println(getTime() + "Write message to client.");

                // 스트림과 소켓을 달아준다.
                dos.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        serverSocket.close();
    }

    static String getTime() {
        SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss] ");
        return f.format(new Date());
    }
}