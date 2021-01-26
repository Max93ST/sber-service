package com.maxim.controller;

import com.maxim.dto.FilterDepDto;
import com.maxim.entity.Department;
import com.maxim.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/department", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/addOrEditDepartment", method = RequestMethod.POST)
    public void addDepartment(@RequestBody Department department) {
        departmentService.addOrEditDepartment(department);
    }

    @RequestMapping(value = "/{id}/deleteDepById", method = RequestMethod.DELETE)
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }

    @RequestMapping(value = "/getAllDepartment", method = RequestMethod.POST)
    public List<Department> getAllDepartment(@RequestBody FilterDepDto filterDepDto) {
        return departmentService.getAllDepartment(filterDepDto);
    }

    @RequestMapping(value = "/{id}/getById", method = RequestMethod.GET)
    public Department getById(@PathVariable Long id) {
        return departmentService.getById(id);
    }

}
