package com.group.dashboard;

import com.group.dashboard.dto.CreateIndicatorDto;
import com.group.dashboard.dto.SetIndicatorValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
