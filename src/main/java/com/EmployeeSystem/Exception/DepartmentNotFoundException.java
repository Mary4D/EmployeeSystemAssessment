package com.EmployeeSystem.Exception;

public class DepartmentNotFoundException extends Exception{

    public DepartmentNotFoundException(String msg){
        super(msg);
    }
    public DepartmentNotFoundException() {
        super();
    }
}
