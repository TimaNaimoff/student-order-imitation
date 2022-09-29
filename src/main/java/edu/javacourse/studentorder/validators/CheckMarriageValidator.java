package edu.javacourse.studentorder.validators;

import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.answerCheckers.AnswerCheckMarriage;

public class CheckMarriageValidator {
    public String log;
    public String login;
    public String password;
    public AnswerCheckMarriage checkMarrige(StudentOrder of){
        System.out.println("Marrige registing is running+" +log+","+login+","+password);
        AnswerCheckMarriage acw=new AnswerCheckMarriage();
        acw.success=false;
        return acw;
    }
}
