/*    */ package control;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.ObjectInput;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.net.DatagramPacket;
/*    */ import java.net.DatagramSocket;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import model.Answer;
/*    */ import model.Student;
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
/*    */ 
/*    */ public class UDPClientThread
/*    */   extends Thread
/*    */ {
/*    */   DatagramSocket clientSocket;
/*    */   UDPServer serverControl;
/*    */   DatagramPacket dp;
/* 35 */   Answer answer = null;
/* 36 */   Student student = null;
/*    */   
/*    */   public UDPClientThread(DatagramSocket s, DatagramPacket dp, UDPServer serverControl) {
/* 39 */     this.clientSocket = s;
/* 40 */     this.dp = dp;
/* 41 */     this.serverControl = serverControl;
/* 42 */     this.answer = new Answer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/*    */     try {
/* 50 */       ByteArrayInputStream bis = new ByteArrayInputStream(this.dp.getData());
/* 51 */       ObjectInput in = null;
/* 52 */       in = new ObjectInputStream(bis);
/* 53 */       this.student = (Student)in.readObject();
/* 54 */       if (this.student == null || this.student.getMaSV().equalsIgnoreCase("")) {
/*    */         return;
/*    */       }
/* 57 */       this.answer = this.serverControl.getAnswer(this.student);
/* 58 */       if (this.answer == null)
/* 59 */         this.answer = new Answer(this.student); 
/* 60 */       this.answer.setAlreadyRegistration(true);
/* 61 */       String ip = this.dp.getAddress().toString();
/* 62 */       this.student.setIP(ip);
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
/*    */ 
/*    */ 
/*    */       
/* 81 */       this.serverControl.updateAnswerList(this.answer);
/* 82 */       this.serverControl.updateView(this.student);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     }
/* 89 */     catch (IOException ex) {
/* 90 */       Logger.getLogger(UDPClientThread.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 91 */     } catch (ClassNotFoundException ex) {
/* 92 */       Logger.getLogger(UDPClientThread.class.getName()).log(Level.SEVERE, (String)null, ex);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\ptit\ltm\OnTap\Socket\Server_At_Class\Server_At_Class.jar!\control\UDPClientThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */