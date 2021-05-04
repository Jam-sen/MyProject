package com.ys.ossservice.controller;

import com.ys.common.utils.R;
import com.ys.ossservice.service.OssService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "对象储存oss管理")
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;

    //上传头像的方法
    @PostMapping("/uploadFile")
    public R uploadOssFile(MultipartFile file) {
        //获取上传的文件
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url", url);
    }

}
