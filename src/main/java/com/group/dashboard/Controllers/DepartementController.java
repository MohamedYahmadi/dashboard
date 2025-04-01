package com.group.dashboard.Controllers;

import com.group.dashboard.Services.DepartmentService;
import com.group.dashboard.dto.CreateDepartement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departements")
public class DepartementController {
    private final DepartmentService departmentService;

    public DepartementController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/create-department")
    public String createDepartment( @RequestBody CreateDepartement departmentData) {
        return departmentService.CreateDepartment(departmentData);
    }
}
