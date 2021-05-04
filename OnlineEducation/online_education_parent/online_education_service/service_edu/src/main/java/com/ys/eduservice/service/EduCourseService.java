package com.ys.eduservice.service;

import com.ys.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ys.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author yaosen
 * @since 2021-02-24
 */
public interface EduCourseService extends IService<EduCourse> {
    String addCourseInfo(CourseInfoVo courseInfoVo);

    boolean updateCourseInfo(CourseInfoVo courseInfoVo);
}
