package com.ys.eduservice.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseInfoVo {
    private String id;
    private String title;
    private BigDecimal price;
    private String description;
    private String teacherId;
    private String subjectId;
    private String subjectParentId;
    private Integer lessonNum;
    private String cover;
}
