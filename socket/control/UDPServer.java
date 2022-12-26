/*     */ package control;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.DatagramPacket;
/*     */ import java.net.DatagramSocket;
/*     */ import java.net.InetAddress;
/*     */ import java.net.SocketException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import model.Answer;
/*     */ import model.Student;
/*     */ import view.ClientList;
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
/*     */ public class UDPServer
/*     */   extends Thread
/*     */ {
/*  28 */   DatagramSocket myServer = null;
/*     */   
/*     */   Student input;
/*     */   
/*     */   ArrayList<Student> svList;
/*     */   ArrayList<Answer> answerList;
/*     */   ClientList view;
/*     */   
/*     */   public UDPServer(ClientList view) {
/*  37 */     this.svList = new ArrayList<>();
/*  38 */     this.answerList = new ArrayList<>();
/*     */     
/*  40 */     this.view = view;
/*  41 */     this.view.setVisible(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void addStudent(Student s) {
/*  46 */     if (this.svList == null) {
/*  47 */       this.svList = new ArrayList<>();
/*  48 */       this.svList.add(s);
/*     */     } else {
/*  50 */       Iterator<Student> it = this.svList.iterator();
/*  51 */       boolean isNew = true;
/*  52 */       while (it.hasNext()) {
/*  53 */         Student next = it.next();
/*  54 */         if (s.getMaSV() != null && next.getMaSV() != null && next.getMaSV().equalsIgnoreCase(s.getMaSV())) {
/*  55 */           isNew = false;
/*     */           break;
/*     */         } 
/*     */       } 
/*  59 */       if (isNew) {
/*  60 */         this.svList.add(s);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Student getStudent(String maSV) {
/*  68 */     Iterator<Student> it = this.svList.iterator();
/*  69 */     while (it.hasNext()) {
/*  70 */       Student next = it.next();
/*  71 */       if (next.getMaSV().equalsIgnoreCase(maSV)) {
/*  72 */         return next;
/*     */       }
/*     */     } 
/*     */     
/*  76 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Answer getAnswer(Student student) {
/*  81 */     Iterator<Answer> it = this.answerList.iterator();
/*  82 */     while (it.hasNext()) {
/*  83 */       Answer next = it.next();
/*  84 */       if (next.getStudent().getMaSV().equalsIgnoreCase(student.getMaSV())) {
/*  85 */         return next;
/*     */       }
/*     */     } 
/*  88 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void updateAnswerList(Answer answer) {
/*  93 */     System.out.println("--- answer.isAlreadyRegistration() ---" + answer.isAlreadyRegistration());
/*  94 */     Iterator<Answer> it = this.answerList.iterator();
/*  95 */     boolean isUpdate = false;
/*  96 */     while (it.hasNext()) {
/*  97 */       Answer next = it.next();
/*  98 */       if (next.getStudent().getMaSV().equalsIgnoreCase(answer.getStudent().getMaSV())) {
/*  99 */         isUpdate = true;
/* 100 */         next.getStudent().setGroup(answer.getStudent().getGroup());
/* 101 */         next.getStudent().setHovaten(answer.getStudent().getHovaten());
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 106 */     if (!isUpdate) {
/* 107 */       this.answerList.add(answer);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void updateView(Student student) {
/* 113 */     Iterator<Answer> it = this.answerList.iterator();
/* 114 */     while (it.hasNext()) {
/* 115 */       Answer next = it.next();
/* 116 */       if (next.getStudent().getMaSV().equalsIgnoreCase(student.getMaSV())) {
/* 117 */         this.view.updateAnswerView(next);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     try {
/* 126 */       super.run();
/* 127 */       openServer();
/* 128 */       listening();
/* 129 */     } catch (IOException ex) {
/* 130 */       Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void openServer() {
/*     */     try {
/* 137 */       System.out.println("Server chay tren IP " + InetAddress.getLocalHost());
/*     */       
/* 139 */       this.myServer = new DatagramSocket(11310);
/*     */     }
/* 141 */     catch (IOException e) {
/* 142 */       System.out.println(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void listening() throws IOException {
/*     */     while (true) {
/*     */       try {
/*     */         while (true) {
/* 153 */           byte[] receiveData = new byte[1024];
/*     */           
/* 155 */           DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
/* 156 */           this.myServer.receive(receivePacket);
/*     */ 
/*     */ 
/*     */           
/* 160 */           System.out.println("Receive data from " + receivePacket.getAddress().getHostName());
/*     */           
/* 162 */           UDPClientThread clientThread = new UDPClientThread(this.myServer, receivePacket, this);
/* 163 */           clientThread.start();
/*     */         }
            /* 165 */       } catch (SocketException so) {
/* 166 */         System.out.println(" ------- Loi client ------- ");
/* 167 */         so.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() {
/* 173 */     this.myServer.close();
/*     */   }
/*     */ }


/* Location:              D:\ptit\ltm\OnTap\Socket\Server_At_Class\Server_At_Class.jar!\control\UDPServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */