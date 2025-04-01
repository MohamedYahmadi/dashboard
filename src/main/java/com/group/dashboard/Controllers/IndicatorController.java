package com.group.dashboard.Controllers;

import com.group.dashboard.Services.IndicatorService;
import com.group.dashboard.dto.CreateIndicatorDto;
import com.group.dashboard.dto.SetIndicatorValue;
import com.group.dashboard.dto.UpdateTargetPerWeekDto;
import com.group.dashboard.entities.Indicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/indicators")
public class IndicatorController {
    private final IndicatorService indicatorService;

    @Autowired
    public IndicatorController(IndicatorService indicatorService) {
        this.indicatorService = indicatorService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createIndicator(@RequestBody CreateIndicatorDto indicatorDto) {
        return indicatorService.createIndicator(indicatorDto);
    }

    @PostMapping("/set-value")
    public ResponseEntity<String> setIndicatorValue(@RequestBody SetIndicatorValue indicatorValue) {
        return indicatorService.setIndicatorValue(indicatorValue);
    }
    @GetMapping("/by-department/{departmentId}")
    public ResponseEntity<List<Indicator>> getIndicatorsByDepartmentId(@PathVariable int departmentId) {
        List<Indicator> indicators = indicatorService.getIndicatorsByDepartmentId(departmentId);
        return ResponseEntity.ok(indicators);
    }
    @PutMapping("/update-target")
    public ResponseEntity<String> updateTargetPerWeek(@RequestBody UpdateTargetPerWeekDto updateTargetPerWeekDto) {
        return indicatorService.updateTargetPerWeek(updateTargetPerWeekDto);
    }

}
