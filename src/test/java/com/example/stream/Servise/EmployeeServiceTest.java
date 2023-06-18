package com.example.stream.Servise;

import com.example.stream.Exeption.EmployeeAlreadyAddedException;
import com.example.stream.Exeption.EmployeeNotFoundException;
import com.example.stream.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    private EmployeeService employeeService;
    @BeforeEach
    public void beforeEach() {
        employeeService = new EmployeeService();
    }

    @Test
    void add() {
        Employee expected = employeeService.add("Вася","Пупкин");
        Employee actual = new Employee("Вася","Пупкин",1000,1000);
        assertEquals(expected,actual);

    }

    @Test
    void delete() {
        employeeService.add("Вася","Пупкин");
        Employee expected = employeeService.delete("Вася","Пупкин");
        Employee actual = new Employee("Вася","Пупкин",1000,1000);
        assertEquals(expected,actual);
    }

    @Test
    void find() {
        employeeService.add("Вася","Пупкин");
        Employee expected = employeeService.find("Вася","Пупкин");
        Employee actual = new Employee("Вася","Пупкин",1000,1000);
        assertEquals(expected,actual);
    }

    @Test
    void getEmployees() {
            Employee emp1 = employeeService.add("Петя", "Петинкин");
            Employee emp2 = employeeService.add("Петя", "Петин");
            Employee emp3 = employeeService.add("Света", "Лютикова");

            Collection<Employee> expected = new ArrayList<>();

            expected.add(emp3);
            expected.add(emp2);
            expected.add(emp1);


            Collection<Employee> actual = employeeService.getEmployees();

            assertIterableEquals(expected, actual);

        }
        @Test
    void trhowAdd(){
            employeeService.add("Вася","Пупкин");
        assertThrows(EmployeeAlreadyAddedException.class, ()->employeeService.add("Вася","Пупкин"));
    }
    @Test
    void trhowFind(){
        assertThrows(EmployeeNotFoundException.class, ()->employeeService.find("Вася","Пупкин"));
    }
    @Test
    void trhowDelite(){
        assertThrows(EmployeeNotFoundException.class, ()->employeeService.delete("Вася","Пупкин"));
    }
    }
