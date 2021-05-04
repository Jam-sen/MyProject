package com.ys.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ys.common.utils.R;
import com.ys.eduservice.entity.EduCourse;
import com.ys.eduservice.entity.EduCourseDescription;
import com.ys.eduservice.entity.vo.CourseInfoVo;
import com.ys.eduservice.service.EduCourseDescriptionService;
import com.ys.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author yaosen
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/eduservice/eduCourse")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = courseService.addCourseInfo(courseInfoVo);
        return R.ok().data("id", id);
    }

    @GetMapping("/getInfoById/{courseId}")
    public R getInfoById(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        EduCourse course = courseService.getById(courseId);
        EduCourseDescription description = eduCourseDescriptionService.getById(courseId);
        BeanUtils.copyProperties(course, courseInfoVo);
        courseInfoVo.setDescription(description.getDescription());
        return R.ok().data("data", courseInfoVo);
    }

    @PostMapping("/updateCourseInfoById")
    public R updateCourseInfoById(@RequestBody CourseInfoVo courseInfoVo) {
        boolean b = courseService.updateCourseInfo(courseInfoVo);
        return b ? R.ok() : R.error();
    }
}