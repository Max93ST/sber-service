package com.maxim;

import com.maxim.entity.Department;
import com.maxim.entity.Employee;
import com.maxim.repository.DepartmentRepo;
import com.maxim.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    void testData() {
        Date startDate = new Date(-312958800000L);
        Date endDate = new Date(949345200000L);
        long random = ThreadLocalRandom.current().nextLong(startDate.getTime(), endDate.getTime());
        List<Department> departments = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Department department = new Department();
            department.setName("Department № " + i);
            departments.add(department);
        }
        departmentRepo.saveAll(departments);

        List<Employee> employees = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            Employee employee = new Employee();
            employee.setFirstName("FirstName № " + i);
            employee.setSecondName("SecondName № " + i);
            employee.setSurName("SurName № " + i);
            employee.setSalary(i * 100L + i);
            employee.setDepId(i % 9L + 1);
            Date date = new Date(random);

            employee.setDateOfBirth(date);
            employees.add(employee);
        }
        employeeRepo.saveAll(employees);
    }
}
