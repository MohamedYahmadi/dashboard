package com.group.dashboard.dto;

import lombok.Getter;

@Getter
public class CreateIndicatorDto {
    private String name;
    private int departmentId;
    private String targetPerDay;
}
