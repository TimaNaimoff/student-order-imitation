package edu.javacourse.studentorder.reg_checkers;

import edu.javacourse.studentorder.domain.Adress;
import edu.javacourse.studentorder.domain.Person;
import edu.javacourse.studentorder.util.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

public class CityRegisterRequest {
    private String surName;
    private String givenName;
    private String patromymic;
    @XmlJavaTypeAdapter(value= LocalDateAdapter.class)
    private LocalDate dateofBirth;
    private Long streetCode;
    private String building;
    private String extension;
    private String apartment;

    public CityRegisterRequest() {
    }

    public String getSurName() {
        return surName;
    }
    public CityRegisterRequest(Person person){
        surName=person.getSurName();
        givenName=person.getGiveName();
        patromymic=person.getPatronymic();
        dateofBirth=person.getDateOfBith();
        Adress adr=person.getAdress();
        streetCode=adr.getStreet().getStreetCode();
        building=adr.getBuilding();
        extension=adr.getExtension();
        apartment=adr.getApartment();

    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getPatromymic() {
        return patromymic;
    }

    public void setPatromymic(String patromymic) {
        this.patromymic = patromymic;
    }

    public LocalDate getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(LocalDate dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public Long getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(Long streetCode) {
        this.streetCode = streetCode;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "CityRegisterResponse{" +
                "surName='" + surName + '\'' +
                ", givenName='" + givenName + '\'' +
                ", patromymic='" + patromymic + '\'' +
                ", dateofBirth=" + dateofBirth +
                ", streetCode=" + streetCode +
                ", building='" + building + '\'' +
                ", extension='" + extension + '\'' +
                ", apartment='" + apartment + '\'' +
                '}';
    }
}
