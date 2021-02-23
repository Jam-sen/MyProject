package com.ys.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ys.common.utils.R;
import com.ys.eduservice.entity.EduTeacher;
import com.ys.eduservice.entity.vo.TeacherQuery;
import com.ys.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author yaosen
 * @since 2021-02-04
 */
@Api(tags = {"讲师管理"})
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "查出所有讲师", tags = "查出所有讲师")
    @GetMapping("/selectAll")
    public R selectAll() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return list != null ? R.ok().data("list", list) : R.error();
    }


    @ApiOperation(value = "根据id删除讲师")
    @DeleteMapping("/deleteById/{id}")
    public R deleteById(@ApiParam(value = "讲师id") @PathVariable String id) {
        boolean b = eduTeacherService.removeById(id);
        return b ? R.ok() : R.error();
    }

    @ApiOperation("分页查询讲师列表")
    @GetMapping("/selectPage/{pageNum}")
    public R selectPage(@PathVariable long pageNum) {
        Page<EduTeacher> page = new Page<>(pageNum, 2);
        eduTeacherService.page(page, null);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("rows", page.getRecords());
        return page.getRecords().size() != 0 ? R.ok().data(map) : R.error();
    }

    @ApiOperation("条件分页查询")
    @PostMapping("/conditionQuery/{pageNum}/{size}")
    public R conditionQuery(@PathVariable long pageNum, @PathVariable long size, @RequestBody(required = false) TeacherQuery teacherQuery) {
        return eduTeacherService.conditionQuery(pageNum, size, teacherQuery);
    }

    @ApiOperation("添加讲师")
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        return eduTeacherService.save(eduTeacher)? R.ok():R.error();
    }

    @ApiOperation("根据id查询讲师")
    @PostMapping("/selectById/{id}")
    public R selectById(@PathVariable String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        return teacher != null ? R.ok().data("teacher",teacher) : R.error();
    }

    @ApiOperation("根据id更新讲师")
    @PostMapping("/updateById")
    public R updateById(@RequestBody EduTeacher teacher) {
        boolean b = eduTeacherService.updateById(teacher);
        return b ? R.ok() : R.error();
    }
}