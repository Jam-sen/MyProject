package com.ys.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.ys.eduservice.entity.EduSubject;
import com.ys.eduservice.entity.excel.SubjectData;
import com.ys.eduservice.entity.vo.SubjectListData;
import com.ys.eduservice.listener.SubjectExcelListener;
import com.ys.eduservice.mapper.EduSubjectMapper;
import com.ys.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author yaosen
 * @since 2021-02-22
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Override
    public void addSubject(MultipartFile file) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //此处new监听器时调用了我们自己定义的有参构造，将service层传入其中，实现在监听器中访问数据库
        EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(this)).sheet().doRead();
    }

    @Override
    public List<SubjectListData> getAllSubject() {
        List<SubjectListData> data = new ArrayList<>();
        List<EduSubject> allSubject = this.list(null);
        List<EduSubject> oneSubjectList = new ArrayList<>();
        List<EduSubject> twoSubjectList = new ArrayList<>();
        for (EduSubject subject : allSubject) {
            if ("0".equals(subject.getParentId())) {
                oneSubjectList.add(subject);
            } else {
                twoSubjectList.add(subject);
            }
        }
        for (EduSubject oneSubject : oneSubjectList) {
            SubjectListData oneSubjectListData = new SubjectListData();
            ArrayList<SubjectListData> childrenSubjectListData = new ArrayList<>();
            oneSubjectListData.setId(oneSubject.getId()).setTitle(oneSubject.getTitle());
            for (EduSubject twoSubject : twoSubjectList) {
                if (oneSubject.getId().equals(twoSubject.getParentId())) {
                    SubjectListData twoSubjectListData = new SubjectListData();
                    twoSubjectListData.setId(twoSubject.getId()).setTitle(twoSubject.getTitle());
                    childrenSubjectListData.add(twoSubjectListData);
                    oneSubjectListData.setChildren(childrenSubjectListData);
                }
            }
            data.add(oneSubjectListData);
        }
        return data;
    }

    @Override
    public String getParentIdByChildrenId(String subjectId) {
        return baseMapper.getParentIdByChildrenId(subjectId);
    }
}
