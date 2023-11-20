package com.czq.blog.controller;

import com.czq.blog.result.Result;
import com.czq.blog.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {
    @Autowired
    AliOssUtil aliOssUtil;
    @PostMapping
    public Result upload(@RequestParam("image") MultipartFile file){
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String name=UUID.randomUUID().toString()+extension;
            String s=aliOssUtil.upload(file.getBytes(),name);
            log.info("图片地址"+s);
            return Result.success(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.fail(20001,"上传失败");
    }
}
