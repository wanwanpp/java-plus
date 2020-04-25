package wp.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    final static int PROT = 8765;

    public static void main(String[] args) {

        ServerSocket server = null;
        try {
            server = new ServerSocket(PROT);
            System.out.println(" server start .. ");
            //进行阻塞
            while (true) {//这里应该循环，使得可以接受多个客户端的请求。
                Socket socket = server.accept();
                //新建一个线程执行客户端的任务
                new Thread(new ServerHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            server = null;
        }
    }
}
