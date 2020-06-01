package com.wzy.controller;

import com.wzy.config.FileUpload;
import com.wzy.holder.RequestHolder;
import com.wzy.pojo.Users;
import com.wzy.utils.ErrorEnum;
import com.wzy.utils.Result;
import com.wzy.utils.UUIDUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v1.0/upload")
public class FileUploadController {

    @Resource
    private FileUpload fileUpload;

    @PostMapping("uploadImg")
    public Result uploadFile(HttpServletRequest request,
                             MultipartFile file,
                             HttpServletResponse response) {
        Users currentUser = RequestHolder.getCurrentUser();
        // 头像保存的地址
//        String fileSpace = IMAGE_USER_FACE_LOCATION;
        String fileSpace = fileUpload.getImageUserFaceLocation();
        // 在路径上为每一个用户增加一个userId，用于区分不同用户上传
        String uploadPathPrefix = File.separator + currentUser.getId();

        // 开始文件上传
        if (file != null) {
            FileOutputStream fileOutputStream = null;
            try {
                // 获得文件上传的文件名称
                String filename = file.getOriginalFilename();
                if (StringUtils.isNotBlank(filename)) {

                    // 文件重名名
                    String[] fileNameArr = filename.split("\\.");
                    // 获取文件后缀名
                    // face-{userId}.png
                    String suffix = fileNameArr[fileNameArr.length - 1];

                    // 判断后缀名防止被攻击，防止上传sh，或者php
                    if (!suffix.equalsIgnoreCase("png") &&
                            !suffix.equalsIgnoreCase("jpg") &&
                            !suffix.equalsIgnoreCase("jpeg")&&
                            !suffix.equalsIgnoreCase("gif")) {
                        ErrorEnum.UPLOAD_IMG_ERROR.throwException();
                    }
                    // 文件名重组 覆盖式上传 增量式：额外拼接时间
                    String newFileName = "img-" + UUIDUtil.uuid() + "." + suffix;
                    // 上传头像最终保存的位置
                    String finalFacePath = fileSpace + uploadPathPrefix + File.separator + newFileName;

                    // 用于提供给web服务访问的地址
                    uploadPathPrefix += ("/" + newFileName);
                    File outFile = new File(finalFacePath);
                    if (outFile.getParentFile() != null) {
                        // 创建文件夹
                        boolean mkdirs = outFile.getParentFile().mkdirs();
                        System.out.println(mkdirs);
                    }
                    // 文件输出保存到目录
                    fileOutputStream = new FileOutputStream(outFile);
                    InputStream inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ;
                }
            }

        } else {
            ErrorEnum.UPLOAD_NOT_BLANK.throwException();
        }
       return Result.success(fileUpload.getImageServerUrl() + uploadPathPrefix);
    }
}
