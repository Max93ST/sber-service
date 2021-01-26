package com.maxim.service;

import com.maxim.dto.FilterEmpDto;
import com.maxim.entity.Employee;
import com.maxim.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public void addOrEditEmployee(Employee employee) {
        employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    public List<Employee> getAllEmployees(FilterEmpDto filterEmpDto) {
        PageRequest pageRequest = PageRequest.of(filterEmpDto.getPage(), filterEmpDto.getSize() == 0 ? 10 : filterEmpDto.getSize());

        Page<Employee> employees = employeeRepo.findAll((Specification<Employee>) (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filterEmpDto.getStartDate() != null) {
                if (filterEmpDto.getEndDate() == null) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(filterEmpDto.getStartDate());
                    cal.add(Calendar.DATE, 1);
                    filterEmpDto.setEndDate(cal.getTime());
                }
                predicates.add(criteriaBuilder.and(criteriaBuilder
                        .between(root.get("dateOfBirth"), filterEmpDto.getStartDate(), filterEmpDto.getEndDate())));
            }

            if (filterEmpDto.getFirstName() != null && !filterEmpDto.getFirstName().trim().isEmpty()) {
                predicates.add(criteriaBuilder.and(
                        criteriaBuilder.like(criteriaBuilder
                                .upper(root.get("firstName")), "%" + filterEmpDto.getFirstName().toUpperCase() + "%")));
            }

            if (filterEmpDto.getSurName() != null && !filterEmpDto.getSurName().trim().isEmpty()) {
                predicates.add(criteriaBuilder.and(
                        criteriaBuilder.like(criteriaBuilder
                                .upper(root.get("surName")), "%" + filterEmpDto.getSurName().toUpperCase() + "%")));
            }

            if (filterEmpDto.getSecondName() != null && !filterEmpDto.getSecondName().trim().isEmpty()) {
                predicates.add(criteriaBuilder.and(
                        criteriaBuilder.like(criteriaBuilder
                                .upper(root.get("surName")), "%" + filterEmpDto.getSecondName().toUpperCase() + "%")));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageRequest);

        return employees.getContent();
    }

    public Employee getById(Long id) {
        if (employeeRepo.findById(id).isPresent()) {
            return employeeRepo.findById(id).get();
        }

        return null;
    }

}
