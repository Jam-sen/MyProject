package com.ys.eduservice.service;

import com.ys.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ys.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author yaosen
 * @since 2021-02-24
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);
}
