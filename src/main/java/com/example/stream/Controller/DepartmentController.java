package com.example.stream.Controller;

import com.example.stream.Servise.DepartmentService;
import com.example.stream.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }
    @GetMapping(path = "/{id}/salary/sum")
    public double sum(@PathVariable int departmentId) {
        return service.sum(departmentId);
    }

    @GetMapping(path = "/{id}/salary/max")
    public Employee max(@PathVariable int departmentId) {
        return service.max(departmentId);
    }

    @GetMapping(path = "/{id}/salary/min")
    public Employee min(@PathVariable int departmentId) {
        return service.min(departmentId);
    }

    @GetMapping(path = "/{id}/salary/employees")
    public Collection<Employee> allByDept(@PathVariable int departmentId) {
        return service.allByDept(departmentId);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> all() {
        return service.all();

    }
}