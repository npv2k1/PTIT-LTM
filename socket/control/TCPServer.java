/*     */ package control;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.InetAddress;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
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
/*     */ public class TCPServer
/*     */   extends Thread
/*     */ {
/*  28 */   ServerSocket myServer = null;
/*     */   
/*     */   Student input;
/*     */   
/*     */   ArrayList<Student> svList;
/*     */   ArrayList<Answer> answerList;
/*     */   ClientList view;
/*     */   
/*     */   public TCPServer(ClientList view) {
/*  37 */     this.svList = new ArrayList<>();
/*  38 */     this.answerList = new ArrayList<>();
/*     */     
/*  40 */     this.view = view;
/*  41 */     this.view.setVisible(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addStudent(Student s) {
/*  47 */     if (this.svList == null) {
/*  48 */       this.svList = new ArrayList<>();
/*  49 */       this.svList.add(s);
/*     */     } else {
/*  51 */       Iterator<Student> it = this.svList.iterator();
/*  52 */       boolean isNew = true;
/*  53 */       while (it.hasNext()) {
/*  54 */         Student next = it.next();
/*  55 */         if (s.getMaSV() != null && next.getMaSV() != null && next.getMaSV().equalsIgnoreCase(s.getMaSV())) {
/*  56 */           isNew = false;
/*     */           break;
/*     */         } 
/*     */       } 
/*  60 */       if (isNew) {
/*  61 */         this.svList.add(s);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Student getStudent(String maSV) {
/*  69 */     Iterator<Student> it = this.svList.iterator();
/*  70 */     while (it.hasNext()) {
/*  71 */       Student next = it.next();
/*  72 */       if (next.getMaSV().equalsIgnoreCase(maSV)) {
/*  73 */         return next;
/*     */       }
/*     */     } 
/*     */     
/*  77 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Answer getAnswer(Student student) {
/*  82 */     Iterator<Answer> it = this.answerList.iterator();
/*  83 */     while (it.hasNext()) {
/*  84 */       Answer next = it.next();
/*  85 */       if (next.getStudent().getMaSV().equalsIgnoreCase(student.getMaSV())) {
/*  86 */         return next;
/*     */       }
/*     */     } 
/*  89 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void updateAnswerList(Answer answer) {
/*  94 */     System.out.println("--- answer.isAlreadyRegistration() ---" + answer.isAlreadyRegistration());
/*  95 */     Iterator<Answer> it = this.answerList.iterator();
/*  96 */     boolean isUpdate = false;
/*  97 */     while (it.hasNext()) {
/*  98 */       Answer next = it.next();
/*  99 */       if (next.getStudent().getMaSV().equalsIgnoreCase(answer.getStudent().getMaSV())) {
/* 100 */         isUpdate = true;
/* 101 */         next.getStudent().setGroup(answer.getStudent().getGroup());
/* 102 */         next.getStudent().setHovaten(answer.getStudent().getHovaten());
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 107 */     if (!isUpdate) {
/* 108 */       this.answerList.add(answer);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void updateView(Student student) {
/* 114 */     Iterator<Answer> it = this.answerList.iterator();
/* 115 */     while (it.hasNext()) {
/* 116 */       Answer next = it.next();
/* 117 */       if (next.getStudent().getMaSV().equalsIgnoreCase(student.getMaSV())) {
/* 118 */         this.view.updateAnswerView(next);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     try {
/* 127 */       super.run();
/* 128 */       openServer();
/* 129 */       listening();
/* 130 */     } catch (IOException ex) {
/* 131 */       Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void openServer() {
/*     */     try {
/* 138 */       System.out.println("Server chay tren IP " + InetAddress.getLocalHost());
/*     */       
/* 140 */       this.myServer = new ServerSocket(11310);
/*     */     }
/* 142 */     catch (IOException e) {
/* 143 */       System.out.println(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void listening() throws IOException {
/*     */     while (true) {
/*     */       try {
/*     */         while (true) {
/* 153 */           Socket clientSocket = this.myServer.accept();
/* 154 */           TCPClientThread clientThread = new TCPClientThread(clientSocket, this);
/* 155 */           clientThread.start();
/*     */         }
            /* 157 */       } catch (SocketException so) {
/* 158 */         System.out.println(" ------- Loi client ------- ");
/* 159 */         so.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() {
/*     */     try {
/* 166 */       this.myServer.close();
/* 167 */     } catch (IOException e) {
/*     */       
/* 169 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\ptit\ltm\OnTap\Socket\Server_At_Class\Server_At_Class.jar!\control\TCPServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */