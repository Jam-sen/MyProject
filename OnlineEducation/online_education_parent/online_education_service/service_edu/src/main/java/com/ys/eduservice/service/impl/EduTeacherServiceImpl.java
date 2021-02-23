package com.ys.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ys.common.utils.R;
import com.ys.eduservice.entity.EduTeacher;
import com.ys.eduservice.entity.vo.TeacherQuery;
import com.ys.eduservice.mapper.EduTeacherMapper;
import com.ys.eduservice.service.EduTeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author yaosen
 * @since 2021-02-04
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    @Override
    public R conditionQuery(long pageNum, long size, TeacherQuery teacherQuery) {
        Page<EduTeacher> page = new Page<>(pageNum, size);
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        if (teacherQuery != null) {
            queryWrapper.like(teacherQuery.getName() != null, "name", teacherQuery.getName());
            queryWrapper.eq(teacherQuery.getLevel() != null, "level", teacherQuery.getLevel());
            queryWrapper.ge(teacherQuery.getBegin() != null, "gmt_create", teacherQuery.getBegin());
            queryWrapper.le(teacherQuery.getEnd() != null, "gtm_create", teacherQuery.getEnd());
        }
        this.page(page, queryWrapper);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        return records.size() != 0 ? R.ok().data("total", total).data("rows", records) : R.error();
    }
}
