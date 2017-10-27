package wp.bio2;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	final static int PORT = 8765;

	public static void main(String[] args) {
		ServerSocket server = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			server = new ServerSocket(PORT);
			System.out.println("server start");
			Socket socket = null;
			//内部封装了 一个ThreadPoolExecutor对象
			HandlerExecutorPool executorPool = new HandlerExecutorPool(50, 1000);
			//使用线程池节省线程的创建与销毁带来的资源浪费。
			while(true){
				socket = server.accept();
				executorPool.execute(new ServerHandler(socket));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null){
				try {
					in.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if(out != null){
				try {
					out.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(server != null){
				try {
					server.close();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
			server = null;				
		}
	}
}
