package com.javatutorials;

import java.util.Comparator;

public class EmpSort {
    static final Comparator<Employee> SALARY_ORDER =
            new Comparator<Employee>() {
                public int compare(Employee e1, Employee e2) {
                    return e2.getSalary().compareTo(e1.getSalary());
                }
            };
}
