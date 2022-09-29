package edu.javacourse.studentorder.domain;

public class Adress {
    private Street street;
    private String building;
    private String extension;
    private String apartment;
    private String postCode;
    public Adress(Street street,String building,String extension,String apartament,String postCode){
        this.street=street;
        this.apartment=apartament;
        this.building=building;
        this.extension=extension;
        this.postCode=postCode;
    }

    public Adress() {

    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
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
        return "Adress{" +
                "street=" + street +
                ", building='" + building + '\'' +
                ", extension='" + extension + '\'' +
                ", apartment='" + apartment + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }
}
