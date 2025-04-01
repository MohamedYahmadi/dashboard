package com.group.dashboard.Services;

import com.group.dashboard.dto.CreateDepartement;
import com.group.dashboard.entities.Department;
import com.group.dashboard.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public String CreateDepartment(CreateDepartement departmentData) {
        Department newDepartment = Department.builder()
                .name(departmentData.getName())
                .build();

        departmentRepository.save(newDepartment);

        return "Department created successfully";
    }
}
