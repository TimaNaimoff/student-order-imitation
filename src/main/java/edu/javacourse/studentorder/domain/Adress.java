package edu.javacourse.studentorder.domain;

public class Adress {
    private String street_code;
    private Street street;
    private String building;
    private String extension;
    private String apartment;

    public Adress(String street_code,Street street,String building,String extension,String apartament){
        this.street_code=street_code;
        this.street=street;
        this.apartment=apartament;
        this.building=building;
        this.extension=extension;

    }

    public Adress() {

    }

//    public String getPostCode() {
//        return postCode;
//    }

//    public void setPostCode(String postCode) {
//        this.postCode = postCode;
//    }

    public String getStreet_code() {
        return street_code;
    }

    public void setStreet_code(String street_code) {
        this.street_code = street_code;
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
    '}';
//                ", postCode='" + postCode + '\'' +
//                '
    }
}
