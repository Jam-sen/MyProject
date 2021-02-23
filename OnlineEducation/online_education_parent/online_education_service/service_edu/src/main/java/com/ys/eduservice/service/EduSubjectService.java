package com.ys.eduservice.service;

import com.ys.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author yaosen
 * @since 2021-02-22
 */
public interface EduSubjectService extends IService<EduSubject> {

    void addSubject(MultipartFile file);
}
