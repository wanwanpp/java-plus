package proxy.推演.demo;

import proxy.推演.ProxyEx;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TicketBuyer {
    public static void main(String[] args) {
        // 创建真正的TickerSeller对象，作为stub实体
        final TicketSeller stub = new Expo2010TicketSeller();

        // 创建扩展调用处理器对象
        InvocationHandler handler = new ProxyEx.InvocationHandlerEx() {
            public Object getStub(Class stubClass) {
                // 仅对可接受的Class类型返回stub实体
                if (stubClass.isAssignableFrom(stub.getClass())) {
                    return stub;
                }
                return null;
            }

            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                Object o;
                try {
                    System.out.println("   >>> Enter method: "
                            + method.getName());
                    o = method.invoke(stub, args);
                } catch (InvocationTargetException e) {
                    throw e.getCause();
                } finally {
                    System.out.println("   <<< Exit method: "
                            + method.getName());
                }
                return o;
            }
        };

        // 通过ProxyEx构造动态代理
        TicketSeller seller = (TicketSeller) ProxyEx.newProxyInstance(
                TicketBuyer.class.getClassLoader(),
                new Class[]{TicketSeller.class},
                handler);

        // 显示代理类的类型
        System.out.println("Ticket Seller Class: " + seller.getClass() + "\n");
        // 直接访问theme变量，验证代理类变量在对象构造时同步的有效性
        System.out.println("Ticket Theme: " + seller.theme + "\n");
        // 函数访问price信息
        System.out.println("Query Ticket Price...");
        System.out.println("Ticket Price: " + seller.getTicketPrice() + " RMB\n");
        // 模拟票务交易
        buyTicket(seller, 1, 200);
        buyTicket(seller, 1, 160);
        buyTicket(seller, 250, 30000);
        // 直接更新theme变量
        System.out.println("Updating Ticket Theme...\n");
        seller.theme = "World Expo 2010 in Shanghai";
        // 函数访问theme信息，验证扩展动态代理机制对变量同步的有效性
        System.out.println("Query Updated Ticket Theme...");
        System.out.println("Updated Ticket Theme: " + seller.getTicketTheme() + "\n");
    }

    // 购票函数
    protected static void buyTicket(TicketSeller seller, int ticketNumber, int money) {
        try {
            System.out.println("Transaction: Order " + ticketNumber + " ticket(s) with "
                    + money + " RMB");
            int charge = seller.buy(ticketNumber, money);
            System.out.println("Transaction: Succeed - Charge is " + charge + " RMB\n");
        } catch (Exception e) {
            System.out.println("Transaction: Fail - " + e.getMessage() + "\n");
        }
    }
}