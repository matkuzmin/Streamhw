package com.example.stream.Servise;

import com.example.stream.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
   private DepartmentService departmentService;

    @BeforeEach
    public void setUp(){
     departmentService = new DepartmentService(employeeService);
    }



    @Test
    void max() {
     var e1 = new Employee("Вася","Пупкин",1,1000);
     var e2  = new Employee("Васян","Пупков",1,1001);
     var e3  = new Employee("Васек","Пупк",1,2000);
     List<Employee> employees = List.of(e1, e2, e3);
     Mockito.when(employeeService.getEmployees()).thenReturn(employees);
 var actual = departmentService.max(1);
 assertEquals(e3,actual);
    }

    @Test
    void min() {
     var e1 = new Employee("Вася","Пупкин",1,1000);
     var e2  = new Employee("Васян","Пупков",1,1001);
     var e3  = new Employee("Васек","Пупк",1,2000);
     List<Employee> employees = List.of(e1, e2, e3);
     Mockito.when(employeeService.getEmployees()).thenReturn(employees);
     var actual = departmentService.min(1);
     assertEquals(e1,actual);
    }

    @Test
    void allByDept() {
        var e1 = new Employee("Вася","Пупкин",1,1000);
        var e2  = new Employee("Васян","Пупков",1,1001);
        var e3  = new Employee("Васек","Пупк",1,2000);
        List<Employee> employees = List.of(e1, e2, e3);
        Mockito.when(employeeService.getEmployees()).thenReturn(employees);
        var actual = departmentService.allByDept(1);
        assertEquals(employees,actual);
    }

    @Test
    void all() {
        var e1 = new Employee("Вася","Пупкин",1,1000);
        var e2  = new Employee("Васян","Пупков",2,1001);
        var e3  = new Employee("Васек","Пупк",1,2000);
        List<Employee> employees = List.of(e1, e2, e3);
        Mockito.when(employeeService.getEmployees()).thenReturn(employees);
        Map<Integer,List<Employee>> actual = departmentService.all();
        Map<Integer,List<Employee>> ex = new HashMap<>();
        List<Employee> list = new ArrayList<>();
        List<Employee> list1 = new ArrayList<>();
        List<Employee> list2 = new ArrayList<>();
        list.add(e2);
        list1.add(e1);
        list1.add(e3);
        ex.put(2, list);
        ex.put(1, list1);
        assertEquals(ex,actual);
    }

    @Test
    void sum() {
     var e1 = new Employee("Вася","Пупкин",1,1000);
     var e2  = new Employee("Васян","Пупков",1,1001);
     var e3  = new Employee("Васек","Пупк",1,2000);
     List<Employee> employees = List.of(e1, e2, e3);
     Mockito.when(employeeService.getEmployees()).thenReturn(employees);
    var exp = employees
             .stream()
             .filter(e->e.getDepartment()==1)
             .mapToDouble(Employee::getSalary)
             .sum();
     var actual = departmentService.sum(1);
     assertEquals(exp,actual);
    }
}