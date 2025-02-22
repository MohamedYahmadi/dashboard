package com.group.dashboard;

import com.group.dashboard.dto.CreateIndicatorDto;
import com.group.dashboard.dto.SetIndicatorValue;
import com.group.dashboard.entities.Department;
import com.group.dashboard.entities.Indicator;
import com.group.dashboard.repositories.DepartmentRepository;
import com.group.dashboard.repositories.IndicatorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IndicatorService {

    private final DepartmentRepository departmentRepository;
    private final IndicatorRepository indicatorRepository;

    public IndicatorService(DepartmentRepository departmentRepository, IndicatorRepository indicatorRepository) {
        this.departmentRepository = departmentRepository;
        this.indicatorRepository = indicatorRepository;
    }

    public ResponseEntity<String> createIndicator(CreateIndicatorDto indicatorDto) {
        Department department = departmentRepository.findById(indicatorDto.getDepartmentId()).orElse(null);

        if (department == null) {
            return ResponseEntity.badRequest().body("Department not found");
        }

        Indicator newIndicator = Indicator.builder()
                .name(indicatorDto.getName())
                .department(department)
                .targetPerDay(indicatorDto.getTargetPerDay())
                .build();

        indicatorRepository.save(newIndicator);

        return ResponseEntity.ok("Indicator created successfully");

    }

    public ResponseEntity<String> setIndicatorValue(SetIndicatorValue indicatorValue) {
        Indicator indicator = indicatorRepository.findById(indicatorValue.getIndicatorId()).orElse(null);

        if (indicator == null) {
            return ResponseEntity.badRequest().body("Indicator not found");
        }

        if (indicator.getDay() == null) {
            indicator.setDay(indicatorValue.getDay());
            indicator.setDayValue(indicatorValue.getValue());
            indicatorRepository.save(indicator);
        }

        else {
            Indicator newIndicator = Indicator.builder()
                    .name(indicator.getName())
                    .department(indicator.getDepartment())
                    .targetPerDay(indicator.getTargetPerDay())
                    .day(indicatorValue.getDay())
                    .dayValue(indicatorValue.getValue())
                    .build();

            indicatorRepository.save(newIndicator);
        }

        return ResponseEntity.ok("Indicator value set successfully");

    }

}
