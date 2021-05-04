package com.ys.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ys.eduservice.entity.EduCourse;
import com.ys.eduservice.entity.EduCourseDescription;
import com.ys.eduservice.entity.vo.CourseInfoVo;
import com.ys.eduservice.mapper.EduCourseMapper;
import com.ys.eduservice.service.EduCourseDescriptionService;
import com.ys.eduservice.service.EduCourseService;
import com.ys.eduservice.service.EduSubjectService;
import com.ys.exception.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author yaosen
 * @since 2021-02-24
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduCourseService eduCourseService;

    @Override
    public String addCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        boolean save = this.save(eduCourse);
        if (!save) {
            throw new CustomException("添加信息失败", 20001);
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(eduCourse.getId());
        eduCourseDescriptionService.save(eduCourseDescription);
        return eduCourse.getId();
    }

    @Override
    public boolean updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse course = new EduCourse();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,course);
        BeanUtils.copyProperties(courseInfoVo,eduCourseDescription);
        boolean b = eduCourseService.updateById(course);
        boolean b1 = eduCourseDescriptionService.updateById(eduCourseDescription);
        return b && b1;
    }
}
