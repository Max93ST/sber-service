package com.maxim.repository;

import com.maxim.entity.Department;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    List<Department> findByNameIsLike(String name, Pageable pageRequest);
}
