/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import model.Department;
import model.Employee;
import model.SalaryGrade;
import model.Timekeeper;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author nguyen
 */
public class Main extends javax.swing.JFrame {
    DefaultTableModel tmTimekeeper, tmDepartment,tmEmployee, tmSalaryGrade;
    ArrayList<Employee> listEmployee;
    ArrayList<Department> listDepartment;
    ArrayList<Timekeeper> listTimekeeper;
    ArrayList<SalaryGrade> listSalaryGrade;

    private static final int PORT = 5000;
    private Socket s;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

   
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();

        // getting localhost ip
        InetAddress ip = null;
        try {
            ip = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        try {
            s = new Socket(ip, 5000);
            // obtaining input and out streams
            this.dataInputStream = new DataInputStream(s.getInputStream());
            this.dataOutputStream = new DataOutputStream(s.getOutputStream());
            this.objectOutputStream = new ObjectOutputStream(s.getOutputStream());
            this.objectInputStream = new ObjectInputStream(s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
//        fNV = "src/controller/NV.dat";
//        
        String[] phongBanTitle = {"ID","Ten phòng ban","Số phòng ban","Địa chỉ"};
        String[] employeeTitle = {"ID","Tên","Số","Ngày tham gia","Ảnh", "Công viêc", "Lương", "Phòng ban"};
        String[] timekeeperTitle = {"ID","Ngày", "Loại", "Nhân viên"};
        String[] salaryGradeTitle = {"Mức","Thấp nhất","Cao nhất"};
        
//       
        tmDepartment = new DefaultTableModel(phongBanTitle,0);
        tmEmployee= new DefaultTableModel(employeeTitle,0);
        tmTimekeeper = new DefaultTableModel(timekeeperTitle,0);
        tmSalaryGrade = new DefaultTableModel(salaryGradeTitle,0);

//        
        tableDepartment.setModel(tmDepartment);
        tableEmployee.setModel(tmEmployee);
        tableTimekeeper.setModel(tmTimekeeper);
        
//        
        loadDepartment();
        loadEmployee();
        loadTimekeeper();
//        showNV(listNV);
//        initDV();
//        
//        tableDV.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
//            public void valueChanged(ListSelectionEvent event) {
//            
//               tfMaDV.setText(tableDV.getValueAt(tableDV.getSelectedRow(), 0).toString());
//               tfTenDV.setText(tableDV.getValueAt(tableDV.getSelectedRow(), 1).toString());
////               cbNhomDV.setText(tableDV.getValueAt(tableDV.getSelectedRow(), 0).toString());
//                cbNhomDV.setSelectedItem(tableDV.getValueAt(tableDV.getSelectedRow(), 2));
//               tfChiPhi.setText(tableDV.getValueAt(tableDV.getSelectedRow(), 3).toString());
//               tfCuoc.setText(tableDV.getValueAt(tableDV.getSelectedRow(), 4).toString());
//            }
//        });     
        
        
    }
    
    private void loadDepartment(){
        try {
            String menu = this.dataInputStream.readUTF();
            this.dataOutputStream.writeUTF("1");
            this.dataOutputStream.flush();
            List<Department> listDepartment = (List<Department>) this.objectInputStream.readObject();
            tmDepartment.setRowCount(0);
            for (Department department : listDepartment) {
                tmDepartment.addRow(department.toObject());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    private void loadEmployee(){
        try {
            String menu = this.dataInputStream.readUTF();
            this.dataOutputStream.writeUTF("5");
            this.dataOutputStream.flush();
            listEmployee = (ArrayList<Employee>) this.objectInputStream.readObject();
            tmEmployee.setRowCount(0);
            for (Employee employee : listEmployee) {
                tmEmployee.addRow(employee.toObject());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTimekeeper(){
        try {
            String menu = this.dataInputStream.readUTF();
            this.dataOutputStream.writeUTF("9");
            this.dataOutputStream.flush();
            listTimekeeper = (ArrayList<Timekeeper>) this.objectInputStream.readObject();
            tmTimekeeper.setRowCount(0);
            for (Timekeeper timekeeper : listTimekeeper) {
                System.out.println("add timekeep");
                tmTimekeeper.addRow(timekeeper.toObject());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    
//    private void initDV(){
//        fDV = "src/controller/DV.dat";
//        String[] dv = {"ma DV","ten DV","nhom DV","Chi phi","Cuoc"};
//        tmDV = new DefaultTableModel(dv,0);
//        tableDV.setModel(tmDV);
//        loadDV();
//        showDV(listDV);
//       
//        
//    }
//    private void loadDV(){
//        File f = new File(fDV);
//        if(f.exists()){
//            listDV = IOFile.doc(fDV);
//        }else{
//            listDV = new ArrayList<>();
//        }     
//        
//    }
//    private void showDV(List<DichVu> list){
//        tmDV.setRowCount(0);
//        for(DichVu i:list)
//        {
//            tmDV.addRow(i.toObject());
//        }
//        
//    }  
    private void loadNV(){
//        File f = new File(fNV);
//        if(f.exists()){
//            listNV = IOFile.doc(fNV);
//        }else{
//            listNV = new ArrayList<>();
//        }
        
        
    }
    
//    private void showNV(List<NhanVien> list){
////        tmNV.setRowCount(0);
////        for(NhanVien i:list)
////        {
////            tmNV.addRow(i.toObject());
////        }
//        
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        p1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEmployee = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lbMaDV = new javax.swing.JLabel();
        lbTenDV = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfMaDV = new javax.swing.JTextField();
        tfTenDV = new javax.swing.JTextField();
        cbNhomDV = new javax.swing.JComboBox<>();
        tfChiPhi = new javax.swing.JTextField();
        tfCuoc = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tfCuoc2 = new javax.swing.JTextField();
        tfCuoc3 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        tfSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        p2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDepartment = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnThem1 = new javax.swing.JButton();
        btnXoa1 = new javax.swing.JButton();
        btnSave1 = new javax.swing.JButton();
        p3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableTimekeeper = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        lbMaDV1 = new javax.swing.JLabel();
        lbTenDV1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tfMaDV1 = new javax.swing.JTextField();
        tfTenDV1 = new javax.swing.JTextField();
        cbNhomDV1 = new javax.swing.JComboBox<>();
        tfChiPhi1 = new javax.swing.JTextField();
        tfCuoc1 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        btnThem2 = new javax.swing.JButton();
        btnXoa2 = new javax.swing.JButton();
        btnSave2 = new javax.swing.JButton();
        tfSearch1 = new javax.swing.JTextField();
        btnSearch1 = new javax.swing.JButton();
        btnSua1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableEmployee);

        lbMaDV.setText("Mã NV");

        lbTenDV.setText("Tên NV");

        jLabel3.setText("Chi Nhánh");

        jLabel4.setText("Ngày Tham gia");

        jLabel5.setText("Lương");

        tfMaDV.setText("100");
        tfMaDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMaDVActionPerformed(evt);
            }
        });

        cbNhomDV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Truc", "sau", "gia tang" }));

        jLabel14.setText("Ảnh");

        jLabel15.setText("Mức lương");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbMaDV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfMaDV))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbTenDV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfTenDV))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbNhomDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 106, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(8, 8, 8)
                        .addComponent(tfChiPhi))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(20, 20, 20)
                        .addComponent(tfCuoc))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(20, 20, 20)
                        .addComponent(tfCuoc2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(20, 20, 20)
                        .addComponent(tfCuoc3)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMaDV)
                    .addComponent(tfMaDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTenDV)
                    .addComponent(tfTenDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbNhomDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfChiPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfCuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tfCuoc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(tfCuoc3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnThem.setText("ThemDV");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setText("Xoa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnSua.setText("sua");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSua)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnXoa)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave)
                        .addGap(92, 92, 92)
                        .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch)))
                .addContainerGap(380, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnXoa)
                    .addComponent(btnSave)
                    .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSua)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p1Layout = new javax.swing.GroupLayout(p1);
        p1.setLayout(p1Layout);
        p1Layout.setHorizontalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p1Layout.createSequentialGroup()
                .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        p1Layout.setVerticalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p1Layout.createSequentialGroup()
                .addGroup(p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 73, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nhân viên", p1);

        tableDepartment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableDepartment);

        jLabel6.setText("Ma NV");

        jLabel7.setText("TenNV");

        jLabel8.setText("Nhom DV");

        jLabel9.setText("Dia CHi");

        jLabel10.setText("SDT");

        jTextField2.setText("jTextField1");

        jTextField6.setText("jTextField1");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Truc", "sau", "gia tang" }));

        jTextField7.setText("jTextField1");

        jTextField8.setText("jTextField1");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField6))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 88, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(8, 8, 8)
                        .addComponent(jTextField7))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(20, 20, 20)
                        .addComponent(jTextField8)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnThem1.setText("ThemDV");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        btnXoa1.setText("Xoa");
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });

        btnSave1.setText("Save");
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem1)
                .addGap(18, 18, 18)
                .addComponent(btnXoa1)
                .addGap(18, 18, 18)
                .addComponent(btnSave1)
                .addContainerGap(730, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem1)
                    .addComponent(btnXoa1)
                    .addComponent(btnSave1))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p2Layout = new javax.swing.GroupLayout(p2);
        p2.setLayout(p2Layout);
        p2Layout.setHorizontalGroup(
            p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p2Layout.createSequentialGroup()
                .addGroup(p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        p2Layout.setVerticalGroup(
            p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p2Layout.createSequentialGroup()
                .addGroup(p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 73, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chi nhánh", p2);

        tableTimekeeper.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tableTimekeeper);

        lbMaDV1.setText("Ma DV:");

        lbTenDV1.setText("Ten DV");

        jLabel11.setText("Nhom DV");

        jLabel12.setText("Chi phi:");

        jLabel13.setText("Cuoc");

        tfMaDV1.setText("100");
        tfMaDV1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMaDV1ActionPerformed(evt);
            }
        });

        cbNhomDV1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Truc", "sau", "gia tang" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbMaDV1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfMaDV1))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbTenDV1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfTenDV1))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbNhomDV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 103, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(8, 8, 8)
                        .addComponent(tfChiPhi1))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(20, 20, 20)
                        .addComponent(tfCuoc1)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMaDV1)
                    .addComponent(tfMaDV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTenDV1)
                    .addComponent(tfTenDV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbNhomDV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(tfChiPhi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(tfCuoc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnThem2.setText("ThemDV");
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });

        btnXoa2.setText("Xoa");
        btnXoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa2ActionPerformed(evt);
            }
        });

        btnSave2.setText("Save");
        btnSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave2ActionPerformed(evt);
            }
        });

        btnSearch1.setText("Search");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });

        btnSua1.setText("sua");
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem2)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSua1)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnXoa2)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave2)
                        .addGap(92, 92, 92)
                        .addComponent(tfSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch1)))
                .addContainerGap(380, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem2)
                    .addComponent(btnXoa2)
                    .addComponent(btnSave2)
                    .addComponent(tfSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSua1)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p3Layout = new javax.swing.GroupLayout(p3);
        p3.setLayout(p3Layout);
        p3Layout.setHorizontalGroup(
            p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p3Layout.createSequentialGroup()
                .addGroup(p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        p3Layout.setVerticalGroup(
            p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p3Layout.createSequentialGroup()
                .addGroup(p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 73, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chấm công", p3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
//        int r = tableDV.getSelectedRow();
//        if(r>=0 && r<tableDV.getRowCount()){
//            tmDV.removeRow(r);
//            listDV.remove(r);
//        }
//        else{
//             JOptionPane.showMessageDialog(this, "Chon dich vu bi xoa");
//             return;
//            
//        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_btnXoa1ActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnSave1ActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
//        int n = listDV.size();
//        if(n>0){
//            DichVu.setSma(listDV.get(n-1).getMa()+1);
//        }
//        tfMaDV.setText(DichVu.getSma()+"");
//        String ten,nhom;
//        double cp,cuoc;
//        try{
//            ten = tfTenDV.getText();
//            nhom = cbNhomDV.getSelectedItem().toString();
//            cp = Double.parseDouble(tfChiPhi.getText());
//            cuoc= Double.parseDouble(tfCuoc.getText());
//            DichVu dv = new DichVu(ten,nhom,cp,cuoc);
//            tmDV.addRow(dv.toObject());
//            listDV.add(dv);
//        }
//        catch(TrongException e){
//            JOptionPane.showMessageDialog(this, "Khong de trong");
//            return;
//            
//        }catch(NumberFormatException e){
//             JOptionPane.showMessageDialog(this, "Nhap vao so");
//             return;
//        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void tfMaDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMaDVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMaDVActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
//        IOFile.viet(fDV, listDV);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        
       
//        String key = tfSearch.getText().toLowerCase();
//        List<DichVu> l = new ArrayList<>();
//        for (DichVu i:listDV){
//            if(i.getTen().toLowerCase().contains(key) || i.getNhom().toLowerCase().contains(key)){
//            l.add(i);
//            }
//        }
//        showDV(l);
        
        
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
//        int r = tableDV.getSelectedRow();
//        if(r>=0 && r<tableDV.getRowCount()){
//            tmDV.setValueAt(tfTenDV.getText(), r, 1);
//            tmDV.setValueAt(cbNhomDV.getSelectedItem().toString(), r, 2);
//            tmDV.setValueAt(tfChiPhi.getText(), r, 3);
//            tmDV.setValueAt(tfCuoc.getText(), r, 4);
//        }
//        else{
//             JOptionPane.showMessageDialog(this, "Chon dich vu de sua");
//             return;
//            
//        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tfMaDV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMaDV1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMaDV1ActionPerformed

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThem2ActionPerformed

    private void btnXoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoa2ActionPerformed

    private void btnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSave2ActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSua1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton btnSave2;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnThem2;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoa1;
    private javax.swing.JButton btnXoa2;
    private javax.swing.JComboBox<String> cbNhomDV;
    private javax.swing.JComboBox<String> cbNhomDV1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel lbMaDV;
    private javax.swing.JLabel lbMaDV1;
    private javax.swing.JLabel lbTenDV;
    private javax.swing.JLabel lbTenDV1;
    private javax.swing.JPanel p1;
    private javax.swing.JPanel p2;
    private javax.swing.JPanel p3;
    private javax.swing.JTable tableDepartment;
    private javax.swing.JTable tableEmployee;
    private javax.swing.JTable tableTimekeeper;
    private javax.swing.JTextField tfChiPhi;
    private javax.swing.JTextField tfChiPhi1;
    private javax.swing.JTextField tfCuoc;
    private javax.swing.JTextField tfCuoc1;
    private javax.swing.JTextField tfCuoc2;
    private javax.swing.JTextField tfCuoc3;
    private javax.swing.JTextField tfMaDV;
    private javax.swing.JTextField tfMaDV1;
    private javax.swing.JTextField tfSearch;
    private javax.swing.JTextField tfSearch1;
    private javax.swing.JTextField tfTenDV;
    private javax.swing.JTextField tfTenDV1;
    // End of variables declaration//GEN-END:variables
}
