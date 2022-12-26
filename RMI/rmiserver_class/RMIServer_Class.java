/*    */ package rmiserver_class;
/*    */ 
/*    */ import control.Registration;
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.UnknownHostException;
/*    */ import java.rmi.RemoteException;
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
/*    */ 
/*    */ 
/*    */ public class RMIServer_Class
/*    */ {
/*    */   public static void main(String[] args) throws RemoteException, MalformedURLException, UnknownHostException {
    System.out.println("Server is running...");
/* 26 */     ClientList view = new ClientList();
/* 27 */     Registration registration = new Registration(view);
/*    */   }
/*    */ }


/* Location:              D:\ptit\ltm\OnTap\RMI\dist\Server.jar!\rmiserver_class\RMIServer_Class.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */