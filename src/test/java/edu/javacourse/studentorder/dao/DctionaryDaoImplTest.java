package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.domain.CountryArea;
import edu.javacourse.studentorder.domain.PassportOffice;
import edu.javacourse.studentorder.domain.RegisterOffice;
import edu.javacourse.studentorder.domain.Street;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class DctionaryDaoImplTest {
    private static final Logger logger= LoggerFactory.getLogger(DctionaryDaoImpl.class);

    @BeforeClass
    public static void startUp() throws Exception {
        DbInit.startUp();
    }
    @Test
    public void testStreet() throws DaoException {
        LocalDateTime dt= LocalDateTime.now();
        LocalDateTime dt2=LocalDateTime.now();
        logger.info("TEST {},{}",dt,dt2);
        List<Street> streets = new DctionaryDaoImpl().findStreet("am");
        Assert.assertTrue(streets.size() == 1);
    }

    @Test
    public void testPassportOffice() throws DaoException {
        List<PassportOffice> pasports = new DctionaryDaoImpl().findPassportOffices("010010000001");
        Assert.assertTrue(pasports.size() == 1);
    }

    @Test
    public void testRegisterOffice() throws DaoException {
        List<RegisterOffice> offices = new DctionaryDaoImpl().findRegisterOffices("020010010001");
        Assert.assertTrue(offices.size() == 1);
    }

    @Test
    public void testCountryArea() throws DaoException {
        List<CountryArea> countryAreas1 = new DctionaryDaoImpl().findAreas("");
        Assert.assertTrue(countryAreas1.size()==2);
        List<CountryArea> countryAreas2 = new DctionaryDaoImpl().findAreas("020000000000");
        Assert.assertTrue(countryAreas2.size()==2);
        List<CountryArea> countryAreas3 = new DctionaryDaoImpl().findAreas("020010000000");
        Assert.assertTrue(countryAreas3.size()==2);
        List<CountryArea> countryAreas4 = new DctionaryDaoImpl().findAreas("020010010000");
        Assert.assertTrue(countryAreas4.size()==2);
    }
}