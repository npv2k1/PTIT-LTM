/*     */ package control;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketException;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import model.Answer;
/*     */ import model.Student;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TCPClientThread
/*     */   extends Thread
/*     */ {
/*     */   Socket clientSocket;
/*     */   TCPServer serverControl;
/*     */   DataInputStream is;
/*     */   DataOutputStream os;
/*     */   ObjectOutputStream objectOutputStream;
/*     */   ObjectInputStream objectInputStream;
/*  30 */   Answer answer = null;
/*  31 */   Student student = null;
/*     */   
/*     */   public TCPClientThread(Socket s, TCPServer serverControl) {
/*  34 */     this.clientSocket = s;
/*  35 */     this.serverControl = serverControl;
/*  36 */     this.answer = new Answer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean initiateStudentAnswer() throws IOException, ClassNotFoundException {
/*  47 */     String masv = this.is.readUTF();
/*  48 */     if (masv.equals(null) || masv.equalsIgnoreCase("")) {
/*  49 */       System.out.println("Ma SV null");
/*  50 */       return false;
/*     */     } 
/*     */     
/*  53 */     this.student = new Student();
/*  54 */     this.student.setMaSV(masv);
/*  55 */     this.objectOutputStream.writeObject(this.student);
/*  56 */     this.student = (Student)this.objectInputStream.readObject();
/*     */     
/*  58 */     String hovaten = this.is.readUTF();
/*  59 */     int nhom = this.is.readInt();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  72 */     String ipClient = "";
/*     */     try {
/*  74 */       ipClient = this.clientSocket.getInetAddress().getHostAddress();
/*  75 */     } catch (Exception e) {
/*  76 */       System.out.println("Error while getting IP");
/*  77 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  80 */     if (!ipClient.equalsIgnoreCase("")) {
/*  81 */       this.student.setIP(ipClient);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     this.answer = (Answer)this.objectInputStream.readObject();
/*     */     
/*  99 */     if (this.answer == null) {
/* 100 */       System.out.println("Nhan Answer loi");
/* 101 */       return false;
/*     */     } 
/* 103 */     if (!this.answer.getStudent().getMaSV().equalsIgnoreCase(this.student.getMaSV()) || 
/* 104 */       !this.answer.getStudent().getHovaten().equalsIgnoreCase(hovaten) || this.answer
/* 105 */       .getStudent().getGroup() != nhom) {
/* 106 */       System.out.println("Nhan Answer cac truong khong khop");
/* 107 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 112 */     System.out.println("NEW REGISTRATION");
/*     */     
/* 114 */     this.answer.setAlreadyRegistration(true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean initiateStudentAnswerObject() throws IOException {
/* 136 */     this
/*     */       
/* 138 */       .student = new Student(this.is.readUTF(), this.is.readUTF(), this.clientSocket.getInetAddress().getHostAddress(), this.is.readInt());
/* 139 */     if (this.student == null) {
/* 140 */       return false;
/*     */     }
/* 142 */     if (this.student.getMaSV().trim().equalsIgnoreCase("")) {
/* 143 */       System.out.println("SV may " + this.student.getIP() + " Nhap khong dung ma sv");
/* 144 */       return false;
/*     */     } 
/*     */     
/* 147 */     this.answer = this.serverControl.getAnswer(this.student);
/*     */     
/* 149 */     if (this.answer == null) {
/* 150 */       System.out.println("NEW REGISTRATION");
/* 151 */       this.answer = new Answer(this.student);
/* 152 */       this.answer.setAlreadyRegistration(true);
/*     */     } else {
/* 154 */       this.answer.setStudent(this.student);
/* 155 */       this.answer.setAlreadyRegistration(true);
/*     */     } 
/* 157 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     try {
/* 163 */       super.run();
/* 164 */       this.os = new DataOutputStream(this.clientSocket.getOutputStream());
/* 165 */       this.objectInputStream = new ObjectInputStream(this.clientSocket.getInputStream());
/* 166 */       this.is = new DataInputStream(this.clientSocket.getInputStream());
/* 167 */       this.objectOutputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
/*     */       
/* 169 */       if (!initiateStudentAnswer()) {
/* 170 */         System.out.println("Client loi");
/*     */ 
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 179 */       this.serverControl.updateAnswerList(this.answer);
/* 180 */       this.serverControl.updateView(this.student);
/*     */     }
/* 182 */     catch (SocketException so) {
/* 183 */       System.out.println(" ------- Loi client ------- ");
/* 184 */       System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
/* 185 */       so.printStackTrace();
/* 186 */       if (this.clientSocket != null) {
/*     */         try {
/* 188 */           this.clientSocket.close();
/* 189 */         } catch (IOException ex) {
/* 190 */           Logger.getLogger(TCPClientThread.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */         } 
/*     */       }
/* 193 */     } catch (IOException e) {
/* 194 */       System.out.println(" ------- Loi client ------- ");
/* 195 */       System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
/* 196 */       e.printStackTrace();
/* 197 */       if (this.is != null) {
/*     */         try {
/* 199 */           this.is.close();
/*     */         }
/* 201 */         catch (IOException ex) {
/* 202 */           Logger.getLogger(TCPClientThread.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */         } 
/*     */       }
/* 205 */       if (this.clientSocket != null) {
/*     */         try {
/* 207 */           this.clientSocket.close();
/* 208 */         } catch (IOException ex) {
/* 209 */           Logger.getLogger(TCPClientThread.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */         } 
/*     */       }
/* 212 */     } catch (Exception e2) {
/* 213 */       System.out.println(" ------- Loi client ------- ");
/* 214 */       System.out.println("Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
/* 215 */       e2.printStackTrace();
/* 216 */       if (this.is != null) {
/*     */         try {
/* 218 */           this.is.close();
/*     */         }
/* 220 */         catch (IOException ex) {
/* 221 */           Logger.getLogger(TCPClientThread.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */         } 
/*     */       }
/*     */       
/* 225 */       if (this.clientSocket != null) {
/*     */         try {
/* 227 */           this.clientSocket.close();
/* 228 */         } catch (IOException ex) {
/* 229 */           Logger.getLogger(TCPClientThread.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */         } 
/*     */       }
/*     */     } finally {
/*     */       
/* 234 */       System.out.println(" finally Client IP : " + this.clientSocket.getInetAddress().getHostAddress());
/* 235 */       if (this.is != null) {
/*     */         try {
/* 237 */           this.is.close();
/*     */         }
/* 239 */         catch (IOException ex) {
/* 240 */           Logger.getLogger(TCPClientThread.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */         } 
/*     */       }
/*     */       
/* 244 */       if (this.clientSocket != null)
/*     */         try {
/* 246 */           this.clientSocket.close();
/* 247 */         } catch (IOException ex) {
/* 248 */           Logger.getLogger(TCPClientThread.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */         }  
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\ptit\ltm\OnTap\Socket\Server_At_Class\Server_At_Class.jar!\control\TCPClientThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */