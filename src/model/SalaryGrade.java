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
public class SalaryGrade implements Serializable {
    static final long serialVersionUID = 4L;
    private String grade;
    private float lowSalary;
    private float highSalary;

    public SalaryGrade() {
    }

    public SalaryGrade(String grade, float lowSalary, float highSalary) {
        this.grade = grade;
        this.lowSalary = lowSalary;
        this.highSalary = highSalary;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public float getLowSalary() {
        return lowSalary;
    }

    public void setLowSalary(float lowSalary) {
        this.lowSalary = lowSalary;
    }

    public float getHighSalary() {
        return highSalary;
    }

    public void setHighSalary(float highSalary) {
        this.highSalary = highSalary;
    }

    @Override
    public String toString() {
        return "SalaryGrade{" +
                "grade='" + grade + '\'' +
                ", lowSalary=" + lowSalary +
                ", highSalary=" + highSalary +
                '}';
    }
    public Object[] toObject() {
        return new Object[] {grade, lowSalary, highSalary};
    }
}
