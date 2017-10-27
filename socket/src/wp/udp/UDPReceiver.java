package wp.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by 王萍 on 2017/10/26 0026.
 */
public class UDPReceiver {

    public static void main(String[] args) {
        try {
            DatagramSocket receiveSocket = new DatagramSocket(10000);
            byte buf[] = new byte[1024];
            DatagramPacket receivepPacket = new DatagramPacket(buf, buf.length);
            System.out.println("开始接收数据报......");
            while (true) {
                receiveSocket.receive(receivepPacket);
                String receiveData = new String(receivepPacket.getData(), 0, receivepPacket.getLength());
                String name = receivepPacket.getAddress().getHostName();
                int port = receivepPacket.getPort();
                System.out.println("来自主机：" + name + "，的端口：" + port + "的数据：" + receiveData);
                if ("Time".equals(receiveData)) {
                    byte[] timeMillis = String.valueOf(System.currentTimeMillis()).getBytes();
                    System.out.println("send msg : " + String.valueOf(System.currentTimeMillis()));
                    receiveSocket.send(new DatagramPacket(timeMillis, timeMillis.length, receivepPacket.getAddress(), receivepPacket.getPort()));
                }
            }
        } catch (SocketException e) {
            System.out.println("can not open the datagram socket");
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

}