package wp.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    final static String ADDRESS = "127.0.0.1";
    final static int PORT = 8765;

    public static void main(String[] args) {

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(ADDRESS, PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String userinfo = "";
            Scanner scanner = new Scanner(System.in);

            System.out.println("请输入用户名：");
            userinfo += scanner.next();
            userinfo += ":";
            System.out.println("请输入密码：");
            userinfo += scanner.next();

            out.println(userinfo);
            //向服务器端发送数据
            //每进行一次out.println，就会收到一次服务器的响应。
//            out.println("接收到客户端的请求数据...");
//            System.out.println("Client: " + in.readLine());
//
//            out.println("接收到客户端的请求数据1111...");
//            System.out.println("Client: " + in.readLine());
//
//            Thread.sleep(3000);
//            out.println("接收到客户端的请求数据...");
//            out.println("接收到客户端的请求数据1111...");

            String response = in.readLine();
            System.out.println("Client: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socket = null;
        }
    }
}
