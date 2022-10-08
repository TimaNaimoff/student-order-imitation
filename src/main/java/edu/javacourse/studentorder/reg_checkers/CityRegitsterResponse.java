package edu.javacourse.studentorder.reg_checkers;

public class CityRegitsterResponse {
    private boolean registered;
    private boolean temporal;
    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean existing) {
        this.registered = existing;
    }

    public boolean isTemporal() {
        return temporal;
    }

    public void setTemporal(boolean temporal) {
        this.temporal = temporal;
    }

    @Override
    public String toString() {
        return "SityRegitsterCheckerResponse{" +
                "existing=" + registered +
                ", temporal=" + temporal +
                '}';
    }
}
