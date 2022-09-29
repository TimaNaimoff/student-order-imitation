package edu.javacourse.studentorder.domain;

import java.time.LocalDate;

public abstract class Person{

    private String surName;
    private String giveName;
    private String patronymic;
    private LocalDate dateOfBith;
    private Adress adress;
    private String studentId;

    public Person(String surName, String giveName, String patronymic, LocalDate dateOfBith, Adress adress, String university, String studentId) {
        this.surName = surName;
        this.giveName = giveName;
        this.patronymic = patronymic;
        this.dateOfBith = dateOfBith;
        this.adress = adress;
        this.studentId = studentId;
    }
    public Person(){}



    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGiveName() {
        return giveName;
    }

    public void setGiveName(String giveName) {
        this.giveName = giveName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getDateOfBith() {
        return dateOfBith;
    }

    public void setDateOfBith(LocalDate dateOfBith) {
        this.dateOfBith=dateOfBith;
    }

    @Override
    public String toString() {
        return "Person{" +
                "surName='" + surName + '\'' +
                ", giveName='" + giveName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", dateOfBith=" + dateOfBith +
                ", adress=" + adress +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}
