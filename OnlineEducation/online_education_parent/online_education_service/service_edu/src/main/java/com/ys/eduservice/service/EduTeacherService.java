package com.ys.eduservice.service;

import com.ys.common.utils.R;
import com.ys.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ys.eduservice.entity.vo.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author yaosen
 * @since 2021-02-04
 */
public interface EduTeacherService extends IService<EduTeacher> {

    R conditionQuery(long pageNum, long size, TeacherQuery teacherQuery);
}
