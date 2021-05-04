package com.ys.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ys.eduservice.entity.EduSubject;
import com.ys.eduservice.entity.excel.SubjectData;
import com.ys.eduservice.service.EduSubjectService;
import com.ys.exception.CustomException;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    private EduSubjectService eduSubjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }


    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new CustomException("文件数据为空", 20001);
        }
        //一行一行读取，每次读取有两个值，第一个值是一级分类，第二个值是二级分类
        EduSubject eduSubject = this.existSubject(subjectData.getOneSubjectName(), "0");
        if (eduSubject==null) {
            eduSubject = new EduSubject();
            eduSubject.setParentId("0").setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(eduSubject);
        }

        EduSubject eduSubject1 = this.existSubject(subjectData.getTwoSubjectName(), eduSubject.getId());
        if (eduSubject1==null) {
            eduSubject1 = new EduSubject();
            eduSubject1.setTitle(subjectData.getTwoSubjectName()).setParentId(eduSubject.getId());
            eduSubjectService.save(eduSubject1);
        }
    }

    //判断数据库中是否已有此分类
    public EduSubject existSubject(String title, String parentId) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", parentId);
        return eduSubjectService.getOne(queryWrapper);
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
