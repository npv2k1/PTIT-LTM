/*     */ package view;
/*     */ 
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
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ import model.Answer;
/*     */ import model.Student;
/*     */ import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*     */ import org.apache.poi.ss.usermodel.Cell;
/*     */ import org.apache.poi.ss.usermodel.CreationHelper;
/*     */ import org.apache.poi.ss.usermodel.Row;
/*     */ import org.apache.poi.ss.usermodel.Sheet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClientList
/*     */   extends JFrame
/*     */ {
/*     */   DefaultTableModel model;
/*  40 */   private String[] columnNames = new String[] { "Số TT", "Mã SV", "Họ và Tên", "IP", "Nhóm", "Đăng ký", "Time" }; private Object[][] data;
/*     */   private JMenu jMenu1;
/*     */   private JMenu jMenu2;
/*     */   
/*     */   public ClientList() {
/*  45 */     initComponents();
/*  46 */     this.model = new DefaultTableModel(this.data, (Object[])this.columnNames);
/*  47 */     this.jTabClientList.setModel(this.model);
/*     */   }
/*     */   private JMenuBar jMenuBar1; private JMenuItem jMenuItem1; private JScrollPane jScrollPane1; private JTable jTabClientList;
/*     */   public void addNewRow(Student sv) {
/*  51 */     SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
/*  52 */     Date currentTime = new Date();
/*  53 */     String dateString = formatter.format(currentTime);
/*     */     
/*  55 */     this.model.addRow(new Object[] { Integer.valueOf(this.model.getRowCount()), sv
/*  56 */           .getMaSV(), sv.getHovaten(), sv.getIP(), Integer.valueOf(sv.getGroup()), dateString });
/*     */   }
/*     */   
/*     */   public void addNewRows(Answer answer) {
/*  60 */     SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
/*  61 */     Date currentTime = new Date();
/*  62 */     String dateString = formatter.format(currentTime);
/*     */     
/*  64 */     this.model.addRow(new Object[] { Integer.valueOf(this.model.getRowCount() + 1), answer
/*  65 */           .getStudent().getMaSV(), answer.getStudent().getHovaten(), answer
/*  66 */           .getStudent().getIP(), Integer.valueOf(answer.getStudent().getGroup()), answer.isAlreadyRegistration() ? "Yes" : "No", dateString });
/*     */   }
/*     */   
/*     */   public void updateAnswerView(Answer answer) {
/*  70 */     if (this.model.getRowCount() == 0) {
/*  71 */       addNewRows(answer);
/*     */       
/*     */       return;
/*     */     } 
/*  75 */     Vector<Vector> rows = this.model.getDataVector();
/*  76 */     Iterator<Vector> it = rows.iterator();
/*  77 */     int rowPos = 0;
/*  78 */     boolean isUpdate = false;
/*  79 */     while (it.hasNext()) {
/*  80 */       rowPos++;
/*  81 */       Vector next = it.next();
/*  82 */       Iterator itItem = next.iterator();
/*  83 */       if (next != null && next.get(1).toString().equalsIgnoreCase(answer.getStudent().getMaSV())) {
/*  84 */         isUpdate = true;
/*  85 */         this.model.setValueAt(answer.getStudent().getHovaten(), rowPos - 1, 2);
/*     */         
/*  87 */         this.model.setValueAt(answer.getStudent().getIP(), rowPos - 1, 3);
/*  88 */         this.model.setValueAt(Integer.valueOf(answer.getStudent().getGroup()), rowPos - 1, 4);
/*  89 */         this.model.setValueAt(answer.isAlreadyRegistration() ? "Yes" : "No", rowPos - 1, 5);
/*     */ 
/*     */ 
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  99 */     if (!isUpdate) {
/* 100 */       addNewRows(answer);
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
/* 113 */     this.jMenu1 = new JMenu();
/* 114 */     this.jScrollPane1 = new JScrollPane();
/* 115 */     this.jTabClientList = new JTable();
/* 116 */     this.jMenuBar1 = new JMenuBar();
/* 117 */     this.jMenu2 = new JMenu();
/* 118 */     this.jMenuItem1 = new JMenuItem();
/*     */     
/* 120 */     this.jMenu1.setText("jMenu1");
/*     */     
/* 122 */     setDefaultCloseOperation(3);
/* 123 */     addWindowListener(new WindowAdapter() {
/*     */           public void windowClosing(WindowEvent evt) {
/* 125 */             ClientList.this.windowClosing(evt);
/*     */           }
/*     */         });
/*     */     
/* 129 */     this.jTabClientList.setModel(new DefaultTableModel(new Object[][] { {}, {}, {}, {} }, (Object[])new String[0]));
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
/* 140 */     this.jScrollPane1.setViewportView(this.jTabClientList);
/*     */     
/* 142 */     this.jMenu2.setText("Export Excel");
/*     */     
/* 144 */     this.jMenuItem1.setText("Save Excel");
/* 145 */     this.jMenuItem1.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 147 */             ClientList.this.jMenuItem1ActionPerformed(evt);
/*     */           }
/*     */         });
/* 150 */     this.jMenu2.add(this.jMenuItem1);
/*     */     
/* 152 */     this.jMenuBar1.add(this.jMenu2);
/*     */     
/* 154 */     setJMenuBar(this.jMenuBar1);
/*     */     
/* 156 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 157 */     getContentPane().setLayout(layout);
/* 158 */     layout.setHorizontalGroup(layout
/* 159 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 160 */         .addGroup(layout.createSequentialGroup()
/* 161 */           .addContainerGap()
/* 162 */           .addComponent(this.jScrollPane1, -1, 507, 32767)
/* 163 */           .addContainerGap()));
/*     */     
/* 165 */     layout.setVerticalGroup(layout
/* 166 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 167 */         .addGroup(layout.createSequentialGroup()
/* 168 */           .addContainerGap()
/* 169 */           .addComponent(this.jScrollPane1, -1, 339, 32767)
/* 170 */           .addContainerGap()));
/*     */ 
/*     */     
/* 173 */     pack();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void windowClosing(WindowEvent evt) {}
/*     */ 
/*     */   
/*     */   private void jMenuItem1ActionPerformed(ActionEvent evt) {
/* 182 */     HSSFWorkbook hSSFWorkbook = new HSSFWorkbook();
/* 183 */     CreationHelper createhelper = hSSFWorkbook.getCreationHelper();
/* 184 */     Sheet sheet = hSSFWorkbook.createSheet("new sheet");
/* 185 */     Row firstRow = sheet.createRow(0);
/* 186 */     for (int j = 0; j < this.model.getColumnCount(); j++) {
/* 187 */       firstRow.createCell(j).setCellValue(this.columnNames[j]);
/*     */     }
/*     */     
/* 190 */     Row row = null;
/* 191 */     Cell cell = null;
/* 192 */     for (int i = 0; i < this.model.getRowCount(); i++) {
/* 193 */       row = sheet.createRow(i + 1);
/* 194 */       for (int k = 0; k < this.model.getColumnCount(); k++) {
/* 195 */         cell = row.createCell(k);
/* 196 */         cell.setCellValue(String.valueOf(this.model.getValueAt(i, k)));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 202 */       SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
/* 203 */       Date currentTime = new Date();
/* 204 */       String dateString = formatter.format(currentTime);
/*     */       
/* 206 */       FileOutputStream out = new FileOutputStream("Kip2.xls");
/*     */       
/* 208 */       hSSFWorkbook.write(out);
/* 209 */       out.close();
/* 210 */     } catch (FileNotFoundException ex) {
/* 211 */       Logger.getLogger(ClientList.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 212 */     } catch (IOException ex) {
/* 213 */       Logger.getLogger(ClientList.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\ptit\ltm\OnTap\Socket\Server_At_Class\Server_At_Class.jar!\view\ClientList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */