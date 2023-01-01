/*     */ package model;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class Answer
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 2L;
/*     */   Student student;
/*     */   String reverseStringAnswer;
/*     */   String normalizationStringAnswer;
/*     */   int maxNumericAnswer;
/*     */   int uclnNumericAnswer;
/*     */   int bcnnNumericAnswer;
/*     */   boolean isAlreadyRegistration = false;
/*     */   boolean isReverseStringAnswerRight = false;
/*     */   boolean isMaxNumericAnswerRight = false;
/*     */   boolean isNormalizationStringAnswerRight = false;
/*     */   boolean isBSCNNNumericAnswerRight = false;
/*     */   boolean isUSCLNNumericAnswerRight = false;
/*     */   
/*     */   public boolean isIsAlreadyRegistration() {
/*  35 */     return this.isAlreadyRegistration;
/*     */   }
/*     */   
/*     */   public void setIsAlreadyRegistration(boolean isAlreadyRegistration) {
/*  39 */     this.isAlreadyRegistration = isAlreadyRegistration;
/*     */   }
/*     */ 
/*     */   
/*     */   public Student getStudent() {
/*  44 */     return this.student;
/*     */   }
/*     */   
/*     */   public void setStudent(Student student) {
/*  48 */     this.student = student;
/*     */   }
/*     */   
/*     */   public String getStringAnswer() {
/*  52 */     return this.reverseStringAnswer;
/*     */   }
/*     */   
/*     */   public void setStringAnswer(String stringAnswer) {
/*  56 */     this.reverseStringAnswer = stringAnswer;
/*     */   }
/*     */   
/*     */   public int getNumericAnswer() {
/*  60 */     return this.maxNumericAnswer;
/*     */   }
/*     */   
/*     */   public void setNumericAnswer(int numericAnswer) {
/*  64 */     this.maxNumericAnswer = numericAnswer;
/*     */   }
/*     */   
/*     */   public boolean isIsStringAnswerRight() {
/*  68 */     return this.isReverseStringAnswerRight;
/*     */   }
/*     */   
/*     */   public void setIsStringAnswerRight(boolean isStringAnswerRight) {
/*  72 */     this.isReverseStringAnswerRight = isStringAnswerRight;
/*     */   }
/*     */   
/*     */   public boolean isIsNumericAnswerRight() {
/*  76 */     return this.isMaxNumericAnswerRight;
/*     */   }
/*     */   
/*     */   public void setIsNumericAnswerRight(boolean isNumericAnswerRight) {
/*  80 */     this.isMaxNumericAnswerRight = isNumericAnswerRight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIsReverseStringAnswerRight() {
/*  87 */     return this.isReverseStringAnswerRight;
/*     */   }
/*     */   
/*     */   public void setIsReverseStringAnswerRight(boolean isReverseStringAnswerRight) {
/*  91 */     this.isReverseStringAnswerRight = isReverseStringAnswerRight;
/*     */   }
/*     */   
/*     */   public boolean isIsMaxNumericAnswerRight() {
/*  95 */     return this.isMaxNumericAnswerRight;
/*     */   }
/*     */   
/*     */   public void setIsMaxNumericAnswerRight(boolean isMaxNumericAnswerRight) {
/*  99 */     this.isMaxNumericAnswerRight = isMaxNumericAnswerRight;
/*     */   }
/*     */   
/*     */   public boolean isIsNormalizationStringAnswerRight() {
/* 103 */     return this.isNormalizationStringAnswerRight;
/*     */   }
/*     */   
/*     */   public void setIsNormalizationStringAnswerRight(boolean isNormalizationStringAnswerRight) {
/* 107 */     this.isNormalizationStringAnswerRight = isNormalizationStringAnswerRight;
/*     */   }
/*     */   
/*     */   public boolean isIsBSCNNNumericAnswerRight() {
/* 111 */     return this.isBSCNNNumericAnswerRight;
/*     */   }
/*     */   
/*     */   public void setIsBSCNNNumericAnswerRight(boolean isBSCNNNumericAnswerRight) {
/* 115 */     this.isBSCNNNumericAnswerRight = isBSCNNNumericAnswerRight;
/*     */   }
/*     */   
/*     */   public boolean isIsUSCLNNumericAnswerRight() {
/* 119 */     return this.isUSCLNNumericAnswerRight;
/*     */   }
/*     */   
/*     */   public void setIsUSCLNNumericAnswerRight(boolean isUSCLNNumericAnswerRight) {
/* 123 */     this.isUSCLNNumericAnswerRight = isUSCLNNumericAnswerRight;
/*     */   }
/*     */   
/*     */   public String getReverserStringAnswer() {
/* 127 */     return this.reverseStringAnswer;
/*     */   }
/*     */   
/*     */   public void setReverserStringAnswer(String reverserStringAnswer) {
/* 131 */     this.reverseStringAnswer = reverserStringAnswer;
/*     */   }
/*     */   
/*     */   public String getNormalizationStringAnswer() {
/* 135 */     return this.normalizationStringAnswer;
/*     */   }
/*     */   
/*     */   public void setNormalizationStringAnswer(String normalizationStringAnswer) {
/* 139 */     this.normalizationStringAnswer = normalizationStringAnswer;
/*     */   }
/*     */   
/*     */   public int getMaxNumericAnswer() {
/* 143 */     return this.maxNumericAnswer;
/*     */   }
/*     */   
/*     */   public void setMaxNumericAnswer(int maxNumericAnswer) {
/* 147 */     this.maxNumericAnswer = maxNumericAnswer;
/*     */   }
/*     */   
/*     */   public int getUclnNumericAnswer() {
/* 151 */     return this.uclnNumericAnswer;
/*     */   }
/*     */   
/*     */   public void setUclnNumericAnswer(int uclnNumericAnswer) {
/* 155 */     this.uclnNumericAnswer = uclnNumericAnswer;
/*     */   }
/*     */   
/*     */   public int getBcnnNumericAnswer() {
/* 159 */     return this.bcnnNumericAnswer;
/*     */   }
/*     */   
/*     */   public void setBcnnNumericAnswer(int bcnnNumericAnswer) {
/* 163 */     this.bcnnNumericAnswer = bcnnNumericAnswer;
/*     */   }
/*     */ }


/* Location:              D:\ptit\ltm\OnTap\RMI\dist\Server.jar!\model\Answer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */