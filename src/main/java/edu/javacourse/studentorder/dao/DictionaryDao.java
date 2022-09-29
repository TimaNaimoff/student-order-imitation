package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.CountryArea;
import edu.javacourse.studentorder.domain.PassportOffice;
import edu.javacourse.studentorder.domain.Street;
import edu.javacourse.studentorder.domain.RegisterOffice;

import java.util.List;

public interface DictionaryDao {
    List <Street> findStreet(String pattern)throws DaoException;
    List<PassportOffice>findPassportOffices(String areaId)throws DaoException;
    List<RegisterOffice>findRegisterOffices(String registerId)throws DaoException;
    List<CountryArea>findAreas(String areaId)throws DaoException;
}
