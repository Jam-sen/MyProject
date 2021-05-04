package com.ys.eduservice.service;

import com.ys.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ys.eduservice.entity.vo.SubjectListData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    List<SubjectListData> getAllSubject();

    String getParentIdByChildrenId(String subjectId);
}
