package edu.javacourse.studentorder.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class StudentOrder {
    private StudentOrderStatus studentOrderStatus;
    private LocalDateTime studentOrderTime;
    private long studentId;
    private Adult husband;
    private Adult wife;
    private long marriageSertificated;
    private LocalDate marriageDate;
    private RegisterOffice marriageOffice;
    private List<Child>children;
    public LocalDateTime getStudentOrderTime() {
        return studentOrderTime;
    }

    public void setStudentOrderTime(LocalDateTime studentOrderTime) {
        this.studentOrderTime = studentOrderTime;
    }



    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public LocalDateTime getStudentOrderDate() {
        return studentOrderDate;
    }

    public void setStudentOrderDate(LocalDateTime studentOrderDate) {
        this.studentOrderDate = studentOrderDate;
    }

    public StudentOrderStatus getStudentOrderStatus() {
        return studentOrderStatus;
    }

    public void setStudentOrderStatus(StudentOrderStatus studentOrderStatus) {
        this.studentOrderStatus = studentOrderStatus;
    }

    private LocalDateTime studentOrderDate;


   public long getMarriageSertificated() {
        return marriageSertificated;
    }

    public void setMarriageSertificated(long marriageSertificated) {
        this.marriageSertificated = marriageSertificated;
    }

    public LocalDate getMarriageDate() {
        return marriageDate;
    }

    public void addChild(Child childreni){
        if(children==null){
            children=new ArrayList<>(5);
        }
        children.add(childreni);
    }
    public List<Child> getList(){
       return children;
    }
    public void setMarriageDate(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
    }

    public RegisterOffice getMarriageOffice() {
        return marriageOffice;
    }

    public void setMarriageOffice(RegisterOffice marriageOffice) {
        this.marriageOffice = marriageOffice;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public Adult getHusband() {
        return husband;
    }

    public void setHusband(Adult husband) {
        this.husband = husband;
    }

    public Adult getWife() {
        return wife;
    }

    public void setWife(Adult wife) {
        this.wife = wife;
    }

    @Override
    public String toString() {
        return "StudentOrder{" +
                "studentOrderStatus=" + studentOrderStatus +
                ", studentOrderTime=" + studentOrderTime +
                ", studentId=" + studentId +
                ", husband=" + husband +
                ", wife=" + wife +
                ", marriageSertificated=" + marriageSertificated +
                ", marriageDate=" + marriageDate +
                ", marriageOffice=" + marriageOffice +
                ", children=" + children +
                ", studentOrderDate=" + studentOrderDate +
                '}';
    }
}
