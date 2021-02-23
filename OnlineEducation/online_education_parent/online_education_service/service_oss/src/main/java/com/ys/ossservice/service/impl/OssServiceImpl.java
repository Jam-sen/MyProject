package com.ys.ossservice.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ys.ossservice.service.OssService;
import com.ys.ossservice.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         *调用oss方法实现上传
         * 第一个参数：Bucket名称
         * 第二个参数：上传到oss文件路径和文件名称
         * 第三个参数：上传文件的输入流
         */
        //获取文件为名称
        String filename = file.getOriginalFilename();
        //在文件名称里面添加随机唯一的值，防止文件名重复，导致文件覆盖
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        filename =  uuid+filename;
        //把文件按照日期进行分类
        String datePath =  new DateTime().toString("yyyy/MM/dd");
        filename = datePath + "/" + filename;
        ossClient.putObject(bucketName, filename, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();


        //把上传之后文件路径返回
        //需要把上传到阿里云oss路径手动拼接出来
        String simpleEndpoint = endpoint.substring(8);
        return "https://" + bucketName + "." + simpleEndpoint + "/" + filename;
    }
}
