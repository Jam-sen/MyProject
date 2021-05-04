package com.ys.eduservice.controller;


import com.ys.common.utils.R;
import com.ys.eduservice.entity.vo.ChapterVo;
import com.ys.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author yaosen
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    @GetMapping("/getAllChapterVideo/{courseId}")
    public R getChapter(@PathVariable String courseId) {
        List<ChapterVo> chapter = eduChapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("data", chapter);
    }
}

