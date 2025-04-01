package com.group.dashboard.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class CreateIndicatorDto {
    private String name;
    private int departmentId;
    private String targetPerWeek;
}
