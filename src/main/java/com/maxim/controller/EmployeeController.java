package com.maxim.controller;

import com.maxim.dto.FilterEmpDto;
import com.maxim.entity.Employee;
import com.maxim.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/addOrEditEmployee", method = RequestMethod.POST)
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addOrEditEmployee(employee);
    }

    @RequestMapping(value = "/{id}/deleteEmpById", method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable(required = false) Long id) {
        employeeService.deleteEmployee(id);
    }

    @RequestMapping(value = "/getAllEmployees", method = RequestMethod.POST)
    public List<Employee> getAllEmployees(@RequestBody(required = false) FilterEmpDto filterEmpDto) {
        return employeeService.getAllEmployees(filterEmpDto);
    }

    @RequestMapping(value = "/{id}/getById", method = RequestMethod.GET)
    public Employee getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

}
