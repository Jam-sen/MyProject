package com.ys.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ys.eduservice.entity.EduChapter;
import com.ys.eduservice.entity.EduVideo;
import com.ys.eduservice.entity.vo.ChapterVo;
import com.ys.eduservice.entity.vo.VideoVo;
import com.ys.eduservice.mapper.EduChapterMapper;
import com.ys.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author yaosen
 * @since 2021-02-24
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoServiceImpl eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.orderByAsc("sort");
        List<EduChapter> chapterList = this.list(queryWrapper);
        QueryWrapper<EduVideo> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("course_id", courseId);
        queryWrapper1.orderByAsc("sort");
        List<EduVideo> videoList = eduVideoService.list(queryWrapper1);
        List<ChapterVo> data = new ArrayList<>();
        for (EduChapter eduChapter : chapterList) {
            ChapterVo chapterVo = new ChapterVo();
            chapterVo.setId(eduChapter.getId()).setTitle(eduChapter.getTitle());
            List<VideoVo> videoVoList = new ArrayList<>();
            for (EduVideo eduVideo : videoList) {
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    videoVo.setId(eduVideo.getId()).setTitle(eduVideo.getTitle());
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setVideoList(videoVoList);
            data.add(chapterVo);
        }
        return data;
    }
}
