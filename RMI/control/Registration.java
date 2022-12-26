/*     */ package control;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.net.InetAddress;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.UnknownHostException;
/*     */ import java.rmi.RemoteException;
/*     */ import java.rmi.registry.LocateRegistry;
/*     */ import java.rmi.registry.Registry;
/*     */ import java.rmi.server.UnicastRemoteObject;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Registration
/*     */   extends UnicastRemoteObject
/*     */   implements IRegistration, ActionListener
/*     */ {
/*  37 */   ArrayList<Student> studentLists = new ArrayList<>();
/*  38 */   ArrayList<Answer> answerLists = new ArrayList<>(); public Registration(ClientList view) throws RemoteException, MalformedURLException, UnknownHostException {
/*  39 */     Registry reg = LocateRegistry.createRegistry(1099);
/*  40 */     System.out.println("IP " + InetAddress.getLocalHost().getHostAddress());
/*  41 */     reg.rebind("Server", this);
/*     */     
/*  43 */     this.view = view;
/*  44 */     this.view.setVisible(true);
/*     */   }
/*     */   ClientList view;
/*     */   
/*     */   public synchronized Student register(Student s) throws RemoteException {
/*  49 */     Iterator<Student> it = this.studentLists.iterator();
/*  50 */     if (s.getMaSV() == null)
/*  51 */       return null; 
/*  52 */     while (it.hasNext()) {
/*  53 */       Student next = it.next();
/*  54 */       if (s.getMaSV().equalsIgnoreCase(next.getMaSV()) && s.getMaSV() != null) {
/*  55 */         return next;
/*     */       }
/*     */     } 
/*     */     
/*  59 */     s.setIsAlreadyRegistration(true);
/*  60 */     if (s.getMaSV() != null) {
/*  61 */       this.studentLists.add(s);
/*  62 */       this.view.addNewRow(s);
/*     */     } 
/*  64 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Answer answer(Answer answer) throws RemoteException {
/*  72 */     Iterator<Student> it_s = this.studentLists.iterator();
/*  73 */     if (it_s.hasNext()) {
/*  74 */       Student next = it_s.next();
/*  75 */       if (next.getMaSV().equalsIgnoreCase(answer.getStudent().getMaSV())) {
/*  76 */         answer.setIsAlreadyRegistration(true);
/*     */       }
/*     */     } 
/*  79 */     Iterator<Answer> it = this.answerLists.iterator();
/*  80 */     int index = 0;
/*  81 */     while (it.hasNext()) {
/*  82 */       Answer next = it.next();
/*  83 */       if (next.getStudent().getMaSV().equalsIgnoreCase(answer.getStudent().getMaSV())) {
/*  84 */         answer.setIsAlreadyRegistration(true);
/*  85 */         this.answerLists.set(index, answer);
/*  86 */         this.view.updateAnswerView(answer);
/*  87 */         return answer;
/*     */       } 
/*  89 */       index++;
/*     */     } 
/*  91 */     System.out.println("Get answer from " + answer.getStudent().getMaSV() + " IP " + answer.getStudent().getIP());
/*     */     
/*  93 */     this.view.updateAnswerView(answer);
/*  94 */     this.answerLists.add(answer);
/*  95 */     return answer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 102 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ }


/* Location:              D:\ptit\ltm\OnTap\RMI\dist\Server.jar!\control\Registration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */