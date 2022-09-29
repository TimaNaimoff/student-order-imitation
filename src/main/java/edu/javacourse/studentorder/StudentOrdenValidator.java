package edu.javacourse.studentorder;

import edu.javacourse.studentorder.dao.DaoException;
import edu.javacourse.studentorder.dao.StudentDaoImpl;
import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.mail.MailSender;
import edu.javacourse.studentorder.answerCheckers.AnswerCheckChildren;
import edu.javacourse.studentorder.answerCheckers.AnswerCheckMarriage;
import edu.javacourse.studentorder.answerCheckers.AnswerCheckStudent;
import edu.javacourse.studentorder.answerCheckers.AnswerCityRegister;
import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.exception.CityRegisterException;
import edu.javacourse.studentorder.validators.CheckChildrenValidator;
import edu.javacourse.studentorder.validators.CheckMarriageValidator;
import edu.javacourse.studentorder.validators.CheckSityValidator;
import edu.javacourse.studentorder.validators.CheckStudentValidator;

import java.util.LinkedList;
import java.util.List;



public class StudentOrdenValidator {
    private CheckSityValidator checkSityVal;
    private CheckMarriageValidator checkMarriageVal;
    private CheckChildrenValidator checkChildrenVal;
    private CheckStudentValidator studentVal;
    private MailSender mailSender;


    public StudentOrdenValidator(){
        studentVal=new CheckStudentValidator();
        checkSityVal=new CheckSityValidator();
        checkMarriageVal=new CheckMarriageValidator();
        checkChildrenVal=new CheckChildrenValidator();
        mailSender=new MailSender();
    }

    public static void main(String[]args) throws CityRegisterException {
        StudentOrdenValidator studentVal=new StudentOrdenValidator();
        studentVal.checkAll();
    }
    public void checkAll() throws CityRegisterException {
        try {
            List<StudentOrder> soList = readStudentOrders();
            for (int i = 0; i < soList.size(); i++) {
                System.out.println();
                checkOneOrder(soList.get(i));
            }
        }catch (DaoException e){
            e.printStackTrace();
        }
    }
    public List<StudentOrder> readStudentOrders() throws DaoException {
        return new StudentDaoImpl().getStudentOrders();
    }
    public void checkOneOrder(StudentOrder so) throws CityRegisterException {
        //AnswerCheckChildren chil = checkChildren(so);
        //AnswerCheckMarriage mar = checkMarrige(so);
       // AnswerCheckStudent stude = checkStudent(so);
        AnswerCityRegister ans = checkSityRegister(so);

    //     sendMail(so);
    }

    public AnswerCityRegister checkSityRegister(StudentOrder so) throws CityRegisterException {
        AnswerCityRegister as=checkSityVal.checkSityRegister(so);
        return as;
    }
    public AnswerCheckMarriage checkMarrige(StudentOrder so){
        return checkMarriageVal.checkMarrige(so);

    }
    public AnswerCheckChildren checkChildren(StudentOrder so){
        return checkChildrenVal.checkChildren(so);
    }

    public AnswerCheckStudent checkStudent(StudentOrder so){
        return studentVal.checkStudent(so);
    }
    public  void sendMail(StudentOrder so){
        mailSender.sendMail(so);

    }

}
