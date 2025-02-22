package com.group.dashboard.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Indicator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String targetPerDay;

    private String day;

    private String dayValue;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
