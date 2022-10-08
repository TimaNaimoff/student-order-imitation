package edu.javacourse.studentorder.dao.validator;

import edu.javacourse.studentorder.domain.Adult;
import edu.javacourse.studentorder.domain.Child;
import edu.javacourse.studentorder.domain.Person;
import edu.javacourse.studentorder.exception.CityRegisterException;
import edu.javacourse.studentorder.exception.TransportException;
import edu.javacourse.studentorder.reg_checkers.CityRegisterChecker;
import edu.javacourse.studentorder.reg_checkers.CityRegitsterResponse;

public class FakeCityRegisterChecker implements CityRegisterChecker {
    private static final String GOOD_1="1000";
    private static final String GOOD_2="2000";
    private static final String BAD_1="1001";
    private static final String BAD_2="2001";
    private static final String ERROR_1="2001";
    private static final String ERROR_2="1002";
    private static final String ERROR_T_1="1003";
    private static final String ERROR_T_2="2003";
    CityRegitsterResponse rs=new CityRegitsterResponse();
    public CityRegitsterResponse checkPerson(Person person)throws CityRegisterException {
        if(person instanceof Adult){
            Adult t=(Adult)person;
            String tt=t.getPassportSeria();
            if(tt.equals(GOOD_1)||tt.equals(GOOD_2)){
                rs.setTemporal(false);
                rs.setRegistered(true);
            }
            if(tt.equals(BAD_1)||tt.equals(BAD_2)){
                rs.setRegistered(false);
            }
            if(tt.equals(ERROR_2)||tt.equals(ERROR_1)){
                CityRegisterException ex=new CityRegisterException("1","GRN ERROR"+ tt);
                throw ex;
            }



            System.out.println("ADULT");
        }
        if(person instanceof Child){
           rs.setRegistered(true);
           rs.setTemporal(true);
        }


        System.out.println(rs);
        return rs;
    }
}
