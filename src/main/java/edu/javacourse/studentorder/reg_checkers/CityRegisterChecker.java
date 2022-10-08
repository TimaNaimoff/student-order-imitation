package edu.javacourse.studentorder.reg_checkers;

import edu.javacourse.studentorder.domain.Person;
import edu.javacourse.studentorder.exception.CityRegisterException;


public interface CityRegisterChecker {
    CityRegitsterResponse checkPerson(Person person) throws CityRegisterException;
}
