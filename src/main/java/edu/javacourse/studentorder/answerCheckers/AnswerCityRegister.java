package edu.javacourse.studentorder.answerCheckers;
import java.util.ArrayList;
import java.util.List;

import edu.javacourse.studentorder.reg_checkers.AnswerSityRegisterItem;

public class AnswerCityRegister {
    private boolean success;
    private List<AnswerSityRegisterItem>item;
    public boolean getSuccess(){
        return success;
    }
    public void setSuccess(boolean success){
        this.success=success;
    }
    public void addItem(AnswerSityRegisterItem e){
        if(item==null)
            item=new ArrayList<>(10);
        item.add(e);
    }
    public List<AnswerSityRegisterItem> getItem(){
        return item;
    }


}
