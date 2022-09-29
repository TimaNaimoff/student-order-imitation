package edu.javacourse.studentorder.validators;

import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.answerCheckers.AnswerCheckChildren;

public class CheckChildrenValidator {
    public String howMuch;
    public String login;
    public String password;
    public AnswerCheckChildren checkChildren(StudentOrder obj){
        System.out.println("Children registing is running,"+howMuch+","+login+","+password);
        AnswerCheckChildren objius=new AnswerCheckChildren();
        objius.success=false;
        return objius;
    }
}
