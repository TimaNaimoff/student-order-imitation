package edu.javacourse.studentorder.validators;

import edu.javacourse.studentorder.domain.Person;
import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.answerCheckers.AnswerSityRegisterItem;
import edu.javacourse.studentorder.reg_checkers.CityRegisterChecker;
import edu.javacourse.studentorder.reg_checkers.CityRegitsterResponse;
import edu.javacourse.studentorder.answerCheckers.AnswerCityRegister;
import edu.javacourse.studentorder.exception.CityRegisterException;
import edu.javacourse.studentorder.exception.TransportException;
import edu.javacourse.studentorder.reg_checkers.RealSityRegisterChecker;

public class CheckSityValidator {

    public static final String IN_CODE="NO_GRN";
    private CityRegisterChecker personChecker;

    public CheckSityValidator(){
        personChecker=new RealSityRegisterChecker();
    }
    public AnswerCityRegister checkSityRegister(StudentOrder so) throws CityRegisterException {
        AnswerCityRegister soa=new AnswerCityRegister();
        soa.addItem(checkPerson(so.getWife()));
        soa.addItem(checkPerson(so.getHusband()));
       // soa.addItem(checkPerson(so.getChild()));
            for(Person x:so.getList()) {
              soa.addItem(checkPerson(x));
            }

        return soa;
    }
    private AnswerSityRegisterItem checkPerson(Person person){
       AnswerSityRegisterItem.CityStatus status=null;
       AnswerSityRegisterItem.CityError error=null;
        try {
              CityRegitsterResponse tmp=personChecker.checkPerson(person);
              status=tmp.isRegistered()?AnswerSityRegisterItem.CityStatus.YES: AnswerSityRegisterItem.CityStatus.NO;
        }catch(CityRegisterException e) {
            e.printStackTrace(System.out);
            status = AnswerSityRegisterItem.CityStatus.ERROR;
            error = new AnswerSityRegisterItem.CityError(e.getCode(), e.getMessage());
        }
//        catch(TransportException e){
//            e.printStackTrace(System.out);
//            status=AnswerSityRegisterItem.CityStatus.ERROR;
//            error=new AnswerSityRegisterItem.CityError(IN_CODE,e.getMessage());
//        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }
        AnswerSityRegisterItem ans=new AnswerSityRegisterItem(person,status,error);
        return ans;

    }
}
