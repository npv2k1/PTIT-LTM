/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author nguyen
 */
public class Student implements Serializable {

    // Khởi tạo model gửi qua mạng
    // class luôn phải implements Serializable
    // class luôn có serialVersionUID // Cái này phải giống đề bài yêu cầu

    static final long serialVersionUID = 11L;



    // Khai báo các thuộc tính
    private String maSv;

    public Student() {
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }
    
    
}
