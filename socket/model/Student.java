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
/*    */ public class Student
/*    */   implements Serializable
/*    */ {
/*    */   static final long serialVersionUID = 12L;
/*    */   private String maSV;
/*    */   private String hovaten;
/*    */   private String IP;
/*    */   private int group;
/*    */   
/*    */   public int getGroup() {
/* 27 */     return this.group;
/*    */   }
/*    */   
/*    */   public Student() {
/* 31 */     System.out.println("Create a blank student");
/*    */   }
/*    */   
/*    */   public Student(String maSV, String hovaten, String IP, int group) {
/* 35 */     this.maSV = maSV;
/* 36 */     this.hovaten = hovaten;
/* 37 */     this.IP = IP;
/* 38 */     this.group = group;
/*    */   }
/*    */   
/*    */   public String getMaSV() {
/* 42 */     return this.maSV;
/*    */   }
/*    */   
/*    */   public String getHovaten() {
/* 46 */     return this.hovaten;
/*    */   }
/*    */   
/*    */   public String getIP() {
/* 50 */     return this.IP;
/*    */   }
/*    */   
/*    */   public void setMaSV(String maSV) {
/* 54 */     this.maSV = maSV;
/*    */   }
/*    */   
/*    */   public void setHovaten(String hovaten) {
/* 58 */     this.hovaten = hovaten;
/*    */   }
/*    */   
/*    */   public void setIP(String IP) {
/* 62 */     this.IP = IP;
/*    */   }
/*    */   
/*    */   public void setGroup(int nhom) {
/* 66 */     this.group = nhom;
/*    */   }
/*    */ }


/* Location:              D:\ptit\ltm\OnTap\Socket\Server_At_Class\Server_At_Class.jar!\model\Student.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */