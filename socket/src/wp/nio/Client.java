package wp.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {

    //需要一个Selector
    public static void main(String[] args) {

        //创建连接的地址
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8765);
        //声明连接通道
        SocketChannel sc = null;
        //建立缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        ByteBuffer readBuf = ByteBuffer.allocate(1024);

        try {
            //打开通道
            sc = SocketChannel.open();
            //进行连接
            sc.connect(address);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String str = scanner.next();
                if (str.equals("q")){
                    break;
                }
                buf.put(str.getBytes());
                //对缓冲区进行复位
                buf.flip();
                //写出数据
                sc.write(buf);
                //清空缓冲区数据
                buf.clear();

//                sc.read(readBuf);
//                System.out.println(new String(readBuf.array(),"utf-8"));

                sc.read(readBuf);
                readBuf.flip();
                //6 根据缓冲区的数据长度创建相应大小的byte数组，接收缓冲区的数据
                byte[] byteRead = new byte[readBuf.remaining()];
                //7 接收缓冲区数据
                readBuf.get(byteRead);
                //8 打印结果
                String body = new String(byteRead).trim();
                System.out.println("客户端收到: " + body);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sc != null) {
                try {
                    sc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
