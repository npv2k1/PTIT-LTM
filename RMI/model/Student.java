/*    */ package model;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public class Student
/*    */   implements Serializable
/*    */ {
/*    */   static final long serialVersionUID = 1L;
/*    */   private String maSV;
/*    */   private String hovaten;
/*    */   private String IP;
/*    */   private int group;
/*    */   boolean isAlreadyRegistration = false;
/*    */   
/*    */   public boolean isIsAlreadyRegistration() {
/* 31 */     return this.isAlreadyRegistration;
/*    */   }
/*    */   
/*    */   public void setIsAlreadyRegistration(boolean isAlreadyRegistration) {
/* 35 */     this.isAlreadyRegistration = isAlreadyRegistration;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getGroup() {
/* 41 */     return this.group;
/*    */   }
/*    */   
/*    */   public Student(String maSV, String hovaten, String IP, int group) {
/* 45 */     this.maSV = maSV;
/* 46 */     this.hovaten = hovaten;
/* 47 */     this.IP = IP;
/* 48 */     this.group = group;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMaSV() {
/* 53 */     return this.maSV;
/*    */   }
/*    */   
/*    */   public String getHovaten() {
/* 57 */     return this.hovaten;
/*    */   }
/*    */   
/*    */   public String getIP() {
/* 61 */     return this.IP;
/*    */   }
/*    */   
/*    */   public void setMaSV(String maSV) {
/* 65 */     this.maSV = maSV;
/*    */   }
/*    */   
/*    */   public void setHovaten(String hovaten) {
/* 69 */     this.hovaten = hovaten;
/*    */   }
/*    */   
/*    */   public void setIP(String IP) {
/* 73 */     this.IP = IP;
/*    */   }
/*    */   
/*    */   public void setNhom(int nhom) {
/* 77 */     this.group = nhom;
/*    */   }
/*    */ }


/* Location:              D:\ptit\ltm\OnTap\RMI\dist\Server.jar!\model\Student.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */