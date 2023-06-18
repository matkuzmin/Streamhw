package com.example.stream.Servise;


import com.example.stream.Exeption.EmployeeAlreadyAddedException;
import com.example.stream.Exeption.EmployeeNotFoundException;
import com.example.stream.Exeption.EmployeeStorageIsFullException;
import com.example.stream.Exeption.InvalidInputExeption;
import com.example.stream.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeService {

    private static final int MAX_SIZE = 100;

    private final Map<String, Employee> employees = new HashMap<>(MAX_SIZE);

    public Employee add(String firstName, String lastName) {
        validateInput(firstName, lastName);
        if (employees.size() > MAX_SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        var key = (firstName + "_" + lastName).toLowerCase();
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        var employee = new Employee(firstName, lastName, 1000, 1000);
        employees.put(key, employee);
        return employee;
    }

    public Employee delete(String firstName, String lastName) {
        validateInput(firstName, lastName);
        var key = (firstName + "_" + lastName).toLowerCase();
        var removed = employees.remove(key);
        if (removed == null) {
            throw new EmployeeNotFoundException();
        }
        return removed;
    }

    public Employee find(String firstName, String lastName) {
        validateInput(firstName, lastName);


        var key = (firstName + "_" + lastName).toLowerCase();
        var employee = employees.get(key);
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }



    public Collection<Employee> getEmployees() {
        return employees.values();
    }
    private void validateInput(String firstname,String lastname){
if(!(isAlpha(firstname) && isAlpha(lastname))){
        throw new InvalidInputExeption();
    }
}
}
