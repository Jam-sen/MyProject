package com.ys.eduservice.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class ChapterVo {
    private String id;
    private String title;
    private List<VideoVo> videoList = new ArrayList<>();
}
