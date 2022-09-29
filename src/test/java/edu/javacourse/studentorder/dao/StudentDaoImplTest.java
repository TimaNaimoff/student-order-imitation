package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.SaveStudentOrder;
import edu.javacourse.studentorder.domain.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class StudentDaoImplTest {
    @BeforeClass
    public static void startUp() throws Exception {
        DbInit.startUp();
    }
    @Test
    public void saveStudentOrder() throws DaoException {
        StudentOrder s= buildStudent(10);
        new StudentDaoImpl().saveStudentOrder(s);
    }
    @Test
    public void saveStudentOrderError() throws DaoException {

            StudentOrder s = buildStudent(10);
            s.getHusband().setGiveName(null);
            Long id=new StudentDaoImpl().saveStudentOrder(s);
            //Assert.fail("Must be error");
    }


    @Test
    public void getStudentOrders() throws DaoException {
        List<StudentOrder> list=new StudentDaoImpl().getStudentOrders();
    }
    public StudentOrder buildStudent(long id){
        StudentOrder so=new StudentOrder();
        so.setStudentId(id);

        //so.setWife(new Adult("",null,null,null,null,null,null));
        so.setMarriageSertificated(123456000L+id);
        so.setMarriageDate(LocalDate.of(2014,4,2));
        RegisterOffice officeR=new RegisterOffice(1L,"","");
        PassportOffice po1=new PassportOffice(1L,"","");
        so.setMarriageOffice(officeR);
        Street street=new Street(1L,"First street");
        Adress adress=new Adress(street,"Zakamsk","","","");
        Adult husband=new Adult("Petrov","Anton","Vasilyevich",LocalDate.of(1997,8,24));
        husband.setIssueDate(LocalDate.of(2017,9,15));
        husband.setIssueDepartment(po1);
        husband.setPassportNumber(""+10000+id);
        husband.setPassportSeria(""+100+id);
        husband.setStudentId(""+(100000+id));
        husband.setAdress(adress);
        husband.setDateOfBith(LocalDate.of(2001,2,21));
        husband.setUniversity(new University(2L,""));
        husband.setStudentId("HH12345");
        PassportOffice po2=new PassportOffice(2L,"","");
        RegisterOffice officeR2=new RegisterOffice(2L,"","");
        Street street2=new Street(1L,"");
        Adress adress1=new Adress(street2,"Zakamsk","","","");
        Adult wife=new Adult("Petrova","Antonina","Vasilyevna",LocalDate.of(1997,8,24));
        wife.setIssueDate(LocalDate.of(2018,1,5));
        wife.setIssueDepartment(po2);
        wife.setPassportNumber(""+20000+id);
        wife.setPassportSeria(""+200+id);
        wife.setStudentId(""+(200000+id));
        wife.setAdress(adress1);
        wife.setDateOfBith(LocalDate.of(1994,6,9));
        wife.setUniversity(new University(1L,""));
        wife.setStudentId("WW12345");
        Child child=new Child("Tupak","Tupakina","Kukuruzov",
                LocalDate.of(2007,1,2));
        child.setCertificateNumber(""+(300000+id));
        child.setIssueDate(LocalDate.of(2018,6,11));
        child.setIssueDepartment(officeR2);
        child.setAdress(adress);
        RegisterOffice officeR3=new RegisterOffice(1L,"","");
        Child child1=new Child("Tupak","Tupakia","Kukuruzovna",
                LocalDate.of(2002,2,2));
        child1.setCertificateNumber(""+(400000+id));
        child1.setIssueDate(LocalDate.of(2018,7,19));
        child1.setIssueDepartment(officeR3);
        child1.setAdress(adress1);


        so.setHusband(husband);
        so.setWife(wife);
        so.addChild(child);
        so.addChild(child1);
        return so;
    }
}