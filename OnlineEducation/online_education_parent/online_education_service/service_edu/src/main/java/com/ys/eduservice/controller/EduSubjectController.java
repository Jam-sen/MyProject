package com.ys.eduservice.controller;


import com.ys.common.utils.R;
import com.ys.eduservice.entity.excel.SubjectData;
import com.ys.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author yaosen
 * @since 2021-02-22
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    //添加课程分类
    //获取上传过来的文件，把文件内容读取过来
    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file) {
        eduSubjectService.addSubject(file);
        return R.ok();
    }
}

