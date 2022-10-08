package edu.javacourse.studentorder.answerCheckers;

import edu.javacourse.studentorder.domain.Person;

public class AnswerSityRegisterItem {
    public enum CityStatus{
        YES,NO,ERROR
    }
    public static class CityError{
        private String code;
        private String text;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public CityError(String code, String text) {
            this.code = code;
            this.text = text;
        }
    }
    private Person person;
    private CityStatus status;
    private CityError error;

    public AnswerSityRegisterItem(Person person, CityStatus status,CityError error) {
        this.person = person;
        this.status = status;
        this.error=error;
    }

    public Person getPerson() {
        return person;
    }

    public CityStatus getStatus() {
        return status;
    }

    public CityError getError() {
        return error;
    }
}
