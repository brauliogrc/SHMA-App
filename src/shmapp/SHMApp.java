/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shmapp;

import backend.dao.employee.EmployeeDAO;
import backend.models.Employee;


/**
 *
 * @author bruno
 */
public class SHMApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println( "Hello world" );
        EmployeeDAO employee = new EmployeeDAO();
        Employee employee2;
        employee2 = employee.getOne(1);
        System.out.println( employee2.getNombre() + " " + employee2.getApellido_paterno() );
    }    
}
