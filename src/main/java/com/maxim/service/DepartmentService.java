package com.maxim.service;

import com.maxim.dto.FilterDepDto;
import com.maxim.entity.Department;
import com.maxim.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    public void addOrEditDepartment(@RequestBody Department department) {
        departmentRepo.save(department);
    }

    public void deleteDepartment(Long id) {
        departmentRepo.deleteById(id);
    }

    public List<Department> getAllDepartment(FilterDepDto filterDepDto) {
        Pageable pageRequest = PageRequest.of(filterDepDto.getPage(), filterDepDto.getSize() == 0 ? 10 : filterDepDto.getSize());

        if (filterDepDto.getName() != null) {
            return departmentRepo.findByNameIsLike("%" + filterDepDto.getName() + "%", pageRequest);
        }

        return departmentRepo.findAll(pageRequest).getContent();
    }

    public Department getById(Long id) {
        if (departmentRepo.findById(id).isPresent()) {
            return departmentRepo.findById(id).get();
        }

        return null;
    }
}
