package edu.javacourse.studentorder.domain;

public enum StudentOrderStatus {
    START,CHECKED;

    public static StudentOrderStatus fromValue(int value){
        for(StudentOrderStatus so:StudentOrderStatus.values()){
            if(so.ordinal()==value){
                 return  so;
            }
        }
        throw new RuntimeException("Unknown Value: "+value);


   }
}
