package wp.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    HashMap<String, String> users = new HashMap<>();

    {
        users.put("wanwanpp", "980325");
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String body = in.readLine();
            //       wanwanpp:980325
            String[] nameAndPwd = body.split(":");
            String user = nameAndPwd[0];
            String pwd = nameAndPwd[1];
            boolean hasUser = users.containsKey(user);
            if (!hasUser) {
                out.println("服务器：认证失败，不存在用户名为“" + user + "“的用户。");
            } else {
                String pwdFromDb = users.get(user);
                if (pwdFromDb.equals(pwd)) {
                    out.println("服务器：认证成功。");
                } else {
                    out.println("服务器：您的密码输入错误。");
                }
            }
            System.out.println("完成认证，用户名为：" + user + ",密码为：" + pwd);
//            while (true) {
//                body = in.readLine();
//                if (body == null) break;
//                System.out.println("Server :" + body);
//                out.println("服务器端回送响的应数据.");
//            }

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
