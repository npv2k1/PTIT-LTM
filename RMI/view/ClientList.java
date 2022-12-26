/*     */ package view;
/*     */ 
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.Vector;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ import model.Answer;
/*     */ import model.Student;
/*     */ import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*     */ import org.apache.poi.ss.usermodel.Cell;
/*     */ import org.apache.poi.ss.usermodel.CreationHelper;
/*     */ import org.apache.poi.ss.usermodel.Row;
/*     */ import org.apache.poi.ss.usermodel.Sheet;
/*     */ 
/*     */ public class ClientList
/*     */   extends JFrame
/*     */ {
/*     */   DefaultTableModel model;
/*  39 */   private String[] columnNames = new String[] { "Số TT", "Mã SV", "Họ và Tên", "IP", "Nhóm", "Đăng ký", "De1", "De2", "De3", "De4", "De5" }; private Object[][] data; private JMenu jMenu1;
/*     */   private JMenu jMenu2;
/*     */   
/*     */   public ClientList() {
/*  43 */     initComponents();
/*  44 */     this.model = new DefaultTableModel(this.data, (Object[])this.columnNames);
/*  45 */     this.jTabClientList.setModel(this.model);
/*     */   }
/*     */   private JMenuBar jMenuBar1; private JMenuItem jMenuItem1; private JScrollPane jScrollPane1; private JTable jTabClientList;
/*     */   public void addNewRow(Student sv) {
/*  49 */     SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
/*  50 */     Date currentTime = new Date();
/*  51 */     String dateString = formatter.format(currentTime);
/*     */     
/*  53 */     this.model.addRow(new Object[] { Integer.valueOf(this.model.getRowCount()), sv
/*  54 */           .getMaSV(), sv.getHovaten(), sv.getIP(), Integer.valueOf(sv.getGroup()), "Yes" });
/*     */   }
/*     */   
/*     */   public void addNewRows(Answer answer) {
/*  58 */     this.model.addRow(new Object[] { Integer.valueOf(this.model.getRowCount() + 1), answer
/*  59 */           .getStudent().getMaSV(), answer.getStudent().getHovaten(), answer
/*  60 */           .getStudent().getIP(), Integer.valueOf(answer.getStudent().getGroup()), answer.isIsAlreadyRegistration() ? "Yes" : "No", 
/*  61 */           Boolean.valueOf(answer.isIsStringAnswerRight()), Boolean.valueOf(answer.isIsNumericAnswerRight()), "", "", "" });
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAnswerView(Answer answer) {
/*  66 */     if (this.model.getRowCount() == 0) {
/*  67 */       addNewRows(answer);
/*     */       
/*     */       return;
/*     */     } 
/*  71 */     Vector<Vector> rows = this.model.getDataVector();
/*  72 */     Iterator<Vector> it = rows.iterator();
/*  73 */     int rowPos = 0;
/*  74 */     boolean isUpdate = false;
/*  75 */     while (it.hasNext()) {
/*  76 */       rowPos++;
/*  77 */       Vector next = it.next();
/*  78 */       Iterator itItem = next.iterator();
/*  79 */       if (next != null && next.get(1).toString().equalsIgnoreCase(answer.getStudent().getMaSV())) {
/*  80 */         isUpdate = true;
/*  81 */         System.out.println(" answer.isIsAlreadyRegistration() " + answer.isIsAlreadyRegistration());
/*  82 */         this.model.setValueAt(answer.isIsAlreadyRegistration() ? "Yes" : "No", rowPos - 1, 5);
/*     */         
/*  84 */         this.model.setValueAt(Boolean.valueOf(answer.isIsReverseStringAnswerRight()), rowPos - 1, 6);
/*  85 */         this.model.setValueAt(Boolean.valueOf(answer.isIsNormalizationStringAnswerRight()), rowPos - 1, 7);
/*  86 */         this.model.setValueAt(Boolean.valueOf(answer.isIsBSCNNNumericAnswerRight()), rowPos - 1, 8);
/*  87 */         this.model.setValueAt(Boolean.valueOf(answer.isIsUSCLNNumericAnswerRight()), rowPos - 1, 9);
/*  88 */         this.model.setValueAt(Boolean.valueOf(answer.isIsMaxNumericAnswerRight()), rowPos - 1, 10);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  93 */     if (!isUpdate) {
/*  94 */       addNewRows(answer);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initComponents() {
/* 107 */     this.jMenu1 = new JMenu();
/* 108 */     this.jScrollPane1 = new JScrollPane();
/* 109 */     this.jTabClientList = new JTable();
/* 110 */     this.jMenuBar1 = new JMenuBar();
/* 111 */     this.jMenu2 = new JMenu();
/* 112 */     this.jMenuItem1 = new JMenuItem();
/*     */     
/* 114 */     this.jMenu1.setText("jMenu1");
/*     */     
/* 116 */     setDefaultCloseOperation(3);
/* 117 */     addWindowListener(new WindowAdapter() {
/*     */           public void windowClosing(WindowEvent evt) {
/* 119 */             ClientList.this.windowClosing(evt);
/*     */           }
/*     */         });
/*     */     
/* 123 */     this.jTabClientList.setModel(new DefaultTableModel(new Object[][] { {}, {}, {}, {} }, (Object[])new String[0]));
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
/* 134 */     this.jScrollPane1.setViewportView(this.jTabClientList);
/*     */     
/* 136 */     this.jMenu2.setText("Export Excel");
/*     */     
/* 138 */     this.jMenuItem1.setText("Save Excel");
/* 139 */     this.jMenuItem1.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 141 */             ClientList.this.jMenuItem1ActionPerformed(evt);
/*     */           }
/*     */         });
/* 144 */     this.jMenu2.add(this.jMenuItem1);
/*     */     
/* 146 */     this.jMenuBar1.add(this.jMenu2);
/*     */     
/* 148 */     setJMenuBar(this.jMenuBar1);
/*     */     
/* 150 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 151 */     getContentPane().setLayout(layout);
/* 152 */     layout.setHorizontalGroup(layout
/* 153 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 154 */         .addGroup(layout.createSequentialGroup()
/* 155 */           .addContainerGap()
/* 156 */           .addComponent(this.jScrollPane1, -1, 507, 32767)
/* 157 */           .addContainerGap()));
/*     */     
/* 159 */     layout.setVerticalGroup(layout
/* 160 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 161 */         .addGroup(layout.createSequentialGroup()
/* 162 */           .addContainerGap()
/* 163 */           .addComponent(this.jScrollPane1, -1, 339, 32767)
/* 164 */           .addContainerGap()));
/*     */ 
/*     */     
/* 167 */     pack();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void windowClosing(WindowEvent evt) {}
/*     */ 
/*     */   
/*     */   private void jMenuItem1ActionPerformed(ActionEvent evt) {
/* 176 */     HSSFWorkbook hSSFWorkbook = new HSSFWorkbook();
/* 177 */     CreationHelper createhelper = hSSFWorkbook.getCreationHelper();
/* 178 */     Sheet sheet = hSSFWorkbook.createSheet("new sheet");
/* 179 */     Row firstRow = sheet.createRow(0);
/* 180 */     for (int j = 0; j < this.model.getColumnCount(); j++) {
/* 181 */       firstRow.createCell(j).setCellValue(this.columnNames[j]);
/*     */     }
/*     */     
/* 184 */     Row row = null;
/* 185 */     Cell cell = null;
/* 186 */     for (int i = 0; i < this.model.getRowCount(); i++) {
/* 187 */       row = sheet.createRow(i + 1);
/* 188 */       for (int k = 0; k < this.model.getColumnCount(); k++) {
/* 189 */         cell = row.createCell(k);
/* 190 */         cell.setCellValue(String.valueOf(this.model.getValueAt(i, k)));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 196 */       SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
/* 197 */       Date currentTime = new Date();
/* 198 */       String dateString = formatter.format(currentTime);
/*     */       
/* 200 */       FileOutputStream out = new FileOutputStream(dateString + ".xls");
/*     */       
/* 202 */       hSSFWorkbook.write(out);
/* 203 */       out.close();
/* 204 */     } catch (FileNotFoundException ex) {
/* 205 */       Logger.getLogger(ClientList.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 206 */     } catch (IOException ex) {
/* 207 */       Logger.getLogger(ClientList.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */   }
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
/*     */   public static void main(String[] args) {
/*     */     try {
/* 225 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 226 */         if ("Nimbus".equals(info.getName())) {
/* 227 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/* 231 */     } catch (ClassNotFoundException ex) {
/* 232 */       Logger.getLogger(ClientList.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 233 */     } catch (InstantiationException ex) {
/* 234 */       Logger.getLogger(ClientList.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 235 */     } catch (IllegalAccessException ex) {
/* 236 */       Logger.getLogger(ClientList.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 237 */     } catch (UnsupportedLookAndFeelException ex) {
/* 238 */       Logger.getLogger(ClientList.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 246 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 248 */             ClientList client = new ClientList();
/* 249 */             client.setVisible(true);
/* 250 */             client.addNewRow((Student)null);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              D:\ptit\ltm\OnTap\RMI\dist\Server.jar!\view\ClientList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */