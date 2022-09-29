package edu.javacourse.studentorder.domain;

import java.time.LocalDate;

public class Adult extends Person {
    private String passportSeria;
    private String passportNumber;
    private LocalDate issueDate;
    private University university;
    private PassportOffice issueDepartment;

    public Adult(){

    }
    public Adult(String surName, String giveName, String patronymic, LocalDate dateOfBith) {
        this.setSurName(surName);
        this.setGiveName(giveName);
        this.setPatronymic(patronymic);
        this.setDateOfBith(dateOfBith);

    }


    public String getPassportSeria() {
        return passportSeria;
    }

    public void setPassportSeria(String passportSeria) {
        this.passportSeria = passportSeria;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public PassportOffice getIssueDepartment() {
        return issueDepartment;
    }


    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public void setIssueDepartment(PassportOffice issueDepartment) {
        this.issueDepartment = issueDepartment;
    }

    @Override
    public String toString() {
        return "Adult{" +
                "passportSeria='" + passportSeria + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", issueDate=" + issueDate +
                ", university=" + university +
                ", issueDepartment=" + issueDepartment +
                "} " + super.toString();
    }
}

