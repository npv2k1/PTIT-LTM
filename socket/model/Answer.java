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
/*    */ public class Answer
/*    */   implements Serializable
/*    */ {
/*    */   static final long serialVersionUID = 23L;
/*    */   Student student;
/*    */   boolean alreadyRegistration = false;
/*    */   
/*    */   public boolean isAlreadyRegistration() {
/* 22 */     return this.alreadyRegistration;
/*    */   }
/*    */   
/*    */   public void setAlreadyRegistration(boolean alreadyRegistration) {
/* 26 */     this.alreadyRegistration = alreadyRegistration;
/*    */   }
/*    */ 
/*    */   
/*    */   public Answer() {}
/*    */   
/*    */   public Answer(Student student) {
/* 33 */     this.student = student;
/*    */   }
/*    */   
/*    */   public Student getStudent() {
/* 37 */     return this.student;
/*    */   }
/*    */   
/*    */   public void setStudent(Student student) {
/* 41 */     this.student = student;
/*    */   }
/*    */   
/*    */   public boolean isMyAnswer(String maSV) {
/* 45 */     if (this.student == null || this.student.getMaSV() == null) {
/* 46 */       System.err.println("Chua co sinh vien nay !");
/* 47 */       return false;
/*    */     } 
/* 49 */     return this.student.getMaSV().equalsIgnoreCase(maSV);
/*    */   }
/*    */ }


/* Location:              D:\ptit\ltm\OnTap\Socket\Server_At_Class\Server_At_Class.jar!\model\Answer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */