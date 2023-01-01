/*    */ package server;
/*    */ 
/*    */ import control.TCPServer;
import control.UDPServer;
/*    */ import java.io.IOException;
/*    */ import view.ClientList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FinalTest
/*    */ {
/*    */   public static void main(String[] args) throws ClassNotFoundException, IOException {
                System.out.println("UDP Server is running...");
/* 22 */     ClientList view = new ClientList();
///* 23 */     UDPServer udpServer = new UDPServer(view);
///* 24 */     udpServer.start();
                TCPServer tcpServer = new TCPServer(view);
                tcpServer.start();
/*    */   }
/*    */ }


/* Location:              D:\ptit\ltm\OnTap\Socket\Server_At_Class\Server_At_Class.jar!\server\FinalTest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */