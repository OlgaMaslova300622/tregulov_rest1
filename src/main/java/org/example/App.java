package org.example;

import org.example.configuration.MyConfig;
import org.example.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {   // используем для конфигурации java класс, поэтому контекст получаем так

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);


       // List<Employee> allEmployees = communication.getAllEmployees(); // 1 метод
       // System.out.println(allEmployees);

       // Employee empById = communication.getEmployee(1); // 2 метод
       // System.out.println(empById);

       // Employee emp = new Employee("Sveta", "Sokolova", "HR", 900); // 3 метод, создаем нового
       //   communication.saveEmployee(emp);        // добавляем в базу

       // Employee emp = new Employee("Sveta", "Sokolova", "IT", 1200); // 3 метод, обновляем информацию
       // emp.setId(7);
       // communication.saveEmployee(emp);

        communication.deleteEmployee(7);  // 4 метод


    }
}
