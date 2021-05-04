package com.ys.eduservice.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class VideoVo {
    private String id;
    private String title;
}
