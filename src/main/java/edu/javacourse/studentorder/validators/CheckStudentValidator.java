package edu.javacourse.studentorder.validators;

import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.answerCheckers.AnswerCheckStudent;

public class CheckStudentValidator {
    public String firstName;
    public String lastName;
    public String login;
    public AnswerCheckStudent checkStudent(StudentOrder or){
        System.out.println("Checking students is running "+firstName+","+lastName+","+login);
        AnswerCheckStudent student=new AnswerCheckStudent();
        student.success=false;
        return student;
    }
}
