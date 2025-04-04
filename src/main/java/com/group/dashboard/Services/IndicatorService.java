package com.group.dashboard.Services;

    import com.group.dashboard.dto.CreateIndicatorDto;
    import com.group.dashboard.dto.SetIndicatorValue;
    import com.group.dashboard.dto.UpdateTargetPerWeekDto;
    import com.group.dashboard.entities.Department;
    import com.group.dashboard.entities.Indicator;
    import com.group.dashboard.repositories.DepartmentRepository;
    import com.group.dashboard.repositories.IndicatorRepository;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Service;

    import java.util.Date;
    import java.util.List;

@Service
    public class IndicatorService {

        private final DepartmentRepository departmentRepository;
        private final IndicatorRepository indicatorRepository;

        public IndicatorService(DepartmentRepository departmentRepository, IndicatorRepository indicatorRepository) {
            this.departmentRepository = departmentRepository;
            this.indicatorRepository = indicatorRepository;
        }

        public ResponseEntity<String> createIndicator(CreateIndicatorDto indicatorDto) {
            Department department = departmentRepository.findById(indicatorDto.getDepartmentId());

            if (department == null) {
                return ResponseEntity.badRequest().body("Department not found");
            }

            Indicator newIndicator = Indicator.builder()
                    .name(indicatorDto.getName())
                    .department(department)
                    .targetPerWeek(indicatorDto.getTargetPerWeek())
                    .build();

            indicatorRepository.save(newIndicator);

            return ResponseEntity.ok("Indicator created successfully");
        }

      public ResponseEntity<String> setIndicatorValue(SetIndicatorValue indicatorValue) {
            Indicator indicator = indicatorRepository.findById(indicatorValue.getIndicatorId()).orElse(null);

            if (indicator == null) {
                return ResponseEntity.badRequest().body("Indicator not found");
            }

            Date currentDate = new Date();

            if (indicator.getDay() != null && indicator.getDay().equals(currentDate)) {
                return ResponseEntity.badRequest().body("Value for the current date already set");
            }

            indicator.setDay(currentDate);
            indicator.setDayValue(indicatorValue.getValue());
            indicatorRepository.save(indicator);

            return ResponseEntity.ok("Indicator value set successfully");
        }

        public List<Indicator> getIndicatorsByDepartmentId(int departmentId) {
            return indicatorRepository.findByDepartmentId(departmentId);
        }
      public ResponseEntity<String> updateTargetPerWeek(UpdateTargetPerWeekDto updateTargetPerWeekData) {
            Indicator indicator = indicatorRepository.findById(updateTargetPerWeekData.getIndicatorId()).orElse(null);

            if (indicator == null) {
                return ResponseEntity.badRequest().body("Indicator not found");
            }

            indicator.setTargetPerWeek(updateTargetPerWeekData.getNewTargetPerWeek());
            indicatorRepository.save(indicator);

            return ResponseEntity.ok("Target per week updated successfully");
        }
    }
