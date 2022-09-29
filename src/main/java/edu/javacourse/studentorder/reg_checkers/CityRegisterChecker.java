package edu.javacourse.studentorder.reg_checkers;

import edu.javacourse.studentorder.domain.Person;
import edu.javacourse.studentorder.exception.CityRegisterException;
import edu.javacourse.studentorder.exception.TransportException;

public interface CityRegisterChecker {
    CityRegitsterResponse checkPerson(Person person) throws CityRegisterException, TransportException;
}
