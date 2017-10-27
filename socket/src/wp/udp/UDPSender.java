package wp.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 王萍 on 2017/10/26 0026.
 */
public class UDPSender {

    public static void main(String[] args) {

        try {
            DatagramSocket socket = new DatagramSocket();
            String string = "Time";
            byte[] databyte = string.getBytes();
            DatagramPacket packet = new DatagramPacket(databyte,
                    databyte.length, InetAddress.getByName("127.0.0.1"), 10000);
            socket.send(packet);
            System.out.println("send the data:" + string);

            DatagramPacket receiveDp = new DatagramPacket(new byte[1024], 1024);
            socket.receive(receiveDp);
            String response = new String(receiveDp.getData(), 0, receiveDp.getLength());
            String rcvd = "服务器时间为:  " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.valueOf(response)));
            System.out.println(rcvd);
        } catch (SocketException e) {
            System.out.println("can not open the datagram socket");
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}